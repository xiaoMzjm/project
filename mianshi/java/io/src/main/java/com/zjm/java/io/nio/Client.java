package com.zjm.java.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:小M
 * @date:2019/3/17 6:31 PM
 */
public class Client {

    private final static int PORT = 6666;
    private final static int BUFFER_SIZE = 1024;
    private final static String IP_ADDRESS = "127.0.0.1";

    public static void main(String[] args) throws Exception{

        long start = System.currentTimeMillis();

        List<Thread> threads = new ArrayList<Thread>(1000);

        for (int i = 0; i < 1; i++) {

            Thread thread = new Thread(new Runnable() {
                public void run() {
                    // 1.创建连接地址
                    InetSocketAddress inetSocketAddress = new InetSocketAddress(IP_ADDRESS, PORT);
                    // 2.声明一个连接通道
                    SocketChannel socketChannel = null;
                    // 3.创建一个缓冲区
                    ByteBuffer byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);
                    try {
                        // 4.打开通道
                        socketChannel = SocketChannel.open();
                        // 5.连接服务器
                        socketChannel.connect(inetSocketAddress);
                        //while(true){
                            // 6.定义一个字节数组，然后使用系统录入功能：
                            byte[] bytes = new byte[BUFFER_SIZE];
                            // 7.键盘输入数据
                            //System.in.read(bytes);
                            // 8.把数据放到缓冲区中
                            byteBuffer.put(bytes);
                            // 9.对缓冲区进行复位
                            byteBuffer.flip();
                            // 10.写出数据
                            socketChannel.write(byteBuffer);
                            // 11.清空缓冲区数据
                            byteBuffer.clear();
                        //}
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (null != socketChannel) {
                            try {
                                socketChannel.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
            threads.add(thread);

        }

        System.out.println("client创建的线程数：" + threads.size());

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long end = System.currentTimeMillis();

        // 1000线程测试5次，分别用时1690、1741、1366、1260、1246
        System.out.println("用时：" + (end - start));


    }
}
