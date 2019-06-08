package com.zjm.java.basic.ffinal;

import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.bcel.internal.classfile.InnerClass;
import javafx.scene.SubScene;

/**
 * @author:小M
 * @date:2019/5/19 2:28 PM
 */
public class FinalTest4{

    public static class MyInnerClass {
        @Override
        public void finalize(){
            System.out.println("help me");
        }
    }


    public static void main(String[] args) throws Exception{
        MyInnerClass clazz = new MyInnerClass();
        clazz = null;
        Thread.sleep(10000);
        System.gc();
        System.out.println("执行回收完毕");
        Thread.sleep(300000);

    }
}
