package com.zjm;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


/**
 * @author:黑绝
 * @date:2017/8/15 0:05
 */
public class HttpSender {

    private final static String host = "127.0.0.1";
    private final static Integer port = 4567;

    public static Response send(Request request) throws Exception{
        Socket socket = new Socket(host , port);
        OutputStream os = socket.getOutputStream();

        // 组装请求
        request.setEncode(request.getEncode() == null ? Encode.UTF8 : request.getEncode());
        request.setCommandLength(request.getCommand().length());

        // 发出请求
        writeRequest(request,os);

        // 接收响应
        InputStream is = socket.getInputStream();

        // 返回响应
        return readResponse(is);
    }

    private static void writeRequest(Request request , OutputStream os) throws Exception{
        // 编码
        os.write(request.getEncode().getEncode());
        // 命令长度
        os.write(ByteUtil.int2Bytes(request.getCommandLength()));
        // 命令
        os.write(request.getCommand().getBytes(request.getEncode().getName()));
    }

    private static Response readResponse(InputStream is) throws Exception{
        // 读取编码
        byte[] encodeBytes = new byte[1];
        is.read(encodeBytes);
        byte encode = encodeBytes[0];
        Encode encodeEnum = Encode.getEncodeByByte(encode);

        // 读取响应长度
        byte[] commandLengtBytes = new byte[4];
        is.read(commandLengtBytes);
        Integer commandLength = ByteUtil.bytes2Int(commandLengtBytes);

        // 读取响应
        byte[] commandBytes = new byte[commandLength];
        is.read(commandBytes);
        String command = new String(commandBytes , encodeEnum.getName());

        return new Response(encodeEnum , commandLength , command);
    }
}
