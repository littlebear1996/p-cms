package com.chinaredstar.cms.api.model;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 广告实体
 */
@ApiModel("广告实体")
public class Advertisement extends BaseModel{

    private static final long serialVersionUID = -7494011537048440878L;

    /** 主App首页首位 */
    public  static final String MAIN_APP_INDEX = "mainAppIndex_first";

    /** 主App首页品牌墙 */
    public static final String MAIN_APP_INDEX_BRAND = "mainAppIndex_brandList";

    /** 家装首页首位 */
    public static final String HOME_DECO_INDEX_FIRST = "homeDecoIndex_first";

    /** 商场首页首位 */
    public static final String MARKET_INDEX_FIRST = "marketIndex_first";

    /** 商场首页促销位 */
    public static final String MARKET_INDEX_SALE = "marketIndex_sale";

    /** 房产首页首位 */
    public static final String HOUSE_INDEX_FIRST = "houseIndex_first";

    /** 自增主键id */
    @ApiModelProperty("自增主键id")
    private Integer id;

    /** 标题 */
    @ApiModelProperty("广告标题")
    private String title;

    /** 描述 */
    @ApiModelProperty("广告描述")
    private String description;

    /** 创建者id */
    @ApiModelProperty("创建者id")
    private Integer creatorId;

    /** 创建时间 */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /** 上线时间 */
    @ApiModelProperty("上线时间")
    private Date startTime;

    /** 下线时间 */
    @ApiModelProperty("下线时间")
    private Date endTime;

    /** 作用页面外键，关联cms_advertisement_position表 */
    @ApiModelProperty("作用页面外键，关联cms_advertisement_position表")
    private Integer positionId;

    /** 横划顺位 */
    @ApiModelProperty("广告横划顺位")
    private Integer sequence;

    /** 图片url */
    @ApiModelProperty("广告图片url")
    private String imgUrl;

    /** 审核状态，0：待审核，1：审核通过，2：审核驳回 */
    @ApiModelProperty("审核状态，0：待审核，1：审核通过，2：审核驳回")
    private Integer checkStatus;

    /** 驳回原因 */
    @ApiModelProperty("驳回原因")
    private String rejectCause;

    /** 是否上线，1：上线，0：下线 */
    @ApiModelProperty("是否上线，1：上线，0：下线")
    private Boolean isOnline;

    /** 浏览次数，来源：外部系统 */
    @ApiModelProperty("浏览次数，来源：外部系统")
    private Integer viewCount;

    /** 点赞数，来源：外部系统 */
    @ApiModelProperty("点赞数，来源：外部系统")
    private Integer agreeCount;

    /** 广告链接url */
    @ApiModelProperty("广告跳转链接url")
    private String linkUrl;

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
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

    public Boolean getOnline() {
        return isOnline;
    }

    public void setOnline(Boolean online) {
        isOnline = online;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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
        this.rejectCause = rejectCause ;
    }

    public Boolean getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Boolean isOnline) {
        this.isOnline = isOnline;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getAgreeCount() {
        return agreeCount;
    }

    public void setAgreeCount(Integer agreeCount) {
        this.agreeCount = agreeCount;
    }
}