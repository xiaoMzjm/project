package com.zjm.framework.springboot.third.application.runListener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class MyRunListener implements SpringApplicationRunListener {

    /**
     * 源码：org.springframework.boot.SpringApplication#createSpringFactoriesInstances(java.lang.Class, java.lang.Class[], java.lang.ClassLoader, java.lang.Object[], java.util.Set)
     * 在初始化 SpringApplicationRunListener 的实例时，入参为这两个参数。
     * @param springApplication
     * @param strs
     */
    public MyRunListener(SpringApplication springApplication , String[] strs){

    }


    @Override
    public void starting() {
        System.out.println("===MyRunListener===starting");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        System.out.println("===MyRunListener===environmentPrepared");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        System.out.println("===MyRunListener===contextPrepared");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        System.out.println("===MyRunListener===contextLoaded");
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        System.out.println("===MyRunListener===started");
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        System.out.println("===MyRunListener===running");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        System.out.println("===MyRunListener===");
    }
}
