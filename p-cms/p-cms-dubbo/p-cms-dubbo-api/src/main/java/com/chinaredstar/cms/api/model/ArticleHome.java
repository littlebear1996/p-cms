package com.chinaredstar.cms.api.model;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 家装文章实体
 */
@ApiModel("家装文章实体")
public class ArticleHome extends Article {

    private static final long serialVersionUID = 5072549069707466260L;

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
     * 设计师联系方式
     */
    @ApiModelProperty("设计师联系方式")
    private String designerTel;

    /**
     * 业主姓名
     */
    @ApiModelProperty("业主姓名")
    private String ownerName;

    /**
     * 业主性别
     */
    @ApiModelProperty("业主性别")
    private Boolean ownerGender;

    /**
     * 业主职业
     */
    @ApiModelProperty("业主职业")
    private Integer ownerProfession;

    /**
     * 业主爱好
     */
    @ApiModelProperty("业主爱好")
    private Integer ownerHobby;

    /**
     * 案例风格
     */
    @ApiModelProperty("案例风格")
    private Integer caseHouseStyle;

    /**
     * 案例户型
     */
    @ApiModelProperty("案例户型")
    private Integer caseHouseType;

    /**
     * 案例面积
     */
    @ApiModelProperty("案例面积")
    private Integer caseHouseArea;

    /**
     * 案例费用
     */
    @ApiModelProperty("案例费用")
    private Integer caseHouseExpense;

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
    @ApiModelProperty("是否置顶，true：置顶，false:非置顶")
    private Boolean isTop;

    /**
     * 是否推荐，1：推荐，0：非推荐
     */
    @ApiModelProperty("是否推荐，true：推荐，false：非推荐")
    private Boolean isRecommand;

    /**
     * 是否上线，1：上线，0：下线
     */
    @ApiModelProperty("是否上线，true：上线，false：下线")
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

    public String getDesignerTel() {
        return designerTel;
    }

    public void setDesignerTel(String designerTel) {
        this.designerTel = designerTel;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Boolean getOwnerGender() {
        return ownerGender;
    }

    public void setOwnerGender(Boolean ownerGender) {
        this.ownerGender = ownerGender;
    }

    public Integer getOwnerProfession() {
        return ownerProfession;
    }

    public void setOwnerProfession(Integer ownerProfession) {
        this.ownerProfession = ownerProfession;
    }

    public Integer getOwnerHobby() {
        return ownerHobby;
    }

    public void setOwnerHobby(Integer ownerHobby) {
        this.ownerHobby = ownerHobby;
    }

    public Integer getCaseHouseStyle() {
        return caseHouseStyle;
    }

    public void setCaseHouseStyle(Integer caseHouseStyle) {
        this.caseHouseStyle = caseHouseStyle;
    }

    public Integer getCaseHouseType() {
        return caseHouseType;
    }

    public void setCaseHouseType(Integer caseHouseType) {
        this.caseHouseType = caseHouseType;
    }

    public Integer getCaseHouseArea() {
        return caseHouseArea;
    }

    public void setCaseHouseArea(Integer caseHouseArea) {
        this.caseHouseArea = caseHouseArea;
    }

    public Integer getCaseHouseExpense() {
        return caseHouseExpense;
    }

    public void setCaseHouseExpense(Integer caseHouseExpense) {
        this.caseHouseExpense = caseHouseExpense;
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
}