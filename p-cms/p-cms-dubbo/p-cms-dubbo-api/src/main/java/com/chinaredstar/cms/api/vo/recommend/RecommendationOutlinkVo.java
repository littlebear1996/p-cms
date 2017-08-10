package com.chinaredstar.cms.api.vo.recommend;

import com.chinaredstar.cms.api.vo.outlink.OutlinkVo;

/**
 * 推荐外链
 */
public class RecommendationOutlinkVo extends RecommendationDataVo {

    private static final long serialVersionUID = -4515760485756978555L;

    private OutlinkVo recommendationData;

    public OutlinkVo getRecommendationData() {
        return recommendationData;
    }

    public void setRecommendationData(OutlinkVo recommendationData) {
        this.recommendationData = recommendationData;
    }
}
