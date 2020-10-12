package com.zjm.design.pattern.责任链;

import java.util.*;

/**
 * @author:小M
 * @date:2019/1/26 2:11 AM
 */
public class FilterChain {

    // 节点集合
    private List<Filter> filters = new ArrayList<Filter>();

    // 当前执行到第几个filter
    Integer index = 0 ;

    // 增加filter
    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    // 执行下一个filler
    public void doFilter(Request rq, Response rp){
        if(index == filters.size()) {
            return;
        }
        Filter filter = filters.get(index++);
        filter.doFilter(rq , rp , this);
    }
}
