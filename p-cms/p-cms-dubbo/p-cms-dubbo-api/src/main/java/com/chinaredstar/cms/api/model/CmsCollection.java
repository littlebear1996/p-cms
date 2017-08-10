package com.chinaredstar.cms.api.model;

import java.util.Date;

/**
 * CMS合辑信息实体
 */
public class CmsCollection extends BaseModel{
    private static final long serialVersionUID = -3130309455464295853L;

    /**  类型：3 家装，1 商品，4 房产,2 商场 */
    /** 类型 1 : 商品  */
    public static final int TYPE_PRODUCT = 1;

    /** 类型 2 : 商场  */
    public static final int TYPE_MARKET = 2;

    /** 类型 3 : 家装  */
    public static final int TYPE_HOME_DECORATION = 3;

    /** 类型 4 : 房产  */
    public static final int TYPE_HOUSE_PROPERTY = 4;

    /**子类型: 家装公司31，设计师32，方案33；学校合辑41，小区合辑42，楼盘合辑43；品牌合辑21，导购员合辑22，商场外链23，商场文章24， */
    /** 子类型: 21 品牌合辑 */
    public static final int SUBTYPE_MARKET_BRAND = 21;

    /** 子类型: 22 导购员合辑 */
    public static final int SUBTYPE_MARKET_SALES = 22;

    /** 子类型: 23 商场外链 */
    public static final int SUBTYPE_MARKET_OUTLINK = 23;

    /** 子类型: 24 商场文章 */
    public static final int SUBTYPE_MARKET_ARTICLE = 24;

    /**子类型: 32 设计师 */
    public static final int SUBTYPE_HOME_DECORATION_DESIGNER = 32;

    /**子类型: 33 方案 */
    public static final int SUBTYPE_HOME_DECORATION_CASE = 33;

    /** 子类型: 41 学校合辑 */
    public static final int SUBTYPE_HOUSE_PROPERTY_SCHOOL = 41;

    /** 子类型: 42 小区合辑 */
    public static final int SUBTYPE_HOUSE_PROPERTY_COMMUNITY = 42;

    /** 子类型: 43 楼盘合辑 */
    public static final int SUBTYPE_HOUSE_PROPERTY_HOUSES = 43;

    /** PK */
    private Integer id;

    /** 类型ID */
    private Long typeId;

    /** 子类型 */
    private Integer subType;

    /** 标题 */
    private String title;

    /** 描述 */
    private String desc;

    /** 封面图URL */
    private String coverUrl;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date updateTime;

    /** 创建者 */
    private String creator;

    /** 创建者名称 */
    private String creatorName;

    /** 修改者 */
    private String updator;

    /** 修改者名称 */
    private String updatorName;

    /** 审核状态，0：待审核，1：审核通过，2：审核驳回 */
    private Integer checkStatus;

    /** 审核驳回原因 */
    private String rejectCause;

    /** 开始时间 */
    private Date startTime;

    /** 结束时间 */
    private Date endTime;

    /** 排序号 */
    private Integer sortNo;

    /** 是否置顶 */
    private Boolean isTop;

    /** 是否推荐 */
    private Boolean isRecommend;

    /** 是否上线，1：上线，0：下线 */
    private Integer isOnline;

    /** 上线时间 */
    private Date onlineTime;

    /** 浏览量 */
    private Integer viewCount;

    /** 点赞量 */
    private Integer agreeCount;

    /** 商场id */
    private String marketId;

    /** 商场名字 */
    private String marketName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Integer getSubType() {
        return subType;
    }

    public void setSubType(Integer subType) {
        this.subType = subType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
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

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public String getUpdatorName() {
        return updatorName;
    }

    public void setUpdatorName(String updatorName) {
        this.updatorName = updatorName;
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

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Boolean getIsTop() {
        return isTop;
    }

    public void setIsTop(Boolean isTop) {
        this.isTop = isTop;
    }

    public Boolean getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Boolean isRecommend) {
        this.isRecommend = isRecommend;
    }

    public Integer getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Integer isOnline) {
        this.isOnline = isOnline;
    }

    public Date getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Date onlineTime) {
        this.onlineTime = onlineTime;
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

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }
}