package com.zjm.advertisement.api;

/**
 * @author:小M
 * @date:2019/2/18 11:41 PM
 */

import java.util.List;

import com.alibaba.fastjson.JSON;

import com.zjm.advertisement.constant.Constants.AdBizKeyEnum;
import com.zjm.advertisement.constant.Constants.AdTypeEnum;
import com.zjm.advertisement.model.AdvertisementDO;
import com.zjm.advertisement.service.AdvertisementService;
import com.zjm.common.constant.Result;
import com.zjm.user.filter.TokenAnnotation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 广告接口
 * @author:小M
 * @date:2019/1/13 10:41 PM
 */
@Api(description = "广告接口")
@Controller
@ResponseBody
@RequestMapping("/advertisement")
@RestController
public class AdvertisementController {

    @Autowired
    private AdvertisementService advertisementService;

    /**
     * 获取健身小程序首页广告
     * @return
     */
    @ApiOperation(value = "获取健身小程序首页广告" ,  notes="获取健身小程序首页广告")
    @RequestMapping(value = "/findFitHomePageAd" , method = RequestMethod.POST , produces = {"application/json;charset=UTF-8"})
    @TokenAnnotation
    public String findFitHomePageAd(){
        Result<List<AdvertisementDO>> result = advertisementService.findByBizKeyAndType(AdBizKeyEnum.FIT.getCode(),
            AdTypeEnum.HOME_PAGE.getCode());
        return JSON.toJSONString(result);
    }
}
