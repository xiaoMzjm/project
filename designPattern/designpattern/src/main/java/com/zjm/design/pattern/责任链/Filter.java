package com.zjm.design.pattern.������;

/**
 * @author:СM
 * @date:2019/1/26 2:12 AM
 */
public abstract class Filter {

    abstract void doFilter(Request rq, Response rp , FilterChain filterChain);


}
