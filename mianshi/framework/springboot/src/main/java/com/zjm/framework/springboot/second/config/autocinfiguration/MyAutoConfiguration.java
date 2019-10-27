package com.zjm.framework.springboot.second.config.autocinfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyAutoConfiguration {

    @Bean
    public String myConfigurationBean(){
        return "hello world";
    }
}
