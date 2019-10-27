package com.zjm.java.jvm.outofmemoryerror;

public class StackOverFlowError {

    public static void main(String[] args) {
        StackOverFlowError.method();
    }

    public static void method(){
        method();
    }
}
