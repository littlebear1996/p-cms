package com.chinaredstar.cms.api.component;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 分页实体
 */
public class Page implements Serializable {

    private static final int DEFAULT_PAGE_SIZE = 10;

    @ApiModelProperty("页数,从1开始")
    private int pageNo;

    @ApiModelProperty("每页显示多少条")
    private int pageSize = DEFAULT_PAGE_SIZE;

    public Page() {

    }

    public Page(int pageNo) {
        this.pageNo = pageNo;
    }

    public Page(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public int getStart() {
        if (pageNo < 1) {
            pageNo = 1;
        }
        return (pageNo - 1) * pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize <= 0 ? DEFAULT_PAGE_SIZE : (pageSize > 100 ? 100 : pageSize);
    }
}
