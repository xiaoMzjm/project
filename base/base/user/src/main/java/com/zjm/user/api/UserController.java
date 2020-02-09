package com.zjm.user.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import com.zjm.user.filter.TokenAnnotation;
import com.zjm.user.manager.UserManager;
import com.zjm.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import sun.jvm.hotspot.asm.Register;

/**
 * 通用登陆接口
 * @author:小M
 * @date:2019/1/13 10:41 PM
 */
@Api(description = "用户接口")
@Controller
@ResponseBody
@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "注册" ,  notes="注册")
    @RequestMapping(value = "/register" , method = RequestMethod.POST , produces = {"application/json;charset=UTF-8"})
    public String register(@RequestBody RegisterParam registerParam,
                        HttpServletRequest request,
                        HttpServletResponse response) throws Exception{

        userService.register(registerParam.account , registerParam.password);

        return JSON.toJSONString("success");
    }
    static class RegisterParam{
        @ApiParam(name="账号",value="account",required=true)
        public String account;
        @ApiParam(name="密码",value="password",required=true)
        public String password;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
