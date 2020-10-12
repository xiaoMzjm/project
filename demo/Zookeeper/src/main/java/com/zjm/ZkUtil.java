package com.zjm;

import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;

/**
 * @author:ºÚ¾ø
 * @date:2017/8/20 11:04
 */
public class ZkUtil {

    private final static Integer connectionTimeout = 5000;
    private final static Integer sessionTimeout = 2000;

    private ZkClient zkClient;

    private static volatile Watcher.Event.KeeperState state = Watcher.Event.KeeperState.SyncConnected;

    private static String url = "";

    static {
        url += "192.168.99.100:2181";
        url += ",";
        url += "192.168.99.100:2182";
        url += ",";
        url += "192.168.99.100:2183";
    }

    public static ZkClient createZkClient() {
        return new ZkClient(url , sessionTimeout,connectionTimeout);
    }


    public static ZkClient createZkClient(String url) {
        return new ZkClient(url , sessionTimeout,connectionTimeout);
    }

}
