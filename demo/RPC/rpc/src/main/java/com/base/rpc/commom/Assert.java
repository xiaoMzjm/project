package com.base.rpc.commom;

/**
 * @author:小M
 * @date:2020/10/13 8:24 PM
 */
public class Assert {

    public static void notNull(Object o, String text) throws IllegalArgumentException {
        if(o == null) {
            throw new IllegalArgumentException(text);
        }
    }

    public static void hasText(String s, String text) throws IllegalArgumentException {
        if(s == null || "".equals(s)) {
            throw new IllegalArgumentException(text);
        }
    }
}
