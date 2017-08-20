package com.zjm;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper.States;
import org.apache.zookeeper.data.Stat;

/**
 * @author:黑绝
 * @date:2017/8/19 19:20
 */
public class MyZooKeeper {

    private static String url = "";
    private static Integer sessionTimeout = 2000;
    private static org.apache.zookeeper.ZooKeeper zooKeeper = null;

    static {
        url += "192.168.99.100:2181";
        url += ",";
        url += "192.168.99.100:2182";
        url += ",";
        url += "192.168.99.100:2183";
    }

    public static void main(String[] args) throws Exception {
        MyZooKeeper.连接();
        MyZooKeeper.删除节点();
        MyZooKeeper.创建节点();
        MyZooKeeper.设置节点内容();
        MyZooKeeper.获取节点内容();
        Thread.sleep(10000);
    }


    public static void 连接() throws Exception{
        zooKeeper = new org.apache.zookeeper.ZooKeeper(url , sessionTimeout , null);
        waitUntilConnected(zooKeeper);
    }


    public  static void 删除节点() throws Exception {
        zooKeeper.delete("/root" , -1);
    }

    public static void 创建节点() throws Exception{
        zooKeeper.create("/root" , "root data".getBytes() , Ids.OPEN_ACL_UNSAFE , CreateMode.PERSISTENT);
    }


    public static void 设置节点内容() throws Exception{
        zooKeeper.setData("/root" , "hello".getBytes() , -1);
    }


    public static void 获取节点内容() throws  Exception {
        byte[] data = zooKeeper.getData("/root", false, new Stat());
        System.out.println(new String(data));
    }

    public static void 判断节点是否存在() throws  Exception {
        Stat exists = zooKeeper.exists("/root", false);
        if(exists == null) {
            System.out.println("/root 节点不存在");
        }
        else {
            System.out.println("/root 节点存在");
        }
    }






    public static void waitUntilConnected(org.apache.zookeeper.ZooKeeper zooKeeper) {
        CountDownLatch connectedLatch = new CountDownLatch(1);
        Watcher watcher = new ConnectedWatcher(connectedLatch);
        Watcher watcher1 = new MyWatch();
        zooKeeper.register(watcher1);
        zooKeeper.register(watcher);
        if (States.CONNECTING == zooKeeper.getState()) {
            try {
                connectedLatch.await();
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    static class ConnectedWatcher implements Watcher {

        private CountDownLatch connectedLatch;

        ConnectedWatcher(CountDownLatch connectedLatch) {
            this.connectedLatch = connectedLatch;
        }

        public void process(WatchedEvent event) {
            System.out.println("ConnectedWatcher : "+ event.getState());
            if (event.getState() == KeeperState.SyncConnected) {
                connectedLatch.countDown();
            }
        }
    }

    static class MyWatch implements Watcher {
        public void process(WatchedEvent event) {
            System.out.println("MyWatch : "+ event.getState());
        }
    }
}