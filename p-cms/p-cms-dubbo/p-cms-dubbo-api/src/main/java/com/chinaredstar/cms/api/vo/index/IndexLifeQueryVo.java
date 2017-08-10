package com.chinaredstar.cms.api.vo.index;

import com.chinaredstar.cms.api.component.Page;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Created by yixin.sun on 2017/3/26.
 */
@ApiModel("生活家查询参数")
public class IndexLifeQueryVo extends Page {
    /**
     * 是否首页推荐
     */
    @ApiModelProperty("是否首页推荐")
    private boolean recommend;

    /**
     * 是否最新或者最热，true 最新,false最热
     */
    @ApiModelProperty("是否最新或者最热，true 最新,false最热")
    private boolean newestOrHotest;

    public boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(boolean recommend) {
        this.recommend = recommend;
    }

    public boolean getNewestOrHotest() {
        return newestOrHotest;
    }

    public void setNewestOrHotest(boolean newestOrHotest) {
        this.newestOrHotest = newestOrHotest;
    }
}
