package com.zjm.user.common;

/**
 * @author:小M
 * @date:2020/2/9 9:14 PM
 */
public class Constant {


    public enum ErrorCode {

        TOKEN_NULL("TOKEN_NULL"),
        TOKEN_ERROR("TOKEN_ERROR"),
        TOKEN_EXPIRE("TOKEN_ERROR"),

        ;

        ErrorCode(String code) {
            this.code = code;
        }

        private String code;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

}
