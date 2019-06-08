package com.zjm.java.thread.cyclicBarrir;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author:小M
 * @date:2019/4/14 10:14 PM
 */
public class CyclicBarrirTest {

    public static void main(String[] args) {

        final CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for(int i = 0 ; i < 10 ; i++) {
            executorService.execute(new Task(cyclicBarrier));
        }

    }


    public static class Task implements Runnable {

        private static CyclicBarrier cyclicBarrier;

        public Task(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                System.out.println("等待执行");
                cyclicBarrier.await();
                System.out.println("执行完毕");
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
