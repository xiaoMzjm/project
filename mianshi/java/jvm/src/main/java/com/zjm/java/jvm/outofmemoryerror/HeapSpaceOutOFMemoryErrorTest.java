package com.zjm.java.jvm.outofmemoryerror;

import java.util.ArrayList;
import java.util.List;

public class HeapSpaceOutOFMemoryErrorTest {

    /**
     * 堆内存溢出
     *
     * 当前文件目录执行：
     * javac HeapSpaceOutOFMemoryErrorTest.java
     *
     *
     * 退回java目录下执行：
     * java -Xmx10m -Xms10m -XX:+HeapDumpOnOutOfMemoryError com.zjm.java.jvm.outofmemoryerror.HeapSpaceOutOFMemoryErrorTest
     *
     *
     * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
     *         at com.zjm.java.jvm.outofmemoryerror.HeapSpaceOutOFMemoryErrorTest.main(HeapSpaceOutOFMemoryErrorTest.java:17)
     */
    public static void main(String[] args) {
        List<Byte[]> list = new ArrayList<>();

        for(int i = 0 ; i < 1000 ; i++) {
            System.out.println(i);
            list.add(new Byte[1024 * 1024 ]);
        }
    }
}
