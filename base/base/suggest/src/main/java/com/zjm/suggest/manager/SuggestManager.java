package com.zjm.suggest.manager;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.zjm.common.util.UUIDUtil;
import com.zjm.suggest.constant.Constants.SuggestStatus;
import com.zjm.suggest.model.SuggestConvertor;
import com.zjm.suggest.model.SuggestDO;
import com.zjm.suggest.model.SuggestDTO;
import com.zjm.suggest.model.SuggestQO;
import com.zjm.suggest.model.SuggestQueryResult;
import com.zjm.suggest.model.SuggestSaveDTO;
import com.zjm.suggest.repository.SuggestRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

/**
 * @author:小M
 * @date:2019/2/20 11:54 PM
 */
@Component
public class SuggestManager {

    @Autowired
    private SuggestRespository suggestRespository;

    public SuggestDTO save(SuggestSaveDTO suggestSaveDTO) {
        SuggestDO suggestDO = new SuggestDO();

        suggestDO.setBizKey(suggestSaveDTO.getBizKey());
        suggestDO.setType(suggestSaveDTO.getType());
        suggestDO.setSuggest(suggestSaveDTO.getSuggest());
        suggestDO.setUserId(suggestSaveDTO.getUserId());

        String id = UUIDUtil.get();
        suggestDO.setId(id);
        Date now = new Date();
        suggestDO.setGmtCreate(now);
        suggestDO.setGmtModified(now);
        suggestDO.setStatus(SuggestStatus.CREATE.getStatus());
        suggestDO.setDeleted(0);

        suggestDO = suggestRespository.save(suggestDO);
        SuggestDTO suggestDTO = SuggestConvertor.do2Dto(suggestDO);
        return suggestDTO;
    }

    public SuggestQueryResult findByQO(SuggestQO suggestQO) {

        SuggestDO suggestDO = new SuggestDO();
        suggestDO.setId(suggestQO.getId());
        suggestDO.setBizKey(suggestQO.getBizKey());
        suggestDO.setType(suggestQO.getType());
        suggestDO.setUserId(suggestQO.getUserId());
        Example<SuggestDO> example = Example.of(suggestDO);
        Pageable pageable = PageRequest.of(suggestQO.getPageIndex(),suggestQO.getPageSize());
        Page<SuggestDO> page = suggestRespository.findAll(example,pageable);
        List<SuggestDO> suggestDOList = page.getContent();
        Integer totalPage = page.getTotalPages();
        Long totalData = page.getTotalElements();
        List<SuggestDTO> suggestDTOList = SuggestConvertor.do2Dto(suggestDOList);

        SuggestQueryResult result = new SuggestQueryResult();
        result.setTotalData(totalData);
        result.setTotalPage(totalPage);
        result.setSuggestDTOList(suggestDTOList);
        return result;
    }
}
