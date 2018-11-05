package com.zjm.web.api.example.example_redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Jedis;

/**
 * @author:黑绝
 * @date:2018/11/5 下午11:17
 */
@Configuration
public class RedisConfig {

    private final static Logger logger = LoggerFactory.getLogger(RedisCacheConfiguration.class);

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.pool.max-wait}")
    private long maxWaitMillis;

    @Value("${spring.redis.paasword:}")
    private String password;

    @Value("${spring.redis.username:}")
    private String username;

    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        if(StringUtils.isEmpty(password)) {
            password = null;
        }
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password);
        jedisPool.getResource();
        return jedisPool;
    }

    @Bean
    public Jedis jedis(){
        Jedis jedis = new Jedis(host, port);
        jedis.connect();
        if(!StringUtils.isEmpty(password)) {
            jedis.auth(password);
        }
        return jedis;
    }
}
