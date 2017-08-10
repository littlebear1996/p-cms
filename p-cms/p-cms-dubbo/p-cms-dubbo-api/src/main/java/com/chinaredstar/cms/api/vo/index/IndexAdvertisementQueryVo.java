package com.chinaredstar.cms.api.vo.index;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yixin.sun on 2017/3/29.
 */
@ApiModel("首页广告查询参数")
public class IndexAdvertisementQueryVo implements Serializable {
    private static final long serialVersionUID = 8383286423889458917L;

    /**
     * 当前时间
     */
    @ApiModelProperty("当前时间")
    private Date currentTime;

    /**
     * 广告位置编码
     */
    @ApiModelProperty("广告位置编码")
    private String positionCode;

    /**
     * 城市id
     */
    @ApiModelProperty("城市id")
    private String cityId;

    /**
     * 是否全店推广
     */
    @ApiModelProperty("是否全店推广")
    private Boolean isShop;

    /**
     * 是否推荐首页
     */
    @ApiModelProperty("是否推荐首页")
    private Boolean isHome;

    /**
     * 横划顺位
     */
    @ApiModelProperty("横划顺位")
    private Integer sequence;

    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public Boolean getShop() {
        return isShop;
    }

    public void setShop(Boolean shop) {
        isShop = shop;
    }

    public Boolean getHome() {
        return isHome;
    }

    public void setHome(Boolean home) {
        isHome = home;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}
