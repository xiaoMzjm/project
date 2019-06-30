package com.zjm.framework.springmvc.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Component
@WebFilter(urlPatterns = "/*",filterName = "loginFilter")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("login filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("login filter do filter");
        filterChain.doFilter(servletRequest , servletResponse);
    }

    @Override
    public void destroy() {

    }
}
