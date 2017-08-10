package com.chinaredstar.cms.api.vo.index;

import com.chinaredstar.cms.api.component.Page;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Created by pengfei.wang on 2017/3/24.
 */
public class IndexTopicQueryVo extends Page {

    private static final long serialVersionUID = 3034105421063255184L;

    @ApiModelProperty("是否首页推荐，false：否；true：是；")
    private Boolean isRecommend;

    public Boolean getRecommend() {
        return isRecommend;
    }

    public void setRecommend(Boolean recommend) {
        isRecommend = recommend;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IndexTopicQueryVo{");
        sb.append("isRecommend=").append(isRecommend);
        sb.append('}');
        return sb.toString();
    }
}
