package com.zjm.user.common;

/**
 * @author:小M
 * @date:2020/2/9 9:14 PM
 */
public class Constant {


    public enum ErrorCode {

        // token
        TOKEN_NULL("TOKEN_NULL", "Token为空"),
        TOKEN_ERROR("TOKEN_ERROR", "TOKEN错误"),
        TOKEN_EXPIRE("TOKEN_EXPIRE", "TOKEN过期"),

        // 微信登录
        LOGIN_USER_CODE_EMPTY("LOGIN_USER_CODE_EMPTY" , "微信登录code为空"),
        LOGIN_ENCRYPTED_DATA_EMPTY("LOGIN_ENCRYPTED_DATA_EMPTY" , "微信登录encryptedData为空"),
        LOGIN_IV_EMPTY("LOGIN_IV_EMPTY" , "微信登录iv为空"),
        LOGIN_REQUIRED_LOGIN_ERROR("LOGIN_REQUIRE_LOGIN_ERROR" , "微信请求登录失败"),
        LOGIN_REQUIRED_LOGIN_SESSION_NULL("LOGIN_REQUIRED_LOGIN_SESSION_NULL" , "微信服务端返回的session_key为空"),

        // 注册
        REGISTER_ACCOUNT_NULL("ACCOUNT_NULL" , "账号为空"),
        REGISTER_PASSWORD_NULL("PASSWORD_NULL" , "密码为空"),
        REGISTER_REPEAT("REGISTER_REPEAT","账户已被注册"),

        // 用户
        CAN_NOT_FIND_USER_BY_ID("CAN_NOT_FIND_USER_BY_ID" , "通过id无法查询到用户")
        ;

        ErrorCode(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        private String code;
        private String desc;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

}
