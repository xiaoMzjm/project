package com.zjm.advertisement.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author:小M
 * @date:2019/2/18 11:13 PM
 */
@Configuration
@ComponentScan("com.zjm.advertisement")
@EnableJpaRepositories("com.zjm.advertisement")
@EntityScan("com.zjm.advertisement")
@EnableTransactionManagement
public class AdvertisementAutoConfig {

}

