package com.zjm;

/**
 * ģ��HTTP RESPONSE
 * @author:�ھ�
 * @date:2017/8/15 0:01
 */
public class Response {

    /**
     * ����
     */
    private Encode encode = Encode.UTF8;

    /**
     * ��Ӧ����
     */
    private Integer responseLength;

    /**
     * ��Ӧ
     */
    private String response;

    public Response() {
    }

    public Response(Encode encode, Integer responseLength, String response) {
        this.encode = encode;
        this.responseLength = responseLength;
        this.response = response;
    }

    public Encode getEncode() {
        return encode;
    }

    public void setEncode(Encode encode) {
        this.encode = encode;
    }

    public Integer getResponseLength() {
        return responseLength;
    }

    public void setResponseLength(Integer responseLength) {
        this.responseLength = responseLength;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
