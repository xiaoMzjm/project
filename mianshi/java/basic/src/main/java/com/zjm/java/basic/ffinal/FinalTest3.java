package com.zjm.java.basic.ffinal;

/**
 * @author:小M
 * @date:2019/5/15 12:39 AM
 */
public class FinalTest3 {
    int a;
    final int b;
    static FinalTest3 finalTest3;
    public FinalTest3(){
        a = 1;
        b = 2;
    }

    public static void main(String[] args) {
        finalTest3 = new FinalTest3();
    }
}
