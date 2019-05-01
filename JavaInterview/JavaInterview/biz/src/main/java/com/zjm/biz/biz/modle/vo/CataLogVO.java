package com.zjm.biz.biz.modle.vo;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.zjm.biz.biz.modle.datao.CataLogDO;
import com.zjm.biz.biz.modle.dto.CataLogDTO;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;

/**
 * @author:小M
 * @date:2019/4/29 11:03 PM
 */
@Data
public class CataLogVO {

    private Long id;

    private String gmtCreate;

    private String gmtModified;

    private Integer deleted;

    private String features;

    private Integer version;

    private String type;

    private String code;

    private String cataLog;

    private Integer pv;

    public static CataLogVO ofDtO(CataLogDTO cataLogDTO){

        if(cataLogDTO == null) {
            return null;
        }
        DateFormat dateFormat = DateFormat.getDateInstance();

        CataLogVO cataLogVO = new CataLogVO();
        cataLogVO.setId(cataLogDTO.getId());
        cataLogVO.setGmtCreate(dateFormat.format(cataLogDTO.getGmtCreate()));
        cataLogVO.setGmtModified(dateFormat.format(cataLogDTO.getGmtModified()));
        cataLogVO.setDeleted(cataLogDTO.getDeleted());
        cataLogVO.setFeatures(cataLogDTO.getFeatures());
        cataLogVO.setType(cataLogDTO.getType());
        cataLogVO.setCataLog(cataLogDTO.getCataLog());
        cataLogVO.setVersion(cataLogDTO.getVersion());
        cataLogVO.setPv(cataLogDTO.getPv());
        cataLogVO.setCode(cataLogDTO.getCode());
        return cataLogVO;
    }

    public static List<CataLogVO> ofDtO(List<CataLogDTO> cataLogDTOList){
        if(CollectionUtils.isEmpty(cataLogDTOList)) {
            return null;
        }
        return cataLogDTOList.stream().map(CataLogVO::ofDtO).collect(Collectors.toList());
    }
}
