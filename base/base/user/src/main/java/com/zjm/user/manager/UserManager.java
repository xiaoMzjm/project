package com.zjm.user.manager;

import java.util.Date;
import java.util.Optional;

import com.zjm.common.exception.BaseException;
import com.zjm.common.util.UUIDUtil;
import com.zjm.user.common.Constant.ErrorCode;
import com.zjm.user.model.UserConvertor;
import com.zjm.user.model.UserDO;
import com.zjm.user.model.UserDTO;
import com.zjm.user.repository.UserDORepository;
import org.bouncycastle.jce.provider.symmetric.ARC4.Base;
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
    public UserDORepository userDORepository;

    /**
     * 根据openId查询
     * @param openId
     * @return
     */
    public UserDTO findByOpenId(String openId) {
        UserDO wxUserInfoDO = new UserDO();
        wxUserInfoDO.setOpenId(openId);
        Example<UserDO> example = Example.of(wxUserInfoDO);
        Optional<UserDO> findResult = userDORepository.findOne(example);
        if(findResult.isPresent()) {
            return UserConvertor.do2DTO(findResult.get());
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
        Optional<UserDO> findResult = userDORepository.findOne(example);
        if(findResult.isPresent()) {
            return findResult.get();
        }
        return null;
    }

    /**
     * 根据account查询
     * @param account
     * @return
     */
    public UserDTO findByAccount(String account) {
        UserDO wxUserInfoDO = new UserDO();
        wxUserInfoDO.setAccount(account);
        Example<UserDO> example = Example.of(wxUserInfoDO);
        Optional<UserDO> findResult = userDORepository.findOne(example);
        if(findResult.isPresent()) {
            return UserConvertor.do2DTO(findResult.get());
        }
        return null;
    }

    /**
     * 保存 或 修改
     * @param userDTO
     */
    public void save(UserDTO userDTO){
        UserDO userDO = UserConvertor.dto2DO(userDTO);
        userDO.setCode(UUIDUtil.get());
        Date now = new Date();
        userDO.setGmtCreate(now);
        userDO.setGmtModified(now);
        userDO.setToken(UUIDUtil.get());
        userDORepository.save(userDO);
    }

    /**
     * 更新用户信息
      * @param id
     * @param gmtModified
     * @throws BaseException
     */
    public void update(Long id, Date gmtModified) throws BaseException {

        UserDO userDO = new UserDO();
        userDO.setId(id);
        Example<UserDO> example = Example.of(userDO);
        Optional<UserDO> findResult = userDORepository.findOne(example);
        if(!findResult.isPresent()) {
            throw new BaseException(ErrorCode.CAN_NOT_FIND_USER_BY_ID.getCode() , ErrorCode.CAN_NOT_FIND_USER_BY_ID.getDesc());
        }
        userDO = findResult.get();
        if (gmtModified != null) {
            userDO.setGmtModified(gmtModified);
        }
        userDO = userDORepository.save(userDO);
    }


}
