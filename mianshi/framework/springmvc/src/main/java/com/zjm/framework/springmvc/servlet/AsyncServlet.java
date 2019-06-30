package com.zjm.framework.springmvc.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/springmvc/asyncServlet" ,
        asyncSupported = true // 开启异步支持
)
public class AsyncServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        AsyncContext asyncContext = req.startAsync();

        asyncContext.start(()->{
            try{
                resp.getWriter().println("AsyncServlet");
                asyncContext.complete();
            }catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

}
