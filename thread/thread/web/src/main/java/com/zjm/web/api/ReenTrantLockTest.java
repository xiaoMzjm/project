package com.zjm.web.api;

import java.util.concurrent.locks.ReentrantLock;
import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 测试ReenTrantLock和效率
 * @author:黑绝
 * @date:2018/11/17 下午9:10
 *
 * 比较结果：
 * ReenTrantLock:   787ms   739ms   856ms   851ms   912ms
 * Synchronized:    743ms   849ms   737ms   744ms   802ms
 *
 * 速度上，现在synchronized甚至比reenTrantLock更优。
 *
 */
public class ReenTrantLockTest {

    private final static ReentrantReadWriteLock LOCK = new ReentrantReadWriteLock();

    public static void main(String[] args) throws Exception{

        List<Thread> reenThreadList = new ArrayList<>(10000);
        List<Thread> synThreadList = new ArrayList<>(10000);

        for(int i = 0 ; i < 10000 ; i++) {
            Thread reenThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    reentrantlock();
                }
            });
            reenThreadList.add(reenThread);

            Thread synThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    syn();
                }
            });
            synThreadList.add(synThread);
        }

        Long start = System.currentTimeMillis();
        for(Thread thread : reenThreadList) {
            thread.start();
        }
        for(Thread thread : reenThreadList) {
            thread.join();
        }
        Long end = System.currentTimeMillis();
        System.out.println("reenTrantLock use time = " + (end - start) + " ms" );

        start = System.currentTimeMillis();
        for(Thread thread : synThreadList) {
            thread.start();
        }
        for(Thread thread : synThreadList) {
            thread.join();
        }
        end = System.currentTimeMillis();
        System.out.println("synLock use time = " + (end - start) + " ms" );


    }

    private static void reentrantlock(){
        int i = 0 ;
        try {
            LOCK.writeLock().lock();
            i++;
        }finally {
            LOCK.writeLock().unlock();
        }

    }

    private static synchronized void syn(){
        int i = 0 ;
        i++;
    }
}
