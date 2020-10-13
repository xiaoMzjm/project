package com.base.rpc.consumer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.base.rpc.commom.MessageIdCreator;
import com.base.rpc.commom.RPCException;
import com.base.rpc.io.client.RPCClient;
import com.base.rpc.protocol.RpcRequest;
import com.base.rpc.protocol.RpcResponse;

/**
 * @author:小M
 * @date:2020/10/13 9:45 PM
 */
public class RpcConsumer implements InvocationHandler {

    private Class clazz;
    private RPCClient rpcClient;

    public RpcConsumer(Class clazz) throws Exception{
        this.clazz = clazz;
    }

    public Object getBean(){
        return Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setRequestId(MessageIdCreator.next());
        rpcRequest.setInterfaceName(clazz.getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParameterTypes(method.getParameterTypes());
        rpcRequest.setParameters(args);
        RpcResponse rpcResponse = rpcClient.send(rpcRequest);
        if(rpcResponse.getError() != null) {
            throw new RPCException(rpcResponse.getError());
        }
        return rpcResponse.getResult();
    }

    public void setRpcClient(RPCClient rpcClient) {
        this.rpcClient = rpcClient;
    }

    public Class getClazz() {
        return clazz;
    }

}
