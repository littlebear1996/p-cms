package com.chinaredstar.cms.api.vo.recommend;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 推荐信息VO
 */
public class RecommendationVo implements Serializable {

    private static final long serialVersionUID = 1015002651560412999L;

    /**
     * 推荐记录ID
     */
    @ApiModelProperty("推荐记录ID")
    private Long recomId;

    /**
     * 推荐页面显示排序号
     */
    @ApiModelProperty("推荐页面显示排序号")
    private Integer sortId;

    /**
     * 推荐分类:1家装,2商品,3房产,4商场,5主APP
     */
    @ApiModelProperty("推荐分类:1家装,2商品,3房产,4商场,5主APP")
    private Integer type;

    /**
     * 推荐子类型一:1APP推荐,2PC推荐
     */
    @ApiModelProperty("推荐子类型一:1APP推荐,2PC推荐")
    private Integer subType1;

    /**
     * 推荐子类型二:1家装首页,2好玩
     */
    @ApiModelProperty("荐子类型二:1家装首页,2好玩")
    private Integer subType2;

    /**
     * 推荐标题
     */
    @ApiModelProperty("推荐标题")
    private String title;

    /**
     * 展示样式
     */
    @ApiModelProperty("展示样式")
    private String style;

    /**
     * 推荐封面
     */
    @ApiModelProperty("推荐封面")
    private String cover;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

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

    /**
     * 内容类型: 1,方案、2，设计师合辑，3，文章 4, 图集
     */
    @ApiModelProperty(" 内容类型: 1,方案、2，设计师合辑，3，文章 4, 图集")
    private Integer contentType;

    /**
     * 内当content_type为3时, 该字段有用.文章类型，1：商品文章，2：商场文章，3：家装案例文章，4：房产文章;
     */
    @ApiModelProperty(" 内当content_type为3时, 该字段有用.文章类型，1：商品文章，2：商场文章，3：家装案例文章，4：房产文章;")
    private Integer articleType;

    /**
     * 内容
     */
    @ApiModelProperty("内容")
    private String content;

    public Long getRecomId() {
        return recomId;
    }

    public void setRecomId(Long recomId) {
        this.recomId = recomId;
    }

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSubType1() {
        return subType1;
    }

    public void setSubType1(Integer subType1) {
        this.subType1 = subType1;
    }

    public Integer getSubType2() {
        return subType2;
    }

    public void setSubType2(Integer subType2) {
        this.subType2 = subType2;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public Integer getArticleType() {
        return articleType;
    }

    public void setArticleType(Integer articleType) {
        this.articleType = articleType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
