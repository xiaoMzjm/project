package com.zjm.user.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author:小M
 * @date:2019/1/14 12:18 AM
 */
@Configuration
@ComponentScan("com.zjm.user")
@EnableJpaRepositories("com.zjm.user")
@EntityScan("com.zjm.user")
@EnableTransactionManagement
public class UserAutoConfiguration {

}
