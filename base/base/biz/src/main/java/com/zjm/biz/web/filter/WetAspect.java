package com.zjm.biz.web.filter;

import java.lang.reflect.Method;

import com.zjm.common.constant.Result;
import com.zjm.common.exception.BaseException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import sun.jvm.hotspot.ui.ObjectListPanel;

/**
 * @author:小M
 * @date:2019/4/27 12:15 AM
 */
@Aspect
@Component
public class WetAspect {

    // 声明切点
    @Pointcut("@annotation(com.zjm.biz.web.filter.ApiFilter)")
    public void annotationPointCut(){};

    @Around("annotationPointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        Object processResult = null;
        try {
            processResult = proceedingJoinPoint.proceed();
            return Result.success(processResult);
        }
        catch (BaseException be) {
            return Result.error(be.getErrorCode(), be.getMessage());
        }
        catch (Throwable e) {
            return Result.error("System Error" , "系统异常");
        }
    }
}
