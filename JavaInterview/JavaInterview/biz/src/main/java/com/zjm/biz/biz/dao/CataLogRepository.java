package com.zjm.biz.biz.dao;

import java.util.List;

import com.zjm.biz.biz.modle.datao.CataLogDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author:小M
 * @date:2019/4/27 1:31 AM
 */
@Repository
public interface CataLogRepository extends JpaRepository<CataLogDO, String> {

    @Query(value = "select * from catalog where code like CONCAT('%',:code,'%') and deleted != 1 and type = :type order by type asc limit 10", nativeQuery = true)
    List<CataLogDO> findCodesByTypeAndCodeLike(@Param("type") String type , @Param("code") String code);
}
