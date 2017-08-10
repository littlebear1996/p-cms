package com.chinaredstar.cms.api.vo.recommend;

import com.chinaredstar.cms.api.model.Atlas;
import com.chinaredstar.cms.api.vo.atlas.AtlasVo;

/**
 * Created by lenovo on 2016/8/21.
 */
public class RecommendationAtlasVo extends RecommendationDataVo {

    private static final long serialVersionUID = -5902977786753256355L;

    private AtlasVo recommendationData;

    public AtlasVo getRecommendationData() {
        return recommendationData;
    }

    public void setRecommendationData(AtlasVo recommendationData) {
        this.recommendationData = recommendationData;
    }
}
