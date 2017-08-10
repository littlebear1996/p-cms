package com.chinaredstar.cms.api.model;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 专题详情信息
 */
@ApiModel("专题详情")
public class CmsTopicDetail extends BaseModel {
    private static final long serialVersionUID = 7389633533398650941L;
    /**
     * id,主键,自增长
     */
    @ApiModelProperty("主键")
    private Integer id;

    /**
     * 排序号
     */
    @ApiModelProperty("排序号")
    private Integer sequence;

    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;

    /**
     * 内容类型
     * 家装类型为：11家装问答、12设计师合辑、13家装百科合辑、14家装百科、15家装文章、16家装广告、17家装图集、18家装外链
     * 商品类型:21商品问答、22商品百科、23商品软文、24商品广告位、25商品图集、26商品外链
     * 房产类型:31房产问答、32学校合辑、33小区合辑、34楼盘合辑、35房产百科、36房产软文、37房产广告位、38房产图集
     * 商场类型：41品牌合辑、42导购员合辑、43外链合辑、44商场文章合辑、45商场软文、46商场广告位
     */
    @ApiModelProperty(
            "内容类型\n" +
                    "     家装类型为：11家装问答、12设计师合辑、13家装百科合辑、14家装百科、15家装文章、16家装广告、17家装图集、18家装外链\n" +
                    "     商品类型:21商品问答、22商品百科、23商品软文、24商品广告位、25商品图集、26商品外链\n" +
                    "     房产类型:31房产问答、32学校合辑、33小区合辑、34楼盘合辑、35房产百科、36房产软文、37房产广告位、38房产图集\n" +
                    "     商场类型：41品牌合辑、42导购员合辑、43外链合辑、44商场文章合辑、45商场软文、46商场广告位"
    )
    private Integer contentType;

    /**
     * 内容值
     */
    @ApiModelProperty("内容值")
    private String content;

    /**
     * 封面图
     */
    @ApiModelProperty("封面图")
    private String coverImg;

    /**
     * 展示样式,1大图，2，横滑
     */
    @ApiModelProperty("展示样式,1大图，2，横滑")
    private Integer style;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 创建者id
     */
    @ApiModelProperty("创建者id")
    private Integer creatorId;

    /**
     * 创建者
     */
    @ApiModelProperty("创建者")
    private String creator;

    /**
     * 专题分类id
     */
    @ApiModelProperty("专题分类id")
    private Integer topicTypeId;

    /**
     * 目标对象
     */
    @ApiModelProperty("目标对象")
    private Object targerVo;

    public Integer getPicCount() {
        return picCount;
    }

    public void setPicCount(Integer picCount) {
        this.picCount = picCount;
    }

    /**
     * 当类型为图集时，表示图集的图片数量
     */
    @ApiModelProperty("当类型为图集时，表示图集的图片数量")

    private Integer picCount;

    public Object getTargerVo() {
        return targerVo;
    }

    public void setTargerVo(Object targerVo) {
        this.targerVo = targerVo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg == null ? null : coverImg.trim();
    }

    public Integer getStyle() {
        return style;
    }

    public void setStyle(Integer style) {
        this.style = style;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Integer getTopicTypeId() {
        return topicTypeId;
    }

    public void setTopicTypeId(Integer topicTypeId) {
        this.topicTypeId = topicTypeId;
    }
}