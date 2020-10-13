package com.base.rpc.invoke;

import com.base.rpc.protocol.RpcRequest;
import com.base.rpc.protocol.RpcResponse;

/**
 * @author:小M
 * @date:2020/10/13 5:49 PM
 */
public interface Invoker {

    RpcResponse invoke(RpcRequest request);
}
