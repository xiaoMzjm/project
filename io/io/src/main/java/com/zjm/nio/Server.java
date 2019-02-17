package com.zjm.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author:小M
 * @date:2019/1/26 11:50 PM
 */
public class Server {

    public static void main(java.lang.String[] args) throws Exception{

        // 1.打开服务器通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));

        // 2.创建多路复用器
        Selector selector = Selector.open();

        // 3.把通道注册到多路复用器上
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);

        while (true) {

            // 4.阻塞
            selector.select();

            // 5.多路复用器已经选择的结果集
            Iterator<SelectionKey> selectionKeys = selector.selectedKeys().iterator();

            while (selectionKeys.hasNext()) {

                // 6.获取一个选中的key
                SelectionKey selectionKey = selectionKeys.next();

                // 7.获取后便将其从容器中移除
                selectionKeys.remove();

                // 8.过滤无效的key
                if(!selectionKey.isValid()) {
                    continue;
                }

                // 9.阻塞状态处理（新的客户端连接时，进入这里）
                if (selectionKey.isAcceptable()){

                    // 9.1 获取服务单管道
                    ServerSocketChannel ssc = (ServerSocketChannel)selectionKey.channel();

                    // 9.2 从幅度按管道中，获取客户端管道
                    SocketChannel sc = ssc.accept();

                    // 9.3 设置非阻塞
                    sc.configureBlocking(false);

                    // 9.4 把客户端通道注册到多路复用器上，并设置读取标识
                    sc.register(selector, SelectionKey.OP_READ);

                    System.out.println("client connect");
                }

                // 10.可读状态处理
                if(selectionKey.isReadable()) {

                    // 10.1 获取客户端管道
                    SocketChannel sc = (SocketChannel) selectionKey.channel();

                    // 10.2 创建缓冲区
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);

                    // 10.3 读取数据到缓冲区
                    int count = sc.read(readBuffer);

                    // 10.4 返回内容为-1 表示没有数据
                    if (-1 == count) {
                        selectionKey.channel().close();
                        selectionKey.cancel();
                        return ;
                    }

                    // 10.5 有数据则在读取数据前进行复位操作
                    readBuffer.flip();

                    // 10.6 根据缓冲区大小创建一个相应大小的bytes数组，用来获取值
                    byte[] bytes = new byte[readBuffer.remaining()];

                    // 10.7 接收缓冲区数据
                    readBuffer.get(bytes);
                    System.out.println(new String(bytes));

                    // 10.8 回复
                    ByteBuffer bb = ByteBuffer.allocate(1024);
                    bb.put(new String("hello i am server").getBytes());
                    sc.write(bb);

                    System.out.println("client msg");

                }
            }
            System.out.println("1");
        }
    }
}
