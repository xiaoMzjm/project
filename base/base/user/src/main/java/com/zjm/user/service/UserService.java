package com.zjm.user.service;

import com.zjm.common.constant.Result;
import com.zjm.common.exception.BaseException;
import com.zjm.common.util.MD5Util;
import com.zjm.common.util.VerifyUtil;
import com.zjm.user.common.Constant.ErrorCode;
import com.zjm.user.manager.UserManager;
import com.zjm.user.model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author:小M
 * @date:2020/2/9 9:44 PM
 */
@Service
public class UserService {

    @Autowired
    private UserManager userManager;

    public Result<Void> register(String account, String password) throws Exception{

        VerifyUtil.isNotNull(account, ErrorCode.REGISTER_ACCOUNT_NULL.getCode(), ErrorCode.REGISTER_ACCOUNT_NULL.getDesc());
        VerifyUtil.isNotNull(password, ErrorCode.REGISTER_PASSWORD_NULL.getCode(), ErrorCode.REGISTER_PASSWORD_NULL.getDesc());

        UserDTO userDTO = userManager.findByAccount(account);
        if (userDTO != null) {
            throw new BaseException(ErrorCode.REGISTER_REPEAT.getCode(), ErrorCode.REGISTER_REPEAT.getDesc());
        }

        userDTO = new UserDTO();
        userDTO.setAccount(account);
        userDTO.setPassword(MD5Util.MD5(password));

        userManager.save(userDTO);

        return null;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }
}
