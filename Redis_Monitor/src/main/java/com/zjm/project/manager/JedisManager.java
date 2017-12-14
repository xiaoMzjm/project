package com.zjm.project.manager;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author:黑绝
 * @date:2017/8/22 23:22
 */
public class JedisManager {

    private static Map<String , JedisPool> cache = new HashMap<>();

    private static JedisPool jedisPool;

    public static void initPool(String host , Integer port){
        String key = host + ":" + port.toString();
        if(jedisPool != null) {
            return;
        }
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        // 设置最大连接数为默认值的5倍
        poolConfig.setMaxTotal(GenericObjectPoolConfig.DEFAULT_MAX_TOTAL * 5);
        // 设置最大空闲连接数为默认值的3倍
        poolConfig.setMaxIdle(GenericObjectPoolConfig.DEFAULT_MAX_IDLE * 3);
        // 设置最小空闲连接数为默认值的2倍
        poolConfig.setMinIdle(GenericObjectPoolConfig.DEFAULT_MIN_IDLE * 2);
        // 设置开启jmx功能
        poolConfig.setJmxEnabled(true);
        // 设置连接池没有连接后客户端的最大等待时间(单位为毫秒)
        poolConfig.setMaxWaitMillis(3000);

        jedisPool = new JedisPool(poolConfig, host, port);
    }

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }

}
