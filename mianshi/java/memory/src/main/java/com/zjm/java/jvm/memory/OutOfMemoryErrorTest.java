package com.zjm.java.jvm.memory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.sf.cglib.beans.BeanGenerator;

/**
 * @author:小M
 * @date:2019/4/14 3:18 PM
 */
public class OutOfMemoryErrorTest {

    public static void main(String[] args) throws Exception{
        OutOfMemoryErrorTest.thread();
    }

    public static void thread() throws Exception{
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i = 0 ; i < 10 ; i++) {
            executorService.execute(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(1000000);

                    }catch (Exception e) {

                    }
                }
            });
        }
        Thread.sleep(1000000);
    }

    public static void sleep() throws Exception{
        Thread.sleep(100000000);
    }

    /**
     * 堆溢出
     * -Xmx10m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./
     */
    public static void outOfMemoryErrorHead(){
        List list = new ArrayList();
        while (true) {
            list.add(new byte[1024*1024]);
        }
    }

    /**
     * 元空间溢出
     * -XX:MaxMetaspaceSize=10m
     */
    public static void outOfMemoryErrorMateSpace() throws Exception{
        List list = new ArrayList();
        BeanGenerator generator = new BeanGenerator();
        generator.addProperty("name", String.class);
        while (true) {
            list.add(generator.create());
        }
    }
}
