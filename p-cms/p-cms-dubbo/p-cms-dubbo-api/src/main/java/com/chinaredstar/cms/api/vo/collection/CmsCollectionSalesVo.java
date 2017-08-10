package com.chinaredstar.cms.api.vo.collection;

import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 导购员合辑
 */
public class CmsCollectionSalesVo extends CmsCollectionTargetVo {

    private static final long serialVersionUID = 6659777725725793964L;

    @ApiModelProperty("主键ID")
    private Long id;

    @ApiModelProperty("手机号")
    private String mobilePhone;

    @ApiModelProperty("openId")
    private String openId;

    @ApiModelProperty("全称")
    private String fullname;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("头像")
    private String picUrl;

    @ApiModelProperty("自我介绍")
    private String introduction;

    @ApiModelProperty("店铺名称")
    private String shopName;

    @ApiModelProperty("店铺ID")
    private Long shopId;

    @ApiModelProperty("商场ID")
    private Long marketId;

    @ApiModelProperty("商场名称")
    private String marketName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getMarketId() {
        return marketId;
    }

    public void setMarketId(Long marketId) {
        this.marketId = marketId;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }
}