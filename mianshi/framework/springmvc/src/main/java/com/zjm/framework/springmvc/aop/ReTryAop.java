package com.zjm.framework.springmvc.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ReTryAop {

    @Pointcut("@annotation(com.zjm.framework.springmvc.aop.ReTry)")
    public void pointCut() {}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {

        ReTry reTry = point.getTarget().getClass().getMethod(point.getSignature().getName() , ((MethodSignature) point.getSignature()).getParameterTypes()).getAnnotation(ReTry.class);

        int times = reTry.times();

        if(times < 0) {
            times = 1;
        }
        if(times > 10) {
            times = 10;
        }

        Object result = null;
        Integer count = 1;
        while(true) {
            try {
                result = point.proceed();
                break;
            } catch (Exception e) {
                if(count++ >= times) {
                    throw e;
                }
            }
        }

        return result;
    }
}
