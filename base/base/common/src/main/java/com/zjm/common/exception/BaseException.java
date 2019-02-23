package com.zjm.common.exception;

/**
 * 错误类
 * @author:小M
 * @date:2019/1/12 12:25 PM
 */
public class BaseException extends Exception{

    private String errorCode;

    public BaseException(String msg) {
        super(msg);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
