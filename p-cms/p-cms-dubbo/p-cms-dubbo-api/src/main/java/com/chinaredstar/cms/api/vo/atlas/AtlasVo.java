package com.chinaredstar.cms.api.vo.atlas;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by sunny on 16-8-19.
 */
public class AtlasVo implements Serializable {
    private static final long serialVersionUID = 1068432412831588856L;
    /**
     * 图集详情记录数
     */
    @ApiModelProperty("图集详情记录数")
    private Integer count;
    /**
     * 自增主键id
     */
    @ApiModelProperty("自增主键id")
    private Integer id;

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
    private String categoryTags;

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

    @ApiModelProperty("设计师openId")
    private String openId;
    /**
     * 方案id
     */
    @ApiModelProperty("方案id")
    private String caseId;

    /**
     * 点赞数
     */
    @ApiModelProperty("点赞数")
    private Integer agreeCount;
    /**
     * 查看次数
     */
    @ApiModelProperty("查看次数")
    private Integer viewCount;

    @ApiModelProperty("对象类型")
    private Integer objType;

    @ApiModelProperty("对象值")
    private String objValue;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("对象唯一标示")
    private String objUuid;

    public String getObjUuid() {
        return objUuid;
    }

    public void setObjUuid(String objUuid) {
        this.objUuid = objUuid;
    }

    private List<AtlasDetailVo> atlasDetails;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryTags() {
        return categoryTags;
    }

    public void setCategoryTags(String categoryTags) {
        this.categoryTags = categoryTags;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }

    public String getDesignerId() {
        return designerId;
    }

    public void setDesignerId(String designerId) {
        this.designerId = designerId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<AtlasDetailVo> getAtlasDetails() {
        return atlasDetails;
    }

    public void setAtlasDetails(List<AtlasDetailVo> atlasDetails) {
        this.atlasDetails = atlasDetails;
    }
}
