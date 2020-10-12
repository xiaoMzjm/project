package com.zjm;

/**
 * @author:ºÚ¾ø
 * @date:2017/8/16 0:04
 */
public class Main {

    public static void main(String[] args) throws Exception{
        Request request = new Request();
        request.setCommand("hahaha");
        Response response = HttpSender.send(request);
        System.out.println(response.getResponse());
    }
}
