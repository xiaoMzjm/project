package com.zjm.java.thread.threadtype;

import java.util.concurrent.*;

import oracle.jvm.hotspot.jfr.TraceTypes;

/**
 * @author:小M
 * @date:2019/4/14 12:52 AM
 */
public class CachedThreadPoolTest {

    public static class MyTask implements Runnable {
        private String name;

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                System.out.println(name + " 开始运行");
                Thread.sleep(1000000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = new ThreadPoolExecutor(10,
            100, 1, TimeUnit.MINUTES,
            new LinkedBlockingDeque<Runnable>(1));
        // 运行10个任务
        for (int i = 0; i < 10; i++) {
            MyTask myTask = new MyTask("task" + (i + 1));
            executorService.execute(myTask);
        }

        MyTask myTask11 = new MyTask("task11");
        executorService.execute(myTask11);

        MyTask myTask12 = new MyTask("task12");
        executorService.execute(myTask12);
    }
}
