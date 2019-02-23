package com.zjm.suggest.model;

import java.util.List;

/**
 * @author:小M
 * @date:2019/2/21 12:10 AM
 */
public class SuggestQueryResult {

    private Integer totalPage;

    private Long totalData;

    private List<SuggestDTO> suggestDTOList;

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Long getTotalData() {
        return totalData;
    }

    public void setTotalData(Long totalData) {
        this.totalData = totalData;
    }

    public List<SuggestDTO> getSuggestDTOList() {
        return suggestDTOList;
    }

    public void setSuggestDTOList(List<SuggestDTO> suggestDTOList) {
        this.suggestDTOList = suggestDTOList;
    }
}
