package com.zjm.suggest.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author:小M
 * @date:2019/2/18 11:13 PM
 */
@Configuration
@ComponentScan("com.zjm.suggest")
@EnableJpaRepositories("com.zjm.suggest")
@EntityScan("com.zjm.suggest")
@EnableTransactionManagement
public class SuggestAutoConfig {

}

