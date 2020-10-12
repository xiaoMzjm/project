package com.zjm.impl;

import com.zjm.interfaces.SayHelloService;

/**
 * @author:ºÚ¾ø
 * @date:2017/8/14 0:56
 */
public class SayHelloServiceImpl implements SayHelloService {
    @Override
    public String sayHello(String msg) {
        if("bye".equals(msg)) {
            return "from server : bye bye!";
        }
        return "from server : " + msg;
    }
}
