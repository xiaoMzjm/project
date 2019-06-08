package com.zjm.java.thread.threadtype;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author:小M
 * @date:2019/5/12 1:53 PM
 */
public class ThreadTypeTest {

    public static class MyTask implements Runnable{
        @Override
        public void run(){
            System.out.println("exe");
        }
    }

    public static void main(String[] args) {

        MyTask task = new MyTask();

        // 1、线程数固定为1的线程池
        ExecutorService singleExe = Executors.newSingleThreadExecutor();
        singleExe.execute(task);

        // 2、线程数固定的线程池
        ExecutorService fixedExe = Executors.newFixedThreadPool(10);
        fixedExe.execute(task);

        // 3、线程数量无限制的线程池
        ExecutorService cachedExe = Executors.newCachedThreadPool();
        cachedExe.execute(task);

        // 4、计划性执行的固定线程数线程池
        ScheduledExecutorService schedualExe = Executors.newScheduledThreadPool(10);
        schedualExe.schedule(task , 1 , TimeUnit.MINUTES);
        schedualExe.scheduleAtFixedRate(task , 0 , 1 , TimeUnit.MINUTES);

        // 5、计划性执行的单个线程数的线程池
        ScheduledExecutorService singleSchedualExe = Executors.newSingleThreadScheduledExecutor();
        singleSchedualExe.schedule(task , 1 , TimeUnit.MINUTES);
        singleSchedualExe.scheduleAtFixedRate(task , 0 , 1 , TimeUnit.MINUTES);
    }
}
