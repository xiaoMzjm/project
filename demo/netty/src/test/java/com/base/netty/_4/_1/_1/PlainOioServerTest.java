package com.base.netty._4._1._1;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import org.junit.Test;

/**
 * @author:小M
 * @date:2020/10/11 3:39 PM
 */
public class PlainOioServerTest {

    @Test
    public void testOioServer() throws Exception{

        // 启动服务端
        PlainOioServer plainOioServer = new PlainOioServer();
        plainOioServer.oioServer(8888);

        // 启动客户端
        //Socket client;
        //client = new SocketClient(InetAddress.getByName("127.0.0.1"), 60000);

    }

    @Test
    public void test() throws Exception{
        PrintWriter printWriter = new PrintWriter("/Users/zhangjiaming/Desktop/test.txt");
        printWriter.print("123123");
        printWriter.flush();
        printWriter.close();
    }
}
