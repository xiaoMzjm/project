package com.zjm.biz.biz.manager;

import java.time.Year;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.zjm.biz.biz.dao.CataLogRepository;
import com.zjm.biz.biz.modle.datao.CataLogDO;
import com.zjm.biz.biz.modle.dto.CataLogDTO;
import com.zjm.biz.biz.modle.enums.CataLogTypeEnum;
import com.zjm.biz.common.CommonConstant.CataLogType;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * 文章服务
 * @author:小M
 * @date:2019/4/27 12:11 AM
 */
@Component
public class ArticleManager {

    @Autowired
    private CataLogRepository cataLogRepository;

    /**
     * 插入一行记录
     * @param type
     * @param code
     * @param cataLog
     */
    public void insertOrUpdateCataLog(String type , String code ,String cataLog) {
        CataLogDTO cataLogDTO = getCataLogByCode(code);
        CataLogDO cataLogDO = CataLogDO.ofDTO(cataLogDTO);

        // 存在则更新
        if(cataLogDO != null) {
            if(!Objects.equals(type , cataLogDO.getType())) {
                throw new RuntimeException("该code记录已存在，但是type不一致");
            }
            cataLogDO.setCataLog(cataLog);
            cataLogDO.setGmtModified(new Date());
        }
        // 否则新增
        else {
            cataLogDO = new CataLogDO();
            Date now = new Date();
            cataLogDO.setGmtCreate(now);
            cataLogDO.setGmtModified(now);
            cataLogDO.setDeleted(0);
            cataLogDO.setType(type);
            cataLogDO.setCode(code);
            cataLogDO.setCataLog(cataLog);
        }
        cataLogDO = cataLogRepository.save(cataLogDO);
    }

    /**
     * 根据code获取文章
     * @param code
     * @return
     */
    public CataLogDTO getCataLogByCode(String code) {
        CataLogDO cataLogDO = new CataLogDO();
        cataLogDO.setCode(code);
        cataLogDO.setDeleted(0);

        Example<CataLogDO> example = Example.of(cataLogDO);
        Optional<CataLogDO> optional = cataLogRepository.findOne(example);
        if(!optional.isPresent()) {
            return null;
        }
        cataLogDO = optional.get();
        if(cataLogDO == null) {
            return null;
        }
        cataLogDO.setPv(cataLogDO.getPv() == null ? 1 : cataLogDO.getPv() + 1);
        cataLogRepository.save(cataLogDO);
        return CataLogDTO.ofDO(cataLogDO);
    }

    /**
     * 获取最新的文章
     * @return
     */
    public CataLogDTO getLastestArticle(){
        CataLogDO cataLogDO = cataLogRepository.getLastestArticle();
        CataLogDTO cataLogDTO = CataLogDTO.ofDO(cataLogDO);
        return cataLogDTO;
    }

    /**
     * 根据文章标题模糊搜索
     * @param code
     * @return
     */
    public List<CataLogDTO> findArticleCodesByCodeLike(String code) {
        List<CataLogDO> result = cataLogRepository.findCodesByTypeAndCodeLike(CataLogType.ARTICLE , code);
        if(CollectionUtils.isEmpty(result)) {
            return null;
        }
        result.stream().forEach((item) -> item.setCataLog(null));
        List<CataLogDTO> cataLogDTOList = CataLogDTO.ofDO(result);
        return cataLogDTOList;
    }


}
