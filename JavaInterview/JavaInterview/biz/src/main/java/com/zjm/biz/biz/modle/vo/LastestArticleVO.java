package com.zjm.biz.biz.modle.vo;

import com.zjm.biz.biz.modle.dto.CataLogDTO;
import lombok.Data;

/**
 * @author:小M
 * @date:2019/5/11 6:22 PM
 */
@Data
public class LastestArticleVO {

    private final static String PREFIX_URL = "/pages/article/article?code=";

    private String title;

    private String url;

    private String code;

    public static LastestArticleVO of(CataLogDTO cataLogDTO) {
        if(cataLogDTO == null) {
            return null;
        }
        LastestArticleVO lastestArticleVO = new LastestArticleVO();
        lastestArticleVO.setTitle("最新文章：" + cataLogDTO.getCode());
        lastestArticleVO.setUrl(PREFIX_URL + cataLogDTO.getCode());
        lastestArticleVO.setCode(cataLogDTO.getCode());
        return lastestArticleVO;
    }
}
