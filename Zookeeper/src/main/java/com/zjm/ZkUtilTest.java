package com.zjm;

import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher.Event.KeeperState;

/**
 * @author:黑绝
 * @date:2017/8/20 17:45
 */
public class ZkUtilTest {

    private static ZkClient zkClient;

    public static void main(String[] args) throws Exception{
        zkClient = ZkUtil.createZkClient();
        ZkUtilTest zkUtil = new ZkUtilTest();
        zkUtil.子节点状态变化监听器();
        zkUtil.数据变化监听器();
        zkUtil.节点连接及状态变化监听器();
        zkUtil.删除节点();  // 不会被监听到
        zkUtil.创建节点();
        zkUtil.设置节点内容();
        zkUtil.获取节点内容();
        zkUtil.判断节点是否存在();
    }

    public void 子节点状态变化监听器(){
        zkClient.subscribeChildChanges("/root", new IZkChildListener() {
            public void handleChildChange(String s, List<String> list) throws Exception {
                System.out.println("子节点状态变化监听器：" + s + " , " + list);
            }
        });
    }

    public void 数据变化监听器() throws Exception {
        zkClient.subscribeDataChanges("/root", new IZkDataListener(){
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("节点数据变化监听器：" + s + " , " + o);
            }

            public void handleDataDeleted(String s) throws Exception {
                System.out.println("节点数据删除监听器：" + s);
            }
        });
    }

    public void 节点连接及状态变化监听器(){
        zkClient.subscribeStateChanges(new IZkStateListener() {
            public void handleStateChanged(KeeperState keeperState) throws Exception {
                System.out.println("节点连接及状态变监听器：");
                System.out.println(keeperState);
            }

            public void handleNewSession() throws Exception {
                System.out.println("创建新Session监听器：");
            }

            public void handleSessionEstablishmentError(Throwable throwable) throws Exception {
                System.out.println("断开Session监听器：" + throwable);
            }
        });

    }


    public  void 删除节点() throws Exception {
        zkClient.delete("/root" );
    }

    public void 创建节点() throws Exception{
        zkClient.create("/root" , "root data", CreateMode.PERSISTENT);
    }


    public void 设置节点内容() throws Exception{
        zkClient.writeData ("/root" , "hello");
    }


    public void 获取节点内容() throws  Exception {
        Object o = zkClient.readData("/root");
        System.out.println(o.toString());
    }

    public void 判断节点是否存在() throws  Exception {
        boolean exists = zkClient.exists("/root");
        if(exists) {
            System.out.println("/root 节点存在");
        }
        else {
            System.out.println("/root 节点不存在");
        }
    }
}


