package com.zjm;

import java.util.HashMap;
import java.util.List;

import javax.swing.*;

import java.util.Map;
import redis.clients.jedis.Jedis;

/**
 * @author:�ھ�
 * @date:2017/8/22 23:24
 */
public class MyTest {

    /*==================================================================*/
    /*                  ��ͨKV�Դ洢                                     */
    /*==================================================================*/
    private void ��ͨKV�Դ洢(){

        Jedis jedis = JedisUtil.jedis;

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

        Jedis jedis = JedisUtil.jedis;

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
    }

    /*==================================================================*/
    /*                  ����                                           */
    /*==================================================================*/
    private void ����(){

        Jedis jedis = JedisUtil.jedis;

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

    }

    /*==================================================================*/
    /*                  Set                                             */
    /*==================================================================*/
    private void Set(){

        Jedis jedis = JedisUtil.jedis;

        // ���Ԫ��
        jedis.sadd("mySet" , "a");
        jedis.sadd("mySet" , "b");

        // ɾ��Ԫ��
        jedis.srem("mySet" , "b");

        // ��ȡ����Ԫ��
        jedis.smembers("mySet");
    }

    /*==================================================================*/
    /*                  ����Set                                         */
    /*==================================================================*/
    private void ZSet(){

        Jedis jedis = JedisUtil.jedis;

        // ��ָ��λ�����Ԫ��
        jedis.zadd("mySortSet" , 1 , "a");
        jedis.zadd("mySortSet" , 2 , "b");
        jedis.zadd("mySortSet" , 3 , "c");

        // ��ȡָ��λ��Ԫ�ؼ���
        jedis.zrange("mySortSet" , 1 , 3);

        // ��ȡָ��λ�õ�Ԫ�ؼ��ϣ�����
        jedis.zrevrange("mySortSet" , 1 , 3);
    }

    public static void main(String[] args) {

        MyTest myTest = new MyTest();
        myTest.��ͨKV�Դ洢();
        myTest.����KV�Դ洢();
        myTest.����();
        myTest.Set();
        myTest.ZSet();
    }
}
