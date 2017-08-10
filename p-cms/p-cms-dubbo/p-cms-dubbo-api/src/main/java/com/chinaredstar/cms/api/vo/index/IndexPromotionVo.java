package com.chinaredstar.cms.api.vo.index;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by yixin.sun on 2017/3/24.
 */
@ApiModel("促销")
public class IndexPromotionVo implements Serializable {
    private static final long serialVersionUID = -8929304439955909889L;

    /**
     * id
     */
    @ApiModelProperty("id")
    private Integer id;

    /**
     * 开始时间
     */
    @ApiModelProperty("开始时间")
    private Date startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间")
    private Date endTime;

    /**
     * 当前时间
     */
    @ApiModelProperty("当前时间")
    private Date currentTime;

    /**
     * 促销id
     */
    @ApiModelProperty("促销id")
    private String promotionId;

    /**
     * 促销名称
     */
    @ApiModelProperty("促销名称")
    private String promotionName;

    /**
     * 促销商品列表
     */
    @ApiModelProperty("促销商品列表")
    private List<IndexPromotionGoodsVo> goodsList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public List<IndexPromotionGoodsVo> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<IndexPromotionGoodsVo> goodsList) {
        this.goodsList = goodsList;
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }
}
