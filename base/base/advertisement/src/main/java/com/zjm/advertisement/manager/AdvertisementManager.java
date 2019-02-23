package com.zjm.advertisement.manager;

import java.util.List;

import com.zjm.advertisement.model.AdvertisementDO;
import com.zjm.advertisement.repository.AdvertisementRepository;
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
public class AdvertisementManager {

    @Autowired
    private AdvertisementRepository advertisementRepository;

    public void save(AdvertisementDO advertisementDO) throws Exception{
        advertisementDO.setId(UUIDUtil.get());
        advertisementDO = advertisementRepository.save(advertisementDO);
    }


    public List<AdvertisementDO> findByBizKeyAndType(String bizType , String type) throws Exception{

        AdvertisementDO advertisementDO = new AdvertisementDO();
        advertisementDO.setBizKey(bizType);
        advertisementDO.setType(type);

        Example<AdvertisementDO> example = Example.of(advertisementDO);

        List<AdvertisementDO> result = advertisementRepository.findAll(example);

        return result;
    }

}
