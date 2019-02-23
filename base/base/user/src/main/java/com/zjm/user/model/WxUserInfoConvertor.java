package com.zjm.user.model;

/**
 * @author:小M
 * @date:2019/2/21 1:13 AM
 */
public class WxUserInfoConvertor {

    public static WxUserInfoDTO do2DTO(WxUserInfoDO wxUserInfoDO) {
        WxUserInfoDTO wxUserInfoDTO = new WxUserInfoDTO();

        wxUserInfoDTO.setOpenId(wxUserInfoDO.getOpenId());

        wxUserInfoDTO.setNickName(wxUserInfoDO.getNickName());

        wxUserInfoDTO.setGender(wxUserInfoDO.getGender());

        wxUserInfoDTO.setLanguage(wxUserInfoDO.getLanguage());

        wxUserInfoDTO.setCity(wxUserInfoDO.getCity());

        wxUserInfoDTO.setProvince(wxUserInfoDO.getProvince());

        wxUserInfoDTO.setCountry(wxUserInfoDO.getCountry());

        wxUserInfoDTO.setAvatarUrl(wxUserInfoDO.getAvatarUrl());

        wxUserInfoDTO.setId(wxUserInfoDO.getId());

        wxUserInfoDTO.setToken(wxUserInfoDO.getToken());

        return wxUserInfoDTO;
    }
}
