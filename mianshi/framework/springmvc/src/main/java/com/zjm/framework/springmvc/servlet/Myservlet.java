package com.zjm.framework.springmvc.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 传统的servlet可以在springboot中运行的
 * 配合Applicaiton的注解：ServletComponentScan
 */
@WebServlet(urlPatterns="/springmvc/myservlet")
public class Myservlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("my servlet");
    }
}
