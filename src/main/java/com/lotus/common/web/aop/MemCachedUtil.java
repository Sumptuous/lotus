package com.lotus.common.web.aop;

import com.danga.MemCached.MemCachedClient;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MemCachedUtil {
    private static final Logger logger = Logger.getLogger(com.lotus.common.web.aop.MemCachedUtil.class);

    public MemCachedUtil() {
    }

    public static Map<String, Object> getKeySet(MemCachedClient memCachedClient) {
        HashMap keys = new HashMap();
        Map statsItems = memCachedClient.statsItems();
        Map statsItems_sub = null;
        String statsItems_sub_key = null;
        boolean items_number = false;
        String server = null;
        Map statsCacheDump = null;
        Map statsCacheDump_sub = null;
        String statsCacheDumpsub_key = null;
        String statsCacheDumpsub_key_value = null;
        Iterator iterator = statsItems.keySet().iterator();

        label50:
        while(iterator.hasNext()) {
            server = (String)iterator.next();
            statsItems_sub = (Map)statsItems.get(server);
            Iterator iterator_item = statsItems_sub.keySet().iterator();

            while(true) {
                do {
                    do {
                        if(!iterator_item.hasNext()) {
                            continue label50;
                        }

                        statsItems_sub_key = (String)iterator_item.next();
                    } while(!statsItems_sub_key.toUpperCase().startsWith("items:".toUpperCase()));
                } while(!statsItems_sub_key.toUpperCase().endsWith(":number".toUpperCase()));

                int items_number1 = Integer.parseInt(((String)statsItems_sub.get(statsItems_sub_key)).trim());
                statsCacheDump = memCachedClient.statsCacheDump(new String[]{server}, Integer.parseInt(statsItems_sub_key.split(":")[1].trim()), items_number1);
                Iterator statsCacheDump_iterator = statsCacheDump.keySet().iterator();

                while(statsCacheDump_iterator.hasNext()) {
                    statsCacheDump_sub = (Map)statsCacheDump.get(statsCacheDump_iterator.next());
                    Iterator iterator_keys = statsCacheDump_sub.keySet().iterator();

                    while(iterator_keys.hasNext()) {
                        statsCacheDumpsub_key = (String)iterator_keys.next();
                        statsCacheDumpsub_key_value = (String)statsCacheDump_sub.get(statsCacheDumpsub_key);

                        try {
                            keys.put(URLDecoder.decode(statsCacheDumpsub_key, "UTF-8"), (Object)null);
                            if(logger.isDebugEnabled()) {
                                logger.debug("key->" + statsCacheDumpsub_key + ": " + "value->" + statsCacheDumpsub_key_value);
                            }
                        } catch (UnsupportedEncodingException var16) {
                            var16.printStackTrace();
                        }
                    }
                }
            }
        }

        return keys;
    }
}
