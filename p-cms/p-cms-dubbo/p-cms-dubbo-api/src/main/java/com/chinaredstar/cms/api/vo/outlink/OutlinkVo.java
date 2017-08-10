package com.chinaredstar.cms.api.vo.outlink;

import com.chinaredstar.ilvb.api.bean.ZbRoom;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by sunny on 16-8-26.
 */
public class OutlinkVo implements Serializable {
    private static final long serialVersionUID = 2705457263282888576L;
    /**
     * 直播数据
     */
    @ApiModelProperty("直播数据")

    private ZbRoom zbRoom;
    /**
     * PK
     */
    @ApiModelProperty("PK")
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
     * 链接
     */
    @ApiModelProperty("链接")
    private String outlink;
    /**
     * 类型：家装 3,商品  1，房产  4,商场 2
     */
    @ApiModelProperty("类型：家装 3,商品  1，房产  4,商场 2")
    private Integer type;

    /**
     * 图片url
     */
    @ApiModelProperty("图片url")
    private String image;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;
    /**
     * 创建者
     */
    @ApiModelProperty("创建者")
    private String creator;
    @ApiModelProperty("标签")
    private String tags;

    @ApiModelProperty("链接类型：1，h5,2家装文章,3家装图集4,商品文章，5，商品图集，6，房产专题，7，直播")
    private String linkType;


    @ApiModelProperty("商场id")
    private String marketId;

    @ApiModelProperty("商场名字")
    private String marketName;

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
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

    public String getOutlink() {
        return outlink;
    }

    public void setOutlink(String outlink) {
        this.outlink = outlink;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public ZbRoom getZbRoom() {
        return zbRoom;
    }

    public void setZbRoom(ZbRoom zbRoom) {
        this.zbRoom = zbRoom;
    }

}
