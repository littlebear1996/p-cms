package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.Page;
import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.RecommendationService;
import com.chinaredstar.cms.api.vo.recommend.RecommendationQueryVo;
import com.chinaredstar.cms.api.vo.recommend.RecommendationVo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by lenovo on 2016/8/21.
 */
public class RecommendationServiceImplTest extends BaseTest {

    @Autowired
    private RecommendationService recommendationService;

    @Test
    public void testGetRecommendationListByType() throws Exception {
        RecommendationQueryVo queryVo = new RecommendationQueryVo();
        queryVo.setType(5);
        queryVo.setSubType1(1);
        queryVo.setSubType2(2);
        ServiceResult<List<RecommendationVo>> result = recommendationService.getRecommendationListByType(queryVo, new Page(1));
        Assert.assertTrue(result.isSuccess());
        result = recommendationService.getRecommendationListByType(null, null);
        Assert.assertTrue(!result.isSuccess());
    }

    @Test
    public void testAddRecommendationCache(){
        recommendationService.addRecommendationCache(5,1,2);
    }

    @Test
    public void testUpdateRecommendationCache(){
        recommendationService.updateRecommendationCache(5,1,2);
    }

    @Test
    public void testRemoveRecommendationCache() throws Exception {
        recommendationService.removeRecommendationCache(5,1,2);
    }

}
