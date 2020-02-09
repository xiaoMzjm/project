package com.zjm.advertisement.service;

import java.util.List;

import com.zjm.advertisement.manager.AdvertisementManager;
import com.zjm.advertisement.model.AdvertisementDO;
import com.zjm.advertisement.repository.AdvertisementRepository;
import com.zjm.common.constant.Result;
import com.zjm.common.exception.ExceptionEnums;
import com.zjm.common.util.UUIDUtil;
import com.zjm.common.util.VerifyUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

/**
 * @author:小M
 * @date:2019/2/18 11:18 PM
 */
@Component
public class AdvertisementService {

    @Autowired
    private AdvertisementManager advertisementManager;

    public Result save(AdvertisementDO advertisementDO){
        try {
            VerifyUtil.isNotNull(advertisementDO,ExceptionEnums.SAVE_AD_DO_IS_NULL.getCode(), ExceptionEnums.SAVE_AD_DO_IS_NULL.getMsg());
            VerifyUtil.isNotNull(StringUtils.isNotEmpty(advertisementDO.getBizKey()),ExceptionEnums.SAVE_AD_BIZ_KEY_IS_NULL.getCode(), ExceptionEnums.SAVE_AD_BIZ_KEY_IS_NULL.getMsg());
            VerifyUtil.isNotNull(StringUtils.isNotEmpty(advertisementDO.getType()),ExceptionEnums.SAVE_AD_TYPE_IS_NULL.getCode(), ExceptionEnums.SAVE_AD_TYPE_IS_NULL.getMsg());
            VerifyUtil.isNotNull(StringUtils.isNotEmpty(advertisementDO.getPicUrl()) || StringUtils.isNotEmpty(advertisementDO.getVideoUrl()) ,ExceptionEnums.SAVE_AD_PIC_OR_VIDEO_IS_NULL.getCode(), ExceptionEnums.SAVE_AD_PIC_OR_VIDEO_IS_NULL.getMsg());

            advertisementManager.save(advertisementDO);
            return Result.success(null);
        }catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }


    public Result<List<AdvertisementDO>> findByBizKeyAndType(String bizType , String type){

        try {
            VerifyUtil.isNotNull(StringUtils.isNotEmpty(bizType),ExceptionEnums.FIND_BIZ_KEY_IS_NULL.getCode(),ExceptionEnums.FIND_BIZ_KEY_IS_NULL.getMsg() );
            VerifyUtil.isNotNull(StringUtils.isNotEmpty(type),ExceptionEnums.FIND_TYPE_IS_NULL.getCode(), ExceptionEnums.FIND_TYPE_IS_NULL.getMsg());

            List<AdvertisementDO> result = advertisementManager.findByBizKeyAndType(bizType , type);

            return Result.success(result);
        }catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
