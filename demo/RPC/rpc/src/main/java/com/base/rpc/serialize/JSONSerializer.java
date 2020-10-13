package com.base.rpc.serialize;

import java.io.IOException;

import com.alibaba.fastjson.JSON;

/**
 * @author:小M
 * @date:2020/10/13 3:47 PM
 */
public class JSONSerializer implements Serializer {
    @Override
    public byte[] serialize(Object object) throws IOException {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) throws IOException {
        return JSON.parseObject(bytes, clazz);
    }
}
