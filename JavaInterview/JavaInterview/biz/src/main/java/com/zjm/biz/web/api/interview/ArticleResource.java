package com.zjm.biz.web.api.interview;

import java.util.List;

import com.alibaba.fastjson.JSON;

import com.google.common.collect.Lists;
import com.zjm.biz.biz.manager.ArticleManager;
import com.zjm.biz.biz.modle.dto.CataLogDTO;
import com.zjm.biz.biz.modle.enums.CataLogTypeEnum;
import com.zjm.biz.biz.modle.vo.CataLogVO;
import com.zjm.biz.biz.modle.vo.LastestArticleVO;
import com.zjm.biz.web.filter.ApiFilter;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:小M
 * @date:2019/4/27 12:09 AM
 */
@RestController
@RequestMapping("article")
public class ArticleResource {

    @Autowired
    ArticleManager articleManager;

    @ApiFilter
    @ApiOperation(value = "新增/修改目录" , notes = "新增/修改目录")
    @RequestMapping(value = "/catalog/insertOrUpdate" , method = RequestMethod.POST)
    public Object insertOrUpdateCataLog(String type , String code , String catalog){
        articleManager.insertOrUpdateCataLog(type , code , catalog);
        return true;
    }

    @ApiFilter
    @ApiOperation(value = "获取目录" , notes = "获取目录")
    @RequestMapping(value = "/catalog/get/{code}" , method = RequestMethod.GET)
    public Object getCatalog(@PathVariable String code){
        CataLogDTO cataLogDTO = articleManager.getCataLogByCode(code) ;
        CataLogVO cataLogVO = CataLogVO.ofDtO(cataLogDTO);
        return cataLogVO;
    }

    @ApiFilter
    @ApiOperation(value = "获取文章" , notes = "获取文章")
    @RequestMapping(value = "/article/get" , method = RequestMethod.POST)
    public Object getArticle(String code){
        CataLogDTO cataLogDTO = articleManager.getCataLogByCode(code) ;
        CataLogVO cataLogVO = CataLogVO.ofDtO(cataLogDTO);
        return cataLogVO;
    }

    @ApiFilter
    @ApiOperation(value = "获取最新文章" , notes = "获取最新文章")
    @RequestMapping(value = "/article/lastest/get" , method = RequestMethod.POST)
    public Object getLastestArticle(){
        CataLogDTO cataLogDTO = articleManager.getLastestArticle() ;
        LastestArticleVO lastestArticleVO = LastestArticleVO.of(cataLogDTO);
        return Lists.newArrayList(lastestArticleVO);
    }

    @ApiFilter
    @ApiOperation(value = "搜索文章" , notes = "搜索文章")
    @RequestMapping(value = "/article/search" , method = RequestMethod.POST)
    public Object search(String code){
        List<CataLogDTO> cataLogDTOList = articleManager.findArticleCodesByCodeLike(code) ;
        List<CataLogVO> cataLogVOList = CataLogVO.ofDtO(cataLogDTOList);
        return cataLogVOList;
    }
}
