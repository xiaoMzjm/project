package com.zjm.user.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import com.zjm.user.filter.TokenAnnotation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 通用登陆接口
 * @author:小M
 * @date:2019/1/13 10:41 PM
 */
@Api(description = "登陆接口")
@Controller
@ResponseBody
@RequestMapping("user")
@RestController
public class LoginController {

    @ApiOperation(value = "登陆" ,  notes="登陆")
    @RequestMapping(value = "/login" , produces = {"application/json;charset=UTF-8"})
    @TokenAnnotation
    public String register(@ApiParam(name="用户code",value="code",required=true)String code ,
                        HttpServletRequest request,
                        HttpServletResponse response) {
        System.out.println("user=" + JSON.toJSONString(request.getAttribute("user")));
        return JSON.toJSONString(request.getAttribute("user"));
    }
}
