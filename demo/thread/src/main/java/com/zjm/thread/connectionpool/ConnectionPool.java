package com.zjm.thread.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * 尝试自己实现一个连接池
 * @author:小M
 * @date:2020/9/12 3:22 PM
 */
public class ConnectionPool {

    /**
     * 实际上的泛型应该是一个Connection对象，不是Object
     */
    private LinkedList<MyConnection> pool = new LinkedList<>();

    /**
     * 初始化一个连接数为 nums 的连接池
     * @param nums
     * @throws SQLException
     */
    public ConnectionPool(int nums) {
        if(nums < 1) {
            throw new RuntimeException("nums < 1");
        }
        for(int i = 0 ; i < nums ; i++) {
            pool.add(new MyConnection());
        }
    }

    /**
     * 释放连接池
     * @param connection
     */
    public void releasePool(MyConnection connection){
        // 调用notifyAll，必须获取对象锁
        synchronized (pool) {
            pool.addLast(connection);
            pool.notifyAll();
        }
    }

    /**
     * 获取连接，超时返回
     * @param mills
     * @return
     * @throws InterruptedException
     */
    public MyConnection fetchConnection(long mills) throws InterruptedException{
        synchronized (pool) {
            if(mills <= 0) {
                if(pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            }else {
                long feature = System.currentTimeMillis() + mills;
                long remain = mills;
                while(pool.isEmpty() && remain > 0) {
                    pool.wait();
                    remain = feature - System.currentTimeMillis();
                }
                if(!pool.isEmpty()) {
                    return pool.removeFirst();
                }else {
                    return null;
                }
            }
        }
    }
}
