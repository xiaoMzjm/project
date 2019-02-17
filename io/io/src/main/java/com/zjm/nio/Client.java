package com.zjm.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author:小M
 * @date:2019/1/26 4:52 PM
 */
public class Client {

    public static void main(String[] args) throws Exception{

        // 1.建立连接
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1" , 6666);

        // 2.打开一个通道
        SocketChannel socketChannel = SocketChannel.open();

        // 3.创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // 4.通道怼向目标地址
        socketChannel.connect(inetSocketAddress);

        // 5.放入内容
        byteBuffer.put(new String("hellp").getBytes());

        // 6.锁定
        byteBuffer.flip();

        // 7.写出
        socketChannel.write(byteBuffer);

        // 8.接收回复
        byte[] bytes = new byte[1024];
        ByteBuffer byteBuffer2 = ByteBuffer.allocate(2048);
        socketChannel.read(byteBuffer2);
        byteBuffer2.get(bytes);
        String response = new String(bytes);
        System.out.println(response);

        socketChannel.close();

    }
}
