package com.lotus.core.sys.task;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wyy
 */
@Configuration
public class QuartzConfig {

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private ApplicationContext applicationContext;

    private Map<String,Properties> propertiesMap=new HashMap<String,Properties>();

    private Map<String,SchedulerFactoryBean> schedulerFactoryBeanMap=new HashMap<String,SchedulerFactoryBean>();

    private Map<String,JobDetailFactoryBean> jobDetailFactoryBeanMap=new HashMap<String,JobDetailFactoryBean>();

    private Map<String,CronTriggerFactoryBean> cronTriggerFactoryBeanMap=new HashMap<String,CronTriggerFactoryBean>();

    /**
     * 测试
     * @return
     */
    @Bean
    public SchedulerFactoryBean quartzScheduler() {
        SchedulerFactoryBean quartzScheduler = new SchedulerFactoryBean();

        quartzScheduler.setQuartzProperties(quartzProperties());
        quartzScheduler.setDataSource(dataSource);
        quartzScheduler.setTransactionManager(transactionManager);
        quartzScheduler.setOverwriteExistingJobs(true);

        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        quartzScheduler.setJobFactory(jobFactory);

        Trigger[] triggers = {
                processMyJobTrigger().getObject()
        };

        quartzScheduler.setTriggers(triggers);

        return quartzScheduler;
    }

    @Bean
    public JobDetailFactoryBean processMyJob() {
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(MyJob.class);
        String myTestJobName = "MyTestJobName";
        jobDetailFactory.setName(myTestJobName);
        String myTestJobGroup = "MyTestJobGroup";
        jobDetailFactory.setGroup(myTestJobGroup);
        jobDetailFactory.setDurability(true);
        return jobDetailFactory;
    }

    @Bean
    public CronTriggerFactoryBean processMyJobTrigger() {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(processMyJob().getObject());
        //在整点和半点时每15秒触发 trigger
        cronTriggerFactoryBean.setCronExpression("0/15 0/30 * * * ?");
        cronTriggerFactoryBean.setGroup("MyTestGroup");
        cronTriggerFactoryBean.setName("MyTestName");
        return cronTriggerFactoryBean;
    }

    @PostConstruct
    public void delayThread() {
        final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
        executor.schedule(new Runnable() {
            public void run() {
                createQuartz();
            }
        }, 10, TimeUnit.SECONDS);

    }

    private void createQuartz(){
        propertiesMap.clear();
        jobDetailFactoryBeanMap.clear();
        cronTriggerFactoryBeanMap.clear();
        schedulerFactoryBeanMap.clear();

        AutowireCapableBeanFactory factory = applicationContext.getAutowireCapableBeanFactory();
        BeanDefinitionRegistry registry = (BeanDefinitionRegistry) factory;


        Properties properties = quartzProperties();
        GenericBeanDefinition propertyBeanDefinition = new GenericBeanDefinition();
        propertyBeanDefinition.setBeanClass(Properties.class);
        propertyBeanDefinition.setAutowireCandidate(true);
        String propertiesBeanName="quartzPropertiesDynamic";
        registry.registerBeanDefinition(propertiesBeanName, propertyBeanDefinition);
        factory.autowireBeanProperties(properties, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, false);
        propertiesMap.put(propertiesBeanName,properties);

        SchedulerFactoryBean quartzScheduler = new SchedulerFactoryBean();
        quartzScheduler.setQuartzProperties(properties);
        quartzScheduler.setDataSource(dataSource);
        quartzScheduler.setTransactionManager(transactionManager);
        quartzScheduler.setOverwriteExistingJobs(true);

        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        quartzScheduler.setJobFactory(jobFactory);

        //取消过期的订单
        List<CronTriggerFactoryBean> scoreJobCronTriggerFactoryBeans = cancelOrderJob();

        List<CronTriggerFactoryBean> allCronTriggerFactoryBean = new ArrayList<CronTriggerFactoryBean>();
        allCronTriggerFactoryBean.addAll(scoreJobCronTriggerFactoryBeans);
        Trigger[] triggers = new Trigger[allCronTriggerFactoryBean.size()];

        for (int i = 0; i < triggers.length; i++) {
            triggers[i] = allCronTriggerFactoryBean.get(i).getObject();
        }
        quartzScheduler.setTriggers(triggers);
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        try {
            quartzScheduler.afterPropertiesSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        beanDefinition.setBeanClass(SchedulerFactoryBean.class);
        beanDefinition.setAutowireCandidate(true);
        String quartzSchedulerDynamicName = "quartzSchedulerDynamic";
        registry.registerBeanDefinition(quartzSchedulerDynamicName, beanDefinition);
        schedulerFactoryBeanMap.put(quartzSchedulerDynamicName,quartzScheduler);
    }

    /**
     * 取消过期的订单
     */
    private List<CronTriggerFactoryBean> cancelOrderJob() {
        AutowireCapableBeanFactory factory = applicationContext.getAutowireCapableBeanFactory();
        BeanDefinitionRegistry registry = (BeanDefinitionRegistry) factory;
        List<CronTriggerFactoryBean> cronTriggerFactoryBeans=new ArrayList<CronTriggerFactoryBean>();
        JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
        jobDetailFactory.setJobClass(CancelOrderJob.class);
        jobDetailFactory.setDurability(true);
        jobDetailFactory.setName(EnumJobName.JobNameOrder.getJobNamePostfix());
        jobDetailFactory.setGroup(EnumJobName.JobNameOrder.getJobGroupName());
        jobDetailFactory.afterPropertiesSet();
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(JobDetailFactoryBean.class);
        beanDefinition.setAutowireCandidate(true);
        String jobDetailFactoryBeanName = "jobDetailFactoryBeanOrder";
        registry.registerBeanDefinition(jobDetailFactoryBeanName, beanDefinition);
        factory.autowireBeanProperties(jobDetailFactory, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, false);
        jobDetailFactoryBeanMap.put(jobDetailFactoryBeanName,jobDetailFactory);

        GenericBeanDefinition beanDefinition2 = new GenericBeanDefinition();
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(jobDetailFactory.getObject());
        //每小时触发 trigger
        cronTriggerFactoryBean.setCronExpression("0 0 * * * ?");
        cronTriggerFactoryBean.setGroup(EnumJobName.JobNameOrder.getTriggerGroupName());
        cronTriggerFactoryBean.setName(EnumJobName.JobNameOrder.getTriggerNamePostfix());
        cronTriggerFactoryBean.afterPropertiesSet();

        beanDefinition2.setBeanClass(CronTriggerFactoryBean.class);
        beanDefinition2.setAutowireCandidate(true);
        String cronTriggerFactoryBeanName = "cronTriggerFactoryBeanOrder";
        registry.registerBeanDefinition(cronTriggerFactoryBeanName, beanDefinition2);
        cronTriggerFactoryBeanMap.put(cronTriggerFactoryBeanName,cronTriggerFactoryBean);
        cronTriggerFactoryBeans.add(cronTriggerFactoryBean);
        return cronTriggerFactoryBeans;
    }


    @Bean
    public Properties quartzProperties() {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("quartz.properties"));
        Properties properties;

        try {
            propertiesFactoryBean.afterPropertiesSet();
            properties = propertiesFactoryBean.getObject();
        }
        catch (IOException e) {
            throw new RuntimeException("Unable to load config.properties", e);
        }

        return properties;
    }
}
