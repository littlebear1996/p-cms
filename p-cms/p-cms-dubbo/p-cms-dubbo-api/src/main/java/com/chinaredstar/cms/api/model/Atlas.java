package com.chinaredstar.cms.api.model;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * 图集记录实体
 */
public class Atlas extends BaseModel {
    public static final int SUBTYPE_HOME_DECORATION = 101;
    public static final int SUBTYPE_HOUSEHOLD = 201;
    public static final int SUBTYPE_SINGLEGOODS = 202;
    private static final long serialVersionUID = 7124311521929857301L;
    /**
     * 自增主键id
     */
    private Integer id;

    /**
     * 图集创建者ID
     */
    @ApiModelProperty("创建者id")
    private Long creatorId;

    /**
     * 图集创建者姓名
     */
    @ApiModelProperty("创建者姓名")
    private String creatorName;

    /**
     * 图集分类:1家装,2商品,3房产,4商场
     */
    @ApiModelProperty("图集分类:1家装,2商品,3房产,4商场")
    private Integer type;

    /**
     * 图集子分类:1家居美图2极致单品
     */
    @ApiModelProperty("图集子分类：101家装，201家具美图，202极致单品")
    private Integer subType;

    /**
     * 图集标题
     */
    @ApiModelProperty("图集标题")
    private String title;

    /**
     * 图集描述
     */
    @ApiModelProperty("图集描述")
    private String description;

    /**
     * 图集手动入力分类标签
     */
    @ApiModelProperty("图集分类标签")
    private String inputTags;

    /**
     * 图集标签
     */
    @ApiModelProperty("图集标签")
    private String tags;

    /**
     * 图集封面
     */
    @ApiModelProperty("图集封面图片url")
    private String coverImgUrl;

    /**
     * 设计师id
     */
    @ApiModelProperty("设计师id")
    private String designerId;
    /**
     * 方案id
     */
    @ApiModelProperty("方案id")
    private String caseId;
    /**
     * 审核状态:0创建,1审核成功,2审核失败
     */
    private Integer checkStatus;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 审核者ID
     */
    private Long reviewerId;
    /**
     * 审核者姓名
     */
    private String reviewerName;
    /**
     * 修改时间
     */
    private Date editTime;
    /**
     * 驳回理由
     */
    private String rejectCause;
    /**
     * 置顶标志位
     */
    private Boolean isTop;
    /**
     * 推荐标志位
     */
    private Boolean isRecommand;
    /**
     * 上下线标志位
     */
    private Boolean isOnline;
    /**
     * 上线时间
     */
    private Date startTime;
    /**
     * 下线时间
     */
    private Date endTime;
    /**
     * 点赞数
     */
    private Integer agreeCount;
    /**
     * 查看次数
     */
    private Integer viewCount;
    /**
     * 修改者ID
     */
    private Long editerId;
    /**
     * 修改者姓名
     */
    private String editerName;

    /**
     * 设计师openid
     */
    private String openId;

    /**
     * 图集明细内容，包含图集中的图片url
     */
    private List<AtlasDetail> atlasDetails;



    @ApiModelProperty("对象类型")
    private Integer objType;

    @ApiModelProperty("对象值")
    private String objValue;

    @ApiModelProperty("对象唯一标示")
    private String objUuid;

    public String getObjUuid() {
        return objUuid;
    }

    public void setObjUuid(String objUuid) {
        this.objUuid = objUuid;
    }

    public Integer getObjType() {
        return objType;
    }

    public void setObjType(Integer objType) {
        this.objType = objType;
    }

    public String getObjValue() {
        return objValue;
    }

    public void setObjValue(String objValue) {
        this.objValue = objValue;
    }

    public String getDesignerId() {
        return designerId;
    }

    public void setDesignerId(String designerId) {
        this.designerId = designerId;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<AtlasDetail> getAtlasDetails() {
        return atlasDetails;
    }

    public void setAtlasDetails(List<AtlasDetail> atlasDetails) {
        this.atlasDetails = atlasDetails;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInputTags() {
        return inputTags;
    }

    public void setInputTags(String inputTags) {
        this.inputTags = inputTags;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(Long reviewerId) {
        this.reviewerId = reviewerId;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
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

    public Integer getAgreeCount() {
        return agreeCount;
    }

    public void setAgreeCount(Integer agreeCount) {
        this.agreeCount = agreeCount;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Long getEditerId() {
        return editerId;
    }

    public void setEditerId(Long editerId) {
        this.editerId = editerId;
    }

    public String getEditerName() {
        return editerName;
    }

    public void setEditerName(String editerName) {
        this.editerName = editerName;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}