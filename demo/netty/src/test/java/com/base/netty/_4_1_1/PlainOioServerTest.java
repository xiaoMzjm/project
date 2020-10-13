package com.base.netty._4_1_1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import com.base.netty._4_1_1.PlainOioServer;
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
        plainOioServer.createServer(8888);

        // 启动客户端
        Socket clientSocket = new Socket("127.0.0.1",8888);
        DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
        DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());

        dataOutputStream.writeUTF("i am client");
        System.out.println("[client] receive server message : " + dataInputStream.readUTF());
        dataOutputStream.writeUTF("bye");
        System.out.println("[client] receive server message : " + dataInputStream.readUTF());

    }

}
