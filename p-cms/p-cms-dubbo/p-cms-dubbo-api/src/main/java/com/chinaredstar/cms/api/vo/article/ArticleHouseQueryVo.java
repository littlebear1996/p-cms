package com.chinaredstar.cms.api.vo.article;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by sunny on 16-8-24.
 */
public class ArticleHouseQueryVo implements Serializable {
    private static final long serialVersionUID = 5448572703058522872L;

    @ApiModelProperty("当前时间")
    private Date currentTime;

    @ApiModelProperty("是否上线")
    private Boolean isOnline;

    @ApiModelProperty("是否置顶")
    private Boolean isTop;

    @ApiModelProperty("是否推荐")
    private Boolean isRecommand;

    @ApiModelProperty("分类标签")
    private String categoryTag;

    private Boolean orderByStartTime;

    public Boolean getOrderByStartTime() {
        return orderByStartTime;
    }

    public void setOrderByStartTime(Boolean orderByStartTime) {
        this.orderByStartTime = orderByStartTime;
    }

    @ApiModelProperty("第几页")
    private Integer pageNo;

    @ApiModelProperty("每页记录数")
    private Integer pageSize;

    @ApiModelProperty("从第几条开始")
    private Integer pageIndex;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
        if (pageNo != null && pageSize != null) {
            pageIndex = (pageNo - 1) * pageSize;
        }
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

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    public Boolean getOnline() {
        return isOnline;
    }

    public void setOnline(Boolean online) {
        isOnline = online;
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

    public String getCategoryTag() {
        return categoryTag;
    }

    public void setCategoryTag(String categoryTag) {
        this.categoryTag = categoryTag;
    }

}
