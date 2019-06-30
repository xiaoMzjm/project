package com.zjm.java.thread.reentrantloc;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author:小M
 * @date:2019/5/12 1:48 AM
 */
public class LockConditionTest {

    private final static ReentrantLock lock = new ReentrantLock();
    private final static Condition putCondition = lock.newCondition();
    private final static Condition getCondition = lock.newCondition();

    private List list = new LinkedList();
    private final static int MAX = 5;
    private Integer count = 0;

    private void put() throws Exception{
        lock.lock();
        try {
            // 如果数组满了，则不满往里面新增元素
            while(count == MAX) {
                System.out.println("写线程：数组满了，无法写入，使用putCondition.await()阻塞");
                putCondition.await();
            }
            list.add(1);
            count++;
            System.out.println("写线程：添加了1个元素,count="+count);
            // 每添加新元素后，通知读线程读取，只唤醒1个线程
            getCondition.signal();
        }finally {
            lock.unlock();
        }
    }

    private void get() throws Exception{
        lock.lock();
        try {
            // 如果数组没有元素，则不再取数据
            while(0 == list.size()) {
                System.out.println("读线程：数组为空，无法读取，使用putCondition.await()阻塞");
                putCondition.await();
            }
            list.remove(0);
            count --;
            System.out.println("读线程：减少了1个元素,count="+count);
            // 每添取出一个元素后，通知写线程写入，只唤醒1个线程
            putCondition.signal();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        final LockConditionTest lockConditionTest = new LockConditionTest();

        for(int i = 0 ; i < 10 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        lockConditionTest.put();
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }


        for(int i = 0 ; i < 10 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        lockConditionTest.get();
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
