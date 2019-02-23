package com.zjm.suggest.repository;

import com.zjm.suggest.model.SuggestDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author:小M
 * @date:2019/2/20 11:53 PM
 */
@Component
public interface SuggestRespository extends JpaRepository<SuggestDO,String> {
}
