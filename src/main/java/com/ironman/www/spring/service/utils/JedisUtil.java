package com.ironman.www.spring.service.utils;

import com.ironman.www.spring.service.factory.JedisFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.Jedis;

/**
 * Created by superuser on 9/15/17.
 */
public class JedisUtil {

    private static Logger logger = LogManager.getLogger("JedisUtil");

    public static String set(String key, String value) {
        Jedis jedis = JedisFactory.getJedis();
        jedis.set(key, value);
        logger.info("=========================" + key);
        logger.info("=========================" + value);
        //jedis.expire(key,10);//expire in 10s
        return key;
    }

    public static String getStringValue(String key) {
        Jedis jedis = JedisFactory.getJedis();
        String temp = jedis.get(key);
        logger.info("=========================" + key);
        logger.info("=========================" + temp);
        return temp;
    }

}
