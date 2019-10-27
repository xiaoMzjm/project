package com.zjm.framework.springmvc.controller;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.io.File;
import java.io.FileInputStream;

@Controller
public class ThyContoller {

    @RequestMapping("thyhi")
    public String thyhi (Model model) {
        model.addAttribute("message" , "hello world");
        System.out.println("thyhi");
        return "thyhi";
    }

    public static class Test1{
        public static void main(String[] args) {

            SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();

            Context context = new Context();

            context.setVariable("message"  , "hello world");

            String html = "<p th:text='${message}'>msg</p>";

            String result = springTemplateEngine.process(html , context);

            System.out.println(result);
        }
    }

    public static class Test2 {
        public static void main(String[] args)throws Exception {

            ResourceLoader resourceLoader = new DefaultResourceLoader();

            Resource resource = resourceLoader.getResource("classpath:/templates/thymeleaf/thyhi.html");

            File file = resource.getFile();

            FileInputStream fileInputStream = new FileInputStream(file);

            ByteOutputStream byteOutputStream = new ByteOutputStream();

            IOUtils.copy(fileInputStream , byteOutputStream);

            fileInputStream.close();


            SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();

            Context context = new Context();

            context.setVariable("message"  , "hello world");

            String result = springTemplateEngine.process(byteOutputStream.toString() , context);

            System.out.println(result);

        }
    }


}
