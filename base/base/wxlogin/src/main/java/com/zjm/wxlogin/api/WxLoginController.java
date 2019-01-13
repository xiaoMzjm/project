package com.zjm.wxlogin.api;

import com.alibaba.fastjson.JSON;

import com.zjm.wxlogin.constant.WxLoginResult;
import com.zjm.wxlogin.manager.WxLoginManager;
import com.zjm.wxlogin.model.dto.WxUserInfoDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    @RequestMapping(value = "/login" , method = RequestMethod.POST )
    public String login(String code , String encryptedData , String iv) {
        try {
            WxUserInfoDTO wxUserInfoDTO = wxLoginManager.login(code, encryptedData ,iv);
            WxLoginResult<WxUserInfoDTO> result = WxLoginResult.success(wxUserInfoDTO);
            return JSON.toJSONString(result);
        }catch (Exception e) {
            e.printStackTrace();
            return JSON.toJSONString(WxLoginResult.error(e.getMessage()));
        }
    }
}
