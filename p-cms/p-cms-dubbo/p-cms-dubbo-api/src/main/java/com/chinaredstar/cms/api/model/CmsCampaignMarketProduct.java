package com.chinaredstar.cms.api.model;


import com.redstar.digital.open.vo.ProductShopGoodsRelation;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 活动商品信息
 */
@ApiModel("活动商品信息")
public class CmsCampaignMarketProduct extends BaseModel{
    private static final long serialVersionUID = -6678999389144490830L;

    /**
     * 主键,自增长
     */
    private Integer id;

    /**
     * 活动商场id
     */
    @ApiModelProperty("活动商场id")
    private Integer campaignMarketId;

    /**
     * 商品id
     */
    @ApiModelProperty("商品id")
    private String productId;

    /**
     * 分类，1：家居馆，2：建材馆
     */
    @ApiModelProperty("分类，1：家居馆，2：建材馆")
    private Integer type;

    /**
     * 是否推荐主会场，1：是；0：否
     */
    @ApiModelProperty("是否推荐主会场，1：是；0：否")
    private Boolean isRecommendIndex;

    /**
     * 分类，是否推荐到品牌页，1：是；0：否
     */
    @ApiModelProperty("是否推荐到品牌页，1：是；0：否")
    private Boolean isRecommendBrand;

    /**
     * 显示顺序
     */
    @ApiModelProperty("显示顺序")
    private Integer sequence;


    /**
     * 品牌id
     */
    @ApiModelProperty("品牌id")
    private String brandId;

    /**
     * 创建时间
     */
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
     * 商品促销价格
     */
    @ApiModelProperty("商品促销价格")
    private BigDecimal cheap;

    /**
     * 商品详情信息
     */
    @ApiModelProperty("商品详情信息")
    private ProductShopGoodsRelation productInfo;

    public BigDecimal getCheap() {
        return cheap;
    }

    public void setCheap(BigDecimal cheap) {
        this.cheap = cheap;
    }

    public ProductShopGoodsRelation getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ProductShopGoodsRelation productInfo) {
        this.productInfo = productInfo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCampaignMarketId() {
        return campaignMarketId;
    }

    public void setCampaignMarketId(Integer campaignMarketId) {
        this.campaignMarketId = campaignMarketId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getIsRecommendIndex() {
        return isRecommendIndex;
    }

    public void setIsRecommendIndex(Boolean isRecommendIndex) {
        this.isRecommendIndex = isRecommendIndex;
    }

    public Boolean getIsRecommendBrand() {
        return isRecommendBrand;
    }

    public void setIsRecommendBrand(Boolean isRecommendBrand) {
        this.isRecommendBrand = isRecommendBrand;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId == null ? null : brandId.trim();
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
}