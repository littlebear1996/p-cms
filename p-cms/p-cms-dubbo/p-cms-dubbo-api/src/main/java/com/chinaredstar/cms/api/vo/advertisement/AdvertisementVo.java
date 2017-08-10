package com.chinaredstar.cms.api.vo.advertisement;

import com.chinaredstar.ilvb.api.bean.ZbRoom;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by sunny on 16-8-19.
 */
public class AdvertisementVo implements Serializable {
    private static final long serialVersionUID = 1151656092690567736L;
    /**
     * 自增主键id
     */
    @ApiModelProperty("自增主键id")
    private Integer id;

    /**
     * 标题
     */
    @ApiModelProperty("广告标题")
    private String title;

    /**
     * 描述
     */
    @ApiModelProperty("广告描述")
    private String description;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;
    /**
     * 作用页面外键，关联cms_advertisement_position表
     */
    @ApiModelProperty("作用页面外键，关联cms_advertisement_position表")
    private Integer positionId;
    /**
     * 横划顺位
     */
    @ApiModelProperty("广告横划顺位")
    private Integer sequence;
    /**
     * 图片url
     */
    @ApiModelProperty("广告图片url")
    private String imgUrl;
    /**
     * 浏览次数，来源：外部系统
     */
    @ApiModelProperty("浏览次数，来源：外部系统")
    private Integer viewCount;
    /**
     * 点赞数，来源：外部系统
     */
    @ApiModelProperty("点赞数，来源：外部系统")
    private Integer agreeCount;
    /**
     * 广告链接url
     */
    @ApiModelProperty("广告跳转链接url")
    private String linkUrl;

    @ApiModelProperty("链接类型：1，h5,2家装文章,3家装图集4,商品文章，5，商品图集，6，房产专题，7，直播")
    private String linkType;

    @ApiModelProperty("直播数据，当linkType =7 时填充数据")
    private ZbRoom zbRoom;

    public ZbRoom getZbRoom() {
        return zbRoom;
    }

    public void setZbRoom(ZbRoom zbRoom) {
        this.zbRoom = zbRoom;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getAgreeCount() {
        return agreeCount;
    }

    public void setAgreeCount(Integer agreeCount) {
        this.agreeCount = agreeCount;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }
}
