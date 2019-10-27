package com.zjm.framework.springmvc.aop;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ReTry {

    int times() default 2;

}
