package com.zjm;

import java.util.HashMap;
import java.util.List;

import javax.swing.*;

import java.util.Map;
import redis.clients.jedis.Jedis;

/**
 * @author:黑绝
 * @date:2017/8/22 23:24
 */
public class MyTest {

    /*==================================================================*/
    /*                  普通KV对存储                                     */
    /*==================================================================*/
    private void 普通KV对存储(){

        Jedis jedis = JedisUtil.jedis;

        jedis.set("name" , "zjm");// 设置一个K-V

        jedis.setex("content" , 5 , "hello"); // 设置K-V的有效期为5秒

        jedis.mset("name" , "xiaoM" , "age" , "25");// 这是多个K-V

        jedis.append("name" , "zjm");// 给字符串追加内容

        String name = jedis.get("name");// 根据K获取V

        List<String> list = jedis.mget("name" , "age");// 一次取多个K
    }

    /*==================================================================*/
    /*                  二级KV对存储                                     */
    /*==================================================================*/
    private void 二级KV对存储(){

        Jedis jedis = JedisUtil.jedis;

        // url 为一级 key ，google , taobao , sina 为二级 key
        jedis.hset("url" , "google" , "www.google.cn");
        jedis.hset("url" , "taobao" , "www.taobao.com");
        jedis.hset("url" , "sina" , "www.sina.cn");

        // 指定二级key 获取 value
        jedis.hget("url" , "google");

        // 指定一级key 获取 value
        jedis.hgetAll("url");

        // 批量设置二级 key
        Map<String,String> map = new HashMap<String, String>();
        map.put("baidu" , "www.baidu.com");
        map.put("qq" , "www.qq.com");
        jedis.hmset("url" , map);

        // 指定多个二级key 获取 value
        jedis.hmget("url" , "baidu" , "qq");
    }

    /*==================================================================*/
    /*                  队列                                           */
    /*==================================================================*/
    private void 队列(){

        Jedis jedis = JedisUtil.jedis;

        // 在队列首添加元素
        jedis.lpush("myList" , "a");

        // 在队列尾添加元素
        jedis.rpush("myList" , "b");

        // 获取指定位置的元素
        jedis.lrange("myList" , 1 , 2);

        // 删除队列首元素
        jedis.lpop("myList");

        // 删除队列尾元素
        jedis.rpop("myList");

        // 获得队列大小
        jedis.llen("myList");

    }

    /*==================================================================*/
    /*                  Set                                             */
    /*==================================================================*/
    private void Set(){

        Jedis jedis = JedisUtil.jedis;

        // 添加元素
        jedis.sadd("mySet" , "a");
        jedis.sadd("mySet" , "b");

        // 删除元素
        jedis.srem("mySet" , "b");

        // 获取所有元素
        jedis.smembers("mySet");
    }

    /*==================================================================*/
    /*                  有序Set                                         */
    /*==================================================================*/
    private void ZSet(){

        Jedis jedis = JedisUtil.jedis;

        // 在指定位置添加元素
        jedis.zadd("mySortSet" , 1 , "a");
        jedis.zadd("mySortSet" , 2 , "b");
        jedis.zadd("mySortSet" , 3 , "c");

        // 获取指定位置元素集合
        jedis.zrange("mySortSet" , 1 , 3);

        // 获取指定位置的元素集合，反向
        jedis.zrevrange("mySortSet" , 1 , 3);
    }

    public static void main(String[] args) {

        MyTest myTest = new MyTest();
        myTest.普通KV对存储();
        myTest.二级KV对存储();
        myTest.队列();
        myTest.Set();
        myTest.ZSet();
    }
}
