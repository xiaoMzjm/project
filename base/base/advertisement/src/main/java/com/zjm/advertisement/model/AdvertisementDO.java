package com.zjm.advertisement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author:小M
 * @date:2019/2/18 11:15 PM
 */
@Entity
@Table(name = "advertisement")
public class AdvertisementDO {

    @Id
    private String id;

    /**
     * 区分不同的业务
     */
    @Column
    private String bizKey;

    /**
     * 图片地址
     */
    @Column
    private String picUrl;

    /**
     * 视频地址
     */
    @Column
    private String videoUrl;

    /**
     * 广告类型
     */
    @Column
    private String type;



    public String getBizKey() {
        return bizKey;
    }

    public void setBizKey(String bizKey) {
        this.bizKey = bizKey;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
