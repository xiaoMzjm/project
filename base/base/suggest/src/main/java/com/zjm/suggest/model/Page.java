package com.zjm.suggest.model;

/**
 * @author:小M
 * @date:2019/2/21 12:02 AM
 */
public class Page {

    private Integer pageIndex = 1;

    private Integer pageSize = 20;

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
