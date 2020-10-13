package com.base.rpcclient;

import com.base.rpc.consumer.RpcConsumer;
import com.base.rpc.io.client.RPCClient;
import com.base.rpc.serialize.JSONSerializer;
import com.base.rpcserver.HelloWorldService;

/**
 * @author:小M
 * @date:2020/10/13 11:42 PM
 */
public class Application {

    public static void main(String[] args)throws Exception {
        RPCClient rpcClient = new RPCClient("localhost", 8888);
        rpcClient.registerConsumer(new RpcConsumer(HelloWorldService.class));
        rpcClient.start();


        HelloWorldService helloWorldService = (HelloWorldService)rpcClient.getConsumer(HelloWorldService.class);
        String result = helloWorldService.hello("zjm");
        System.out.println("[client] " + result);

    }
}
