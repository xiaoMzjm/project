package com.zjm.qiangpiao;

import java.util.Dictionary;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author:小M
 * @date:2019/3/16 10:48 PM
 */
public class HtmlParse {

    public static String parse(String url) throws Exception{
        Document doc = Jsoup.connect(url).get();
        Element body = doc.body();
        Elements figures = body.getElementsByTag("figure");
        for(Element figure : figures) {
            Elements dataQas = figure.getElementsByAttributeValue("data-qa" , "product-card-link");
            for(Element dataQa : dataQas) {
                String attr = dataQa.attr("aria-label");
                System.out.println(attr);
            }
        }


        return null;
    }
}
