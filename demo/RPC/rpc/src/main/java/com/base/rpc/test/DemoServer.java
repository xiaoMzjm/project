package com.base.rpc.test;

import com.base.rpc.io.server.RPCServer;
import com.base.rpc.provider.RpcProvider;
import com.base.rpc.serialize.JSONSerializer;

/**
 * @author:СM
 * @date:2020/10/12 11:57 PM
 */
public class DemoServer {

    public static void main(String[] args) {
        RPCServer server = new RPCServer( 8888,
            2, 16,
            new JSONSerializer());
        server.registerProvider(new RpcProvider(HelloService.class.getName(),new HelloServiceImpl()));
        server.start();
    }

}
