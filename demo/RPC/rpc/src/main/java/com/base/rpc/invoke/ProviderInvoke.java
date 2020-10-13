package com.base.rpc.invoke;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.base.rpc.commom.Assert;
import com.base.rpc.commom.ExceptionUtil;
import com.base.rpc.protocol.RpcRequest;
import com.base.rpc.protocol.RpcResponse;
import com.base.rpc.provider.RpcProvider;

/**
 * @author:小M
 * @date:2020/10/13 8:53 PM
 */
public class ProviderInvoke implements Invoker {

    private static Map<String,Object> beanMap = new ConcurrentHashMap<>();

    @Override
    public RpcResponse invoke(RpcRequest request) {
        RpcResponse rpcResponse = new RpcResponse();
        rpcResponse.setRequestId(request.getRequestId());

        try {
            Object instance = beanMap.get(request.getInterfaceName());
            Assert.notNull(instance, String.format("can not find impl with %s", request.getInterfaceName()));
            Class clazz = Class.forName(request.getInterfaceName());
            Method method = clazz.getDeclaredMethod(request.getMethodName(), request.getParameterTypes());
            Object result = method.invoke(instance, request.getParameters());
            rpcResponse.setResult(result);

        }catch (Exception e) {
            rpcResponse.setError(ExceptionUtil.getErrorMsg(e));
        }
        return rpcResponse;
    }

    public static void register(RpcProvider rpcProvider){
        Assert.hasText(rpcProvider.getInterfaceName(),"interfaceName is null");
        Assert.notNull(rpcProvider.getBean(), String.format("bean is null, interfaceName=%s", rpcProvider.getInterfaceName()));
        beanMap.put(rpcProvider.getInterfaceName(),rpcProvider.getBean());
    }
}
