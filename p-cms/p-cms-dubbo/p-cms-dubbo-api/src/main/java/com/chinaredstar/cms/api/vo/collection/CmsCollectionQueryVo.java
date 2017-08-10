package com.chinaredstar.cms.api.vo.collection;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by lenovo on 2016/8/17.
 */
public class CmsCollectionQueryVo implements Serializable{

    private static final long serialVersionUID = -7632980037358612243L;

    @ApiModelProperty("类型：家装 3, 商品  1，房产  4, 商场 2")
    private Integer type;

    @ApiModelProperty("子类型：家装公司31，设计师32，方案33；学校合辑41，小区合辑42，楼盘合辑43；品牌合辑21，导购员合辑22，商场外链23，商场文章24")
    private Integer subType;

    @ApiModelProperty("是否置顶")
    private Boolean isTop;

    @ApiModelProperty("是否上线")
    private Boolean isOnline;

    @ApiModelProperty("是否推荐")
    private Boolean isRecommend;

    @ApiModelProperty("商场ID")
    private String marketId;

    @ApiModelProperty("城市ID")
    private String cityId;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSubType() {
        return subType;
    }

    public void setSubType(Integer subType) {
        this.subType = subType;
    }

    public Boolean getTop() {
        return isTop;
    }

    public void setTop(Boolean top) {
        isTop = top;
    }

    public Boolean getOnline() {
        return isOnline;
    }

    public void setOnline(Boolean online) {
        isOnline = online;
    }

    public Boolean getRecommend() {
        return isRecommend;
    }

    public void setRecommend(Boolean recommend) {
        isRecommend = recommend;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }
}
