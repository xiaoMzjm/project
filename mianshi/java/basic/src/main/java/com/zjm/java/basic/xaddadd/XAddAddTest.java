package com.zjm.java.basic.xaddadd;

/**
 * @author:小M
 * @date:2019/5/11 4:17 PM
 */
public class XAddAddTest {

    static {
        // 局部变量，不影响main函数计算过程
        int x = 5;
    }
    static int x,y;

    public static void main(String[] args) {
        // x = -1
        x--;

        cal();

        // temp = y = 0
        // y = y++ = 1
        // x = 1
        // 1 + 0 + 1 = 2
        System.out.println(x + y++ + x);


    }

    private static void cal(){
        // temp = x = -1
        // x = x++ = 0
        // x = ++x = 1
        // y = temp + x = -1 + 1 = 0
        y = x++ + ++x;
    }
}
