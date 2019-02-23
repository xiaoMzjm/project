package com.zjm.advertisement.repository;

import com.zjm.advertisement.model.AdvertisementDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author:小M
 * @date:2019/2/18 11:17 PM
 */
@Repository
public interface AdvertisementRepository extends JpaRepository<AdvertisementDO,String> {
}