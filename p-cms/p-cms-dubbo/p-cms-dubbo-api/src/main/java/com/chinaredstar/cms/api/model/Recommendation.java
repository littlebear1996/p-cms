package com.chinaredstar.cms.api.model;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 推荐记录实体
 */
public class Recommendation extends BaseModel {
    private static final long serialVersionUID = 2176800478452559307L;

    /** 推荐分类:1家装,2商品,3房产,4商场,5主APP */
    /** 推荐分类: 5 主APP */
    public static final int TYPE_MAIN_APP = 5;

    /** 推荐子类型一:1APP推荐,2PC推荐 */
    /** 推荐子类型一 : 1 APP推荐 */
    public static final int SUBTYPE1_RECOMM_APP  = 1;

    /** 推荐子类型一 : 2 PC推荐 */
    public static final int SUBTYPE1_RECOMM_PC  = 2;

    /** 推荐子类型二:1家装首页,2发现 */
    /** 推荐子类型二: 1 家装首页 */
    public static final int SUBTYPE2_HOME_DECORATION_INDEX  = 1;

    /** 推荐子类型一 : 2 PC推荐 */
    public static final int SUBTYPE2_DISCOVERY  = 2;

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
     * 推荐创建者ID
     */
    @ApiModelProperty("推荐创建者ID")
    private String createrId;

    /**
     * 推荐创建者名称
     */
    @ApiModelProperty("推荐创建者名称")
    private String createName;

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
     * 审核状态:0创建,1审核成功,2审核失败
     */
    @ApiModelProperty("审核状态:0创建,1审核成功,2审核失败")
    private Integer status;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 审核者ID
     */
    @ApiModelProperty("审核者ID")
    private Long reviewerId;

    /**
     * 修改时间
     */
    @ApiModelProperty("修改时间")
    private Date editTime;

    /**
     * 驳回理由
     */
    @ApiModelProperty("驳回理由")
    private String rejiectReason;

    /**
     * 置顶标志位
     */
    @ApiModelProperty("置顶标志位")
    private Boolean setTopFlg;

    /**
     * 推荐标志位
     */
    @ApiModelProperty("推荐标志位")
    private Boolean setRecomFlg;

    /**
     * 上下线标志位
     */
    @ApiModelProperty("上下线标志位")
    private Boolean isOnline;

    /**
     * 上线时间
     */
    @ApiModelProperty("上线时间")
    private Date startTime;

    /**
     * 下线时间
     */
    @ApiModelProperty("下线时间")
    private Date endTime;

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

    /**
     * 修改者ID
     */
    @ApiModelProperty("修改者ID")
    private String editerId;

    /**
     * 修改者姓名
     */
    @ApiModelProperty("修改者姓名")
    private String editerName;

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

    public String getCreaterId() {
        return createrId;
    }

    public void setCreaterId(String createrId) {
        this.createrId = createrId;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public String getRejiectReason() {
        return rejiectReason;
    }

    public void setRejiectReason(String rejiectReason) {
        this.rejiectReason = rejiectReason;
    }

    public Boolean getSetTopFlg() {
        return setTopFlg;
    }

    public void setSetTopFlg(Boolean setTopFlg) {
        this.setTopFlg = setTopFlg;
    }

    public Boolean getSetRecomFlg() {
        return setRecomFlg;
    }

    public void setSetRecomFlg(Boolean setRecomFlg) {
        this.setRecomFlg = setRecomFlg;
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

    public String getEditerId() {
        return editerId;
    }

    public void setEditerId(String editerId) {
        this.editerId = editerId;
    }

    public String getEditerName() {
        return editerName;
    }

    public void setEditerName(String editerName) {
        this.editerName = editerName;
    }
}