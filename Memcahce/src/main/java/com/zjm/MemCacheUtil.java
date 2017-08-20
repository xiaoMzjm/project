package com.zjm;

import com.schooner.MemCached.MemcachedItem;
import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

/**
 * @author:�ھ�
 * @date:2017/8/20 20:16
 */
public class MemCacheUtil {

    public static MemCachedClient memCachedClient;

    static {
        String[] ips = {"192.168.99.100:11211"};
        SockIOPool pool = SockIOPool.getInstance();
        pool.setServers(ips);
        pool.setFailover(true);// �ݴ�
        pool.setInitConn(10);// ��ʼ������
        pool.setMinConn(5);// ��С������
        pool.setMaxConn(25);// ���������
        pool.setMaintSleep(30);// ���ӳ�ά���̵߳�˯��ʱ��
        pool.setNagle(false);//�Ƿ�ʹ��Nagle�㷨
        pool.setSocketTO(3000);// ����socket�Ķ�ȡ�ȴ���ʱʱ��
        pool.setAliveCheck(true);// ��������������⿪��
        pool.setHashingAlg(SockIOPool.CONSISTENT_HASH);// ����hasdh�㷨:һ���Թ�ϣ
        pool.initialize();
        memCachedClient = new MemCachedClient();
    }


    // test
    public static void main(String[] args) {

        Boolean setResult = MemCacheUtil.memCachedClient.set("zjm", "cool");
        Boolean setResult2 = MemCacheUtil.memCachedClient.set("zjm2", "cool");
        System.out.println("setReuslt:" + setResult);

        Object getResult = MemCacheUtil.memCachedClient.get("zjm");
        System.out.println("getResult:" + getResult);

        Object getMultiResult = MemCacheUtil.memCachedClient.getMulti(new String[]{"zjm","zjm2"});
        System.out.println("getResult:" + getMultiResult);

        Boolean addResult = MemCacheUtil.memCachedClient.add("zjm" , "cool");
        System.out.println("addResult:" + addResult);

        Object replaceResult = MemCacheUtil.memCachedClient.replace("zjm", "happyy");
        System.out.println("replaceReult:" + replaceResult);

        Object getResult2 = MemCacheUtil.memCachedClient.get("zjm");
        System.out.println("getResult:" + getResult2);

        boolean prepend = MemCacheUtil.memCachedClient.prepend("zjm", "pre_");
        System.out.println("prependReult:" + prepend);

        Object getResult3 = MemCacheUtil.memCachedClient.get("zjm");
        System.out.println("getResult:" + getResult3);

        MemCacheUtil.memCachedClient.append("zjm" , "_append");
        Object getResult4 = MemCacheUtil.memCachedClient.get("zjm");
        System.out.println("getResult:" + getResult4);

        MemcachedItem zjm = MemCacheUtil.memCachedClient.gets("zjm");
        boolean zjm1 = MemCacheUtil.memCachedClient.cas("zjm", zjm.getValue() + "_cas", zjm.getCasUnique());
        System.out.println("gets & cas result: " + zjm1);

        Object getResult5 = MemCacheUtil.memCachedClient.get("zjm");
        System.out.println("getResult:" + getResult5);

        MemCacheUtil.memCachedClient.set("mycount" , 1);
        long count = MemCacheUtil.memCachedClient.incr("mycount" , 2);
        System.out.println("incr : " + count);

        System.out.println("getResult:" + MemCacheUtil.memCachedClient.get("mycount"));

        long count1 = MemCacheUtil.memCachedClient.decr("mycount" , 1);
        System.out.println("decr : " + count1);

        System.out.println("getResult:" + MemCacheUtil.memCachedClient.get("mycount"));
    }

}
