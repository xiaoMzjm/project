package com.zjm.suggest.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author:小M
 * @date:2019/2/20 11:51 PM
 */
public class SuggestDTO extends Features{

    private String id;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer deleted;

    private Integer status;

    private String bizKey;

    private String type;

    private String suggest;

    private String userId;

    private String reply;

    private List<SuggestDetailDTO> suggestDetailDTOList;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public List<SuggestDetailDTO> getSuggestDetailDTOList() {
        return suggestDetailDTOList;
    }

    public void setSuggestDetailDTOList(List<SuggestDetailDTO> suggestDetailDTOList) {
        this.suggestDetailDTOList = suggestDetailDTOList;
    }
}
