package com.chinaredstar.cms.api.model;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.Date;


/**
 * 专题信息
 */
@ApiModel(value = "专题信息",description = "专题信息")
public class CmsTopic extends BaseModel{


    private static final long serialVersionUID = 3625545328843928482L;
    /**
     * 专题id,主键,自增长
     */
    @ApiModelProperty("专题id")
    private Integer id;

    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;

    /**
     * 审核状态，0：待审核，1：审核通过，2：审核驳回
     */
    @ApiModelProperty("审核状态，0：待审核，1：审核通过，2：审核驳回")
    private Integer status;

    /**
     * 排序号
     */
    @ApiModelProperty("排序号")
    private Integer sequence;

    /**
     * 驳回原因
     */
    @ApiModelProperty("驳回原因")
    private String rejectCause;

    /**
     * 封面图
     */
    @ApiModelProperty("封面图")
    private String coverImg;


    /**
     * 创建者名字
     */
    @ApiModelProperty("创建者名字")
    private String creator;


    /**
     * 创建者id
     */
    @ApiModelProperty("创建者id")
    private Integer creatorId;


    /**
     * 是否上线,1是;0否
     */
    @ApiModelProperty("是否上线,1是;0否")
    private Byte isOnline;


    /**
     * 是否置顶,1是;0否
     */
    @ApiModelProperty("是否置顶,1是;0否")
    private Byte isTop;

    /**
     * 置顶时间
     */
    @ApiModelProperty("置顶时间")
    private Date setTopTime;


    /**
     * 是否推荐,1是;0否
     */
    @ApiModelProperty("是否推荐,1是;0否")
    private Byte isRecommand;

    /**
     * 推荐时间
     */
    @ApiModelProperty("推荐时间")
    private Date setRecommandTime;

    /**
     * 提交审核时间
     */
    @ApiModelProperty("提交审核时间")
    private Date reviewTime;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    private Date updateTime;

    /**
     * 更新用户id
     */
   // @ApiModelProperty("")
    private Integer updaterId;

    /**
     * 更新用户名
     */
    //@ApiModelProperty("")
    private String updaterName;

    /**
     * 专题类型：1家装,2商品,3房产,4商场
     */
    @ApiModelProperty("专题类型：1家装,2商品,3房产,4商场")
    private Integer type;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String description;

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
        this.title = title == null ? null : title.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getRejectCause() {
        return rejectCause;
    }

    public void setRejectCause(String rejectCause) {
        this.rejectCause = rejectCause == null ? null : rejectCause.trim();
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg == null ? null : coverImg.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Byte getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Byte isOnline) {
        this.isOnline = isOnline;
    }

    public Byte getIsTop() {
        return isTop;
    }

    public void setIsTop(Byte isTop) {
        this.isTop = isTop;
    }

    public Date getSetTopTime() {
        return setTopTime;
    }

    public void setSetTopTime(Date setTopTime) {
        this.setTopTime = setTopTime;
    }

    public Byte getIsRecommand() {
        return isRecommand;
    }

    public void setIsRecommand(Byte isRecommand) {
        this.isRecommand = isRecommand;
    }

    public Date getSetRecommandTime() {
        return setRecommandTime;
    }

    public void setSetRecommandTime(Date setRecommandTime) {
        this.setRecommandTime = setRecommandTime;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(Integer updaterId) {
        this.updaterId = updaterId;
    }

    public String getUpdaterName() {
        return updaterName;
    }

    public void setUpdaterName(String updaterName) {
        this.updaterName = updaterName == null ? null : updaterName.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}