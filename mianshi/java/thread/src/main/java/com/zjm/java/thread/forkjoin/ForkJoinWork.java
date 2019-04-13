package com.zjm.java.thread.forkjoin;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * @author:小M
 * @date:2019/4/13 11:34 PM
 */
public class ForkJoinWork extends RecursiveTask<Long> {

    private Long start ;
    private Long end;
    private Long middle = 100L;

    public ForkJoinWork(Long start , Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        Long sum = 0L;
        if(end - start < middle) {
            for(Long i = start ; i <= end ; i++) {
                try {
                    Thread.sleep(1);
                }catch (Exception e) {

                }
                sum += i;
            }
        }
        else {
            Long m = (end + start) / 2;
            ForkJoinWork left = new ForkJoinWork(start , m);
            left.fork();
            ForkJoinWork right = new ForkJoinWork(m + 1 , end);
            right.fork();
            return left.join() + right.join();
        }
        System.out.println("start="+start + ",end="+end + ",sum="+sum);
        return sum;
    }


    public static void main(String[] args) {
        Long e = 1000L;
        ForkJoinTask<Long> task = new ForkJoinWork(0L , e);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Long start = System.nanoTime() / 1000000;
        Long result = forkJoinPool.invoke(task);
        Long end = System.nanoTime() / 1000000;
        System.out.println("fork join 结果="+result+",用时"+(end-start)+"ms");

        start = System.nanoTime() / 1000000;
        result = 0L;
        for(Long i = 0L; i <= e ; i++) {
            try {
                Thread.sleep(1);
            }catch (Exception t) {

            }
            result += i;
        }
        end = System.nanoTime() / 1000000;
        System.out.println("循环 结果="+result+",用时"+(end-start)+"ms");

    }
}