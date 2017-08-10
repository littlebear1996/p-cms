package com.chinaredstar.cms.api.vo.encyclopedia;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by sunny on 16-8-23.
 */
public class EncyclopediaQueryVo implements Serializable {
    private static final long serialVersionUID = 6746382335875982740L;

    /**
     * 是否置顶
     */
    private Boolean isTop;

    /**
     * 是否推荐
     */
    private Boolean isRecommand;

    /**
     * 是否上线
     */
    private Boolean isOnline;

    /**
     * 当前时间
     */
    private Date currentTime;

    /**
     * 百科分类:1家装,2商品,3房产,4商场
     */
    private Integer type;

    /**
     * 页码
     */
    private Integer pageNo;

    /**
     * 每页记录数
     */
    private Integer pageSize;

    /**
     * 起始数据索引
     */
    private Integer pageIndex;

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
        if (pageSize != null && pageNo != null) {
            pageIndex = (pageNo - 1) * pageSize;
        }
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
        if (pageNo != null && pageSize != null) {
            pageIndex = (pageNo - 1) * pageSize;
        }
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    public Boolean getTop() {
        return isTop;
    }

    public void setTop(Boolean top) {
        isTop = top;
    }

    public Boolean getRecommand() {
        return isRecommand;
    }

    public void setRecommand(Boolean recommand) {
        isRecommand = recommand;
    }

    public Boolean getOnline() {
        return isOnline;
    }

    public void setOnline(Boolean online) {
        isOnline = online;
    }

}
