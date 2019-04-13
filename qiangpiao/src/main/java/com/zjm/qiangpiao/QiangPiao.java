package com.zjm.qiangpiao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author:小M
 * @date:2019/3/16 10:17 PM
 */
public class QiangPiao {


    /**
     *  对以上两个方法进行封装。
     * @return
     */
    public static String getTodayTemperatureInfo() throws Exception{
        // 调用第一个方法，获取html字符串
        //String html = HttpUtil.getHtmlFromUrl("https://www.nike.com/cn/launch/");
        // 调用第二个方法，过滤掉无用的信息
        String result = HtmlParse.parse("https://www.nike.com/cn/launch/");

        return result;
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) throws Exception{
        String info = getTodayTemperatureInfo();
    }
}
