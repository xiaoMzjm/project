package com.zjm.design.pattern.ÔğÈÎÁ´;

/**
 * @author:Ğ¡M
 * @date:2019/1/26 2:15 AM
 */
public class MainTest {

    public static void main(String[] args) {
        FilterChain fc = new FilterChain();
        MyFilter1 f1 = new MyFilter1();
        MyFilter2 f2 = new MyFilter2();
        fc.addFilter(f1);
        fc.addFilter(f2);
        Request rq = new Request();
        Response rp = new Response();
        fc.doFilter(rq,rp);
    }
}
