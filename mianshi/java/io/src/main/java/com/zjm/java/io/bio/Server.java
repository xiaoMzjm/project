package com.zjm.java.io.bio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author:小M
 * @date:2019/3/11 2:12 AM
 */
public class Server {

    private static File file = new File("/Users/zhangjiaming/Desktop/log.txt");

    public static void main(String[] args) throws Exception{

        final FileWriter fileWriter = new FileWriter(file);

        // 启动
        ServerSocket ss = new ServerSocket(6666);

        while(true) {

            // 阻塞
            final Long acceptStart = System.currentTimeMillis();
            final Socket s = ss.accept();
            final Long acceptEnd = System.currentTimeMillis();

            // 一个客户端由一个线程处理
            new Thread(new Runnable() {
                public void run() {
                    try {
                        Long acceptMsgStart = System.currentTimeMillis();
                        // 接收
                        DataInputStream dis = new DataInputStream(s.getInputStream());
                        byte[] bytes = new byte[1024];
                        dis.read(bytes);
                        Long acceptMsgEnd = System.currentTimeMillis();

                        // 模拟服务端做了一个耗时的操作
                        Thread.sleep(1000);

                        // 回复
                        Long sendStart = System.currentTimeMillis();
                        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                        dos.writeUTF("hello i am server , i accept you msg");
                        Long sendEnd = System.currentTimeMillis();

                        fileWriter.write("\r\n server : accept请求用时" + (acceptEnd-acceptStart) + " , 从接收消息到接收完毕" + (acceptMsgEnd-acceptMsgStart)+ " , 从回复消息到回复完毕" + (sendEnd-sendStart) + " , 总用时" + (sendEnd-acceptStart));

                        dos.flush();
                        dos.close();
                        dis.close();
                        s.close();
                        fileWriter.flush();
                    }catch (Exception e) {
                        try {
                            fileWriter.write("\r\n server抛异常：" + e.getMessage());
                            fileWriter.flush();
                        }catch (Exception ee) {
                            ee.printStackTrace();
                        }
                    }

                }
            }).start();
        }
    }
}
