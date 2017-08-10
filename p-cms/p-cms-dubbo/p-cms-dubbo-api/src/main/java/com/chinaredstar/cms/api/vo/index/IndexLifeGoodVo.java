package com.chinaredstar.cms.api.vo.index;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by yixin.sun on 2017/3/24.
 */
@ApiModel("生活家商品")
public class IndexLifeGoodVo implements Serializable {
    private static final long serialVersionUID = 4290113314977546313L;

    /**
     * id
     */
    @ApiModelProperty("id")
    private Integer id;

    /**
     * 生活家id
     */
    @ApiModelProperty("生活家id")
    private Integer lifeId;

    /**
     * 商品id
     */
    @ApiModelProperty("商品id")
    private String goodId;

    /**
     * 商品名字
     */
    @ApiModelProperty("商品名字")
    private String goodName;

    /**
     * 商品封面图
     */
    @ApiModelProperty("商品封面图")
    private String cover;

    /**
     * 商品标题
     */
    @ApiModelProperty("商品标题")
    private String title;

    /**
     * 商品价格
     */
    @ApiModelProperty("商品价格")
    private BigDecimal price;

    @ApiModelProperty("推荐语")
    private String recommendWord;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLifeId() {
        return lifeId;
    }

    public void setLifeId(Integer lifeId) {
        this.lifeId = lifeId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public String getRecommendWord() {
        return recommendWord;
    }

    public void setRecommendWord(String recommendWord) {
        this.recommendWord = recommendWord;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IndexLifeGoodVo{");
        sb.append("id=").append(id);
        sb.append(", lifeId=").append(lifeId);
        sb.append(", goodId='").append(goodId).append('\'');
        sb.append(", goodName='").append(goodName).append('\'');
        sb.append(", cover='").append(cover).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", price=").append(price);
        sb.append(", recommendWord='").append(recommendWord).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
