package com.zjm.wxlogin.manager;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import com.zjm.wxlogin.constant.WxLoginExceptionEnums;
import com.zjm.wxlogin.constant.WxLoginVerifyUtil;
import com.zjm.wxlogin.model.data.WxUserInfoDO;
import com.zjm.wxlogin.model.dto.WxRequireLoginResultDTO;
import com.zjm.wxlogin.model.dto.WxUserInfoDTO;
import com.zjm.wxlogin.repository.WxLoginUserRepository;
import com.zjm.wxlogin.util.UUIDUtil;
import com.zjm.wxlogin.wrapper.WxWapper;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

/**
 * @author:小M
 * @date:2019/1/13 10:49 PM
 */
@Component
public class WxLoginManager {

    @Autowired
    private WxWapper wxWapper;
    @Autowired
    private WxUserInfoManager wxUserInfoManager;

    /**
     * 入参校验
     * @param code
     * @param encryptedData
     * @param iv
     * @throws Exception
     */
    private void validateParam(String code , String encryptedData , String iv) throws Exception {
        WxLoginVerifyUtil.isNotEmpty(code , WxLoginExceptionEnums.LOGIN_USER_CODE_EMPTY);
        WxLoginVerifyUtil.isNotEmpty(encryptedData , WxLoginExceptionEnums.LOGIN_ENCRYPTED_DATA_EMPTY);
        WxLoginVerifyUtil.isNotEmpty(iv , WxLoginExceptionEnums.LOGIN_IV_EMPTY);
    }

    /**
     * 微信登录，获取登录用户信息
     * @param code
     * @param encryptedData
     * @param iv
     * @return
     * @throws Exception
     */
    public WxUserInfoDTO login(String code , String encryptedData , String iv)throws Exception {

        // 1. 校验入参
        this.validateParam(code, encryptedData , iv);

        // 2.获取openId,session
        WxRequireLoginResultDTO wxRequireLoginResultDTO = wxWapper.requireWxForLogin(code);

        // 3.解密获取用户详情
        WxUserInfoDTO wxUserInfoDTO = wxWapper.decodeUserInfo(encryptedData , wxRequireLoginResultDTO.getSession_key() , iv);

        // 4.保存到数据库
        wxUserInfoDTO = this.save2db(wxUserInfoDTO);

        return wxUserInfoDTO;
    }

    /**
     * 保存到数据库
     * @param wxUserInfoDTO
     */
    private synchronized WxUserInfoDTO save2db(WxUserInfoDTO wxUserInfoDTO){

        WxUserInfoDO wxUserInfoDO = wxUserInfoManager.findByOpenId(wxUserInfoDTO.getOpenId());

        // 无则新增
        if(wxUserInfoDO == null) {
            wxUserInfoDO = new WxUserInfoDO();
            BeanUtils.copyProperties(wxUserInfoDTO , wxUserInfoDO);
            String id = UUIDUtil.get();
            wxUserInfoDO.setId(id);
            String token = UUIDUtil.get();
            wxUserInfoDO.setToken(token);
            String code = UUIDUtil.get();
            wxUserInfoDO.setCode(code);
            Date now = new Date();
            wxUserInfoDO.setGmtCreate(now);
            wxUserInfoDO.setGmtModified(now);
            wxUserInfoManager.save(wxUserInfoDO);
            wxUserInfoDTO.setToken(token);
        }
        // 有责更新token
        else {
            String token = UUIDUtil.get();
            wxUserInfoDO.setToken(token);
            wxUserInfoDO.setGmtModified(new Date());
            wxUserInfoManager.save(wxUserInfoDO);
            wxUserInfoDO = wxUserInfoManager.findByOpenId(wxUserInfoDO.getOpenId());
            BeanUtils.copyProperties(wxUserInfoDO , wxUserInfoDTO);
        }
        return wxUserInfoDTO;
    }



}
