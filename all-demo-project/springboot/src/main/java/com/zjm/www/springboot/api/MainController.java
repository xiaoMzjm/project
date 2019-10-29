package com.zjm.www.springboot.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/main")
public class MainController {

    @RequestMapping(value = "/hello" ,  method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }
}
