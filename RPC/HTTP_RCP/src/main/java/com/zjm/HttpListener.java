package com.zjm;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author:�ھ�
 * @date:2017/8/15 0:05
 */
public class HttpListener {

    private static ServerSocket serverSocket;
    private static HttpServlet httpServlet;

    static {
        try {
            serverSocket = new ServerSocket(4567);
            new Thread(){
                @Override
                public void run(){
                    while(true) {

                        try {
                            Socket socket = serverSocket.accept();

                            // ��ȡ��Ӧ����
                            InputStream is = socket.getInputStream();
                            Request request = readRequest(is);

                            // ��װ��Ӧ
                            Response response = new Response();
                            response.setEncode(Encode.UTF8);
                            httpServlet.execute(request,response);
                            response.setResponseLength(response.getResponse().length());

                            // ������Ӧ
                            OutputStream os = socket.getOutputStream();
                            writeResponse(response , os);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HttpListener(HttpServlet httpServlet) throws Exception{
        HttpListener.httpServlet = httpServlet;
    }

    private static Request readRequest(InputStream is) throws Exception{
        // ��ȡ����
        byte[] encodeBytes = new byte[1];
        is.read(encodeBytes);
        byte encode = encodeBytes[0];
        Encode encodeEnum = Encode.getEncodeByByte(encode);

        // ��ȡ��Ӧ����
        byte[] commandLengtBytes = new byte[4];
        is.read(commandLengtBytes);
        Integer commandLength = ByteUtil.bytes2Int(commandLengtBytes);

        // ��ȡ��Ӧ
        byte[] commandBytes = new byte[commandLength];
        is.read(commandBytes);
        String command = new String(commandBytes , encodeEnum.getName());

        return new Request(encodeEnum , commandLength , command);
    }

    private static void writeResponse(Response response , OutputStream os) throws Exception{
        // ����
        os.write(response.getEncode().getEncode());
        // �����
        os.write(ByteUtil.int2Bytes(response.getResponseLength()));
        // ����
        os.write(response.getResponse().getBytes(response.getEncode().getName()));
    }

    public static void main(String[] args) throws Exception {
        HttpListener httpListener = new HttpListener(new MyServlet());
    }
}
