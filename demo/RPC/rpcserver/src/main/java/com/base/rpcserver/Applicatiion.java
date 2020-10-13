package com.base.rpcserver;

import com.base.rpc.io.server.RPCServer;
import com.base.rpc.provider.RpcProvider;
import com.base.rpc.serialize.JSONSerializer;

/**
 * @author:小M
 * @date:2020/10/13 11:37 PM
 */
public class Applicatiion {

    public static void main(String[] args) {
        RPCServer rpcServer = new RPCServer(8888, 2, 10);
        rpcServer.registerProvider(new RpcProvider(HelloWorldService.class.getName(), new HelloWorldServiceImpl() ));
        rpcServer.start();
    }
}
