package com.zjm.project.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.alibaba.fastjson.JSON;

import com.zjm.project.manager.JedisManager;
import com.zjm.project.monitor.ClientInfoMonitor;
import com.zjm.project.monitor.GlobalClientInfoMonitor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:黑绝
 * @date:2017/12/8 21:17
 */
@Controller
public class DefaultController {

    @RequestMapping("/index/{host}/{port}")
    public String index(@PathVariable String host , @PathVariable Integer port){
        if(StringUtils.isEmpty(host) || port == null) {
            return "error";
        }
        try {
            JedisManager.initPool(host , port);
        }catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
        return "index";
    }

    @RequestMapping(value= "/getClientInfoList" , produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String getClientInfoList() throws UnsupportedEncodingException {
        List<String> clientListHtml = ClientInfoMonitor.getClientListHtml();
        return JSON.toJSONString(clientListHtml);
    }

    @RequestMapping(value= "/getGlobalClientInfo" , produces = "application/json; charset=UTF-8")
    @ResponseBody
    public String getGlobalClientInfo() throws UnsupportedEncodingException {
        return JSON.toJSONString(GlobalClientInfoMonitor.getGlobalClientInfoHtml());
    }
}
