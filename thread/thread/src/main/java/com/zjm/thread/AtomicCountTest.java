package com.zjm.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程下i++是不安全的，这里写了个demo证明
 *
 * AtomicInteger可以使得多线程加法安全，这里写了个demo证明，并且分析为什么
 *
 * @author:黑绝
 * @date:2018/11/8 上午2:07
 */
public class AtomicCountTest {

    private AtomicInteger ai = new AtomicInteger(0);
    private int i = 0;

    public static void main(String[] args) throws Exception{

        AtomicCountTest atomicCountTest = new AtomicCountTest();
        List<Thread> list = new ArrayList<>();
        for(int i = 0 ; i < 10000 ; i++) {
            Thread t = new Thread(new Runnable(){

                @Override
                public void run() {
                    try {
                        atomicCountTest.count();
                        atomicCountTest.safeCount();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });
            list.add(t);
        }
        for(Thread t : list) {
            t.start();
        }

        for(Thread t : list) {
            t.join();
        }

        /**
         * 理论上，i不会等于10000，因为i++是一个先get再wirte的过程
         * 假设i=0，线程A读取i的值为0，线程B读取i的值也为0
         * 然后线程A加了1，写回A的缓存中，i=1
         * 然后线程B加了1，写回B的缓存中，i=1
         * A刷新i的值到主存中，i=1
         * B刷新i的值到主存中，i=1
         * 所以加了两次，但是i=1而非i=2
         *
         * 实际测试结果为9950
         */
        System.out.println(atomicCountTest.i);

        /**
         * ai会等于10000的，因为ai是AtomicInteger，每次更新都会写回主存
         * 假设i=0，线程A读取i的值为0，线程B读取i的值也为0
         * 然后线程A加了1，写回主存中，由于入参旧值为0，主存中的值也为0，i=1
         * 然后线程B加了1，写回主存中，由于入参旧值为0，主存的值为1，写回失败
         * 线程B重新get主存中i的值，为1，然后加了1等于2，写回主存中，由于入参旧值为1，主存的值为1，写回成功
         * 所以加了两次，但是i=2
         *
         * 实际测试结果为10000
         */
        System.out.println(atomicCountTest.ai);

    }

    private void count() throws Exception{
        Thread.sleep(1);
        i++;
    }

    private void safeCount(){
        while(true) {
            int old = ai.get();
            boolean result = ai.compareAndSet(old , ++ old);
            if(result) {
                break;
            }
        }
    }
}
