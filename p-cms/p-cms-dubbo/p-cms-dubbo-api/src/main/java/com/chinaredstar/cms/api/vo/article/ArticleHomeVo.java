package com.chinaredstar.cms.api.vo.article;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 家装文章
 */
public class ArticleHomeVo extends ArticleVo {

    private static final long serialVersionUID = 138474151870625791L;

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
     * 业主职业描述
     */
    @ApiModelProperty("业主职业描述")
    private String ownerProfessionDesc;

    /**
     * 业主爱好
     */
    @ApiModelProperty("业主爱好")
    private Integer ownerHobby;

    /**
     * 业主爱好描述
     */
    @ApiModelProperty("业主爱好描述")
    private String ownerHobbyDesc;

    /**
     * 案例风格
     */
    @ApiModelProperty("案例风格")
    private Integer caseHouseStyle;

    /**
     * 案例风格描述
     */
    @ApiModelProperty("案例风格描述")
    private String caseHouseStyleDesc;

    /**
     * 案例户型
     */
    @ApiModelProperty("案例户型")
    private Integer caseHouseType;

    /**
     * 案例户型描述
     */
    @ApiModelProperty("案例户型描述")
    private String caseHouseTypeDesc;
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
     * 文章内容
     */
    @ApiModelProperty("文章内容")
    private String content;
    @ApiModelProperty("数据表名")
    private String tableName = "cms_article_home";
    @ApiModelProperty("创建时间")
    private Date createTime;
    @ApiModelProperty("创建者id")
    private Integer creatorId;
    @ApiModelProperty("标签,用逗号分隔")
    private String tags;
    @ApiModelProperty("文章类型，1：商品文章，2：商场文章，3：家装案例文章，4：房产文章")
    private Integer type;


    public String getCaseHouseTypeDesc() {
        return caseHouseTypeDesc;
    }

    public void setCaseHouseTypeDesc(String caseHouseTypeDesc) {
        this.caseHouseTypeDesc = caseHouseTypeDesc;
    }

    public String getCaseHouseStyleDesc() {
        return caseHouseStyleDesc;
    }

    public void setCaseHouseStyleDesc(String caseHouseStyleDesc) {
        this.caseHouseStyleDesc = caseHouseStyleDesc;
    }

    public String getOwnerHobbyDesc() {
        return ownerHobbyDesc;
    }

    public void setOwnerHobbyDesc(String ownerHobbyDesc) {
        this.ownerHobbyDesc = ownerHobbyDesc;
    }

    public String getOwnerProfessionDesc() {
        return ownerProfessionDesc;
    }

    public void setOwnerProfessionDesc(String ownerProfessionDesc) {
        this.ownerProfessionDesc = ownerProfessionDesc;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
