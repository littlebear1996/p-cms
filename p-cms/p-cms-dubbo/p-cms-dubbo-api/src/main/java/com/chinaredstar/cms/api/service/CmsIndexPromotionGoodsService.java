package com.chinaredstar.cms.api.service;

import com.chinaredstar.cms.api.component.ServiceResult;

/**
 * Created by yixin.sun on 2017/5/22.
 */
public interface CmsIndexPromotionGoodsService {
    ServiceResult offlineGoods(String promotionId,String goodsId);
}
