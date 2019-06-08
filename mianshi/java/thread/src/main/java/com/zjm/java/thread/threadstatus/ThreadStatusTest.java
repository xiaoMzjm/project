package com.zjm.java.thread.threadstatus;

public class ThreadStatusTest {

    private static String str = "";

    public static void main(String[] args) throws Exception{
        synchronized (str) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        Thread.sleep(2000);
                        // 可以获得锁
                        synchronized (str) {
                            System.out.println("子线程");
                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            );
            t.start();
            str.wait();
        }
    }



}
