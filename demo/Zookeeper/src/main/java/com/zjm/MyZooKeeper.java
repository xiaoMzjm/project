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
 * @author:�ھ�
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
        MyZooKeeper.����();
        MyZooKeeper.ɾ���ڵ�();
        MyZooKeeper.�����ڵ�();
        MyZooKeeper.���ýڵ�����();
        MyZooKeeper.��ȡ�ڵ�����();
        Thread.sleep(10000);
    }


    public static void ����() throws Exception{
        zooKeeper = new org.apache.zookeeper.ZooKeeper(url , sessionTimeout , null);
        waitUntilConnected(zooKeeper);
    }


    public  static void ɾ���ڵ�() throws Exception {
        zooKeeper.delete("/root" , -1);
    }

    public static void �����ڵ�() throws Exception{
        zooKeeper.create("/root" , "root data".getBytes() , Ids.OPEN_ACL_UNSAFE , CreateMode.PERSISTENT);
    }


    public static void ���ýڵ�����() throws Exception{
        zooKeeper.setData("/root" , "hello".getBytes() , -1);
    }


    public static void ��ȡ�ڵ�����() throws  Exception {
        byte[] data = zooKeeper.getData("/root", false, new Stat());
        System.out.println(new String(data));
    }

    public static void �жϽڵ��Ƿ����() throws  Exception {
        Stat exists = zooKeeper.exists("/root", false);
        if(exists == null) {
            System.out.println("/root �ڵ㲻����");
        }
        else {
            System.out.println("/root �ڵ����");
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