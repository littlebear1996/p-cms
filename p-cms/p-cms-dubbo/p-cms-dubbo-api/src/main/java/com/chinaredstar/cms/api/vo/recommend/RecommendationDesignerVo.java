package com.chinaredstar.cms.api.vo.recommend;

import com.chinaredstar.cms.api.vo.collection.CmsCollectionVo;

/**
 * 推荐设计师合辑
 */
public class RecommendationDesignerVo extends RecommendationDataVo {


    private static final long serialVersionUID = -718302797274064494L;

    // 设计师合辑
    private CmsCollectionVo recommendationData;

    public CmsCollectionVo getRecommendationData() {
        return recommendationData;
    }

    public void setRecommendationData(CmsCollectionVo recommendationData) {
        this.recommendationData = recommendationData;
    }
}
