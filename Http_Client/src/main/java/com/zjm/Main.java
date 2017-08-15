package com.zjm;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.HttpVersion;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

/**
 * @author:ºÚ¾ø
 * @date:2017/8/16 1:28
 */
public class Main {

    public static void main(String[] args) throws IOException {

        // get
        String getResult = Request.Get("http://www.weather.com.cn/").execute().returnContent().asString();
        System.out.println(getResult);
        System.out.println("============");

        // post
        String postResult = Request.Post("http://blog.csdn.net/vector_yi")
            .bodyForm(Form.form().add("username", "vip")
                .add("password", "secret").build())
            .execute().returnContent().asString();
        System.out.println(postResult);
    }
}
