package com.chinaredstar.cms.api.vo.index;

import com.chinaredstar.cms.api.component.Page;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Created by pengfei.wang on 2017/3/24.
 */
public class IndexBrandQueryVo extends Page {

    @ApiModelProperty("是否首页推荐,false:否;true;是")
    private Boolean isRecommend;

    @ApiModelProperty("是否按最热排序, true:是")
    private Boolean isOrderByHottest;

    public Boolean getRecommend() {
        return isRecommend;
    }

    public void setRecommend(Boolean recommend) {
        isRecommend = recommend;
    }

    public Boolean getOrderByHottest() {
        return isOrderByHottest;
    }

    public void setOrderByHottest(Boolean orderByHottest) {
        isOrderByHottest = orderByHottest;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IndexBrandQueryVo{");
        sb.append("isRecommend=").append(isRecommend);
        sb.append(", isOrderByHottest=").append(isOrderByHottest);
        sb.append('}');
        return sb.toString();
    }
}
