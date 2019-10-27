package com.zjm.framework.springboot.mytest.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DoubleTryAop {

    @Pointcut("@annotation(com.zjm.framework.springboot.mytest.aop.DoubleTry)")
    public void pointCut() {}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        try {
            result = point.proceed();
        } catch (Exception e) {
            result = point.proceed();
        }
        return result;
    }
}
