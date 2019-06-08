package com.zjm.java.container;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HashMapTest {

    public static void main(String[] args) {

        HashMap hashMap = new HashMap<>();
        hashMap.put("1","1");

        System.out.println(tableSizeFor(4));
    }

    /**
     * Returns a power of two size for the given target capacity.
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : n + 1;
    }

    static class Test1{

        static class HashMapPutTest implements Runnable{
            private Integer i;
            private HashMap<String,String> hashMap;

            public HashMapPutTest(Integer i, HashMap<String, String> hashMap) {
                this.i = i;
                this.hashMap = hashMap;
            }

            @Override
            public void run() {
                hashMap.put(i.toString(),i.toString());
            }
        }

        public static void main(String[] args) {
            ExecutorService executorService = Executors.newFixedThreadPool(1000);
            List<Thread> threadList = new ArrayList<>();
            HashMap<String,String> hashMap = new HashMap<>();
            for(int i = 0 ; i < 1000 ; i ++) {
                Thread t = new Thread(new HashMapPutTest(i,hashMap));
                threadList.add(t);
            }
            for(Thread t : threadList) {
                executorService.execute(t);
            }
            System.out.println(hashMap.size());
        }
    }

    static class Test2 {
        public static void main(String[] args) {
            HashMap hashMap = new HashMap(2);
            hashMap.put("test" , "test") ;
        }
    }
}
