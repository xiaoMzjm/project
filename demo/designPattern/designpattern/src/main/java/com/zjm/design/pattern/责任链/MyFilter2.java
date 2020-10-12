package com.zjm.design.pattern.ÔğÈÎÁ´;

/**
 * @author:Ğ¡M
 * @date:2019/1/26 2:15 AM
 */
public class MyFilter2 extends Filter {

    void doFilter(Request rq, Response rp , FilterChain fc) {
        System.out.println("MyFilter2 before");
        fc.doFilter(rq,rp);
        System.out.println("MyFilter2 after");
    }
}
