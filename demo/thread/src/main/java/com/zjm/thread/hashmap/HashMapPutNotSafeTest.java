package com.zjm.thread.hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * HashMap的put方法是线程不安全的，这里写一个例子证明
 * @author:黑绝
 * @date:2018/11/8 下午12:54
 */
public class HashMapPutNotSafeTest {

    /**
     由于put是一个先get再put的操作，我们传入一个key，hashmap计算hash值，然后计算节点位置
     然后进行get操作，如果get该位置没有节点，那么进行put操作。
     所以存在了覆盖节点的情况。
     */
    public static void main(String[] args) throws Exception{

        final Map<Integer , Integer> map = new HashMap<>();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0 ; i < 10000 ; i ++) {
                    map.put(i , i );
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 10000 ; i < 20000 ; i ++) {
                    map.put(i , i );
                }
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("map.size="+map.size());
    }
}
