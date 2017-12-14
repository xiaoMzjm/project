package com.zjm.project.domain;

/**
 * @author:黑绝
 * @date:2017/12/8 21:53
 */
public class ClientInfo {

    /**
     * 客户端连接的唯一标识，这个id是随着Redis的连接自增的，重启Redis后会重置为0。
     */
    private String id;

    /**
     * 客户端IP
     */
    private String ip;

    /**
     * 客户端端口
     */
    private String port;

    /**
     * socket的文件描述
     */
    private String fd;

    /**
     * 客户端名称
     */
    private String name;

    /**
     * 客户端已连接时间，单位秒
     */
    private String age;

    /**
     * 客户端最近一次空闲时间，单位秒
     */
    private String idle;

    /**
     * 用于标识当前客户端的类型，例如flag=S代表当前客户端是slave客户端、flag=N代表当前是普通客户端，flag=O代表当前客户端正在执行monitor命令
     */
    private String flages;

    /**
     * 当前客户端正在使用的数据索引下标
     */
    private String db;

    /**
     * 当前客户端订阅的频道数
     */
    private String sub;

    /**
     * 当前客户端订阅的模式数
     */
    private String psub;

    /**
     * 当前事物中已执行命令个数
     */
    private String multi;

    /**
     * 客户端发送命令到服务端后存在缓冲区，这里指缓冲区大小
     */
    private String qbuf;

    /**
     * 缓冲区剩余大小
     */
    private String qbufFree;

    /**
     * 输出缓冲区固定缓冲区大小
     */
    private String obl;

    /**
     * 输出缓冲区动态缓冲区大小
     */
    private String oll;

    /**
     * 输出缓冲区总共占用大小
     */
    private String omem;

    /**
     * 文件描述符事件(r/w)：r和w分别代表客户端套接字可读和可写
     */
    private String evnets;

    /**
     * 客户端最后一次执行的命令
     */
    private String cmd;

    @Override
    public String toString() {
        return "ClientInfo{" +
            "id='" + id + '\'' +
            ", ip='" + ip + '\'' +
            ", port='" + port + '\'' +
            ", fd='" + fd + '\'' +
            ", name='" + name + '\'' +
            ", age='" + age + '\'' +
            ", idle='" + idle + '\'' +
            ", flages='" + flages + '\'' +
            ", db='" + db + '\'' +
            ", sub='" + sub + '\'' +
            ", psub='" + psub + '\'' +
            ", multi='" + multi + '\'' +
            ", qbuf='" + qbuf + '\'' +
            ", qbufFree='" + qbufFree + '\'' +
            ", obl='" + obl + '\'' +
            ", oll='" + oll + '\'' +
            ", omem='" + omem + '\'' +
            ", evnets='" + evnets + '\'' +
            ", cmd='" + cmd + '\'' +
            '}';
    }

    public String getFd() {
        return fd;
    }

    public void setFd(String fd) {
        this.fd = fd;
    }

    public String getOll() {
        return oll;
    }

    public void setOll(String oll) {
        this.oll = oll;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getIdle() {
        return idle;
    }

    public void setIdle(String idle) {
        this.idle = idle;
    }

    public String getFlages() {
        return flages;
    }

    public void setFlages(String flages) {
        this.flages = flages;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getPsub() {
        return psub;
    }

    public void setPsub(String psub) {
        this.psub = psub;
    }

    public String getMulti() {
        return multi;
    }

    public void setMulti(String multi) {
        this.multi = multi;
    }

    public String getQbuf() {
        return qbuf;
    }

    public void setQbuf(String qbuf) {
        this.qbuf = qbuf;
    }

    public String getQbufFree() {
        return qbufFree;
    }

    public void setQbufFree(String qbufFree) {
        this.qbufFree = qbufFree;
    }

    public String getObl() {
        return obl;
    }

    public void setObl(String obl) {
        this.obl = obl;
    }

    public String getOmem() {
        return omem;
    }

    public void setOmem(String omem) {
        this.omem = omem;
    }

    public String getEvnets() {
        return evnets;
    }

    public void setEvnets(String evnets) {
        this.evnets = evnets;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }
}
