package com.chinaredstar.cms.api.model;

import com.redstar.digital.open.bean.OmsBrandInfo;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 活动品牌
 */
@ApiModel("活动品牌")
public class CmsCampaignBrand extends BaseModel{
    private static final long serialVersionUID = -5462311995344607667L;

    /**
     * id,主键,自增长
     */
    private Integer id;

    /**
     * 活动id
     */
    @ApiModelProperty("活动id")
    private Integer campaignId;

    /**
     * 品牌id
     */
    @ApiModelProperty("品牌id")
    private String brandId;

    /**
     * 封面图
     */
    @ApiModelProperty("封面图")
    private String coverImg;

    /**
     * 是否直播
     */
    @ApiModelProperty("是否直播")
    private Boolean isLive;

    /**
     * 直播id
     */
    @ApiModelProperty("直播id")
    private String liveId;

    /**
     * 是否优惠券
     */
    @ApiModelProperty("是否优惠券")
    private Boolean isCoupon;

    /**
     * 广告id
     */
    @ApiModelProperty("广告id")
    private Integer advertisementId;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 创建人id
     */
    private String creatorId;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 显示顺序
     */
    @ApiModelProperty("显示顺序")
    private Integer sequence;


    /**
     * 品牌信息
     */
    @ApiModelProperty("品牌信息")
    private OmsBrandInfo brandInfo;

    public OmsBrandInfo getBrandInfo() {
        return brandInfo;
    }

    public void setBrandInfo(OmsBrandInfo brandInfo) {
        this.brandInfo = brandInfo;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Integer campaignId) {
        this.campaignId = campaignId;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId == null ? null : brandId.trim();
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg == null ? null : coverImg.trim();
    }

    public Boolean getIsLive() {
        return isLive;
    }

    public void setIsLive(Boolean isLive) {
        this.isLive = isLive;
    }

    public String getLiveId() {
        return liveId;
    }

    public void setLiveId(String liveId) {
        this.liveId = liveId == null ? null : liveId.trim();
    }

    public Boolean getIsCoupon() {
        return isCoupon;
    }

    public void setIsCoupon(Boolean isCoupon) {
        this.isCoupon = isCoupon;
    }

    public Integer getAdvertisementId() {
        return advertisementId;
    }

    public void setAdvertisementId(Integer advertisementId) {
        this.advertisementId = advertisementId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId == null ? null : creatorId.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}