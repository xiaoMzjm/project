package com.zjm;

import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher.Event.KeeperState;

/**
 * @author:�ھ�
 * @date:2017/8/20 17:45
 */
public class ZkUtilTest {

    private static ZkClient zkClient;

    public static void main(String[] args) throws Exception{
        zkClient = ZkUtil.createZkClient();
        ZkUtilTest zkUtil = new ZkUtilTest();
        zkUtil.�ӽڵ�״̬�仯������();
        zkUtil.���ݱ仯������();
        zkUtil.�ڵ����Ӽ�״̬�仯������();
        zkUtil.ɾ���ڵ�();  // ���ᱻ������
        zkUtil.�����ڵ�();
        zkUtil.���ýڵ�����();
        zkUtil.��ȡ�ڵ�����();
        zkUtil.�жϽڵ��Ƿ����();
    }

    public void �ӽڵ�״̬�仯������(){
        zkClient.subscribeChildChanges("/root", new IZkChildListener() {
            public void handleChildChange(String s, List<String> list) throws Exception {
                System.out.println("�ӽڵ�״̬�仯��������" + s + " , " + list);
            }
        });
    }

    public void ���ݱ仯������() throws Exception {
        zkClient.subscribeDataChanges("/root", new IZkDataListener(){
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("�ڵ����ݱ仯��������" + s + " , " + o);
            }

            public void handleDataDeleted(String s) throws Exception {
                System.out.println("�ڵ�����ɾ����������" + s);
            }
        });
    }

    public void �ڵ����Ӽ�״̬�仯������(){
        zkClient.subscribeStateChanges(new IZkStateListener() {
            public void handleStateChanged(KeeperState keeperState) throws Exception {
                System.out.println("�ڵ����Ӽ�״̬���������");
                System.out.println(keeperState);
            }

            public void handleNewSession() throws Exception {
                System.out.println("������Session��������");
            }

            public void handleSessionEstablishmentError(Throwable throwable) throws Exception {
                System.out.println("�Ͽ�Session��������" + throwable);
            }
        });

    }


    public  void ɾ���ڵ�() throws Exception {
        zkClient.delete("/root" );
    }

    public void �����ڵ�() throws Exception{
        zkClient.create("/root" , "root data", CreateMode.PERSISTENT);
    }


    public void ���ýڵ�����() throws Exception{
        zkClient.writeData ("/root" , "hello");
    }


    public void ��ȡ�ڵ�����() throws  Exception {
        Object o = zkClient.readData("/root");
        System.out.println(o.toString());
    }

    public void �жϽڵ��Ƿ����() throws  Exception {
        boolean exists = zkClient.exists("/root");
        if(exists) {
            System.out.println("/root �ڵ����");
        }
        else {
            System.out.println("/root �ڵ㲻����");
        }
    }
}


