package com.chinaredstar.cms.api.vo.encyclopedia;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by sunny on 16-8-23.
 */
public class EncyclopediaVo implements Serializable {
    private static final long serialVersionUID = -5566721599005729247L;
    /**
     * 百科记录ID
     */
    @ApiModelProperty("百科记录ID")
    private Long id;

    /**
     * 百科创建者ID
     */
    @ApiModelProperty("百科创建者ID")
    private Long createrId;

    /**
     * 百科创建者姓名
     */
    @ApiModelProperty("百科创建者姓名")
    private String createrName;

    /**
     * 百科分类:1家装,2商品,3房产,4商场
     */
    @ApiModelProperty("百科分类:1家装,2商品,3房产,4商场")
    private Integer type;

    /**
     * 百科标题
     */
    @ApiModelProperty("百科标题")
    private String title;

    /**
     * 百科副标题
     */
    @ApiModelProperty("百科副标题")
    private String subTitle;

    /**
     * 百科标签
     */
    @ApiModelProperty("百科标签")
    private String tags;

    /**
     * 百科封面
     */
    @ApiModelProperty("百科封面")
    private String cover;

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
     * 百科内容
     */
    @ApiModelProperty("百科内容")
    private String context;

    /**
     * 修改者ID
     */
    @ApiModelProperty("修改者ID")
    private Long editerId;

    /**
     * 修改者姓名
     */
    @ApiModelProperty("修改者姓名")
    private String editerName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreaterId() {
        return createrId;
    }

    public void setCreaterId(Long createrId) {
        this.createrId = createrId;
    }

    public String getCreaterName() {
        return createrName;
    }

    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
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

    public Long getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(Long reviewerId) {
        this.reviewerId = reviewerId;
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

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
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

}
