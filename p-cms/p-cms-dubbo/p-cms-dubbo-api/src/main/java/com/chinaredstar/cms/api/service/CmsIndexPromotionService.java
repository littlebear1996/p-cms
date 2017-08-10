package com.chinaredstar.cms.api.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.vo.index.IndexPromotionQueryVo;
import com.chinaredstar.cms.api.vo.index.IndexPromotionVo;

/**
 * Created by yixin.sun on 2017/3/24.
 */
public interface CmsIndexPromotionService {
    ServiceResult<IndexPromotionVo> getOngoingPromotion(IndexPromotionQueryVo queryVo);

    ServiceResult deleteByPromotionId(String promotionId);
}
