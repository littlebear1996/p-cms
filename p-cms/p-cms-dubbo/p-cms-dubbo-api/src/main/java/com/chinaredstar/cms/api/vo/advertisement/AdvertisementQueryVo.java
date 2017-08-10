package com.chinaredstar.cms.api.vo.advertisement;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by sunny on 16-8-16.
 */
public class AdvertisementQueryVo implements Serializable {
    private static final long serialVersionUID = 3831715447368890105L;

    @ApiModelProperty("广告位置编码")
    private String positionCode;

    @ApiModelProperty("当前时间")
    private Date currentTime = new Date();

    @ApiModelProperty("是否上线")
    private Boolean isOnline = true;

    @ApiModelProperty("是否全店推广")
    private Boolean isShop;

    @ApiModelProperty("是否推荐首页")
    private Boolean isHome;

    @ApiModelProperty("横划顺位")
    private Integer sequence;

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    public Boolean getOnline() {
        return isOnline;
    }

    public void setOnline(Boolean online) {
        isOnline = online;
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
