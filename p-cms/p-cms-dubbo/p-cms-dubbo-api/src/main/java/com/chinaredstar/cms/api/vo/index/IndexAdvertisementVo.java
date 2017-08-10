package com.chinaredstar.cms.api.vo.index;

import com.chinaredstar.ilvb.api.bean.ZbRoom;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 首页广告
 * Created by yixin.sun on 2017/3/29.
 */
@ApiModel("首页广告")
public class IndexAdvertisementVo implements Serializable {
    private static final long serialVersionUID = -4177760957808023248L;

    /**
     * id
     */
    @ApiModelProperty("id")
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
     * 开始时间
     */
    @ApiModelProperty("开始时间")
    private Date startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间")
    private Date endTime;

    /**
     * 排序号
     */
    @ApiModelProperty("排序号")
    private Integer sequence;

    /**
     * 广告图
     */
    @ApiModelProperty("广告图")
    private String imgUrl;

    /**
     * 浏览数
     */
    @ApiModelProperty("浏览数")
    private Integer viewCount;

    /**
     * 点赞数
     */
    @ApiModelProperty("点赞数")
    private Integer agreeCount;

    /**
     * 链接url
     */
    @ApiModelProperty("链接url")
    private String linkUrl;

    /**
     * 链接类型：1 h5,2家装文章,3 家装图集，4 商品文章，5 商品图集，6 房产专题，7 直播
     */
    @ApiModelProperty("链接类型：1 h5,2家装文章,3 家装图集，4 商品文章，5 商品图集，6 房产专题，7 直播, 10 自定义图文页")
    private String linkType;

    /**
     * 直播数据，当linkType =7 时填充数据
     */
    @ApiModelProperty("直播数据，当linkType =7 时填充数据")
    private ZbRoom zbRoom;

    /**
     * 自定义图文页，当linkType =10 时填充数据
     */
    @ApiModelProperty("自定义图文页，当linkType =10 时填充数据")
    private String linkContent;

    /**
     * 广告位id
     */
    @ApiModelProperty("广告位id")
    private Integer positionId;

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
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

    public ZbRoom getZbRoom() {
        return zbRoom;
    }

    public void setZbRoom(ZbRoom zbRoom) {
        this.zbRoom = zbRoom;
    }

    public String getLinkContent() {
        return linkContent;
    }

    public void setLinkContent(String linkContent) {
        this.linkContent = linkContent;
    }
}
