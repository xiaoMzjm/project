package com.base.rpcserver;

/**
 * @author:小M
 * @date:2020/10/13 11:37 PM
 */
public class HelloWorldServiceImpl implements HelloWorldService {
    public String hello(String name) {
        return "hello world " + name;
    }
}
