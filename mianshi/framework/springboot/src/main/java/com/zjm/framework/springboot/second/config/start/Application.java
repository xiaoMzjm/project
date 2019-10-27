package com.zjm.framework.springboot.second.config.start;

import com.zjm.framework.springboot.second.config.condition.ConditionalOnMyCondition;
import com.zjm.framework.springboot.second.config.enable.EnableMyConfifuration;
import com.zjm.framework.springboot.second.config.profile.MyProfileInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;


@SpringBootApplication
/**
 * 首先，由于MyEnableConfiguration跟Application不在一个包路径下， 所以默认不会扫描到
 * 那么我们现在要通过Enable的方式，去引入MyEnableConfiguration，检验方式为HelloWorld这个bean加入到IOC容器中
 */
@EnableMyConfifuration
@ComponentScan("com.zjm.framework.springboot.second.config.profile")
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(Application.class).
                profiles("online").
                run(args);

    }

    /**
     * 由于设置了profiles=online，所以只会加载，而且在MyProfileB上方加入了@Profile("online")，所以会加载MyProfileB
     */
    @Autowired
    MyProfileInterface myProfileInterface;
    @PostConstruct
    public void init(){
        System.out.println(myProfileInterface.helloWorld());
    }


    /**
     * localhost:8888/actuator/beans
     * @return
     */
    @Bean
    @ConditionalOnMyCondition(value = "vip")
    public String conditionBean(){
        return "conditionBean";
    }

    /**
     * localhost:8888/actuator/beans
     * @return
     */
    @Bean
    @ConditionalOnMyCondition(value = "notvip")
    public String conditionBean2(){
        return "conditionBean";
    }
}
