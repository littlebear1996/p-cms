package com.chinaredstar.cms.api.component;

import com.chinaredstar.cms.api.vo.recommend.RecommendationDataVo;

import java.util.Comparator;

/**
 * 推荐排序
 */
public class RecommendationVoComparator implements Comparator<RecommendationDataVo> {


    @Override
    public int compare(RecommendationDataVo o1, RecommendationDataVo o2) {
        if (o1.getRecommendation().getSortId() < o2.getRecommendation().getSortId()) {
            return -1;
        } else if (o1.getRecommendation().getSortId().equals(o2.getRecommendation().getSortId())) {
            return 0;
        } else {
            return 1;
        }
    }
}
