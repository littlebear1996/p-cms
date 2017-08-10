package com.chinaredstar.cms.api.model;

/**
 * 文章标签中间实体
 */
public class ArticleTags extends BaseModel {

    private static final long serialVersionUID = -9069334016012698833L;

    /** PK */
    private Integer id;

    /** 关联文章表ID */
    private Integer articleId;

    /** 标签库获取到的标签ID */
    private Integer tagId;

    /** 标签名称 */
    private String tagName;

    /** 文章类型，1：商品文章，2：商场文章，3：家装案例文章，4：房产文章 */
    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}