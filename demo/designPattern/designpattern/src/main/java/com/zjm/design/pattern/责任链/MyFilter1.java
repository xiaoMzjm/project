package com.zjm.design.pattern.ÔğÈÎÁ´;

/**
 * @author:Ğ¡M
 * @date:2019/1/26 2:14 AM
 */
public class MyFilter1 extends Filter {

    void doFilter(Request rq, Response rp , FilterChain filterChain) {
        System.out.println("MyFilter1 before");
        filterChain.doFilter(rq,rp);
        System.out.println("MyFilter1 after");
    }
}
