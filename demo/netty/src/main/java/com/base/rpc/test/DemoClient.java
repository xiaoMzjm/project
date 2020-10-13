package com.base.rpc.test;

import com.alibaba.fastjson.JSON;

import com.base.rpc.io.client.RPCClient;
import com.base.rpc.protocol.Message;
import com.base.rpc.commom.MessageIdCreator;
import com.base.rpc.protocol.RpcRequest;
import com.base.rpc.protocol.RpcResponse;
import com.base.rpc.serialize.JSONSerializer;

/**
 * @author:小M
 * @date:2020/10/13 2:13 PM
 */
public class DemoClient {

    public static void main(String[] args)throws Exception {

        RPCClient rpcClientNetty = new RPCClient("localhost",8888, new JSONSerializer());

        rpcClientNetty.start();

        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setRequestId(MessageIdCreator.next());

        RpcResponse rpcResponse = rpcClientNetty.send(rpcRequest);
        System.out.println("[client]" + JSON.toJSONString(rpcResponse));
    }
}
