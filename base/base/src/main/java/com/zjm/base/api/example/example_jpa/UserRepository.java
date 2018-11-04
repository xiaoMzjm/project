package com.zjm.base.api.example.example_jpa;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author:黑绝
 * @date:2018/11/4 下午10:21
 */
public interface UserRepository extends JpaRepository<UserDO,Long> {


}
