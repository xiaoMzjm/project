package com.zjm.java.jvm.gc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author:小M
 * @date:2019/4/12 1:31 AM
 */
public class GcTest {
    private static final int MB = 3 * 1024 * 1024;


    /**
     * 请写一段程序，让其运行时的表现为触发5次ygc，然后3次fgc，然后3次ygc，然后1次fgc，请给出代码以及启动参数
     * -Xms42m -Xmx42m -Xmn11m -XX:SurvivorRatio=9 -XX:+UseParallelGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
     * 注意，一个程序启动后，啥都不做，eden区已经被占用了2.8m
     * @param args
     */
    public static void main(String[] args) throws Exception{

        List list = new ArrayList();

        for(int i = 0 ; i < 12 ; i++) {
            new Scanner(System.in).next();
            list.add(new byte[MB]);
        }

        for(int i = 0 ; i < 2 ; i++) {
            list.remove(0);
        }
        System.out.println("remove 2");

        for(int i = 0 ; i < 2 ; i++) {
            new Scanner(System.in).next();
            list.add(new byte[MB]);
        }

        for(int i = 0 ; i < 8 ; i++) {
            list.remove(0);
        }
        System.out.println("remove 8");

        for(int i = 0 ; i < 7 ; i++) {
            new Scanner(System.in).next();
            list.add(new byte[MB]);
        }

        new Scanner(System.in).next();
    }
}
