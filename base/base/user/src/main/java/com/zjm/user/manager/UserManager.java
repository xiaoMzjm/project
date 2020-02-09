package com.zjm.user.manager;

import java.util.Optional;

import com.zjm.user.model.UserDO;
import com.zjm.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

/**
 * @author:小M
 * @date:2019/2/17 6:50 PM
 */
@Component
public class UserManager {

    @Autowired
    public UserRepository wxLoginUserRepository;

    /**
     * 根据openId查询
     * @param openId
     * @return
     */
    public UserDO findByOpenId(String openId) {
        UserDO wxUserInfoDO = new UserDO();
        wxUserInfoDO.setOpenId(openId);
        Example<UserDO> example = Example.of(wxUserInfoDO);
        Optional<UserDO> findResult = wxLoginUserRepository.findOne(example);
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
    public UserDO findByToken(String token) {
        UserDO wxUserInfoDO = new UserDO();
        wxUserInfoDO.setToken(token);
        Example<UserDO> example = Example.of(wxUserInfoDO);
        Optional<UserDO> findResult = wxLoginUserRepository.findOne(example);
        if(findResult.isPresent()) {
            return findResult.get();
        }
        return null;
    }

    /**
     * 保存 或 修改
     * @param wxUserInfoDO
     */
    public void save(UserDO wxUserInfoDO){
        wxLoginUserRepository.save(wxUserInfoDO);
    }


}
