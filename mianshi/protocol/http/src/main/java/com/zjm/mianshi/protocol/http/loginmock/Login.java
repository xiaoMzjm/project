package com.zjm.mianshi.protocol.http.loginmock;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

/**
 * @author:小M
 * @date:2019/3/17 12:57 AM
 */
public class Login {

    public static void main(String[] args) throws Exception {

        String userName = "+8613631474297";
        String password = "Aaa38324836";
        String loginUrl
            = "https://unite.nike.com/login?appVersion=556&experienceVersion=454&uxid=com.nike.commerce.snkrs"
            + ".web&locale=zh_CN&backendEnvironment=identity&browser=Google%20Inc"
            + ".&os=undefined&mobile=false&native=false&visit=5&visitor=c9520d25-54dc-44e8-8562-66125d17a6df";

        // 新建一个客户对象
        CloseableHttpClient client = HttpClients.createDefault();

        // 新建一个HttpPost请求的对象 --并将uri传给这个对象
        HttpPost httpPost = new HttpPost(loginUrl);

        // 使用NameValuePair  键值对  对参数进行打包
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("client_id", "PbCREuPr3iaFANEDjtiEzXooFl7mXGQ7"));
        nvps.add(new BasicNameValuePair("grant_type", "password"));
        nvps.add(new BasicNameValuePair("password", password));
        nvps.add(new BasicNameValuePair("username", userName));
        nvps.add(new BasicNameValuePair("ux_id", "com.nike.commerce.snkrs.web"));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps));

        // header
        httpPost.addHeader(":authority" , "unite.nike.com");
        httpPost.addHeader(":method","POST");
        httpPost.addHeader(":path","/login?appVersion=556&experienceVersion=454&uxid=com.nike.commerce.snkrs.web&locale=zh_CN&backendEnvironment=identity&browser=Google%20Inc.&os=undefined&mobile=false&native=false&visit=5&visitor=c9520d25-54dc-44e8-8562-66125d17a6df");
        httpPost.addHeader(":scheme","https");
        httpPost.addHeader("accept","*/*");
        httpPost.addHeader("accept-encoding","gzip, deflate, br");
        httpPost.addHeader("accept-language","zh-CN,zh;q=0.9");
        httpPost.addHeader("cache-control","no-cache");
        httpPost.addHeader("content-type","application/json");
        httpPost.addHeader("cookie" , "bm_sz=22CFADD7A953F4191B3DD2E5FB7FE011~YAAQDZZUaCIksi1pAQAAMuS+hgOQCRv"
            +
            "/sIe5HJn3pn3kqJ1g0aGmZ9u4qDWYh56YxRDKbpUynImAsvapv4KFCzAHTgscaqzgTXcYYLUXvA6Ei3godtCdj00dLS2TTGKaws3RHWDq0aZcWFwNUqIC3ffQyzF7mYqNSrXJskb+sJQoV92XEmzOQsaBV0JJaw==; AnalysisUserId=371b1614-a931-4dfb-a893-18e2fd98240c; anonymousId=UNTXa41d4338a5a5983e5e98592c2a721c96; siteCatalyst_sample=88; dreamcatcher_sample=75; neo_sample=57; NIKE_CART=b:c; guidU=9d259324-0ec4-4377-c788-58e272aad6de; neo.swimlane=76; _gcl_au=1.1.1714685001.1552743870; sq=3; NIKE_COMMERCE_COUNTRY=CN; NIKE_COMMERCE_LANG_LOCALE=zh_CN; nike_locale=cn/zh_cn; CONSUMERCHOICE=cn/zh_cn; bm_mi=97F18EDA2719445CB9097FC6AB118F8B~QJK0AfSvwg7E2xHP81NnJhhW9tnxYp/znSU6gAuSXGG99g99Xa2+BMx39YEPMSP6jh3xrq8DASA9zCyw7KjJ08jAb0jPupo4KRLWubdqgmWgW5ITIpEPtzwzUoFb7PMGfHmUxFYQi5PyZoRkNmABWLBQ8i9HZOTvgLe/HKHZLl8rA9P8ej6ISHiLQE1VihesJ69jOFGkTjj3pBceBH5LPzfrj5y7lf9MPCDlAmu79jmNyupytv+P/3Ckdt3BZ5EcBwrNwGUA08WItKRTaxUPQw==; utag_main=_st:1552756134662$ses_id:1552754844514%3Bexp-session; mm_wc_pmt=1; AMCVS_F0935E09512D2C270A490D4D%40AdobeOrg=1; cicIntercept=1; neo.experiments=%7B%22snkrs%22%3A%7B%7D%2C%22main%22%3A%7B%223333-interceptor-cn%22%3A%22a%22%7D%7D; ak_bmsc=6F9C3C9AFEBCC5121C0BECEFDBF7A8D17AE00A27753C00009A268D5CBB469311~pl9fEtczkJOHpROBHpTFSo8quClXxe7tATv7ljkybFuUHF/lnd7Qt0gTGvy+7U2d1rHt7J/i1zbHzw8Z8IMKOEn6ujXjH6v3WKJqBkosqn2g0yVtc7HkO2WLSm/SYkdHEUeeP2CqyyK2QQUcDQVfYOmw/e3sjzN8bnP+LnJKU0b7c5iUL5Xkd5/iXRiSXmRYpq9clheYQaVJiLugll/NWx3w3np4JlAJNG0ZbYbTek1je6FB2DjQks22Pt63iYHbzq; exp.swoosh.user=%7B%22granted%22%3A0%7D; RES_TRACKINGID=47625422963345725; RES_SESSIONID=72573532963345725; ResonanceSegment=1; s_sess=%20c51%3Dhorizontal%3B%20prevList2%3D%3B%20s_cc%3Dtrue%3B; ppd=error%7C404%20error; _gscu_207448657=52754341ernffl41; _gscbrs_207448657=1; _gscs_207448657=52754341bgw9np41|pv:1; ajs_user_id=null; ajs_group_id=null; ajs_anonymous_id=%22UNTXa41d4338a5a5983e5e98592c2a721c96%22; _smt_uid=5c8d26a6.218d6665; guidS=8b443e8a-94d8-40df-e641-63372c3b0292; AMCV_F0935E09512D2C270A490D4D%40AdobeOrg=793872103%7CMCIDTS%7C17972%7CMCMID%7C92001618735647682484434621244631355094%7CMCAAMLH-1553359204%7C11%7CMCAAMB-1553359204%7C6G1ynYcLPuiQxYZrsz_pkqfLG9yMXBpb2zX5dvJdYQJzPXImdj0y%7CMCAID%7CNONE%7CMCOPTOUT-1552754343.664%7CNONE%7CvVersion%7C3.4.0; s_cc=true; visitData={\"visit\":\"5\",\"visitor\":\"c9520d25-54dc-44e8-8562-66125d17a6df\"}; s_pers=%20s_dfa%3Dnikecomprod%7C1552756134921%3B%20c58%3Dno%2520value%7C1552756137114%3B%20c6%3Dno%2520value%7C1552757313313%3B%20c5%3Dno%2520value%7C1552757313317%3B; guidSTimestamp=1552754403645|1552755513441; RT=\"sl=1&ss=1552746885293&tt=4221&obo=0&sh=1552755516385%3D1%3A0%3A4221%2C1552754398884%3D1%3A0%3A36339&dm=nike.com&si=37f0446a-771c-40fe-bdc2-e134cd82a934&bcn=%2F%2F0dfab5ec.akstat.io%2F&ld=1552755516385\"; bm_sv=06050973554786F611BFE0105545D493~6Ttf0fr3V3q/JfMjtwhccIjQGcOFor9bjjNZJMR+5rnM53IhUA3eY2qyoNfOrT92UfevsxihkK2Pnvs4ttAEE5jL+qK96wF2xUgZeGXg8EkOUBnq+A9k7AbgXcSPwRsHc3VhykYRKyLR+awrk7uSbQ8U9jsUy4kIiFPqIu3yfHc=; _abck=203CC89482ECCA0F04FE3D1EAB328347~0~YAAQJwrgeqaO9k1pAQAACoNyhwGqx1z8xIeATvtmhRK6k5AJuFehHe33Y7PI15iH0pXQts31SY6VjZ6/+09QPEgQNHpTpF7Ys8XmIBstI5zegz8obYViL8hJh8TWc2XmXyKZzir379YJoRwsK+8pTc2JYzOdlG0QX+C1XjPg/gR/+l9TfrgfzGAJ5AnCLZPRXt2qfIKr5FU33chhLIdJk0hArUXs6lVPWUsXEjpCZStepXF08zBB7Y9jNRRkxIWiSoI5CkWOWIBVi1feX3ElnslJZanoWldwBE0OezXFtyi/JMXYd5uU~-1~-1~-1");

        // 访问
        RequestConfig defaultConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
        httpPost.setConfig(defaultConfig);
        CloseableHttpResponse response = client.execute(httpPost);

        System.out.println("Login form post: " + response.getStatusLine());
    }
}
