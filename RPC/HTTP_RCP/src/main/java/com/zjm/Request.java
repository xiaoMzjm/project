package com.zjm;

/**
 * ģ��HTTP����
 * @author:�ھ�
 * @date:2017/8/14 23:56
 */
public class Request {

    /**
     * Э�����
     */
    private Encode encode = Encode.UTF8;
    /**
     * ����ȣ���ȡ����ʱ�õ�
     */
    private Integer commandLength;

    /**
     * ����
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
