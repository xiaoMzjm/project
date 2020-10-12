package com.zjm.thread.connectionpool;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 对连接池进行测试的类
 * @author:小M
 * @date:2020/9/12 11:10 PM
 */
public class ConnectionPoolTest {

    static ConnectionPool connectionPool = new ConnectionPool(10);
    static AtomicInteger got = new AtomicInteger();
    static AtomicInteger noGot = new AtomicInteger();

    /**
     * 线程数10, 成功获取连接数10, 获取不到连接数0
     * 线程数20, 成功获取连接数13, 获取不到连接数7
     * 线程数30, 成功获取连接数20, 获取不到连接数10
     * 线程数40, 成功获取连接数20, 获取不到连接数20
     * 线程数50, 成功获取连接数18, 获取不到连接数32
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args)throws InterruptedException {

        int threadNum = 50;

        List<Thread> taskList = new ArrayList<>();

        for(int i = 0 ; i < threadNum; i++) {
            MyTask task = new MyTask();
            Thread thread = new Thread(task);
            taskList.add(thread);
        }

        for(Thread thread : taskList) {
            thread.start();
        }

        for(Thread thread : taskList) {
            thread.join();
        }

        System.out.println(String.format("线程数%d, 成功获取连接数%s, 获取不到连接数%s", threadNum, got, noGot));
    }

    static class MyTask implements Runnable {

        public MyTask() {
        }

        @Override
        public void run() {
            try {
                MyConnection myConnection = connectionPool.fetchConnection(0);
                if(myConnection != null) {
                    got.incrementAndGet();
                    myConnection.commit();
                    connectionPool.releasePool(myConnection);
                }else {
                    noGot.incrementAndGet();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
