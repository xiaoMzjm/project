package com.zjm.java.thread.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

    private static Semaphore semaphore = new Semaphore(2);
    static class Test1{
        public static void main(String[] args) {
            for(int i = 0 ; i < 20 ; i++) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            semaphore.acquire();
                            Thread.sleep(1000);
                            System.out.println(Thread.currentThread().getName() + " 执行完毕");
                            semaphore.release();
                        }catch (Exception e) {

                        }
                    }
                });
                thread.setName("线程" + i );
                thread.start();
            }
        }
    }
}
