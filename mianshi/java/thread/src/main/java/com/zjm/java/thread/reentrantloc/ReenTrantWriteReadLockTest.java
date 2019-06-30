package com.zjm.java.thread.reentrantloc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author:小M
 * @date:2019/5/12 2:58 AM
 */
public class ReenTrantWriteReadLockTest {

    private static Lock l = new ReentrantLock();

    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    static class Test1{
        public static void main(String[] args) {
            lock.readLock().lock();
            System.out.println("读锁成功"); // 打印
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lock.readLock().lock();
                    System.out.println("读锁成功"); // 打印
                }
            }).start();
        }
    }

    static class Test2{
        public static void main(String[] args) {
            lock.readLock().lock();
            System.out.println("读锁成功"); // 打印
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lock.writeLock().lock();
                    System.out.println("写锁成功"); //  不打印
                }
            }).start();

        }
    }

    static class Test3{
        public static void main(String[] args) {
            lock.writeLock().lock();
            System.out.println("写锁成功"); // 打印
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lock.readLock().lock();
                    System.out.println("读锁成功"); // 不打印
                }
            }).start();
        }
    }

    static class Test4{
        public static void main(String[] args) {
            lock.writeLock().lock();
            System.out.println("写锁成功"); // 打印
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lock.writeLock().lock();
                    System.out.println("写锁成功"); // 不打印
                }
            }).start();
        }
    }

    static class Test5{
        public static void main(String[] args) throws Exception {
            l.lock();
            System.out.println("主线程锁"); // 打印
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try{
                        l.lockInterruptibly();
                        System.out.println("子线程锁成功"); //  不打印
                    }catch (Exception e) {
                        System.out.println("子线程被中断，抛异常");
                    }
                }
            });
            t.start();
            t.interrupt();

            String a = "1";

        }
    }

}
