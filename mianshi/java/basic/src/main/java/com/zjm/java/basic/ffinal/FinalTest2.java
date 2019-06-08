package com.zjm.java.basic.ffinal;

/**
 * @author:小M
 * @date:2019/5/15 12:39 AM
 */
public class FinalTest2 {
    int a;
    final int b;
    static FinalTest2 finalTest2;
    public FinalTest2(){
        a = 1;
        b = 2;
    }

    public static void main(String[] args) {
        finalTest2 = new FinalTest2();
    }
}
