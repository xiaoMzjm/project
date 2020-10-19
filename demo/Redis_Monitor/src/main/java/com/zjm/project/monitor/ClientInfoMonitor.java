package com.zjm.project.monitor;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.zjm.project.domain.ClientInfo;
import com.zjm.project.manager.JedisManager;
import redis.clients.jedis.Client;

/**
 * @author:黑绝
 * @date:2017/12/9 16:38
 */
public class ClientInfoMonitor {

    public static List<ClientInfo> clientInfoList() throws UnsupportedEncodingException {
        String clientListStr = clientListStr();
        String[] clientListArray = clientListStr.split(System.getProperty("line.separator"));
        List<ClientInfo> list = new ArrayList<>();
        for(String clinetInfoStr : clientListArray) {
            String[] clientInfoArray = clinetInfoStr.split(" ");
            String id = clientInfoArray[0].split("=")[1];
            String ip = clientInfoArray[1].split(":")[0].split("=")[1];
            String port = clientInfoArray[1].split(":")[1];
            String fd = clientInfoArray[2].split("=")[1];
            String name = clientInfoArray[3].split("=").length == 2 ? clientInfoArray[3].split("=")[1] : clientInfoArray[3].split("=")[0];
            String age = clientInfoArray[4].split("=")[1];
            String idle = clientInfoArray[5].split("=")[1];
            String flages = clientInfoArray[6].split("=")[1];
            String db = clientInfoArray[7].split("=")[1];
            String sub = clientInfoArray[8].split("=")[1];
            String psub = clientInfoArray[9].split("=")[1];
            String multi = clientInfoArray[10].split("=")[1];
            String qbuf = clientInfoArray[11].split("=")[1];
            String qbufree = clientInfoArray[12].split("=")[1];
            if(qbufree.equalsIgnoreCase("0")) {
                continue;
            }
            String obl = clientInfoArray[13].split("=")[1];
            String oll = clientInfoArray[14].split("=")[1];
            String omem = clientInfoArray[15].split("=")[1];
            String events = clientInfoArray[16].split("=")[1];
            String cmd = clientInfoArray[17].split("=")[1];
            ClientInfo clientInfo = new ClientInfo();
            clientInfo.setId(id);
            clientInfo.setIp(ip);
            clientInfo.setPort(port);
            clientInfo.setFd(fd);
            clientInfo.setName(name);
            clientInfo.setAge(age);
            clientInfo.setIdle(idle);
            clientInfo.setFlages(flages);
            clientInfo.setDb(db);
            clientInfo.setSub(sub);
            clientInfo.setPsub(psub);
            clientInfo.setMulti(multi);
            clientInfo.setQbuf(qbuf);
            clientInfo.setQbufFree(qbufree);
            clientInfo.setObl(obl);
            clientInfo.setOll(oll);
            clientInfo.setOmem(omem);
            clientInfo.setEvnets(events);
            clientInfo.setCmd(cmd);
            list.add(clientInfo);
        }
        return list;
    }

    public static List<String> getClientListHtml() throws UnsupportedEncodingException {
        String clientListStr = clientListStr();
        String[] clientListArray = clientListStr.split(System.getProperty("line.separator"));
        List<String> htmls = new ArrayList<>();
        for(String clientInfo : clientListArray){
            clientInfo = clientInfo.replaceAll("id" , "客户端id");
            clientInfo = clientInfo.replaceAll("addr" , "ip地址和端口");
            clientInfo = clientInfo.replaceAll("fd" , "socket的文件描述");
            clientInfo = clientInfo.replaceAll("name" , "客户端名称");
            clientInfo = clientInfo.replaceAll("age" , "客户端已连接时间，单位秒");
            clientInfo = clientInfo.replaceAll("idle" , "客户端最近一次空闲时间，单位秒");
            clientInfo = clientInfo.replaceAll("flags" , "客户端的类型");
            clientInfo = clientInfo.replaceAll("db" , "当前客户端正在使用的数据索引下标");
            clientInfo = clientInfo.replaceAll("sub" , "当前客户端订阅的频道数");
            clientInfo = clientInfo.replaceAll("psub" , "当前客户端订阅的模式数");
            clientInfo = clientInfo.replaceAll("multi" , "当前事物中已执行命令个数");
            clientInfo = clientInfo.replaceAll("qbufFree" , "缓冲区剩余大小");
            clientInfo = clientInfo.replaceAll("qbuf" , "输入缓冲区大小");
            clientInfo = clientInfo.replaceAll("obl" , "输出缓冲区固定缓冲区大小");
            clientInfo = clientInfo.replaceAll("oll" , "输出缓冲区动态缓冲区大小");
            clientInfo = clientInfo.replaceAll("omem" , "输出缓冲区总共占用大小");
            clientInfo = clientInfo.replaceAll("events" , "件描述符事件(r/w)");
            clientInfo = clientInfo.replaceAll("cmd" , "客户端最后一次执行的命令");
            clientInfo = clientInfo.replaceAll(" " , "</br>");
            htmls.add(clientInfo);
        }
        return htmls;
    }

    private static String clientListStr() throws UnsupportedEncodingException {
        Client client = JedisManager.getJedis().getClient();
        client.clientList();
        String result = client.getBulkReply();
        String newR = new String(result.getBytes("ISO-8859-1"),"utf-8");
        System.out.println(newR);
        return newR;
    }
}
