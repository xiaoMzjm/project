package com.zjm.wxlogin.manager;

import java.util.Optional;
import java.util.UUID;

import com.zjm.wxlogin.constant.WxLoginExceptionEnums;
import com.zjm.wxlogin.constant.WxLoginVerifyUtil;
import com.zjm.wxlogin.model.data.WxUserInfoDO;
import com.zjm.wxlogin.model.dto.WxRequireLoginResultDTO;
import com.zjm.wxlogin.model.dto.WxUserInfoDTO;
import com.zjm.wxlogin.repository.WxLoginUserRepository;
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
    private WxLoginUserRepository wxLoginUserRepository;

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
        this.save2db(wxUserInfoDTO);

        return wxUserInfoDTO;
    }

    /**
     * 保存到数据库
     * @param wxUserInfoDTO
     */
    private synchronized void save2db(WxUserInfoDTO wxUserInfoDTO){
        WxUserInfoDO wxUserInfoDO = new WxUserInfoDO();
        wxUserInfoDO.setOpenId(wxUserInfoDTO.getOpenId());
        Example<WxUserInfoDO> example = Example.of(wxUserInfoDO);
        Optional<WxUserInfoDO> findResult = wxLoginUserRepository.findOne(example);

        if(!findResult.isPresent()) {
            BeanUtils.copyProperties(wxUserInfoDTO , wxUserInfoDO);
            wxUserInfoDO.setId(UUID.randomUUID().toString());
            wxLoginUserRepository.save(wxUserInfoDO);
        }
    }



}
