package com.chinaredstar.cms.api.model;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 百科记录实体类
 */
@ApiModel("百科记录实体")
public class Encyclopedia extends BaseModel {
    private static final long serialVersionUID = -6447518611176558558L;
    /**
     * 百科记录ID
     */
    @ApiModelProperty("百科记录ID")
    private Long encyId;

    /**
     * 百科创建者ID
     */
    @ApiModelProperty("百科创建者ID")
    private Long createrId;

    /** 百科创建者姓名 */
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

    public Long getEncyId() {
        return encyId;
    }

    public void setEncyId(Long encyId) {
        this.encyId = encyId;
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