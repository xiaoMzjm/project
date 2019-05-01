package com.zjm.biz.biz.modle;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Version;

import lombok.Data;
import org.aspectj.lang.annotation.DeclareAnnotation;

/**
 * @author:小M
 * @date:2019/4/27 1:28 AM
 */
@Data
public class BaseModle {

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer deleted;

    private String features;

    private Integer version;
}
