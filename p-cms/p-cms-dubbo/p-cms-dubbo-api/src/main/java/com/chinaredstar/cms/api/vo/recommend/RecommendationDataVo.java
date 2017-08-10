package com.chinaredstar.cms.api.vo.recommend;

import java.io.Serializable;

/**
 * 推荐数据信息VO
 */
public class RecommendationDataVo implements Serializable {

    private static final long serialVersionUID = -6054775046360288272L;

    // 推荐基本信息
    private RecommendationVo recommendation;

    public RecommendationVo getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(RecommendationVo recommendation) {
        this.recommendation = recommendation;
    }
}
