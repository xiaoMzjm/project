package com.zjm.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * Volatitle 只能使内存可视化，但没有原子化的功能，不能使多线程i++原子化
 * @author:黑绝
 * @date:2018/11/8 上午2:07
 */
public class VolatitleCountTest {

    private volatile int ai = 0;
    private int i = 0;

    public static void main(String[] args) throws Exception{

        VolatitleCountTest atomicCountTest = new VolatitleCountTest();
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
         * 使用volatile无法解决上述的问题，因为volatile只是在写的时候，保证立刻刷新到主存中
         * 但是还是避免不了先读再写的过程中，该变量已经被其他线程写进去了，然后当前再写就会覆盖了其他线程写的值。
         */
        System.out.println(atomicCountTest.ai);

    }

    private void count() throws Exception{
        Thread.sleep(1);
        i++;
    }

    private void safeCount(){
        ai ++;
    }
}
