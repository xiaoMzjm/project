package com.zjm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.prism.shader.DrawPgram_ImagePattern_Loader;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Transaction;

/**
 * @author:�ھ�
 * @date:2017/8/22 23:24
 */
public class MyTest {

    /*==================================================================*/
    /*                  ��ͨKV�Դ洢                                     */
    /*==================================================================*/
    private void ��ͨKV�Դ洢(){

        Jedis jedis = JedisUtil.getJedis();

        jedis.set("name" , "zjm");// ����һ��K-V

        jedis.setex("content" , 5 , "hello"); // ����K-V����Ч��Ϊ5��

        jedis.mset("name" , "xiaoM" , "age" , "25");// ���Ƕ��K-V

        jedis.append("name" , "zjm");// ���ַ���׷������

        String name = jedis.get("name");// ����K��ȡV

        List<String> list = jedis.mget("name" , "age");// һ��ȡ���K
    }

    /*==================================================================*/
    /*                  ����KV�Դ洢                                     */
    /*==================================================================*/
    private void ����KV�Դ洢(){

        Jedis jedis = JedisUtil.getJedis();

        // url Ϊһ�� key ��google , taobao , sina Ϊ���� key
        jedis.hset("url" , "google" , "www.google.cn");
        jedis.hset("url" , "taobao" , "www.taobao.com");
        jedis.hset("url" , "sina" , "www.sina.cn");

        // ָ������key ��ȡ value
        jedis.hget("url" , "google");

        // ָ��һ��key ��ȡ value
        jedis.hgetAll("url");

        // �������ö��� key
        Map<String,String> map = new HashMap<String, String>();
        map.put("baidu" , "www.baidu.com");
        map.put("qq" , "www.qq.com");
        jedis.hmset("url" , map);

        // ָ���������key ��ȡ value
        jedis.hmget("url" , "baidu" , "qq");

        jedis.close();
    }

    /*==================================================================*/
    /*                  ����                                           */
    /*==================================================================*/
    private void ����(){

        Jedis jedis = JedisUtil.getJedis();

        // �ڶ��������Ԫ��
        jedis.lpush("myList" , "a");

        // �ڶ���β���Ԫ��
        jedis.rpush("myList" , "b");

        // ��ȡָ��λ�õ�Ԫ��
        jedis.lrange("myList" , 1 , 2);

        // ɾ��������Ԫ��
        jedis.lpop("myList");

        // ɾ������βԪ��
        jedis.rpop("myList");

        // ��ö��д�С
        jedis.llen("myList");

        jedis.close();

    }

    /*==================================================================*/
    /*                  Set                                             */
    /*==================================================================*/
    private void Set(){

        Jedis jedis = JedisUtil.getJedis();

        // ���Ԫ��
        jedis.sadd("mySet" , "a");
        jedis.sadd("mySet" , "b");

        // ɾ��Ԫ��
        jedis.srem("mySet" , "b");

        // ��ȡ����Ԫ��
        jedis.smembers("mySet");

        jedis.close();
    }

    /*==================================================================*/
    /*                  ����Set                                         */
    /*==================================================================*/
    private void ZSet(){

        Jedis jedis = JedisUtil.getJedis();

        // ��ָ��λ�����Ԫ��
        jedis.zadd("mySortSet" , 1 , "a");
        jedis.zadd("mySortSet" , 2 , "b");
        jedis.zadd("mySortSet" , 3 , "c");

        // ��ȡָ��λ��Ԫ�ؼ���
        jedis.zrange("mySortSet" , 1 , 3);

        // ��ȡָ��λ�õ�Ԫ�ؼ��ϣ�����
        jedis.zrevrange("mySortSet" , 1 , 3);

        jedis.close();
    }


    /*==================================================================*/
    /*                  pipeline                                         */
    /*==================================================================*/
    /**
     * ����pipeline��װmdelԺ�Ӳ���
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
     * �ѽű��ȼ��ص���������
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
        myTest.��ͨKV�Դ洢();
        myTest.����KV�Դ洢();
        myTest.����();
        myTest.Set();
        myTest.ZSet();
        myTest.pipeline_mdel();
        myTest.lua_eval();
        myTest.lua_eval_load();
        myTest.multi();
        myTest.watch();
    }
}
