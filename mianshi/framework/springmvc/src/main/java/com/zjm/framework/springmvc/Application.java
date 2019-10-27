package com.zjm.framework.springmvc;

import com.zjm.framework.springmvc.interceptor.MyHandlerInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"com.zjm.framework.springmvc"})
@ServletComponentScan(basePackages="com.zjm.framework.springmvc.servlet") // 扫描传统的servlet
public class Application {



    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public MyHandlerInterceptor myHandlerInterceptor() {
        return new MyHandlerInterceptor();
    }

    /**
     * springmvc添加拦截器比较麻烦，需要继承DelegatingWebMvcConfiguration且重写addInterceptors方法
     * @param registry
     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(myHandlerInterceptor()).addPathPatterns("/**");
//        super.addInterceptors(registry);
//    }


}

