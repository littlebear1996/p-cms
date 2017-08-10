package com.chinaredstar.cms.api.vo.atlas;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by sunny on 16-8-17.
 */
public class AtlasQueryVo implements Serializable {
    private static final long serialVersionUID = -2471488290380977039L;

    @ApiModelProperty("图集分类,1:家装,2:商品,3房产,4商场")
    private Integer type;

    @ApiModelProperty("图集子分类:101:家装图集，201：家居美图，202：极致单品")
    private Integer subType;

    @ApiModelProperty("当前时间")
    private Date currentTime;

    @ApiModelProperty("是否置顶")
    private Boolean isTop;

    @ApiModelProperty("是否上线")
    private Boolean isOnline;

    @ApiModelProperty("是否推荐")
    private Boolean isRecommand;

    @ApiModelProperty("第几页")
    private Integer pageNo;

    @ApiModelProperty("每页记录数")
    private Integer pageSize;

    private Integer pageIndex;

    //排序字段,便于通用
    private String orderBy;

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Integer getPageNum() {
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
        if(pageSize != null && pageNo != null) {
            pageIndex = (pageNo - 1) * pageSize;
        }
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

    public Boolean getTop() {
        return isTop;
    }

    public void setTop(Boolean top) {
        isTop = top;
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSubType() {
        return subType;
    }

    public void setSubType(Integer subType) {
        this.subType = subType;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

}
