package com.zjm.framework.springmvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class MyController {


    @RequestMapping("/hi")
    public String hi(@PathParam("id") Long id) {
        return "hi";
    }
}
