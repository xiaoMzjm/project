package com.zjm.base.api.example.example_redis;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 运行该例子需要先启动redis，不启动也可以，不影响springboot启动，只是调用时报错
 *
 * @author:黑绝
 * @date:2018/11/5 上午12:56
 */
@RestController
@RequestMapping("/redis/resource")
public class RedisResource {

    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation(value = "插入KV" , notes = "插入KV")
    @RequestMapping(value = "/insertKV/{k}/{v}" , method = RequestMethod.POST)
    public String insertKV(@PathVariable String k , @PathVariable String v) {
        redisTemplate.opsForValue().set(k , v);
        return "success value = " + redisTemplate.opsForValue().get(k).toString();
    }
}
