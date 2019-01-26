package com.zjm.design.pattern.������;

import java.util.*;

/**
 * @author:СM
 * @date:2019/1/26 2:11 AM
 */
public class FilterChain {

    // �ڵ㼯��
    private List<Filter> filters = new ArrayList<Filter>();

    // ��ǰִ�е��ڼ���filter
    Integer index = 0 ;

    // ����filter
    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    // ִ����һ��filler
    public void doFilter(Request rq, Response rp){
        if(index == filters.size()) {
            return;
        }
        Filter filter = filters.get(index++);
        filter.doFilter(rq , rp , this);
    }
}
