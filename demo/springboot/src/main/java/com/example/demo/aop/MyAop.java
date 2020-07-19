package com.example.demo.aop;

import com.alibaba.fastjson.JSON;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author:小M
 * @date:2020/7/19 10:22 PM
 */
@Aspect
@Component
public class MyAop {

    /**
     * 第一个*号：表示返回类型，*号表示所有的类型。
     * 第二个*号：表示类名，*号表示所有的类。
     * 第三个*号：表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数。
     */
    @Pointcut("execution(* com.example.demo.controller.*.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
        String method = joinPoint.getSignature().getName();
        Object[] os = joinPoint.getArgs();
        System.out.println(String.format("log before : method=%s, param=%s", method, JSON.toJSONString(os)));
    }

    @AfterReturning(value="webLog()",returning="result")
    public void doAfter(JoinPoint joinPoint, Object result){
        String method = joinPoint.getSignature().getName();
        Object[] os = joinPoint.getArgs();
        System.out.println(String.format("log after : method=%s, param=%s, result=%s", method, JSON.toJSONString(os), JSON.toJSONString(result)));
    }
}
