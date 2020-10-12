package com.zjm;

import com.schooner.MemCached.MemcachedItem;
import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

/**
 * @author:黑绝
 * @date:2017/8/20 20:16
 */
public class MemCacheUtil {

    public static MemCachedClient memCachedClient;

    static {
        String[] ips = {"192.168.99.100:11211"};
        SockIOPool pool = SockIOPool.getInstance();
        pool.setServers(ips);
        pool.setFailover(true);// 容错？
        pool.setInitConn(10);// 初始连接数
        pool.setMinConn(5);// 最小连接数
        pool.setMaxConn(25);// 最大连接数
        pool.setMaintSleep(30);// 连接池维护线程的睡眠时间
        pool.setNagle(false);//是否使用Nagle算法
        pool.setSocketTO(3000);// 设置socket的读取等待超时时间
        pool.setAliveCheck(true);// 设置连接心跳检测开关
        pool.setHashingAlg(SockIOPool.CONSISTENT_HASH);// 设置hasdh算法:一致性哈希
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
