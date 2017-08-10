package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.CmsIndexPromotionGoodsService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by yixin.sun on 2017/5/26.
 */
public class CmsIndexPromotionGoodsServiceImplTest extends BaseTest {

    @Autowired
    private CmsIndexPromotionGoodsService indexPromotionGoodsService;

    @Test
    public void testDeleteByPromotionId() {
        ServiceResult serviceResult = indexPromotionGoodsService.offlineGoods("101", "101");
        System.out.println(serviceResult);

    }
}
