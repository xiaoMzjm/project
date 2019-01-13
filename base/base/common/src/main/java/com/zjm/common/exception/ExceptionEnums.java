package com.zjm.common.exception;

/**
 * 错误枚举
 * @author:小M
 * @date:2019/1/12 12:31 PM
 */
public enum ExceptionEnums {

    SYSTEM_ERROR("SYSTEM_ERROR","系统错误"),

    ;

    private String code;
    private String msg;

    ExceptionEnums(String code, String msg) {
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
