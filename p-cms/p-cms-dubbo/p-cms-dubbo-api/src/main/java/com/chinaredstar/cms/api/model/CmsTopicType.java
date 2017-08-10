package com.chinaredstar.cms.api.model;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.Date;


/**
 * 专题分类信息
 */
@ApiModel(value = "专题分类信息", description = "专题分类信息")
public class CmsTopicType extends BaseModel {

    private static final long serialVersionUID = 3625545328843928482L;
    /**
     * 专题分类id,主键,自增长
     */
    @ApiModelProperty("专题分类id")
    private Integer id;

    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String description;

    /**
     * 封面
     */
    @ApiModelProperty("封面")
    private String coverImg;

    /**
     * 排序号
     */
    @ApiModelProperty("排序号")
    private Integer sequence;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

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
     * 展示样式：1大图；2横划
     */
    @ApiModelProperty("展示样式：1大图；2横划")
    private String style;

    /**
     * 分类
     */
    @ApiModelProperty("分类")
    private String type;

    /**
     * 专题合辑id
     */
    @ApiModelProperty("专题合辑id")
    private Integer topicId;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

}