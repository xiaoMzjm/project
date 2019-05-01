package com.zjm.biz.biz.modle.enums;

import lombok.Data;

/**
 * @author:小M
 * @date:2019/4/27 2:00 AM
 */
public enum CataLogTypeEnum {
    INTERFACE(1 , "面试题目录");
    ;

    public static CataLogTypeEnum getByType(Integer type){
        for(CataLogTypeEnum e : CataLogTypeEnum.values()) {
            if(e.getType().equals(type)) {
                return e;
            }
        }
        return null;
    }


    private Integer type;
    private String desc;

    CataLogTypeEnum(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
