package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.CmsIndexPromotionService;
import com.chinaredstar.cms.api.vo.index.IndexPromotionQueryVo;
import com.chinaredstar.cms.api.vo.index.IndexPromotionVo;
import com.chinaredstar.perseus.utils.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by yixin.sun on 2017/3/26.
 */
public class CmsIndexPromotionServiceTest extends BaseTest{
    @Autowired
    private CmsIndexPromotionService cmsIndexPromotionService;

    @Test
    public void getOngoingPromotion() {
        IndexPromotionQueryVo queryVo = new IndexPromotionQueryVo();
        queryVo.setCurrentTime(new Date());
        ServiceResult<IndexPromotionVo> serviceResult = cmsIndexPromotionService.getOngoingPromotion(queryVo);
        System.out.println("返回结果:" + JsonUtil.toJson(serviceResult, false));
    }
    @Test
    public void testDeleteByPromotionId() {
        ServiceResult serviceResult = cmsIndexPromotionService.deleteByPromotionId("101");
        System.out.println(serviceResult);
    }

}
