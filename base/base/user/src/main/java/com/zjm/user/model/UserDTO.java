package com.zjm.user.model;

import javax.persistence.Column;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author:小M
 * @date:2018/4/30 0:39
 */
public class UserDTO {

    /**
     * 微信返回的json例子：
     {
         "openId":"ojbj60MA2r6vkD0Wld-tqsjForvA",
         "nickName":"小M张佳铭",
         "gender":1,
         "language":"zh_CN",
         "city":"",
         "province":"广东",
         "country":"中国",
         "avatarUrl":"https://wx.qlogo.cn/mmopen/vi_32/Q02yeejica3KekPsmOLMxKtUyAibC17fribEIhOynXhWLVujiceO3ic0p9tu1Y1reLO7tv0X7EzUw4ib8ddHSmNTaffw/132",
         "watermark":{
            "timestamp":1547392395,
            "appid":"wx37ebc9339ff09baa"
         }
     }
     */

    /**
     * 微信字段
     */
    private String openId;

    /**
     * 数据库字段
     */
    private Long id;

    private String token;

    private String nickName;

    private String gender;

    private String language;

    private String city;

    private String province;

    private String country;

    private String avatarUrl;

    @Column
    private String account;

    @Column
    private String password;


    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
