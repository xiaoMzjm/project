package com.zjm.java.jvm.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:小M
 * @date:2019/4/12 1:31 AM
 */
public class GcTest {
    private static final int MB = 3 * 1024 * 1024;


    /**
     * 请写一段程序，让其运行时的表现为触发5次ygc，然后3次fgc，然后3次ygc，然后1次fgc，请给出代码以及启动参数
     * -Xms40m -Xmx40m -Xmn9m -XX:SurvivorRatio=7 -XX:+UseParallelGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps
     * young:10m
     * old:31m
     * @param args
     */
    public static void main(String[] args) {

        List list = new ArrayList();

        for(int i = 0 ; i < 12 ; i++) {
            list.add(new byte[MB]);
        }

        list.remove(0);
        list.remove(0);

        for(int i = 0 ; i < 2 ; i++) {
            list.add(new byte[MB]);
        }

        for(int i = 0 ; i < 8 ; i++) {
            list.remove(0);
        }

        for(int i = 0 ; i < 7 ; i++) {
            list.add(new byte[MB]);
        }
    }
}
