package com.zjm.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * @author:小M
 * @date:2019/1/12 12:40 PM
 */
@WebFilter(servletNames={"dispatcherServlet"})
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException {

        // 判断request的参数，如果权限错误则抛异常


        filterChain.doFilter(servletRequest, servletResponse);
    }
}
