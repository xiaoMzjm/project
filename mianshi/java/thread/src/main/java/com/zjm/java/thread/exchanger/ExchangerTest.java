package com.zjm.java.thread.exchanger;

import java.util.concurrent.Exchanger;

public class ExchangerTest {

    private static Exchanger<String> exchanger = new Exchanger();

    static class Test1{
        public static void main(String[] args) {

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String str = "我是线程1，我给你一颗糖";
                        String result = exchanger.exchange(str);
                        System.out.println("我是线程1，我接收到了其他线程的消息：" + result);
                    }catch (Exception e) {
                    }
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String str = "我是线程2，我给你一颗糖";
                        String result = exchanger.exchange(str);
                        System.out.println("我是线程2，我接收到了其他线程的消息：" + result);
                    }catch (Exception e) {
                    }
                }
            }).start();


        }
    }
}
