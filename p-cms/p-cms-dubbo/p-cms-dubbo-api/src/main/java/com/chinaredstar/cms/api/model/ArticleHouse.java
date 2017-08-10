package com.chinaredstar.cms.api.model;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 房产文章实体
 */
@ApiModel("房产文章实体")
public class ArticleHouse extends Article {
    public static final String FAMOUS_SCHOOL = "名校探班";
    public static final String SCHOOL_DISTRICT = "学区攻略";
    public static final String HEADLINE = "资讯行情";
    private static final long serialVersionUID = 4355854521671930562L;
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
     * 标签
     */
    @ApiModelProperty("标签")
    private String tags;

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
     * 文章创建来源：1 b端，2 c端，3 e端
     */
    @ApiModelProperty("文章创建来源：1 b端，2 c端，3 e端")
    private Integer source;

    /**
     * 创建者id
     */
    @ApiModelProperty("创建者id")
    private Integer creatorId;

    /**
     * 创建者用户中心openId
     */
    @ApiModelProperty("创建者用户中心openId")
    private String creatorOpenId;
    /**
     * 创建者名称
     */
    @ApiModelProperty("创建者名称")
    private String creatorName;

    /**
     * 更新者id
     */
    private Integer updaterId;

    /**
     * 更新者用户中心openId
     */
    @ApiModelProperty("更新者用户中心openId")
    private String updaterOpenId;
    /**
     * 更新者名称
     */
    private String updaterName;
    /**
     * 驳回原因
     */
    @ApiModelProperty("驳回原因")
    private String rejectCause;

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

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Integer getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(Integer updaterId) {
        this.updaterId = updaterId;
    }

    public String getUpdaterName() {
        return updaterName;
    }

    public void setUpdaterName(String updaterName) {
        this.updaterName = updaterName;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getCreatorOpenId() {
        return creatorOpenId;
    }

    public void setCreatorOpenId(String creatorOpenId) {
        this.creatorOpenId = creatorOpenId;
    }

    public String getUpdaterOpenId() {
        return updaterOpenId;
    }

    public void setUpdaterOpenId(String updaterOpenId) {
        this.updaterOpenId = updaterOpenId;
    }
}