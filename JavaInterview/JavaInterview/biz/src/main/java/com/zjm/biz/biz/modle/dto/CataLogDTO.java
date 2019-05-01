package com.zjm.biz.biz.modle.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Id;
import javax.persistence.Version;

import com.google.common.collect.Lists;
import com.zjm.biz.biz.modle.BaseModle;
import com.zjm.biz.biz.modle.datao.CataLogDO;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;

/**
 * @author:小M
 * @date:2019/4/27 1:30 AM
 */
@Data
public class CataLogDTO {

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer deleted;

    private String features;

    private Integer version;

    private String type;

    private String code;

    private String cataLog;

    private Integer pv;

    public static CataLogDTO ofDO(CataLogDO cataLogDO){

        if(cataLogDO == null) {
            return null;
        }

        CataLogDTO cataLogDTO = new CataLogDTO();
        cataLogDTO.setId(cataLogDO.getId());
        cataLogDTO.setGmtCreate(cataLogDO.getGmtCreate());
        cataLogDTO.setGmtModified(cataLogDO.getGmtModified());
        cataLogDTO.setDeleted(cataLogDO.getDeleted());
        cataLogDTO.setFeatures(cataLogDO.getFeatures());
        cataLogDTO.setType(cataLogDO.getType());
        cataLogDTO.setCataLog(cataLogDO.getCataLog());
        cataLogDTO.setVersion(cataLogDO.getVersion());
        cataLogDTO.setPv(cataLogDO.getPv());
        cataLogDTO.setCode(cataLogDO.getCode());
        return cataLogDTO;
    }

    public static List<CataLogDTO> ofDO(List<CataLogDO> cataLogDOList){
        if(CollectionUtils.isEmpty(cataLogDOList)) {
            return null;
        }
        return cataLogDOList.stream().map(CataLogDTO::ofDO).collect(Collectors.toList());
    }
}
