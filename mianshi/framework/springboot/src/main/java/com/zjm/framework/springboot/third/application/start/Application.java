package com.zjm.framework.springboot.third.application.start;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class , args);
    }

    /**
     * 启动springboot方式一
     */
    @SpringBootApplication
    public static class Application1{
        public static void main(String[] args) {
            ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(Application1.class , args);
            Application1 application1 = configurableApplicationContext.getBean(Application1.class);

        }
    }

    /**
     * 启动springboot方式二
     */
    @SpringBootApplication
    static class Application2{
        public static void main(String[] args) {
            SpringApplication springApplication = new SpringApplication(Application2.class);
            springApplication.setBannerMode(Banner.Mode.CONSOLE);
            springApplication.setWebApplicationType(WebApplicationType.NONE);
            springApplication.setAdditionalProfiles("online");
            springApplication.setHeadless(true);
            springApplication.run(args);
        }
    }

    /**
     * 启动springboot方式三
     */
    @SpringBootApplication
    static class Application3{
        public static void main(String[] args) {
            new SpringApplicationBuilder(Application3.class).bannerMode(Banner.Mode.CONSOLE)
                    .web(WebApplicationType.NONE)
                    .profiles("online")
                    .headless(true)
                    .run(args);
        }
    }
}
