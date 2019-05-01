package com.zjm.biz.web.api;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * https://localhost/swagger-ui.html
 * @author:小M
 * @date:2018/11/4 下午9:59
 */
@RestController
public class BaseResource {


    @ApiOperation(value = "index" , notes = "index")
    @RequestMapping(value = "/" , method = RequestMethod.GET)
    public String index(){
        return "健身信息展示";
    }

    @ApiOperation(value = "hello接口" , notes = "hello")
    @RequestMapping(value = "/hello/{name}" , method = RequestMethod.POST)
    public String hello(@PathVariable String name){
        return "hello " + name ;
    }
}
