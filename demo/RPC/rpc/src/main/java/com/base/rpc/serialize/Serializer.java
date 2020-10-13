package com.base.rpc.serialize;

import java.io.IOException;

/**
 * @author:小M
 * @date:2020/10/13 3:46 PM
 */
public interface Serializer {

    byte[] serialize(Object object) throws IOException;

    <T> T deserialize(Class<T> clazz, byte[] bytes) throws IOException;
}
