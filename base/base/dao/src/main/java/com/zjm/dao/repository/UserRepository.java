package com.zjm.dao.repository;

import com.zjm.dao.model.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author:小M
 * @date:2018/11/4 下午10:21
 */
@Repository
public interface UserRepository extends JpaRepository<UserDO,Long> {
}
