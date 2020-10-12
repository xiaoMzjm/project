package com.zjm.design.pattern.µ¥Àý;

/**
 * @author:Ð¡M
 * @date:2019/1/26 2:20 AM
 */
public enum SingleCase {
    INSTANCE;
    ;

    private String arg = "filed";

    public void print(){
        System.out.println("MyInstance" + " , " + arg);
    }
}
