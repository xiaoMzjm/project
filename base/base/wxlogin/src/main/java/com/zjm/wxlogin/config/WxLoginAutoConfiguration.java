package com.zjm.wxlogin.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author:小M
 * @date:2019/1/14 12:18 AM
 */
@Configuration
@ComponentScan("com.zjm.wxlogin")
@EnableJpaRepositories("com.zjm.wxlogin")
@EntityScan("com.zjm.wxlogin")
@EnableTransactionManagement
public class WxLoginAutoConfiguration{

}
