package com.chinaredstar.cms.api.vo.index;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by pengfei.wang on 2017/3/24.
 */
@ApiModel("大牌商品")
public class IndexBrandGoodVo implements Serializable {

    private static final long serialVersionUID = -8922910793303087229L;

    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("大牌id")
    private Integer indexBrandId;

    @ApiModelProperty("商品id")
    private String goodId;

    @ApiModelProperty("商品名字")
    private String goodName;

    @ApiModelProperty("排序号")
    private Integer sortNo;

    @ApiModelProperty("商品标题")
    private String title;

    @ApiModelProperty("封面图")
    private String cover;

    @ApiModelProperty("商品价格")
    private BigDecimal price;

    @ApiModelProperty("是否首页推荐,false:否;true;是")
    private Boolean isRecommend;

    @ApiModelProperty("推荐语")
    private String recommendWord;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIndexBrandId() {
        return indexBrandId;
    }

    public void setIndexBrandId(Integer indexBrandId) {
        this.indexBrandId = indexBrandId;
    }

    public String getGoodId() {
        return goodId;
    }

    public void setGoodId(String goodId) {
        this.goodId = goodId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getRecommend() {
        return isRecommend;
    }

    public void setRecommend(Boolean recommend) {
        isRecommend = recommend;
    }

    public String getRecommendWord() {
        return recommendWord;
    }

    public void setRecommendWord(String recommendWord) {
        this.recommendWord = recommendWord;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IndexBrandGoodVo{");
        sb.append("id=").append(id);
        sb.append(", indexBrandId=").append(indexBrandId);
        sb.append(", goodId='").append(goodId).append('\'');
        sb.append(", goodName='").append(goodName).append('\'');
        sb.append(", sortNo=").append(sortNo);
        sb.append(", title='").append(title).append('\'');
        sb.append(", cover='").append(cover).append('\'');
        sb.append(", price=").append(price);
        sb.append(", isRecommend=").append(isRecommend);
        sb.append(", recommendWord='").append(recommendWord).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
