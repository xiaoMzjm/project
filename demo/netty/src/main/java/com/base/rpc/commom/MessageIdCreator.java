package com.base.rpc.commom;

import java.util.UUID;

/**
 * @author:小M
 * @date:2020/10/13 10:03 AM
 */
public class MessageIdCreator {

    public static String next() {
        return UUID.randomUUID().toString();
    }
}
