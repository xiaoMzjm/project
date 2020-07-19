package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:小M
 * @date:2020/7/19 9:32 PM
 */
@Controller
@ResponseBody
public class HelloController {

    @RequestMapping("hello")
    public String hello(@RequestParam String name){
        return "hello " + name;
    }
}
