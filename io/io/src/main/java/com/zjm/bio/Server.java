package com.zjm.bio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端
 * 户重点：通过多线程处理不同的客户端请求，阻塞
 * @author:小M
 * @date:2019/1/26 4:38 PM
 */
public class Server {

    public static void main(String[] args) throws Exception{

        // 启动
        ServerSocket ss = new ServerSocket(6666);

        while(true) {

            Long start = System.currentTimeMillis();

            // 阻塞
            final Socket s = ss.accept();

            System.out.println(System.currentTimeMillis() - start);


            // 一个客户端由一个线程处理
            new Thread(new Runnable() {
                public void run() {
                    try {
                        // 接收
                        DataInputStream dis = new DataInputStream(s.getInputStream());
                        System.out.println(dis.readUTF());

                        // 回复
                        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                        dos.writeUTF("hello i am server");

                        dis.close();
                        s.close();
                    }catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }
    }
}
