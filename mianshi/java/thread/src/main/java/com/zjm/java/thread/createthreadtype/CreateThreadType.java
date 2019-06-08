package com.zjm.java.thread.createthreadtype;

import java.util.concurrent.*;

/**
 * 三种创建线程的方式
 */
public class CreateThreadType {

    static class Test1 {
        public static void main(String[] args) throws Exception{

            // 1. 继承Thread
            new Thread(new Thread() {
                @Override
                public void run() {
                    System.out.println("1、实现Thread");
                }
            }).start();

            // 2. 实现Runable
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("2、继承Runable");
                }
            }).start();

            // 3. 继承FutureTask
            FutureTask task = new FutureTask<String>(
                    new Callable<String>() {
                        @Override
                        public String call() throws Exception {
                            return "继承FutureTask";
                        }
                    }
            );
            new Thread(task).start();
            // get方法会阻塞等待
            System.out.println("3、" + task.get());

        }
    }



    static class Test2{
        public static void main(String[] args) throws Exception{

            // 1. 使用Thread#start执行任务
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Thread#start");
                }
            }).start();


            // 2. 使用Executor框架执行任务，不接收返回值
            ExecutorService es = Executors.newFixedThreadPool(4);
            es.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("es.execute");
                }
            });

            // 2. 使用Executor框架执行任务，接收返回值
            Future future = es.submit(new Callable<String>() {
                @Override
                public String call() {
                    return "es.submit";
                }
            });
            System.out.println(future.get());


            // 3.使用forkjoin框架，计算1000内数字累加
            class ForkJoinTask extends RecursiveTask<Long> {
                private Long left;
                private Long right;

                public ForkJoinTask(Long left, Long right) {
                    this.left = left;
                    this.right = right;
                }

                @Override
                protected Long compute() {
                    if(right - left < 10) {
                        long sum = 0L;
                        for(long i = left ; i <= right ; i++) {
                            sum += i;
                        }
                        return sum;
                    }
                    ForkJoinTask lefttask = new ForkJoinTask(left , (left+right) / 2);
                    ForkJoinTask righttask = new ForkJoinTask((left+right) / 2 + 1 , right);
                    // 分发并执行任务
                    lefttask.fork();
                    righttask.fork();
                    // 获取并合并结果
                    return lefttask.join() + righttask.join();
                }
            }
            ForkJoinTask task = new ForkJoinTask(0L,1000L);
            ForkJoinPool forkJoinPool = new ForkJoinPool();
            forkJoinPool.invoke(task);
            System.out.println(task.get());
        }
    }
}