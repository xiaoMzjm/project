package com.zjm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.prism.shader.DrawPgram_ImagePattern_Loader;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Transaction;

/**
 * @author:黑绝
 * @date:2017/8/22 23:24
 */
public class MyTest {

    /*==================================================================*/
    /*                  普通KV对存储                                     */
    /*==================================================================*/
    private void 普通KV对存储(){

        Jedis jedis = JedisUtil.getJedis();

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

        Jedis jedis = JedisUtil.getJedis();

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

        jedis.close();
    }

    /*==================================================================*/
    /*                  队列                                           */
    /*==================================================================*/
    private void 队列(){

        Jedis jedis = JedisUtil.getJedis();

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

        jedis.close();

    }

    /*==================================================================*/
    /*                  Set                                             */
    /*==================================================================*/
    private void Set(){

        Jedis jedis = JedisUtil.getJedis();

        // 添加元素
        jedis.sadd("mySet" , "a");
        jedis.sadd("mySet" , "b");

        // 删除元素
        jedis.srem("mySet" , "b");

        // 获取所有元素
        jedis.smembers("mySet");

        jedis.close();
    }

    /*==================================================================*/
    /*                  有序Set                                         */
    /*==================================================================*/
    private void ZSet(){

        Jedis jedis = JedisUtil.getJedis();

        // 在指定位置添加元素
        jedis.zadd("mySortSet" , 1 , "a");
        jedis.zadd("mySortSet" , 2 , "b");
        jedis.zadd("mySortSet" , 3 , "c");

        // 获取指定位置元素集合
        jedis.zrange("mySortSet" , 1 , 3);

        // 获取指定位置的元素集合，反向
        jedis.zrevrange("mySortSet" , 1 , 3);

        jedis.close();
    }


    /*==================================================================*/
    /*                  pipeline                                         */
    /*==================================================================*/
    /**
     * 借助pipeline封装mdel院子操作
     */
    public void pipeline_mdel(){

        String[] keys = new String[]{ "key1" , "key2" };

        Jedis jedis = JedisUtil.getJedis();

        Pipeline pipeline = jedis.pipelined();

        for(String key : keys) {
            pipeline.del(keys);
        }

        List<Object> results = pipeline.syncAndReturnAll();

        for(Object result : results) {
            //System.out.println(result);
        }
    }

    /*==================================================================*/
    /*                  lua                                             */
    /*==================================================================*/
    public void lua_eval(){

        String luaShell = "return \"helow world \" .. KEYS[1] .. ARGV[1]";

        Integer keyNum = 1;
        
        String key = "redis";

        String argv = "!";
        
        Jedis jedis = JedisUtil.getJedis();

        Object result = jedis.eval(luaShell, keyNum, key , argv);

        //System.out.println(result);

        jedis.close();
    }

    /**
     * 把脚本先加载到服务武中
     */
    public void lua_eval_load(){

        String luaShell = "return \"helow world \" .. KEYS[1] .. ARGV[1]";

        Integer keyNum = 1;

        String key = "redis";

        String argv = "!";

        Jedis jedis = JedisUtil.getJedis();

        String scriptSha = jedis.scriptLoad(luaShell);

        Object result = jedis.evalsha(scriptSha, keyNum, key, argv);

        //System.out.println(result);

        jedis.close();
    }

    /*==================================================================*/
    /*                  multi                                           */
    /*==================================================================*/
    public void multi(){

        Jedis jedis = JedisUtil.getJedis();

        Transaction tx = jedis.multi();

        tx.set("multi_key1" , "multi_value1");

        tx.set("multi_key2" , "multi_value2");

        tx.exec();

        //System.out.println(jedis.get("multi_key1"));

        //System.out.println(jedis.get("multi_key2"));

        jedis.close();
    }

    /*==================================================================*/
    /*                  watch                                           */
    /*==================================================================*/
    public void watch(){

        Jedis jedis = JedisUtil.getJedis();

        jedis.set("watch_key" , "watch_value");

        jedis.watch("watch_key");

        Transaction tx = jedis.multi();

        tx.set("watch_key" , "watch_value_change");

        tx.exec();

        jedis.unwatch();

        //System.out.println(jedis.get("watch_key"));
    }


    public static void main(String[] args) {

        MyTest myTest = new MyTest();
        myTest.普通KV对存储();
        myTest.二级KV对存储();
        myTest.队列();
        myTest.Set();
        myTest.ZSet();
        myTest.pipeline_mdel();
        myTest.lua_eval();
        myTest.lua_eval_load();
        myTest.multi();
        myTest.watch();
    }
}
