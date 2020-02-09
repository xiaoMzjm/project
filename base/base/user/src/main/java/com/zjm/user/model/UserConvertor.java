package com.zjm.user.model;

/**
 * @author:小M
 * @date:2019/2/21 1:13 AM
 */
public class UserConvertor {

    public static UserDTO do2DTO(UserDO userDO) {
        UserDTO userDTO = new UserDTO();

        userDTO.setOpenId(userDO.getOpenId());

        userDTO.setNickName(userDO.getNickName());

        userDTO.setGender(userDO.getGender());

        userDTO.setLanguage(userDO.getLanguage());

        userDTO.setCity(userDO.getCity());

        userDTO.setProvince(userDO.getProvince());

        userDTO.setCountry(userDO.getCountry());

        userDTO.setAvatarUrl(userDO.getAvatarUrl());

        userDTO.setId(userDO.getId());

        userDTO.setToken(userDO.getToken());

        return userDTO;
    }
}
