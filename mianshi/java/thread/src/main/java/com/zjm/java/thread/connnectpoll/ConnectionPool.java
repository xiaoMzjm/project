package com.zjm.java.thread.connnectpoll;

import java.util.LinkedList;

/**
 * @author:小M
 * @date:2020/8/22 11:32 AM
 */
public class ConnectionPool {

    private LinkedList<Connection> pool = new LinkedList<>();

    public void init(Integer connectionNum) {
        if(connectionNum < 1) {
            throw new RuntimeException("connectionNum < 1");
        }
        for(int i = 0 ; i < connectionNum; i++) {
            pool.addLast(new Connection());
        }
    }

    public void releaseConnection(Connection connection) {
        if(connection != null) {
            pool.addLast(connection);
            pool.notifyAll();
        }

}

    public Connection fetchConnection(Long waitTimeMikkis) throws Exception{
        synchronized (pool) {
            if(waitTimeMikkis <= 0) {
                if(pool.isEmpty()){
                    pool.wait();
                }
                return pool.removeFirst();
            }
            else {
                Long target = System.currentTimeMillis() + waitTimeMikkis;
                Long remaing = waitTimeMikkis;
                while (pool.isEmpty() && remaing > 0) {
                    pool.wait();
                    remaing = target - System.currentTimeMillis();
                }
                if(!pool.isEmpty()) {
                    return pool.removeFirst();
                }
                return null;
            }
        }
    }
}
