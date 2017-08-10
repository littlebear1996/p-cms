package com.chinaredstar.cms.api.model;


import com.redstar.digital.open.vo.OmsMarketInfoRelation;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 活动商场信息
 */
@ApiModel("活动商场信息")
public class CmsCampaignMarket extends BaseModel{
    private static final long serialVersionUID = 3943081474968788833L;

    /**
     * 主键、自增长
     */
    private Integer id;

    /**
     * 活动id
     */
    @ApiModelProperty("活动id")
    private Integer campaignId;

    /**
     * 商场id
     */
    @ApiModelProperty("商场id")
    private String marketId;

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
     * 商场详情信息
     */
    @ApiModelProperty("商场详情信息")
    private OmsMarketInfoRelation omsMarketInfo;

    public OmsMarketInfoRelation getOmsMarketInfo() {
        return omsMarketInfo;
    }

    public void setOmsMarketInfo(OmsMarketInfoRelation omsMarketInfo) {
        this.omsMarketInfo = omsMarketInfo;
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

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId == null ? null : marketId.trim();
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