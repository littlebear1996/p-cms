package com.chinaredstar.cms.api.vo.campaign;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ykk on 16/10/17.
 */
public class CampaignQueryVo implements Serializable {


    private static final long serialVersionUID = -3060173466319582426L;
    @ApiModelProperty("当前时间")
    private Date currentTime = new Date();


    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }
}
