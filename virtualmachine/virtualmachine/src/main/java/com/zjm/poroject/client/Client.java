package com.zjm.poroject.client;

import java.lang.reflect.Field;

import com.sun.tools.attach.VirtualMachine;

/**
 * @author:黑绝
 * @date:2017/12/12 0:36
 */
public class Client {

    public static void main(String[] args) throws Exception {
        //注意，是jre的bin目录，不是jdk的bin目录
        //VirtualMachine need the attach.dll in the jre of the JDK.
        System.setProperty("java.library.path", "D:\\Program Files\\Java\\jdk1.8.0_121\\jre\\bin");
        Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
        fieldSysPath.setAccessible(true);
        fieldSysPath.set(null, null);

        //目标进程的进程id -- 记得改成正确的数字
        VirtualMachine vm = VirtualMachine.attach("29188");

        //参数1：代理jar的位置
        //参数2：代理类的类名
        vm.loadAgent("C:\\Users\\jiaming.zjm\\Desktop\\agent.jar", "D:\\User.class");
    }
}
