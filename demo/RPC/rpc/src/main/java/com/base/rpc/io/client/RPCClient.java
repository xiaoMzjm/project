package com.base.rpc.io.client;

import java.net.InetSocketAddress;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import com.base.rpc.commom.Assert;
import com.base.rpc.consumer.RpcConsumer;
import com.base.rpc.protocol.RpcRequest;
import com.base.rpc.protocol.RpcResponse;
import com.base.rpc.serialize.JSONSerializer;
import com.base.rpc.serialize.MessageDecoder;
import com.base.rpc.serialize.MessageEncoder;
import com.base.rpc.serialize.Serializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author:小M
 * @date:2020/10/13 11:00 AM
 */
public class RPCClient {

    public static final int MAX_RETRY = 5;
    private String ip;
    private int port;
    private Serializer serializer;

    private Bootstrap bootstrap;
    private Channel channel;
    private ClientChannelHandler clientChannelHandler;
    private EventLoopGroup group;
    private Map<Class,Object> rpcConsumerMap = new ConcurrentHashMap<>();

    public RPCClient(String ip, int port) {
        this(ip, port, new JSONSerializer());
    }

    public RPCClient(String ip, int port, Serializer serializer) {
        this.ip = ip;
        this.port = port;
        this.serializer = serializer;
    }

    public void start() throws Exception{

        clientChannelHandler = new ClientChannelHandler(this);
        group = new NioEventLoopGroup();

        bootstrap = new Bootstrap();
        bootstrap
            .group(group)
            .channel(NioSocketChannel.class)
            .handler(new ChannelInitializer<Channel>() {
                @Override
                protected void initChannel(Channel channel) throws Exception {
                    ChannelPipeline pipe = channel.pipeline();
                    // 挂上编码器
                    pipe.addLast(new MessageEncoder(RpcRequest.class, serializer));
                    // 挂上解码器
                    pipe.addLast(new MessageDecoder(RpcResponse.class, serializer));
                    // 将业务处理器放在最后
                    pipe.addLast(clientChannelHandler);
                }
            })
            .option(ChannelOption.SO_KEEPALIVE, true)
            .option(ChannelOption.TCP_NODELAY, true)
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
            .remoteAddress(new InetSocketAddress(ip, port));

       connect(MAX_RETRY);
    }


    public void connect(int retry) {
        ChannelFuture channelFuture = bootstrap.connect().addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("connect to server success");
            } else if (retry == 0) {
                System.out.println("connect server fail finally");
            } else {
                //第几次重连：
                int order = (MAX_RETRY - retry) + 1;
                //本次重连的间隔
                int delay = 1 << order;
                System.out.println(String.format("%s : connect fail, %d retry connect....", new Date(), order));
                stop();
                bootstrap.config().group().schedule(() -> connect(retry - 1), delay, TimeUnit.SECONDS);
            }
        });
        channel = channelFuture.channel();

    }

    public void stop(){
        channel.close();
    }

    public RpcResponse send(RpcRequest rpcRequest){
        Assert.hasText(rpcRequest.getRequestId(),"requestId is null");
        Assert.hasText(rpcRequest.getInterfaceName(),"className is null");
        Assert.hasText(rpcRequest.getMethodName(),"method is null");
        try {
            channel.writeAndFlush(rpcRequest).await();
        }catch (Exception e) {
            e.printStackTrace();
        }
        RpcResponse rpcResponse = clientChannelHandler.getRpcResponse(rpcRequest.getRequestId());
        return rpcResponse;
    }

    public void registerConsumer(RpcConsumer rpcConsumer) {
        rpcConsumer.setRpcClient(this);
        Object o = rpcConsumer.getBean();
        rpcConsumerMap.put(rpcConsumer.getClazz(), o);
    }

    public Object getConsumer(Class clazz) {
        Assert.notNull(clazz,"clazz is null");
        Object o = rpcConsumerMap.get(clazz);
        Assert.notNull(o, String.format("clazz(%s) have not register", clazz.getName()));
        return o;
    }
}
