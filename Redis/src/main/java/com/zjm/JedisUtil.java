package com.zjm;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author:�ھ�
 * @date:2017/8/22 23:22
 */
public class JedisUtil {

    private static JedisPool jedisPool;

    static{
        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        // �������������ΪĬ��ֵ��5��
        poolConfig.setMaxTotal(GenericObjectPoolConfig.DEFAULT_MAX_TOTAL * 5);
        // ����������������ΪĬ��ֵ��3��
        poolConfig.setMaxIdle(GenericObjectPoolConfig.DEFAULT_MAX_IDLE * 3);
        // ������С����������ΪĬ��ֵ��2��
        poolConfig.setMinIdle(GenericObjectPoolConfig.DEFAULT_MIN_IDLE * 2);
        // ���ÿ���jmx����
        poolConfig.setJmxEnabled(true);
        // �������ӳ�û�����Ӻ�ͻ��˵����ȴ�ʱ��(��λΪ����)
        poolConfig.setMaxWaitMillis(3000);

        jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379);
    }

    public static Jedis getJedis(){
        return jedisPool.getResource();
    }

}
