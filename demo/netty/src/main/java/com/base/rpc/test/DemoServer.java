package com.base.rpc.test;

import com.base.rpc.serialize.JSONSerializer;
import com.base.rpc.io.server.RPCServer;

/**
 * @author:СM
 * @date:2020/10/12 11:57 PM
 */
public class DemoServer {

    public static void main(String[] args) {
        RPCServer server = new RPCServer("localhost", 8888, 2, 16, new JSONSerializer());
        server.start();
    }

}
