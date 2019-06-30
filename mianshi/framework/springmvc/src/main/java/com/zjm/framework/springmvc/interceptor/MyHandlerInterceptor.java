package com.zjm.framework.springmvc.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyHandlerInterceptor implements HandlerInterceptor {

    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response,
            Object handler)
            throws Exception {

        System.out.println("come on");

        return true;
    }
}
