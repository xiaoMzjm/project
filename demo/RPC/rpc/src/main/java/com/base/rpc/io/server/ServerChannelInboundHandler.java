package com.base.rpc.io.server;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.base.rpc.invoke.Invoker;
import com.base.rpc.protocol.RpcRequest;
import com.base.rpc.protocol.RpcResponse;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author:小M
 * @date:2020/10/13 12:23 AM
 */
@Sharable
public class ServerChannelInboundHandler extends ChannelInboundHandlerAdapter {

    // 业务线程池
    private ThreadPoolExecutor executor;
    private Invoker invoker;

    public ServerChannelInboundHandler(int workerThreads, Invoker invoker) {
        this.invoker = invoker;
        // 业务队列最大1000，避免堆积
        // 如果子线程处理不过来，io线程也会加入处理业务逻辑(callerRunsPolicy)
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(1000);
        // 给业务线程命名
        ThreadFactory factory = new ThreadFactory() {

            AtomicInteger seq = new AtomicInteger();

            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("rpc-" + seq.getAndIncrement());
                return t;
            }

        };
        // 闲置时间超过30秒的线程自动销毁
        this.executor = new ThreadPoolExecutor(1, workerThreads, 30, TimeUnit.SECONDS, queue, factory,
            new CallerRunsPolicy());
    }

    public void closeGracefully() {
        // 优雅一点关闭，先通知，再等待，最后强制关闭
        this.executor.shutdown();
        try {
            this.executor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
        }
        this.executor.shutdownNow();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 客户端来了一个新链接
        System.out.println("connection comes");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 客户端走了一个
        System.out.println("connection leaves");
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof RpcRequest) {
            // 用业务线程池处理消息
            this.executor.execute(() -> {
                this.handleMessage(ctx, (RpcRequest) msg);
            });
        }
    }

    // 处理消息
    private void handleMessage(ChannelHandlerContext ctx, RpcRequest request) {
        System.out.println("[server] read a message : " + request);
        RpcResponse rpcResponse = invoker.invoke(request);
        rpcResponse.setRequestId(request.getRequestId());
        ctx.writeAndFlush(rpcResponse);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("connection error");
        cause.printStackTrace();
        ctx.close();
    }
}
