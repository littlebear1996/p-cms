package com.chinaredstar.cms.api.vo.recommend;

import com.chinaredstar.cms.api.model.CmsJzCase;

/**
 * 推荐方案
 */
public class RecommendationCaseVo extends RecommendationDataVo {

    private static final long serialVersionUID = -2209879148368608207L;

    private CmsJzCase recommendationData;

    public CmsJzCase getRecommendationData() {
        return recommendationData;
    }

    public void setRecommendationData(CmsJzCase recommendationData) {
        this.recommendationData = recommendationData;
    }
}
