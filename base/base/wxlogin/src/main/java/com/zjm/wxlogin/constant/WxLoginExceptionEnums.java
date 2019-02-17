package com.zjm.wxlogin.constant;

/**
 * 错误枚举
 * @author:小M
 * @date:2019/1/12 12:31 PM
 */
public enum WxLoginExceptionEnums {

    SYSTEM_ERROR("SYSTEM_ERROR","系统错误"),

    LOGIN_USER_CODE_EMPTY("LOGIN_USER_CODE_EMPTY" , "微信登录code为空"),
    LOGIN_ENCRYPTED_DATA_EMPTY("LOGIN_ENCRYPTED_DATA_EMPTY" , "微信登录encryptedData为空"),
    LOGIN_IV_EMPTY("LOGIN_IV_EMPTY" , "微信登录iv为空"),
    LOGIN_REQUIRED_LOGIN_ERROR("LOGIN_REQUIRE_LOGIN_ERROR" , "微信请求登录失败"),
    LOGIN_REQUIRED_LOGIN_SESSION_NULL("LOGIN_REQUIRED_LOGIN_SESSION_NULL" , "微信服务端返回的session_key为空"),

    ;

    private String code;
    private String msg;

    WxLoginExceptionEnums(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
