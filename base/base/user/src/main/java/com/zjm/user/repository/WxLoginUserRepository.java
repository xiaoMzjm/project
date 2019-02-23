package com.zjm.user.repository;

import com.zjm.user.model.WxUserInfoDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author:小M
 * @date:2019/1/13 10:58 PM
 */
@Repository
public interface WxLoginUserRepository extends JpaRepository<WxUserInfoDO,String> {
}