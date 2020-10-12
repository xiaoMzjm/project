package com.base.netty._4._1._1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 不通过netty使用OIO和NIO
 * @author:小M
 * @date:2020/10/11 3:29 PM
 */
public class PlainOioServer {

    public void oioServer(int port) throws Exception {
        // 绑定端口
        ServerSocket socket = new ServerSocket(port);
        System.out.println("server run success");

        for(;;) {
            // 接收连接
            final Socket clientSocket = socket.accept();
            System.out.println("accept connection from " + clientSocket.toString());

            // 创建一个线程来处理该连接
            new Thread(new Runnable() {
                public void run() {
                    InputStream in ;
                    OutputStream out ;
                    try {
                        while (true) {
                            // 读取客户端的消息
                            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                            String message;
                            while ((message = reader.readLine()) != null) {
                                System.out.println("receive client message : " + message);

                                // 回复客户端
                                DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
                                dataOutputStream.writeUTF("server : " + message);
                            }
                        }

                     } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            System.out.println("client err , close");
                            clientSocket.close();
                        }catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                }
            }).start();
        }
    }

}
