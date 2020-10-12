package com.zjm;

import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

/**
 * @author:ºÚ¾ø
 * @date:2017/8/20 13:01
 */
public class BaseClient {

    protected final static String PATH = "/root/RPC/IP";

    protected static void init(){
        ZkClient zkClient = ZkUtil.createZkClient();
        boolean exists = zkClient.exists("/root");
        if(!exists) {
            zkClient.create("/root" , null , CreateMode.PERSISTENT);
        }

        boolean exists2 = zkClient.exists("/root/RPC");
        if(!exists2) {
            zkClient.create("/root/RPC" , null , CreateMode.PERSISTENT);
        }

        boolean exists3 = zkClient.exists("/root/RPC/IP");
        if(!exists3) {
            zkClient.create("/root/RPC/IP" , null , CreateMode.PERSISTENT);
        }
    }
}
