package com.zjm.java.io.nio;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * NIO 也称 New IO， Non-Block IO，非阻塞同步通信方式
 * 从BIO的阻塞到NIO的非阻塞，这是一大进步。功归于Buffer，Channel，Selector三个设计实现。
 * Buffer   ：  缓冲区。NIO的数据操作都是在缓冲区中进行。缓冲区实际上是一个数组。而BIO是将数据直接写入或读取到Stream对象。
 * Channel  ：  通道。NIO可以通过Channel进行数据的读，写和同时读写操作。
 * Selector ：  多路复用器。NIO编程的基础。多路复用器提供选择已经就绪状态任务的能力。
 * 客户端和服务器通过Channel连接，而这些Channel都要注册在Selector。Selector通过一个线程不停的轮询这些Channel。找出已经准备就绪的Channel执行IO操作。
 * NIO通过一个线程轮询，实现千万个客户端的请求，这就是非阻塞NIO的特点。
 */
public class Server {

    private static File file = new File("/Users/zhangjiaming/Desktop/log2.txt");


    private static FileWriter fileWriter = null;
    static{
        try {
            fileWriter = new FileWriter(file);
        }catch (Exception e) {
        }
    }

    public static void main(String[] args) throws Exception{

        final int PORT = 6666;        // 监听的端口
        Selector selector = null;            // 多路复用器，NIO编程的基础，负责管理通道Channel
        ByteBuffer readBuffer = ByteBuffer.allocate(1024*100);  // 缓冲区Buffer

        try {
            // 1.开启多路复用器
            Long selectorOpenStart = System.currentTimeMillis();
            selector = Selector.open();
            Long selectorOpenEnd = System.currentTimeMillis();
            System.out.println("开启多路复用器用时：" + (selectorOpenEnd-selectorOpenStart));

            // 2.打开服务器通道(网络读写通道)
            Long serverSocketChannelOpenStart = System.currentTimeMillis();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            Long serverSocketChannelOpenEnd = System.currentTimeMillis();
            System.out.println("打开服务器通道用时：" + (serverSocketChannelOpenEnd-serverSocketChannelOpenStart));

            // 3.设置服务器通道为非阻塞模式，true为阻塞，false为非阻塞
            serverSocketChannel.configureBlocking(false);

            // 4.绑定端口
            serverSocketChannel.socket().bind(new InetSocketAddress(PORT));

            // 5.把通道注册到多路复用器上，并监听阻塞事件
            /**
             * SelectionKey.OP_READ   : 表示关注读数据就绪事件
             * SelectionKey.OP_WRITE  : 表示关注写数据就绪事件
             * SelectionKey.OP_CONNECT: 表示关注socket channel的连接完成事件
             * SelectionKey.OP_ACCEPT : 表示关注server-socket channel的accept事件
             */
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("Server start >>>>>>>>> port :" + PORT);
        } catch (IOException e) {
            try {
                System.out.println(e.getMessage());
            }catch (Exception e1) {

            }
        }











        while (true) {
            try {
                /**
                 * a.select() 阻塞到至少有一个通道在你注册的事件上就绪
                 * b.select(long timeOut) 阻塞到至少有一个通道在你注册的事件上就绪或者超时timeOut
                 * c.selectNow() 立即返回。如果没有就绪的通道则返回0
                 * select方法的返回值表示就绪通道的个数。
                 */
                // 1.多路复用器监听阻塞
                Long selectorSelecrtStart = System.currentTimeMillis();
                selector.select();
                Long selectorSelecrtEnd = System.currentTimeMillis();
                System.out.println("selector.select用时：" + (selectorSelecrtEnd-selectorSelecrtStart));

                // 2.多路复用器已经选择的结果集
                Iterator<SelectionKey> selectionKeys = selector.selectedKeys().iterator();

                // 3.不停的轮询
                while (selectionKeys.hasNext()) {

                    // 4.获取一个选中的key
                    SelectionKey key = selectionKeys.next();
                    // 5.获取后便将其从容器中移除
                    selectionKeys.remove();
                    // 6.只获取有效的key
                    if (!key.isValid()){
                        continue;
                    }
                    // 阻塞状态处理
                    if (key.isAcceptable()){
                        try {
                            // 1.获取通道服务
                            Long keyChannelStart = System.currentTimeMillis();
                            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                            Long keyChannelEnd = System.currentTimeMillis();
                            System.out.println("accept key.channel用时：" + (keyChannelEnd-keyChannelStart));

                            // 2.执行阻塞方法
                            Long serverSocketChannelAcceptStart = System.currentTimeMillis();
                            SocketChannel socketChannel = serverSocketChannel.accept();
                            Long serverSocketChannelAcceptEnd = System.currentTimeMillis();
                            System.out.println("serverSocketChannel.accept()用时：" + (serverSocketChannelAcceptEnd-serverSocketChannelAcceptStart));

                            // 3.设置服务器通道为非阻塞模式，true为阻塞，false为非阻塞
                            socketChannel.configureBlocking(false);
                            // 4.把通道注册到多路复用器上，并设置读取标识
                            socketChannel.register(selector, SelectionKey.OP_READ);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    // 可读状态处理
                    if (key.isReadable()){
                        try {
                            // 1.清空缓冲区数据
                            readBuffer.clear();
                            // 2.获取在多路复用器上注册的通道
                            Long readKeyChannelStart = System.currentTimeMillis();
                            SocketChannel socketChannel = (SocketChannel) key.channel();
                            Long readKeyChannelEnd = System.currentTimeMillis();
                            System.out.println("read key.channel()用时：" + (readKeyChannelEnd-readKeyChannelStart));


                            // 3.读取数据，返回
                            Long readStart = System.currentTimeMillis();
                            int count = socketChannel.read(readBuffer);
                            // 4.返回内容为-1 表示没有数据
                            if (-1 == count) {
                                key.channel().close();
                                key.cancel();
                                return ;
                            }
                            Long readEnd = System.currentTimeMillis();
                            System.out.println("read 用时：" + (readEnd-readStart));

                            // 5.有数据则在读取数据前进行复位操作
                            readBuffer.flip();
                            // 6.根据缓冲区大小创建一个相应大小的bytes数组，用来获取值
                            byte[] bytes = new byte[readBuffer.remaining()];
                            // 7.接收缓冲区数据
                            readBuffer.get(bytes);
                            // 8.打印获取到的数据
                            //System.out.println("NIO Server : " + new String(bytes)); // 不能用bytes.toString()
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
