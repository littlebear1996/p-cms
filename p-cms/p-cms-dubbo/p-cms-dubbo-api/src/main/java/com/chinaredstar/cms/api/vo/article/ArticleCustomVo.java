package com.chinaredstar.cms.api.vo.article;

import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 商品文章
 */
public class ArticleCustomVo extends ArticleVo {

    private static final long serialVersionUID = -404348637282371723L;

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
     * 文章内容
     */
    @ApiModelProperty("文章内容")
    private String content;

    @ApiModelProperty("标签")
    private String tags;
    @ApiModelProperty("数据表名")
    private String tableName = "cms_article_custom";

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
