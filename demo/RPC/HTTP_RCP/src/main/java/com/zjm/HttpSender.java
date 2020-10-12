package com.zjm;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


/**
 * @author:�ھ�
 * @date:2017/8/15 0:05
 */
public class HttpSender {

    private final static String host = "127.0.0.1";
    private final static Integer port = 4567;

    public static Response send(Request request) throws Exception{
        Socket socket = new Socket(host , port);
        OutputStream os = socket.getOutputStream();

        // ��װ����
        request.setEncode(request.getEncode() == null ? Encode.UTF8 : request.getEncode());
        request.setCommandLength(request.getCommand().length());

        // ��������
        writeRequest(request,os);

        // ������Ӧ
        InputStream is = socket.getInputStream();

        // ������Ӧ
        return readResponse(is);
    }

    private static void writeRequest(Request request , OutputStream os) throws Exception{
        // ����
        os.write(request.getEncode().getEncode());
        // �����
        os.write(ByteUtil.int2Bytes(request.getCommandLength()));
        // ����
        os.write(request.getCommand().getBytes(request.getEncode().getName()));
    }

    private static Response readResponse(InputStream is) throws Exception{
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

        return new Response(encodeEnum , commandLength , command);
    }
}
