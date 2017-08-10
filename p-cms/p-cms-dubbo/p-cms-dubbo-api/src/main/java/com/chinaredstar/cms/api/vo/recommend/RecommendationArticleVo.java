package com.chinaredstar.cms.api.vo.recommend;

import com.chinaredstar.cms.api.vo.article.ArticleVo;

/**
 * 推荐文章
 */
public class RecommendationArticleVo extends RecommendationDataVo {

    private static final long serialVersionUID = -7681142589620089531L;

    private ArticleVo recommendationData;

    public ArticleVo getRecommendationData() {
        return recommendationData;
    }

    public void setRecommendationData(ArticleVo recommendationData) {
        this.recommendationData = recommendationData;
    }
}
