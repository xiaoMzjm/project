package com.zjm.publisher;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

import com.zjm.impl.SayHelloServiceImpl;
import com.zjm.interfaces.SayHelloService;

/**
 * @author:黑绝
 * @date:2017/8/14 1:06
 */
public class Publisher {

    public static void main(String[] args) throws  Exception{
        ServerSocket server = new ServerSocket(1234);
        System.out.println("start server success");
        while(true) {
            Socket socket = server.accept();;

            // 读取服务信息
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            String interfaceName = ois.readUTF();
            String methodName = ois.readUTF();
            Class<?>[] paramsType = (Class<?>[])ois.readObject();
            Object[] params = (Object[])ois.readObject();

            // 调用
            Class clazz = Class.forName(interfaceName);
            Method method = clazz.getMethod(methodName , paramsType);
            Object invokeObject = null;
            Object result = null;
            if(methodName.equals(SayHelloService.class.getMethod("sayHello" , java.lang.String.class).getName())) {
                invokeObject = new SayHelloServiceImpl();
                result = method.invoke(invokeObject , params);
            }
            else {
                result = "找不到服务";
            }
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(result);
        }

    }
}
