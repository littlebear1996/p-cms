package com.chinaredstar.cms.api.vo.campaign;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by Ykk on 16/10/17.
 */
public class CampaignMarketProductQueryVo implements Serializable{

    /**
     * 活动商场id
     */
    @ApiModelProperty("活动商场id")
    private Integer campaignMarketId;


    /**
     * 分类，1：家居馆，2：建材馆
     */
    @ApiModelProperty("分类，1：家居馆，2：建材馆")
    private Integer type;


    /**
     * 是否推荐主会场，1：是；0：否
     */
    @ApiModelProperty("是否推荐主会场，1：是；0：否")
    private Integer isRecommendIndex;


    /**
     * 分类，是否推荐到品牌页，1：是；0：否
     */
    @ApiModelProperty("是否推荐到品牌页，1：是；0：否")
    private Integer isRecommendBrand;


    /**
     * 品牌id
     */
    @ApiModelProperty("品牌id")
    private String brandId;



    private Integer pageStart;


    public Integer getPageStart() {
        return (pageNo-1)*pageSize;
    }

    public void setPageStart(Integer pageStart) {
        this.pageStart = pageStart;
    }

    @ApiModelProperty("第几页")
    private Integer pageNo;



    @ApiModelProperty("每页记录数")
    private Integer pageSize;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCampaignMarketId() {
        return campaignMarketId;
    }

    public void setCampaignMarketId(Integer campaignMarketId) {
        this.campaignMarketId = campaignMarketId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsRecommendIndex() {
        return isRecommendIndex;
    }

    public void setIsRecommendIndex(Integer isRecommendIndex) {
        this.isRecommendIndex = isRecommendIndex;
    }

    public Integer getIsRecommendBrand() {
        return isRecommendBrand;
    }

    public void setIsRecommendBrand(Integer isRecommendBrand) {
        this.isRecommendBrand = isRecommendBrand;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }
}
