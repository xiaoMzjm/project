package com.zjm.framework.springboot.second.config.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;

import java.util.List;

public class MyCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {

        MultiValueMap<String, Object> keyValue = annotatedTypeMetadata.getAllAnnotationAttributes(ConditionalOnMyCondition.class.getName());

        List<Object> values = keyValue.get("value");

        if(!CollectionUtils.isEmpty(values)) {
            if(values.get(0).toString().equals("vip")) {
                return true;
            }
        }
        return false;
    }
}
