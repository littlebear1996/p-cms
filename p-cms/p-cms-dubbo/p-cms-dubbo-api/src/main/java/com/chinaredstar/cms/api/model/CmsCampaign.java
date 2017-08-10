package com.chinaredstar.cms.api.model;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 活动信息
 */
@ApiModel(value = "活动信息",description = "活动信息")
public class CmsCampaign extends BaseModel{


    private static final long serialVersionUID = 2705386286456108709L;
    /**
     * 活动id,主键,自增长
     */
    @ApiModelProperty(value = "活动id,主键,自增长")
    private Integer id;

    /**
     * 活动标题
     */
    @ApiModelProperty(value = "活动标题")
    private String title;

    /**
     * 活动开始时间
     */
    @ApiModelProperty(value = "活动开始时间")
    private Date startTime;

    /**
     * 活动结束时间
     */
    @ApiModelProperty(value = "活动结束时间")
    private Date endTime;

    /**
     * 活动创建时间
     */
    @ApiModelProperty(value = "活动创建时间")
    private Date createTime;

    /**
     * 活动创建人id
     */
    private String creatorId;

    /**
     * 活动创建人
     */
    private String creator;

    /**
     * 活动描述
     */
    @ApiModelProperty(value = "活动描述")
    private String desc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId == null ? null : creatorId.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }
}