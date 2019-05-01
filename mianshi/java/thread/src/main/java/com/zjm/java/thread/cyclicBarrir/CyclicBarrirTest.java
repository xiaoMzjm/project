package com.zjm.java.thread.cyclicBarrir;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author:小M
 * @date:2019/4/14 10:14 PM
 */
public class CyclicBarrirTest {

    public static void main(String[] args) {


        String a = "* nio 解决了 bio 哪些问题\n"
            + "* 请您描述一下nio中Reactor模型\n"
            + "\n"
            + "# 精品文章\n"
            + "- [全面解读Java NIO工作原理(1)](https://www.cnblogs.com/isoftware/p/3751800.html)\n"
            + "- [全面解读Java NIO工作原理(2)](https://www.cnblogs.com/isoftware/p/3751802.html)\n"
            + "- [全面解读Java NIO工作原理(3)](https://www.cnblogs.com/isoftware/p/3751803.html)\n"
            + "- [全面解读Java NIO工作原理(4)](https://www.cnblogs.com/isoftware/p/3751807.html)\n"
            + "- [Reactor模型](https://blog.csdn.net/qq924862077/article/details/81026740)\n"
            + "- [java nio虚拟内存原理](https://blog.csdn.net/u012684933/article/details/49682649)\n"
            + "\n"
            + "# nio 解决了 bio 哪些问题\n"
            + "\n"
            + "- ==多路复用技术==。多路复用技术是为了解决频繁地切换线程带来的开销的一种技术。是BIO为同步阻塞IO，是一路一用，我一个线程在接待某一个IO请求，其他IO"
            + "请求就需要在门口等待，为了提高性能，必然得使用线程池。假设在4个CPU下，BIO最多并行accept并处理4个IO请求。而NIO是多路复用的，还是4个线程，分1个线程出来专门accept "
            + "IO请求，3个线程处理业务，那么就可以accept非常多的IO请求了（当然accpet完还是得等另外三个处理线程有空才能真正干完）。再极端一点，分一个线程去accpet，两个线程去read&process"
            + "，剩下一个线程去write，那么可以走到read&process的连接就可能会变得更多。每个线程各干自己的活，无需频繁切换线程。NIO基于Reactor"
            + "模型，他把每个步奏都拆成拆开，通过事件的方式，分发给事件注册者，而且每次可以发给注册者多个事件，所以NIO得吞吐量会上去。\n"
            + "- ==Buffer技术==。\n"
            + "\n"
            + "\n"
            + "# 请您描述一下nio中Reactor模型\n"
            + "\n"
            + "Reactor其实是一种设计模式，特点为：\n"
            + "- 可以处理一个或多个输入源（one or more inputs）\n"
            + "- 事件驱动（event handling）\n"
            + "- 通过Service Handler同步的将输入事件（Event）采用多路复用分发给相应的Request Handler（多个）处理\n"
            + "- \n"
            + "NIO通过selector实现了三面的三个特点。\n"
            + "\n"
            + "这里又根据accept线程的数量，处理线程的数量，分成三中模型：\n"
            + "\n"
            + "单Reactor单线程模型：\n"
            + "\n"
            + "![单Reactor单线程模型](https://raw.githubusercontent.com/xiaoMzjm/images/master/单nio中的Reactor单线程模型.png)\n"
            + "\n"
            + "单Reactor多线程模型：\n"
            + "\n"
            + "![单Reactor多线程模型](https://raw.githubusercontent.com/xiaoMzjm/images/master/单Reactor多线程模型.png)\n"
            + "\n"
            + "多Reactor多线程模型：\n"
            + "\n"
            + "![多Reactor多线程模型](https://raw.githubusercontent.com/xiaoMzjm/images/master/多Reactor多线程模型.png)\n"
            + "\n"
            + "多Reactor多线程模型相比于单Reactor多线程模型，又进一步地对读写事做了一次多路复用。\n"
            + "\n";

        final CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for(int i = 0 ; i < 10 ; i++) {
            executorService.execute(new Task(cyclicBarrier));
        }

    }


    public static class Task implements Runnable {

        private static CyclicBarrier cyclicBarrier;

        public Task(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                System.out.println("等待执行");
                cyclicBarrier.await();
                System.out.println("执行了");
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
