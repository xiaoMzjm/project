package com.zjm.user.filter;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import com.zjm.common.constant.Result;
import com.zjm.common.util.DateUtil;
import com.zjm.user.manager.WxUserInfoManager;
import com.zjm.user.model.WxUserInfoConvertor;
import com.zjm.user.model.WxUserInfoDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author:小M
 * @date:2019/1/12 12:40 PM
 */
public class TokenInterceptor implements HandlerInterceptor {

    @Value("${local:false}")
    private Boolean local;

    @Value("${local:true}")
    private Boolean enableWeiXinLoginFilter;

    @Autowired
    private WxUserInfoManager wxUserInfoManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {

        /**
         * 不开启拦截的，直接返回
         */
        if(!enableWeiXinLoginFilter) {
            return true;
        }

        this.setUtf8(response);

        String uri = request.getRequestURI().toString();
        System.out.println("uri:"+uri);

        // 如果不是映射到方法里，直接通过
        if(!(handler instanceof HandlerMethod)) {
            return true;
        }
        if(local) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();

        /**
         * 打了注解的才需要拦截
         */
        WeiXinLoginFilter weiXinLoginFilter = method.getAnnotation(WeiXinLoginFilter.class);
        if(weiXinLoginFilter == null) {
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
            Result wxLoginResult = Result.error("TOKEN_NULL","TOKEN_NULL");
            response.getWriter().write(JSON.toJSONString(wxLoginResult));
            return false;
        }
        WxUserInfoDO wxUserInfoDO = wxUserInfoManager.findByToken(token);
        if(wxUserInfoDO == null) {
            Result wxLoginResult = Result.error("TOKEN_ERROR","TOKEN_NULL");
            response.getWriter().write(JSON.toJSONString(wxLoginResult));
            return false;
        }

        if(DateUtil.addDays(new Date(), -1).getTime() > wxUserInfoDO.getGmtModified().getTime()) {
            Result wxLoginResult = Result.error("TOKEN_EXPIRE","TOKEN_NULL");
            response.getWriter().write(JSON.toJSONString(wxLoginResult));
            return false;
        }
        request.setAttribute("user" , WxUserInfoConvertor.do2DTO(wxUserInfoDO));

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
