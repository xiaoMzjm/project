package com.zjm.java.jvm.memory.deallock;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author:小M
 * @date:2019/4/14 7:19 PM
 */
public class DealLock {

    private static Object o1 = new Object();
    private static Object o2 = new Object();

    public static void main(String[] args) throws Exception{

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new Runnable() {
            public void run() {
                synchronized (o1) {
                    try {
                        Thread.sleep(1000);
                        synchronized (o2) {

                        }
                    }catch (Exception e) {

                    }

                }
            }
        });

        executorService.execute(new Runnable() {
            public void run() {
                synchronized (o2) {
                    try {
                        Thread.sleep(1000);
                        synchronized (o1) {

                        }
                    }catch (Exception e) {

                    }

                }
            }
        });


        Thread.sleep(10000000);
    }
}
