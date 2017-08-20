package com.zjm;

import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher.Event.KeeperState;

/**
 * @author:�ھ�
 * @date:2017/8/20 12:54
 */
public class Client3  extends  BaseClient{

    public static void main(String[] args) throws InterruptedException {
        init();
        ZkClient zkClient = ZkUtil.createZkClient();
        zkClient.subscribeChildChanges(PATH, new IZkChildListener() {
            public void handleChildChange(String s, List<String> list) throws Exception {
                System.out.println("Client3 , ips = " + list);
            }
        });
        Thread.sleep(20000);
        zkClient.create(PATH + "/0.0.0.3" , null , CreateMode.EPHEMERAL);
        Thread.sleep(20000);
    }
}
