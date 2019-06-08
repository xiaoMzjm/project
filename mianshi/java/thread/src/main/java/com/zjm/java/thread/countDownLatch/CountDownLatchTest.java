package com.zjm.java.thread.countDownLatch;

import com.sun.tools.internal.xjc.api.util.ToolsJarNotFoundException;
import sun.jvm.hotspot.debugger.ThreadAccess;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static Thread t = null;

    public static void main(String[] args) throws Exception{

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("进入子线程");
                    Thread.sleep(1000);
                    System.out.println("主线程的状态为：" + t.getState());
                    countDownLatch.countDown();
                    System.out.println("子线程countDown");
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t = Thread.currentThread();
        thread.start();
        // 当countDownLatch为0时，开始执行
        countDownLatch.await();
        System.out.println("主线程继续执行");
    }
}
