package com.zjm.wxlogin.filter;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import com.zjm.wxlogin.anno.NotCheckToken;
import com.zjm.wxlogin.constant.WxLoginResult;
import com.zjm.wxlogin.manager.WxUserInfoManager;
import com.zjm.wxlogin.model.data.WxUserInfoDO;
import com.zjm.wxlogin.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author:小M
 * @date:2019/1/12 12:40 PM
 */
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private WxUserInfoManager wxUserInfoManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {

        this.setUtf8(response);

        String uri = request.getRequestURI().toString();
        System.out.println("uri:"+uri);

        // 如果不是映射到方法里，直接通过
        if(!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();

        NotCheckToken notCheckToken = method.getAnnotation(NotCheckToken.class);
        if(notCheckToken != null) {
            return true;
        }

        String token = request.getParameter("token");
        if(StringUtils.isEmpty(token)) {
            token = request.getHeader("token");
        }
        if(StringUtils.isEmpty(token)) {
            Cookie[] cookies = request.getCookies();
            if(cookies != null) {
                for(Cookie cookie : cookies) {
                    if(cookie.getName().equals("token")) {
                        token = cookie.getValue();
                    }
                }
            }
        }
        if(StringUtils.isEmpty(token)) {
            WxLoginResult wxLoginResult = WxLoginResult.error("TOKEN_NULL","TOKEN_NULL");
            response.getWriter().write(JSON.toJSONString(wxLoginResult));
            return false;
        }
        WxUserInfoDO wxUserInfoDO = wxUserInfoManager.findByToken(token);
        if(wxUserInfoDO == null) {
            WxLoginResult wxLoginResult = WxLoginResult.error("TOKEN_ERROR","TOKEN_NULL");
            response.getWriter().write(JSON.toJSONString(wxLoginResult));
            return false;
        }

        if(DateUtil.addDays(new Date(), -1).getTime() > wxUserInfoDO.getGmtModified().getTime()) {
            WxLoginResult wxLoginResult = WxLoginResult.error("TOKEN_EXPIRE","TOKEN_NULL");
            response.getWriter().write(JSON.toJSONString(wxLoginResult));
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                    @Nullable ModelAndView modelAndView) throws Exception {

        this.setUtf8(response);
    }

    private void setUtf8(HttpServletResponse response){
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        response.setContentType("text/html;charset=utf-8");
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
    }
}
