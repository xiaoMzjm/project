package com.zjm.framework.springboot.second.config.enable;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyEnableConfiguration {

    @Bean
    public String helloWorld(){
        return "Hello world bean";
    }
}
