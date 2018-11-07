package com.zjm.web.api;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * http://localhost:8099/swagger-ui.html
 * @author:黑绝
 * @date:2018/11/4 下午9:59
 */
@RestController
@RequestMapping("/base/resource")
public class BaseResource {


    @ApiOperation(value = "hello接口" , notes = "hello")
    @RequestMapping(value = "/hello/{name}" , method = RequestMethod.POST)
    public String hello(@PathVariable String name){
        return "hello " + name ;
    }


}
