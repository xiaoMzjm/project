package com.base.rpc.test;

import com.base.rpc.consumer.RpcConsumer;
import com.base.rpc.io.client.RPCClient;
import com.base.rpc.serialize.JSONSerializer;

/**
 * @author:小M
 * @date:2020/10/13 2:13 PM
 */
public class DemoClient {

    public static void main(String[] args)throws Exception {

        RPCClient rpcClient = new RPCClient("localhost",8888, new JSONSerializer());
        rpcClient.registerConsumer(new RpcConsumer(HelloService.class));
        rpcClient.start();


        HelloService helloService = (HelloService)rpcClient.getConsumer(HelloService.class);
        String result =  helloService.hello("zjm");
        System.out.println("[client] " + result);

    }
}
