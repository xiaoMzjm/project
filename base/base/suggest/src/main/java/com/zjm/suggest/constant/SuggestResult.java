package com.zjm.suggest.constant;

/**
 * @author:小M
 * @date:2018/4/29 22:10
 */
public class SuggestResult<T> {

    private Boolean isSuccess;

    private T data;

    private String errorMsg;

    private String errorCode;

    public static <T> SuggestResult<T> success(T t){
        SuggestResult result = new SuggestResult();
        result.setSuccess(true);
        result.setData(t);
        return result;
    }

    public static <T> SuggestResult<T> error(String errorCode , String errorMsg) {
        SuggestResult result = new SuggestResult();
        result.setSuccess(false);
        result.setErrorCode(errorCode);
        result.setErrorMsg(errorMsg);
        return result;
    }


    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
