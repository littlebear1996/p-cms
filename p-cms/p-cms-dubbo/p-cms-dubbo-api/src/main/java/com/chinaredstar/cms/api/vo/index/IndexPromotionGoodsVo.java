package com.chinaredstar.cms.api.vo.index;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by yixin.sun on 2017/3/24.
 */
@ApiModel("促销购商品")
public class IndexPromotionGoodsVo implements Serializable {

    private static final long serialVersionUID = -7504678984159034601L;

    /**
     * 促销商品id
     */
    @ApiModelProperty("促销商品id")
    private Integer id;

    /**
     * 商品图片
     */
    @ApiModelProperty("商品图片")
    private String cover;

    /**
     * 商品sku
     */
    @ApiModelProperty("商品sku")
    private String sku;

    /**
     * 促销商品库存
     */
    @ApiModelProperty("商品库存")
    private BigDecimal skuStock;

    /**
     *
     */
    @ApiModelProperty("促销库存")
    private BigDecimal promotionStock;
    /**
     * 商品名称
     */
    @ApiModelProperty("商品名称")
    private String name;

    /**
     * 商品原价
     */
    @ApiModelProperty("商品原价")
    private BigDecimal price;
    /**
     * 商品促销价
     */
    @ApiModelProperty("商品促销价")
    private BigDecimal promotionPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(BigDecimal promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public BigDecimal getSkuStock() {
        return skuStock;
    }

    public void setSkuStock(BigDecimal skuStock) {
        this.skuStock = skuStock;
    }

    public BigDecimal getPromotionStock() {
        return promotionStock;
    }

    public void setPromotionStock(BigDecimal promotionStock) {
        this.promotionStock = promotionStock;
    }
}
