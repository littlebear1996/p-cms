//package com.chinaredstar.cms.api.model;
//
//
//
//import com.chinaredstar.p_trade_promotion.api.vo.Coupon;
//import com.wordnik.swagger.annotations.ApiModel;
//import com.wordnik.swagger.annotations.ApiModelProperty;
//
//import java.util.Date;
//
///**
// * 活动优惠券
// */
//@ApiModel("活动优惠券")
//public class CmsCampaignMarketCoupon extends BaseModel{
//    private static final long serialVersionUID = -459537224765335106L;
//
//    /**
//     * 主键、自增长
//     */
//    private Integer id;
//
//    /**
//     * 活动商场id
//     */
//    @ApiModelProperty("活动商场id")
//    private Integer campaignMarketId;
//
//    /**
//     * 优惠券id
//     */
//    @ApiModelProperty("优惠券id")
//    private String couponId;
//
//    /**
//     * 显示顺序
//     */
//    @ApiModelProperty("显示顺序")
//    private Integer sequence;
//
//    /**
//     * 优惠券详情
//     */
//    @ApiModelProperty("优惠券详情")
//    private Coupon coupon;
//
//    public Coupon getCoupon() {
//        return coupon;
//    }
//
//    public void setCoupon(Coupon coupon) {
//        this.coupon = coupon;
//    }
//
//    private Date createTime;
//
//    private String creatorId;
//
//    private String creator;
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public Integer getCampaignMarketId() {
//        return campaignMarketId;
//    }
//
//    public void setCampaignMarketId(Integer campaignMarketId) {
//        this.campaignMarketId = campaignMarketId;
//    }
//
//    public String getCouponId() {
//        return couponId;
//    }
//
//    public void setCouponId(String couponId) {
//        this.couponId = couponId == null ? null : couponId.trim();
//    }
//
//    public Integer getSequence() {
//        return sequence;
//    }
//
//    public void setSequence(Integer sequence) {
//        this.sequence = sequence;
//    }
//
//    public Date getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(Date createTime) {
//        this.createTime = createTime;
//    }
//
//    public String getCreatorId() {
//        return creatorId;
//    }
//
//    public void setCreatorId(String creatorId) {
//        this.creatorId = creatorId == null ? null : creatorId.trim();
//    }
//
//    public String getCreator() {
//        return creator;
//    }
//
//
//
//    public void setCreator(String creator) {
//        this.creator = creator == null ? null : creator.trim();
//    }
//}