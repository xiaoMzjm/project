package com.zjm.project.monitor;

import java.io.UnsupportedEncodingException;

import com.zjm.project.domain.GlobalClientInfo;
import com.zjm.project.manager.JedisManager;
import redis.clients.jedis.Client;

/**
 * @author:黑绝
 * @date:2017/12/9 13:51
 */
public class GlobalClientInfoMonitor {

    public static String getGlobalClientInfoHtml() throws UnsupportedEncodingException {
        String globalClientInfoStr = getGlobalClientInfoStr();
        globalClientInfoStr = globalClientInfoStr.replaceAll("Server" , "服务端");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        globalClientInfoStr = globalClientInfoStr.replaceAll("" , "");
        return globalClientInfoStr;
    }

    private static  String getGlobalClientInfoStr() throws UnsupportedEncodingException {
        Client client = JedisManager.getJedis().getClient();
        client.info();
        String result = client.getBulkReply();
        String newR = new String(result.getBytes("ISO-8859-1"),"utf-8");
        return newR;
    }
}
