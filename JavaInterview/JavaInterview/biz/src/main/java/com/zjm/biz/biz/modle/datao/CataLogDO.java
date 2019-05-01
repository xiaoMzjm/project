package com.zjm.biz.biz.modle.datao;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import com.zjm.biz.biz.modle.BaseModle;
import com.zjm.biz.biz.modle.dto.CataLogDTO;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;

/**
 * @author:小M
 * @date:2019/4/27 1:25 AM
 */
@Data
@Entity
@Table(name = "catalog")
public class CataLogDO{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="gmt_create")
    private Date gmtCreate;

    @Column(name="gmt_modified")
    private Date gmtModified;

    @Column
    private Integer deleted;

    @Column
    private String features;

    @Version
    private Integer version;

    @Column
    private String type;

    @Column
    private String code;

    @Column(name="cata_log")
    private String cataLog;

    @Column
    private Integer pv;

    public static CataLogDO ofDTO(CataLogDTO cataLogDTO){
        if(cataLogDTO == null) {
            return null;
        }

        CataLogDO cataLogDO = new CataLogDO();
        cataLogDO.setId(cataLogDTO.getId());
        cataLogDO.setGmtCreate(cataLogDTO.getGmtCreate());
        cataLogDO.setGmtModified(cataLogDTO.getGmtModified());
        cataLogDO.setDeleted(cataLogDTO.getDeleted());
        cataLogDO.setFeatures(cataLogDTO.getFeatures());
        cataLogDO.setType(cataLogDTO.getType());
        cataLogDO.setCataLog(cataLogDTO.getCataLog());
        cataLogDO.setVersion(cataLogDTO.getVersion());
        cataLogDO.setPv(cataLogDTO.getPv());
        cataLogDO.setCode(cataLogDTO.getCode());
        return cataLogDO;
    }

}
