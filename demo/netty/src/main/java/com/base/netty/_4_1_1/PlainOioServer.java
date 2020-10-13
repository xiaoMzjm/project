package com.base.netty._4_1_1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 纯oio使用socket
 * @author:小M
 * @date:2020/10/11 3:29 PM
 */
public class PlainOioServer {

    public void createServer(int port) throws Exception {
        // 绑定端口
        final ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("server run success");

        new Thread(new Runnable() {
            public void run() {
                for(;;) {
                    // 接收连接
                    try {
                        Socket clientSocket = serverSocket.accept();
                        System.out.println("[server] accept connection from " + clientSocket.toString());

                        // 创建一个线程来处理该连接
                        new Thread(new ClientThread(clientSocket)).start();
                    }catch (Exception e) {
                        e.printStackTrace();
                        try {
                            serverSocket.close();
                        }catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        break;
                    }
                }
            }
        }).start();

    }

    /**
     * 处理连接线程
     */
    public class ClientThread implements Runnable{

        Socket clientSocket;

        public ClientThread(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        public void run() {
            try {
            DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(clientSocket.getOutputStream());
            boolean flag = true;

                while (flag) {
                    // 读取客户端的消息,约定消息的结束为回车换行符，所以每次读取一行
                    String message;
                    while ((message = dataInputStream.readUTF()) != null) {
                        System.out.println("[server] receive client message : " + message);

                        // 回复客户端
                        dataOutputStream.writeUTF(message);

                        if("bye".equals(message)) {
                            flag = false;
                            break;
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    System.out.println("close client " + clientSocket.toString());
                    clientSocket.close();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
