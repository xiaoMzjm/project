package com.zjm.framework.springmvc.responsebodyadvice;

import com.alibaba.fastjson.JSON;
import com.zjm.framework.springmvc.common.Result;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;

/**
 * 实现对restful的接口统一封装Result
 */
@ControllerAdvice(annotations = RestController.class)
public class MyResponseBodyAdvice implements ResponseBodyAdvice {

    private static final Class[] annos = {
            RequestMapping.class,
            GetMapping.class,
            PostMapping.class,
            DeleteMapping.class,
            PutMapping.class
    };



    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        AnnotatedElement element = returnType.getAnnotatedElement();
        return Arrays.stream(annos).anyMatch(anno -> anno.isAnnotation() && element.isAnnotationPresent(anno));

    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        Object out = null;
        if(body instanceof Result) {
            out = body;
        }
        else {
            Result result = new Result();
            result.setData(body);
            out = result;
        }

        return JSON.toJSONString(out);
    }
}
