package com.chinaredstar.cms.api.service;

import com.chinaredstar.cms.api.component.Page;
import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.vo.recommend.RecommendationQueryVo;
import com.chinaredstar.cms.api.vo.recommend.RecommendationVo;

import java.util.List;

/**
 * 推荐服务接口
 */
public interface RecommendationService {

    ServiceResult<List<RecommendationVo>> getRecommendationListByType(RecommendationQueryVo queryVo, Page page);

    //通过dubbo接口,把数据从缓存中清除
    void removeRecommendationCache(Integer type,
                                            Integer subType1,
                                            Integer subType2);



    ServiceResult<List<RecommendationVo>> addRecommendationCache(Integer type,
                                                                    Integer subType1,
                                                                    Integer subType2);


    ServiceResult<List<RecommendationVo>> updateRecommendationCache(Integer type,
                                                                Integer subType1,
                                                                Integer subType2);

}
