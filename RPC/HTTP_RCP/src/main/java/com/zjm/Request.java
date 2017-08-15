package com.zjm;

/**
 * 模拟HTTP请求
 * @author:黑绝
 * @date:2017/8/14 23:56
 */
public class Request {

    /**
     * 协议编码
     */
    private Encode encode = Encode.UTF8;
    /**
     * 命令长度，读取命令时用到
     */
    private Integer commandLength;

    /**
     * 命令
     */
    private String command;

    public Request() {
    }

    public Request(Encode encode, Integer commandLength, String command) {
        this.encode = encode;
        this.commandLength = commandLength;
        this.command = command;
    }

    public Encode getEncode() {
        return encode;
    }

    public void setEncode(Encode encode) {
        this.encode = encode;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public Integer getCommandLength() {
        return commandLength;
    }

    public void setCommandLength(Integer commandLength) {
        this.commandLength = commandLength;
    }
}
