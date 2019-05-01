package com.zjm.biz.web.filter;

import com.zjm.common.constant.Result;
import com.zjm.common.exception.BaseException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

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
            e.printStackTrace();
            return Result.error("System Error" , e.getMessage());
        }
    }
}
