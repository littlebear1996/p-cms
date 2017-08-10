package com.chinaredstar.cms.api.model;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 商场文章实体
 */
@ApiModel("商品文章实体")
public class ArticleMarket extends Article {
    private static final long serialVersionUID = 825849735913294978L;

    /**
     * 自增主键id，无实际意义
     */
    @ApiModelProperty("自增主键id")
    private Integer id;

    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;

    /**
     * 副标题
     */
    @ApiModelProperty("副标题")
    private String subTitle;

    /**
     * 分类标签
     */
    @ApiModelProperty("分类标签")
    private String categoryTags;

    /**
     * 封面图片url
     */
    @ApiModelProperty("封面图片url")
    private String coverImgUrl;

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
     * 审核状态，0：待审核，1：审核通过，2：审核驳回
     */
    @ApiModelProperty("审核状态，0：待审核，1：审核通过，2：审核驳回")
    private Integer checkStatus;

    /**
     * 创建者id
     */
    @ApiModelProperty("创建者id")
    private Integer creatorId;

    /**
     * 驳回原因
     */
    @ApiModelProperty("驳回原因")
    private String rejectCause;

    /**
     * 是否置顶，1：置顶，非置顶
     */
    @ApiModelProperty("是否置顶，1：置顶，非置顶")
    private Boolean isTop;

    /**
     * 是否推荐，1：推荐，0：非推荐
     */
    @ApiModelProperty("是否推荐，1：推荐，0：非推荐")
    private Boolean isRecommand;
    /**
     * 是否上线，1：上线，0：下线
     */
    @ApiModelProperty("是否上线，1：上线，0：下线")
    private Boolean isOnline;
    /**
     * 上线开始时间
     */
    @ApiModelProperty("上线开始时间")
    private Date startTime;
    /**
     * 下线时间
     */
    @ApiModelProperty("下线时间")
    private Date endTime;
    /**
     * 文章内容
     */
    @ApiModelProperty("文章内容")
    private String content;

    @ApiModelProperty("版本号，通过该字段可以区分不同版本文章的功能，新版APPverson为2")
    private Integer version;

    public Boolean getOnline() {
        return isOnline;
    }

    public void setOnline(Boolean online) {
        isOnline = online;
    }

    public Boolean getRecommand() {
        return isRecommand;
    }

    public void setRecommand(Boolean recommand) {
        isRecommand = recommand;
    }

    public Boolean getTop() {
        return isTop;
    }

    public void setTop(Boolean top) {
        isTop = top;
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

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getCategoryTags() {
        return categoryTags;
    }

    public void setCategoryTags(String categoryTags) {
        this.categoryTags = categoryTags;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
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

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getRejectCause() {
        return rejectCause;
    }

    public void setRejectCause(String rejectCause) {
        this.rejectCause = rejectCause;
    }

    public Boolean getIsTop() {
        return isTop;
    }

    public void setIsTop(Boolean isTop) {
        this.isTop = isTop;
    }

    public Boolean getIsRecommand() {
        return isRecommand;
    }

    public void setIsRecommand(Boolean isRecommand) {
        this.isRecommand = isRecommand;
    }

    public Boolean getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Boolean isOnline) {
        this.isOnline = isOnline;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}