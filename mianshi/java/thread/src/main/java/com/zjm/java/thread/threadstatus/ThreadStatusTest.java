package com.zjm.java.thread.threadstatus;

public class ThreadStatusTest {

    private static String str = "";

    /**
     * 线程1获得锁成功
     * 线程1等待
     * 线程2获得锁成功
     * 线程2唤醒线程1
     * 线程1被唤醒
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{

        Thread t1 = new Thread(()->{
            try {
                // 可以获得锁
                synchronized (str) {
                    System.out.println("线程1获得锁成功");
                    System.out.println("线程1等待");
                    str.wait();
                    System.out.println("线程1被唤醒");
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        });


        Thread t2 = new Thread(()->{
            try {
                // 可以获得锁
                synchronized (str) {
                    System.out.println("线程2获得锁成功");
                    System.out.println("线程2唤醒线程1");
                    str.notify();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();

    }

    public static class Test2 {
        private static String str = "";

        /**
         * 主线程开始运行
         * 主线程让子线程先运行
         * 子线程正在执行长耗时任务
         * 子线程运行完毕
         * 主线程继续运行
         * @param args
         * @throws Exception
         */
        public static void main(String[] args) throws Exception{

            Thread t = new Thread(()->{
                try {

                    System.out.println("子线程正在执行长耗时任务");
                    Thread.sleep(2000);
                    System.out.println("子线程运行完毕");

                }catch (Exception e) {
                    e.printStackTrace();
                }
            });
            // 运行子线程
            t.start();

            // 让目标线程先执行
            System.out.println("主线程开始运行");
            System.out.println("主线程让子线程先运行");
            t.join();
            System.out.println("主线程继续运行");

        }
    }

}
