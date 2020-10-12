package com.zjm;

/**
 * 模拟HTTP RESPONSE
 * @author:黑绝
 * @date:2017/8/15 0:01
 */
public class Response {

    /**
     * 编码
     */
    private Encode encode = Encode.UTF8;

    /**
     * 相应长度
     */
    private Integer responseLength;

    /**
     * 响应
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
