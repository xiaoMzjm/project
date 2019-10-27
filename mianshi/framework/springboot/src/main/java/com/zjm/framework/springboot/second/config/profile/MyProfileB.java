package com.zjm.framework.springboot.second.config.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * 当springboot启动时，profile存在字符串online时，加载该bean
 */
@Profile("online")
@Component
public class MyProfileB implements MyProfileInterface {
    @Override
    public String helloWorld() {
        return "MyProfileB";
    }
}
