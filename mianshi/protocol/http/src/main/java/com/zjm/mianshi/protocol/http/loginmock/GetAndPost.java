package com.zjm.mianshi.protocol.http.loginmock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:小M
 * @date:2019/5/19 7:09 PM
 */
@RequestMapping("/getandpost")
@ResponseBody
@SpringBootApplication
public class GetAndPost {
    public static void main(String[] args) {
        SpringApplication.run(GetAndPost.class, args);
    }


    @RequestMapping(value = "/get" , method = RequestMethod.GET)
    public String getMethod(){
        return "get";
    }

    @RequestMapping(value = "/post" , method = RequestMethod.POST)
    public String postMethod(){
        return "post" ;
    }
}
