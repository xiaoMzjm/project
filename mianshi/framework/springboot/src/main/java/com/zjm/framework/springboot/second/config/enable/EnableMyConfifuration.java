package com.zjm.framework.springboot.second.config.enable;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
// 引入selector，决定要引入哪些Configuration
@Import(MyEnableSelector.class)
public @interface EnableMyConfifuration {
}
