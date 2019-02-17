package com.zjm.wxlogin.api;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import com.zjm.wxlogin.anno.NotCheckToken;
import com.zjm.wxlogin.constant.WxLoginResult;
import com.zjm.wxlogin.manager.WxLoginManager;
import com.zjm.wxlogin.model.WxUserInfoVO;
import com.zjm.wxlogin.model.dto.WxUserInfoDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 微信登陆接口
 * @author:小M
 * @date:2019/1/13 10:41 PM
 */
@Api(description = "微信登陆接口")
@Controller
@ResponseBody
@RequestMapping("/wx")
@RestController
public class WxLoginController {

    @Autowired
    private WxLoginManager wxLoginManager;

    /**
     * http://localhost/wx/login
     * 登陆，返回带skey
     * @param code
     * @param encryptedData
     * @param iv
     * @return
     */
    @ApiOperation(value = "微信登陆" ,  notes="微信登陆")
    @RequestMapping(value = "/login" , method = RequestMethod.POST , produces = {"application/json;charset=UTF-8"})
    @NotCheckToken
    public String login(@ApiParam(name="用户code",value="code",required=true)String code ,
                        @ApiParam(name="密文",value="encryptedData",required=true)String encryptedData ,
                        @ApiParam(name="解密向量",value="iv",required=true)String iv,
                        HttpServletRequest request,
                        HttpServletResponse response) {
        try {
            System.out.println("code="+code + ",encryptedData="+encryptedData+",iv="+iv);
            WxUserInfoDTO wxUserInfoDTO = wxLoginManager.login(code, encryptedData ,iv);
            WxUserInfoVO wxUserInfoVO = new WxUserInfoVO();
            BeanUtils.copyProperties(wxUserInfoDTO , wxUserInfoVO);
            WxLoginResult<WxUserInfoVO> result = WxLoginResult.success(wxUserInfoVO);
            System.out.println("微信登录成功：");
            System.out.println(JSON.toJSONString(result));
            response.addHeader("token" , result.getData().getToken());
            Cookie cookie = new Cookie("token" , result.getData().getToken());
            response.addCookie(cookie);
            return JSON.toJSONString(result);
        }catch (Exception e) {
            e.printStackTrace();
            return JSON.toJSONString(WxLoginResult.error(e.getMessage() , e.getMessage()));
        }
    }
}
