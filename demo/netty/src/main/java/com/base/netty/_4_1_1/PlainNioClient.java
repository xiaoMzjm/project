package com.base.netty._4_1_1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author:小M
 * @date:2020/10/12 9:31 PM
 */
public class PlainNioClient {

    public void create(final String clientName) {

        new Thread(new Runnable() {
            public void run() {
                try {
                    Selector selector = Selector.open();
                    SocketChannel socketChannel = SocketChannel.open();
                    socketChannel.configureBlocking(false);
                    socketChannel.connect(new InetSocketAddress("127.0.0.1", 8888));
                    socketChannel.register(selector, SelectionKey.OP_CONNECT);

                    //轮询访问selector
                    while(true){

                        selector.select();
                        Iterator<SelectionKey> ite = selector.selectedKeys().iterator();

                        while(ite.hasNext()){

                            SocketChannel channel = null;

                            try {

                                SelectionKey key = ite.next();
                                ite.remove();

                                try {

                                }catch (Exception e) {

                                }

                                if(key.isConnectable()){

                                    channel = (SocketChannel)key.channel();
                                    if(channel.isConnectionPending()){
                                        channel.finishConnect();
                                    }
                                    channel.configureBlocking(false);
                                    channel.register(selector, SelectionKey.OP_READ);

                                    channel.write(ByteBuffer.wrap(new String("i am client").getBytes()));
                                }else if(key.isReadable()){

                                    channel = (SocketChannel)key.channel();
                                    String message = ChannelUtil.readChannelStr(channel);
                                    System.out.println("[client "+ clientName +"] receive server message : " + message);

                                    channel.write(ByteBuffer.wrap(new String("bye").getBytes()));
                                }

                            }catch (IOException e) {
                                channel.close();
                                break;
                            }

                        }
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();


    }
}
