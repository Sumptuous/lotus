package com.lotus.common.web.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangyangyang on 16/12/7.
 */
public class RedisCache {

    @Resource
    private JedisPoolConfig poolConfig;

    @Value("${redis.host}")
    private String REDIS_HOST;

    @Value("${redis.port}")
    private int REDIS_PORT;

    private int expiry = 60*60*2;

    private JedisPool jedisPool = null;

    private final static int DEFAULT_TIMEOUT = 1000;

    private static final Logger LOG = LoggerFactory.getLogger(RedisCache.class);

    private static final Queue<String> queue = new ConcurrentLinkedQueue<String>();

    private ObjectMapper mapper = new ObjectMapper();

    /**
     * 环绕，查询
     * @param pjp
     * @return
     * @throws Throwable
     */
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
        String cacheKey = getCacheKey(pjp);
        Jedis jedis = getJedis();
        if (jedis == null){
            return pjp.proceed();
        }
        String result = jedis.get(cacheKey);
        if (result == null){
            Object proceed = pjp.proceed();

            jedis.set(cacheKey, mapper.writeValueAsString(proceed));
            jedis.expire(cacheKey, expiry);
            return proceed;
        }
        return mapper.readValue(result, Object.class);
    }

    /**
     * 后置，数据变动，同步到redis
     * @param jp
     */
    public void doAfter(JoinPoint jp){
        String packageName = jp.getTarget().getClass().getName();
        Jedis jedis = getJedis();
        if (jedis != null){
            Set<String> keys = jedis.keys(packageName + "*");

            for (String key : keys){
                jedis.del(key);
            }
        }else {
            queue.add(packageName);
        }
    }

    /**
     * 包名+ 类名 + 方法名 + 参数(多个)  生成Key
     * @param pjp
     * @return
     */
    private String getCacheKey(ProceedingJoinPoint pjp){
        StringBuilder key = new StringBuilder();
        //包名+ 类名
        String packageName = pjp.getTarget().getClass().getName();
        key.append(packageName);
        // 方法名
        String methodName = pjp.getSignature().getName();
        key.append(".").append(methodName);

        //参数(多个)
        Object[] args = pjp.getArgs();

        mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);

        for(Object arg : args){

            //流
            StringWriter str = new StringWriter();

            //对象转Json
            try {
                mapper.writeValue(str, arg);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //参数
            key.append(".").append(str);
        }

        return key.toString();
    }

    public void setExpiry(int expiry) {
        this.expiry = expiry;
    }

    /**
     * 定时同步redis中的数据
     */
    @PostConstruct
    private void delete(){
        ScheduledExecutorService service = Executors
                .newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                LOG.info("Began to delete the cached data.");
                Jedis jedis = getJedis();
                if (jedis != null){
                    String packageName = null;
                    while ((packageName = queue.poll()) != null){
                        Set<String> keys = jedis.keys(packageName + "*");
                        for (String key : keys){
                            jedis.del(key);
                            LOG.info("Deleted successfully cached data.");
                        }
                    }
                }
            }
        }, 1, 20, TimeUnit.MINUTES);
    }

    /**
     * 初始化Redis连接池
     */
    private void initialPool(){
        try {
            jedisPool = new JedisPool(poolConfig, REDIS_HOST, REDIS_PORT, DEFAULT_TIMEOUT);
        } catch (Exception e) {
            LOG.error("Create JedisPool error : "+e);
        }
    }

    /**
     * 在多线程环境同步初始化
     */
    private synchronized void poolInit() {
        if (jedisPool == null) {
            initialPool();
        }
    }

    /**
     * 同步获取Jedis实例
     * @return Jedis
     */
    private synchronized Jedis getJedis() {
        if (jedisPool == null) {
            poolInit();
        }
        Jedis jedis = null;
        try {
            if (jedisPool != null) {
                jedis = jedisPool.getResource();
            }
        } catch (Exception e) {
            LOG.error("Get jedis error : "+e);
        }finally{
            returnResource(jedis);
        }
        return jedis;
    }

    /**
     * 释放jedis资源
     * @param jedis
     */
    private void returnResource(final Jedis jedis) {
        if (jedis != null && jedisPool !=null) {
            jedisPool.returnResource(jedis);
        }
    }
}
