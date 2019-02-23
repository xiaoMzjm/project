package com.zjm.user.manager;

import java.util.Optional;

import com.zjm.user.model.WxUserInfoDO;
import com.zjm.user.repository.WxLoginUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

/**
 * @author:小M
 * @date:2019/2/17 6:50 PM
 */
@Component
public class WxUserInfoManager {

    @Autowired
    private WxLoginUserRepository wxLoginUserRepository;

    /**
     * 根据openId查询
     * @param openId
     * @return
     */
    public WxUserInfoDO findByOpenId(String openId) {
        WxUserInfoDO wxUserInfoDO = new WxUserInfoDO();
        wxUserInfoDO.setOpenId(openId);
        Example<WxUserInfoDO> example = Example.of(wxUserInfoDO);
        Optional<WxUserInfoDO> findResult = wxLoginUserRepository.findOne(example);
        if(findResult.isPresent()) {
            return findResult.get();
        }
        return null;
    }

    /**
     * 根据token查询
     * @param token
     * @return
     */
    public WxUserInfoDO findByToken(String token) {
        WxUserInfoDO wxUserInfoDO = new WxUserInfoDO();
        wxUserInfoDO.setToken(token);
        Example<WxUserInfoDO> example = Example.of(wxUserInfoDO);
        Optional<WxUserInfoDO> findResult = wxLoginUserRepository.findOne(example);
        if(findResult.isPresent()) {
            return findResult.get();
        }
        return null;
    }

    /**
     * 保存 或 修改
     * @param wxUserInfoDO
     */
    public void save(WxUserInfoDO wxUserInfoDO){
        wxLoginUserRepository.save(wxUserInfoDO);
    }


}
