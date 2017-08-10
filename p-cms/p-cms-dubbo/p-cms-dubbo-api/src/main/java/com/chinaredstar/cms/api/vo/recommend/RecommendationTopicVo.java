package com.chinaredstar.cms.api.vo.recommend;

import com.chinaredstar.cms.api.vo.topic.TopicVo;

/**
 * Created by pengfei.wang on 2016/12/28.
 */
public class RecommendationTopicVo extends RecommendationDataVo {

    private static final long serialVersionUID = -6900558680640480098L;

    private TopicVo topicVo;

    public TopicVo getTopicVo() {
        return topicVo;
    }

    public void setTopicVo(TopicVo topicVo) {
        this.topicVo = topicVo;
    }

    public void setRecommendationData(TopicVo recommendationData) {
        this.topicVo = recommendationData;
    }
}
