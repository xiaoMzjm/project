package com.zjm.bio;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 客户端
 * @author:小M
 * @date:2019/1/26 4:39 PM
 */
public class Client {

    public static void main(String[] args) throws Exception {

        Socket s = new Socket("127.0.0.1",6666);

        // 发送
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        dos.writeUTF("hello");

        // 接收
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        String line = br.readLine();
        System.out.println(line);

        dos.flush();
        dos.close();
        s.close();
    }
}
