package com.zjm.framework.springmvc.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.alibaba.fastjson.JSON;

import com.zjm.framework.springmvc.aop.ReTry;
import com.zjm.framework.springmvc.common.Result;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {

    @RequestMapping(value="/model" , method = RequestMethod.GET)
    public String model(Model model) {
        // 在视图中可以拿到该model
        model.addAttribute("key","value");
        return "model";
    }

    @RequestMapping(value="/header" , method = RequestMethod.GET)
    public String header(@RequestHeader("Accept-Language") String language) {
        System.out.println("language="+language);
        return "header";
    }

    @RequestMapping(value="/cookie" , method = RequestMethod.GET)
    public String cookie(@CookieValue("JSESSIONID") String jsessionId) {
        System.out.println("jsessionId="+jsessionId);
        return "cookie";
    }

    @ReTry(times = 3)
    @RequestMapping(value="/throwable" , method = RequestMethod.GET)
    public String throwable() throws Exception {
        if(true) {
            throw new Exception("my error");
        }
        return "throwable";
    }


    @RequestMapping(value="/valid" , method = RequestMethod.GET)
    public String valid(@Valid User user) throws Exception {
        return "valid";
    }

    @RequestMapping(value="/pathVar/{id}" , method = RequestMethod.GET)
    public String valid(@PathVariable("id")String id) throws Exception {
        return id;
    }

    @RequestMapping(value="/requestparam" , method = RequestMethod.GET)
    public String requestparam(@RequestParam("abc") String abc) {
        System.out.println("abc="+abc);
        return abc;
    }

    public class User {

        @NotBlank(message = "id为空")
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    /**
     * 全局错误处理
     * @param throwable
     * @return
     */
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public String onException(Throwable throwable) {
        Result result = new Result();
        result.setSuccess(false);
        result.setErrorMsg(throwable.getMessage());
        return JSON.toJSONString(result);
    }


}
