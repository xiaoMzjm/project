package com.base.rpc.io.client;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.base.rpc.consumer.RpcConsumer;
import com.base.rpc.protocol.RpcRequest;
import com.base.rpc.protocol.RpcResponse;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

/**
 * 继承了ChannelDuplexHandler类，可以对出站和入站的数据进行处理
 * @author:小M
 * @date:2020/10/13 12:23 AM
 */
@Sharable
public class ClientChannelHandler extends ChannelDuplexHandler {

    private RPCClient rpcClient;

    /**
     * 使用Map维护请求对象ID与响应结果Future的映射关系
     */
    private final Map<String, DefaultFuture> futureMap = new ConcurrentHashMap<>();


    public ClientChannelHandler(RPCClient rpcClient) {
        this.rpcClient = rpcClient;
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 客户端来了一个新链接
        System.out.println("connection active");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 客户端走了一个
        System.out.println("connection inactive");
        ctx.close();
        rpcClient.connect(rpcClient.MAX_RETRY);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof RpcResponse) {
            //获取响应对象
            RpcResponse response = (RpcResponse) msg;
            DefaultFuture defaultFuture = futureMap.get(response.getRequestId());
            //将结果写入DefaultFuture
            defaultFuture.setResponse(response);
        }
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof RpcRequest) {
            RpcRequest request = (RpcRequest) msg;
            //发送请求对象之前，先把请求ID保存下来，并构建一个与响应Future的映射关系
            futureMap.putIfAbsent(request.getRequestId(), new DefaultFuture());
        }
        super.write(ctx, msg, promise);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("connection error");
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * 获取响应结果
     *
     * @param requsetId
     * @return
     */
    public RpcResponse getRpcResponse(String requsetId) {
        try {
            DefaultFuture future = futureMap.get(requsetId);
            return future.getRpcResponse(5000);
        } finally {
            //获取成功以后，从map中移除
            futureMap.remove(requsetId);
        }
    }
}
