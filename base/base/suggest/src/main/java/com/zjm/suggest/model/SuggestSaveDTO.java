package com.zjm.suggest.model;

/**
 * @author:小M
 * @date:2019/2/21 12:49 AM
 */
public class SuggestSaveDTO {

    private String bizKey;

    private String type;

    private String suggest;

    private Long userId;

    public String getBizKey() {
        return bizKey;
    }

    public void setBizKey(String bizKey) {
        this.bizKey = bizKey;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
