package com.zjm.java.basic.transformation;

import java.lang.reflect.Method;

import javax.swing.text.ChangedCharSetException;

/**
 * @author:小M
 * @date:2019/5/11 5:26 PM
 */
public class TransformationTest {

    public static void main(String[] args) {

        int a = 1;
        StringBuilder sb = new StringBuilder("hello");
        String str = "hello";

        changeIntValue(a);
        System.out.println(a);

        changeRefenceValue(sb);
        System.out.println(sb.toString());

        changeStringValue(str);
        System.out.println(str);

    }

    static void changeIntValue(Integer a) {
        a++;
    }

    static void changeRefenceValue(StringBuilder sb) {
        sb.append("world");
    }

    static void changeStringValue(String str) {
        str = "world";
    }

}
