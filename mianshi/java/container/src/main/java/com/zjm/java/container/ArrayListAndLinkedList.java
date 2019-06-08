package com.zjm.java.container;

import org.ehcache.sizeof.SizeOf;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArrayListAndLinkedList {

    public static void main(String[] args)throws Exception {
            ArrayListAndLinkedList.gettime();
    }

    public static void memory() throws Exception{
        ArrayList myarraylist = new ArrayList();
        LinkedList mylinkedlist = new LinkedList();
        Runtime rt = Runtime.getRuntime();
        long startMemory = 0L;

        System.gc();
        Thread.sleep(1000);
        startMemory = rt.freeMemory();
        for(int i = 0 ; i < 300000 ; i++) {
            myarraylist.add(String.valueOf(i));
        }
        System.out.println( "ArrayList添加10W元素耗空间1：" + (startMemory - rt.freeMemory()));

        System.gc();
        Thread.sleep(1000);
        startMemory = rt.freeMemory();
        for(int i = 0 ; i < 300000 ; i++) {
            myarraylist.add(String.valueOf(i));
        }
        System.out.println( "ArrayList添加10W元素耗空间2：" + (startMemory - rt.freeMemory()));

        System.gc();
        Thread.sleep(1000);
        startMemory = rt.freeMemory();
        for(int i = 0 ; i < 300000 ; i++) {
            mylinkedlist.add(String.valueOf(i));
        }
        System.out.println( "LinkedList添加10W元素耗空间：" + (startMemory - rt.freeMemory()));

    }

    public static void addtime() throws Exception{
        ArrayList myarraylist = new ArrayList();
        LinkedList mylinkedlist = new LinkedList();

        long start = System.nanoTime();
        for(int i = 0 ; i < 100000 ; i++) {
            myarraylist.add(0 ,i);
        }
        long end = System.nanoTime();
        System.out.println( "ArrayList往数组头添加10W元素耗时：" + (end-start)/1000000);


        start = System.nanoTime();
        for(int i = 0 ; i < 100000 ; i++) {
            myarraylist.add(i);
        }
        end = System.nanoTime();
        System.out.println( "ArrayList往数组尾添加10W元素耗时：" + (end-start)/1000000);


        start = System.nanoTime();
        for(int i = 0 ; i < 100000 ; i++) {
            mylinkedlist.add(i);
        }
        end = System.nanoTime();
        System.out.println( "LinkedList耗时：" + (end-start)/1000000);
    }

    public static void gettime() throws Exception{
        ArrayList myarraylist = new ArrayList();
        LinkedList mylinkedlist = new LinkedList();
        long start = 0l;
        long end = 0l;

        for(int i = 0 ; i < 100000 ; i++) {
            myarraylist.add(0 ,i);
        }
        for(int i = 0 ; i < 100000 ; i++) {
            mylinkedlist.add(i);
        }


        start = System.nanoTime();
        for(int i = 0 ; i < 100000 ; i++) {
            myarraylist.get(i);
        }
        end = System.nanoTime();
        System.out.println("ArrayList耗时为：" + (end-start)/1000000);

        start = System.nanoTime();
        for(int i = 0 ; i < 100000 ; i++) {
            mylinkedlist.get(i);
        }
        end = System.nanoTime();
        System.out.println("LinkedList耗时为：" + (end-start)/1000000);
    }
}
