package com.zjm;

import redis.clients.jedis.Jedis;

/**
 * @author:ºÚ¾ø
 * @date:2017/8/22 23:22
 */
public class JedisUtil {

    public static Jedis jedis;

    static{
        jedis = new Jedis("192.168.99.100" , 6379);
    }
}
