package com.chinaredstar.cms.api.model;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * CMS外链实体
 */
public class Outlinks extends BaseModel {
    private static final long serialVersionUID = 4346561533516260122L;

    /**
     * PK
     */
    @ApiModelProperty("PK")
    private Integer id;

    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String description;

    /**
     * 链接
     */
    @ApiModelProperty("链接")
    private String outlink;

    /**
     * 上线时间
     */
    @ApiModelProperty("上线时间")
    private Date onlineTime;

    /**
     * 下线时间
     */
    @ApiModelProperty("下线时间")
    private Date offlineTime;

    /**
     * 类型：家装 3,商品  1，房产  4,商场 2
     */
    @ApiModelProperty("类型：家装 3,商品  1，房产  4,商场 2")
    private Integer type;

    /**
     * 商场id
     */
    @ApiModelProperty("商场id")
    private String marketid;

    /**
     * 图片url
     */
    @ApiModelProperty("图片url")
    private String image;

    /**
     * 是否上线，1，上线，0下载
     */
    @ApiModelProperty("是否上线，1，上线，0下载")
    private Boolean isOnline;

    /**
     * 审核状态，1,审核通过，0，待审核，2审核被拒
     */
    @ApiModelProperty("审核状态，1,审核通过，0，待审核，2审核被拒")
    private Integer checkStatus;

    /**
     * 驳回原因
     */
    @ApiModelProperty("驳回原因")
    private String rejectCause;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Date updateTime;

    /**
     * 创建者
     */
    @ApiModelProperty("创建者")
    private String creator;

    @ApiModelProperty("标签")
    private String tags;

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

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
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOutlink() {
        return outlink;
    }

    public void setOutlink(String outlink) {
        this.outlink = outlink;
    }

    public Date getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Date onlineTime) {
        this.onlineTime = onlineTime;
    }

    public Date getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(Date offlineTime) {
        this.offlineTime = offlineTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMarketid() {
        return marketid;
    }

    public void setMarketid(String marketid) {
        this.marketid = marketid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getOnline() {
        return isOnline;
    }

    public void setOnline(Boolean online) {
        isOnline = online;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getRejectCause() {
        return rejectCause;
    }

    public void setRejectCause(String rejectCause) {
        this.rejectCause = rejectCause;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "Outlinks{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", outlink='" + outlink + '\'' +
                ", onlineTime=" + onlineTime +
                ", offlineTime=" + offlineTime +
                ", type=" + type +
                ", marketid='" + marketid + '\'' +
                ", image='" + image + '\'' +
                ", isOnline=" + isOnline +
                ", checkStatus=" + checkStatus +
                ", rejectCause='" + rejectCause + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", creator='" + creator + '\'' +
                ", tags='" + tags + '\'' +
                '}';
    }
}