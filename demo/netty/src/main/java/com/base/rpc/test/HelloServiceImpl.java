package com.base.rpc.test;

/**
 * @author:小M
 * @date:2020/10/13 8:32 PM
 */
public class HelloServiceImpl implements HelloService{

    public String hello(String name){
        return "hello " + name;
    }
}
