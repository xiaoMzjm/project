package com.zjm.suggest.model;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import java.util.*;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;

/**
 * @author:小M
 * @date:2019/2/21 12:26 AM
 */
public class SuggestConvertor {

    public static SuggestDTO do2Dto(SuggestDO suggestDO) {
        SuggestDTO suggestDTO = new SuggestDTO();
        suggestDTO.setId(suggestDO.getId());
        suggestDTO.setGmtCreate(suggestDO.getGmtCreate());
        suggestDTO.setGmtModified(suggestDO.getGmtModified());
        suggestDTO.setDeleted(suggestDO.getDeleted());
        if(StringUtils.isNotEmpty(suggestDO.getFeatures())) {
            Map<String,String> features = JSON.parseObject(suggestDO.getFeatures(),Map.class);
            suggestDTO.appendFeatures(features);
        }
        suggestDTO.setStatus(suggestDO.getStatus());
        suggestDTO.setBizKey(suggestDO.getBizKey());
        suggestDTO.setType(suggestDO.getType());
        suggestDTO.setSuggest(suggestDO.getSuggest());
        suggestDTO.setUserId(suggestDO.getUserId());
        suggestDTO.setReply(suggestDO.getReply());
        return suggestDTO;
    }

    public static List<SuggestDTO> do2Dto(List<SuggestDO> suggestDOS){
        List<SuggestDTO> result = suggestDOS.stream().map(item->{
            return do2Dto(item);
        }).collect(Collectors.toList());
        return result;
    }

    public static SuggestVO dto2vo(SuggestDTO suggestDTO){
        SuggestVO suggestVO = new SuggestVO();

        suggestVO.setId(suggestDTO.getId());

        suggestVO.setType(suggestDTO.getType());

        suggestVO.setSuggest(suggestDTO.getSuggest());

        List<String> picUrlList = Lists.newArrayList();
        if(CollectionUtils.isNotEmpty(suggestDTO.getSuggestDetailDTOList())) {
            picUrlList = suggestDTO.getSuggestDetailDTOList().stream().map(item->{
                return item.getPicUrl();
            }).collect(Collectors.toList());
        }

        suggestVO.setPicUrlList(picUrlList);

        return suggestVO;
    }

}
