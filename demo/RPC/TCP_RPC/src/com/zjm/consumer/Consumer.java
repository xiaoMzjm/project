package com.zjm.consumer;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.rmi.MarshalledObject;

import com.zjm.interfaces.SayHelloService;

/**
 * @author:�ھ�
 * @date:2017/8/14 0:58
 */
public class Consumer {

    public static void main(String[] args) throws  Exception {

        String interfaceName = SayHelloService.class.getName();
        Method method = SayHelloService.class.getMethod("sayHello" , java.lang.String.class );
        Object[] params = {"hello"};

        Socket socket = new Socket("127.0.0.1" , 1234);
        System.out.println("start consumer success");

        // ���������ƺͲ������ݵ�Զ��
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeUTF(interfaceName);
        oos.writeUTF(method.getName());
        oos.writeObject(method.getParameterTypes());
        oos.writeObject(params);

        // ��Զ�˶�ȡ����ִ�н��
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
        Object result = ois.readObject();
        System.out.println(result.toString());
    }
}
