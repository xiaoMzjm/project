package com.zjm.suggest.model;

import java.util.List;

/**
 * @author:小M
 * @date:2019/2/21 1:16 AM
 */
public class SuggestVO {

    private String id;

    private String type;

    private String suggest;

    private List<String> picUrlList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<String> getPicUrlList() {
        return picUrlList;
    }

    public void setPicUrlList(List<String> picUrlList) {
        this.picUrlList = picUrlList;
    }
}
