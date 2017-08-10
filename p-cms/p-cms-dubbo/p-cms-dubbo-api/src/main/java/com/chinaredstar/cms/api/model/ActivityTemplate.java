package com.chinaredstar.cms.api.model;

import java.io.Serializable;
import java.util.Date;

public class ActivityTemplate implements Serializable {

    private static final long serialVersionUID = 1135945804192025306L;
    private Integer id;

    private Date startTime;

    private String activityName;

    private Integer activityType;

    private Integer status;

    private Date endTime;

    private String activityRange;

    private Integer marketId;

    private Integer promotionActivityId;

    private Date createDate;

    private String creater;

    private Integer isDelete;

    private String promotionActivityName;

    private String layouts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
    }

    public Integer getActivityType() {
        return activityType;
    }

    public void setActivityType(Integer activityType) {
        this.activityType = activityType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getActivityRange() {
        return activityRange;
    }

    public void setActivityRange(String activityRange) {
        this.activityRange = activityRange == null ? null : activityRange.trim();
    }

    public Integer getMarketId() {
        return marketId;
    }

    public void setMarketId(Integer marketId) {
        this.marketId = marketId;
    }

    public Integer getPromotionActivityId() {
        return promotionActivityId;
    }

    public void setPromotionActivityId(Integer promotionActivityId) {
        this.promotionActivityId = promotionActivityId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getPromotionActivityName() {
        return promotionActivityName;
    }

    public void setPromotionActivityName(String promotionActivityName) {
        this.promotionActivityName = promotionActivityName == null ? null : promotionActivityName.trim();
    }

    public String getLayouts() {
        return layouts;
    }

    public void setLayouts(String layouts) {
        this.layouts = layouts == null ? null : layouts.trim();
    }
}