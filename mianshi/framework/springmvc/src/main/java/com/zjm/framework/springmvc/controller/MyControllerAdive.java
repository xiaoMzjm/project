package com.zjm.framework.springmvc.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * 公共代码放这里
 */
@ControllerAdvice(assignableTypes = MyRestController.class)
public class MyControllerAdive {

    /**
     * 搜索请求的model都会带上key=message，value=messageValue的Attribute
     * @return
     */
    @ModelAttribute("message")
    public String message(){
        return "messageValue";
    }
}
