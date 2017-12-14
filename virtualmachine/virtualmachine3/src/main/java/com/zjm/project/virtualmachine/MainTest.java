package com.zjm.project.virtualmachine;

import java.io.IOException;
import java.lang.reflect.Field;

import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

/**
 * @author:黑绝
 * @date:2017/12/13 15:24
 */
public class MainTest {

    public static void main(String[] args)
        throws NoSuchFieldException, IllegalAccessException, IOException, AttachNotSupportedException {

        String pid = "171924";

        System.setProperty("java.library.path", "D:/Program Files/Java/jdk1.8.0_121/jre/bin");
        Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
        fieldSysPath.setAccessible(true);
        fieldSysPath.set(null, null);

        //目标进程的进程id -- 记得改成正确的数字
        VirtualMachine vm = VirtualMachine.attach(pid);
    }
}
