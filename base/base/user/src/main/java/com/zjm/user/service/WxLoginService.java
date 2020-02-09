package com.zjm.user.service;

import com.zjm.common.constant.Result;
import com.zjm.common.exception.ExceptionEnums;
import com.zjm.common.util.VerifyUtil;
import com.zjm.user.manager.wx.WxLoginManager;
import com.zjm.user.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author:小M
 * @date:2019/2/24 1:52 AM
 */
@Component
public class WxLoginService {

    @Autowired
    private WxLoginManager wxLoginManager;

    /**
     * 微信登录，获取登录用户信息
     * @param code
     * @param encryptedData
     * @param iv
     * @return
     * @throws Exception
     */
    public Result<UserDTO> login(String code , String encryptedData , String iv){

        try {
            VerifyUtil.isNotEmpty(code , ExceptionEnums.LOGIN_USER_CODE_EMPTY);
            VerifyUtil.isNotEmpty(encryptedData , ExceptionEnums.LOGIN_ENCRYPTED_DATA_EMPTY);
            VerifyUtil.isNotEmpty(iv , ExceptionEnums.LOGIN_IV_EMPTY);


            UserDTO userDTO = wxLoginManager.login(code , encryptedData ,iv);

            return Result.success(userDTO);
        }catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    public void setWxLoginManager(WxLoginManager wxLoginManager) {
        this.wxLoginManager = wxLoginManager;
    }
}
