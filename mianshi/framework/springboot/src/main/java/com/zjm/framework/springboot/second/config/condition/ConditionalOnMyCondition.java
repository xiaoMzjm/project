package com.zjm.framework.springboot.second.config.condition;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE ,ElementType.METHOD})
@Documented
@Conditional(MyCondition.class)
public @interface ConditionalOnMyCondition {

    String value();
}
