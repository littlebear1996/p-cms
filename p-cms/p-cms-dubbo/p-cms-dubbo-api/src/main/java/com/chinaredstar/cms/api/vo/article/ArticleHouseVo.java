package com.chinaredstar.cms.api.vo.article;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by sunny on 16-8-24.
 */
public class ArticleHouseVo extends ArticleVo {

    private static final long serialVersionUID = 1341620709301612529L;


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


    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 文章内容
     */
    @ApiModelProperty("文章内容")
    private String content;

    /**
     * 数据表名
     */
    @ApiModelProperty("数据表名")
    private String tableName = "cms_article_house";

    /**
     * 创建者id
     */
    @ApiModelProperty("创建者id")
    private Integer creatorId;

    /**
     * 创建者姓名
     */
    @ApiModelProperty("创建者姓名")
    private String creatorName;

    /**
     * 更新者id
     */
    @ApiModelProperty("更新者id")
    private String updaterId;

    @ApiModelProperty("标签,用逗号分隔")
    private String tags;

    /**
     * 上线时间
     */
    @ApiModelProperty("上线时间")
    private Date startTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
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

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
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

    public String getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(String updaterId) {
        this.updaterId = updaterId;
    }
}
