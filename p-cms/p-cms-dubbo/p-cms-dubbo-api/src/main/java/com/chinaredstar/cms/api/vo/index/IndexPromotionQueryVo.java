package com.chinaredstar.cms.api.vo.index;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by yixin.sun on 2017/3/26.
 */
public class IndexPromotionQueryVo implements Serializable {
    private static final long serialVersionUID = 3631297478142765653L;

    /**
     * 当前时间
     */
    @ApiModelProperty("当前时间")
    private Date currentTime;

    /**
     * 是否首页推荐
     */
    @ApiModelProperty("是否首页推荐")
    private boolean recommend;

    public Date getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }

    public boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }
}
