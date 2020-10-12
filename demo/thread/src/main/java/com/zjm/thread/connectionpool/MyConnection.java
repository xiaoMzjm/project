package com.zjm.thread.connectionpool;

/**
 * 这里模拟Connection类
 * @author:小M
 * @date:2020/9/12 11:03 PM
 */
public class MyConnection {

    /**
     * 模拟commit方法，
     * @throws InterruptedException
     */
    public void commit() {
        try {
            Thread.sleep(200);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
