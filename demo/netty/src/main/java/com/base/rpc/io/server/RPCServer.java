package com.base.rpc.io.server;

import com.base.rpc.protocol.RpcRequest;
import com.base.rpc.protocol.RpcResponse;
import com.base.rpc.serialize.MessageDecoder;
import com.base.rpc.serialize.MessageEncoder;
import com.base.rpc.serialize.Serializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

/**
 * @author:小M
 * @date:2020/10/13 12:33 AM
 */
public class RPCServer {

    private String ip;
    private int port;
    private int ioThreads; // 用来处理网络流的读写线程
    private int workerThreads; // 用于业务处理的计算线程
    private Serializer serializer;

    // 下面三个变量，一个是服务端socker，两个涉及到线程池，停止服务端的时候需要关闭，所以提到外面
    private ServerBootstrap bootstrap;
    private EventLoopGroup baseGroup;
    private EventLoopGroup workGroup;
    private ServerChannelInboundHandler serverChannelInboundHandler;
    private Channel serverChannel;

    public RPCServer(String ip, int port, int ioThreads, int workerThreads, Serializer serializer) {
        this.ip = ip;
        this.port = port;
        this.ioThreads = ioThreads;
        this.workerThreads = workerThreads;
        this.serializer = serializer;
    }

    // 启动RPC服务
    public void start() {
        baseGroup = new NioEventLoopGroup(ioThreads);
        workGroup = new NioEventLoopGroup(ioThreads);
        serverChannelInboundHandler = new ServerChannelInboundHandler(workerThreads);

        bootstrap = new ServerBootstrap()
            .group(baseGroup,workGroup)
            .channel(NioServerSocketChannel.class)
            .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipe = ch.pipeline();
                    // 如果客户端60秒没有任何请求，就关闭客户端链接
                    pipe.addLast(new ReadTimeoutHandler(60));

                    // 编解码器要放在业务handle之前，两者的顺序无所谓，因为入站时不会调用编码器，出战时不会调用解码器
                    // 挂上编码器
                    pipe.addLast(new MessageEncoder(RpcResponse.class, serializer));
                    // 挂上解码器
                    pipe.addLast(new MessageDecoder(RpcRequest.class, serializer));

                    // 将业务处理器放在最后
                    pipe.addLast(serverChannelInboundHandler);
                }
            })
            .option(ChannelOption.SO_BACKLOG, 100)  // 客户端套件字接受队列大小
            .option(ChannelOption.SO_REUSEADDR, true) // reuse addr，避免端口冲突
            .option(ChannelOption.TCP_NODELAY, true) // 关闭小流合并，保证消息的及时性
            .childOption(ChannelOption.SO_KEEPALIVE, true); // 长时间没动静的链接自动关闭

        bind(this.port);
    }

    private void bind(int port) {
        serverChannel = bootstrap.bind(this.ip, this.port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.printf("server started @ %s:%d\n", ip, port);
            } else {
                System.out.printf("server fail @ %s:%d\n", ip, port);
                bind(port + 1);
            }
        }).channel();
    }


    public void stop() {
        try {
            // 先关闭服务端套件字
            serverChannel.close();
            // 再斩断消息来源，停止io线程池
            baseGroup.shutdownGracefully().sync();
            workGroup.shutdownGracefully().sync();
            // 最后停止业务线程
            serverChannelInboundHandler.closeGracefully();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
