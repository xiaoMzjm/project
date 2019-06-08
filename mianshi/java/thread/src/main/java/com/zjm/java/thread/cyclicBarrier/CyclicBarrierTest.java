package com.zjm.java.thread.cyclicBarrier;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    static class Test1 {
        static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        public static void main(String[] args) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        System.out.println("thread1 wait");
                        cyclicBarrier.await();
                        System.out.println("thread1 run");
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        System.out.println("thread2 wait");
                        cyclicBarrier.await();
                        System.out.println("thread2 run");
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();


        }
    }
}
