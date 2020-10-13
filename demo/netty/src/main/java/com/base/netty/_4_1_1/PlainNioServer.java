package com.base.netty._4_1_1;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * 纯nio使用socket
 * @author:小M
 * @date:2020/10/12 4:55 PM
 */
public class PlainNioServer {

    //通道管理器
    private Selector selector;

    //获取一个ServerSocket通道，并初始化通道
    public void create(final int port) {

        new Thread(new Runnable() {
            public void run() {
                try {
                    selector = Selector.open();
                    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
                    serverSocketChannel.configureBlocking(false);
                    serverSocketChannel.socket().bind(new InetSocketAddress(port));
                    serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

                    listen();
                }catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }

    private void listen() throws Exception{

        System.out.println("server run success");

        //使用轮询访问selector
        while(true){

            selector.select();

            Iterator<SelectionKey> ite = selector.selectedKeys().iterator();

            while(ite.hasNext()){

                SelectionKey key = ite.next();
                ite.remove();

                if(key.isAcceptable()){

                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel)key.channel();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    System.out.println("[server] accept connection from " + socketChannel.toString());
                    socketChannel.register(selector, SelectionKey.OP_READ);

                }else if(key.isReadable()){

                    SocketChannel channel = (SocketChannel)key.channel();
                    String message = ChannelUtil.readChannelStr(channel);

                    System.out.println("[server] receive client message : " + message);

                    if("bye".equals(message)) {
                        System.out.println("close client " + channel.toString());
                        channel.close();
                        continue;
                    }

                    channel.write(ByteBuffer.wrap(new String(message).getBytes()));
                }
            }


        }
    }
}
