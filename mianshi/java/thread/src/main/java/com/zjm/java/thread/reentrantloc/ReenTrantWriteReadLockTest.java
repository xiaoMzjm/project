package com.zjm.java.thread.reentrantloc;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author:小M
 * @date:2019/5/12 2:58 AM
 */
public class ReenTrantWriteReadLockTest {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public  void main(String[] args) {
        lock.readLock().lock();
        lock.writeLock().lock();
    }
}
