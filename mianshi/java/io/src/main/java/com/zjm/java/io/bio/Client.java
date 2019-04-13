package com.zjm.java.io.bio;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:小M
 * @date:2019/3/11 2:12 AM
 */
public class Client {

    public static int j = 0;



    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();

        List<Thread> threads = new ArrayList<Thread>(1000);

        for (int i = 0; i < 10; i++) {

            Thread thread = new Thread(new Runnable() {
                public void run() {
                    Socket s = null;
                    DataOutputStream dos = null;
                    DataInputStream dis = null;
                    String msg = null;
                    Long socketStart = null;
                    Long socketEnd = null;
                    Long sendStart = null;
                    Long receiveEnd = null;
                    try {
                        socketStart = System.currentTimeMillis();
                        s = new Socket("127.0.0.1", 6666);
                        socketEnd = System.currentTimeMillis();

                        // 模拟客户端建立连接后，做了一个耗时的操作，没有断开连接
                        Thread.sleep(10);

                        // 发送
                        sendStart = System.currentTimeMillis();
                        dos = new DataOutputStream(s.getOutputStream());
                        dos.write(new byte[1024]);

                        // 接收
                        dis = new DataInputStream(s.getInputStream());
                        msg = dis.readUTF();
                        receiveEnd = System.currentTimeMillis();

                    } catch (Exception e) {
                        System.out.println("client抛异常：" + e.getMessage());
                        e.printStackTrace();
                    } finally {
                        try {
                            if (dis != null) { dis.close(); }
                            if (dos != null) { dos.flush(); }
                            if (dos != null) { dos.close(); }
                            if (s != null) { s.close(); }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        System.out.println(
                            "t" + j++ + ":" + msg + " | 建立socket用时" + (socketEnd - socketStart) + " , 从发送到接收用时:" + (
                                receiveEnd - sendStart) + " , 从建立socket到接收用时:" + (receiveEnd - socketStart));

                    }
                }
            });
            thread.setName("t" + i);
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
