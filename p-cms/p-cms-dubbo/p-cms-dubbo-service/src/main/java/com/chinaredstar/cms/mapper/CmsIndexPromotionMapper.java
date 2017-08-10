package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.CmsIndexPromotion;
import com.chinaredstar.cms.api.vo.index.IndexPromotionQueryVo;
import com.chinaredstar.cms.api.vo.index.IndexPromotionVo;
import org.apache.ibatis.annotations.Param;

public interface CmsIndexPromotionMapper extends BaseMapper<CmsIndexPromotion> {

    IndexPromotionVo findOngoingPromotion(@Param("queryVo") IndexPromotionQueryVo queryVo);

    IndexPromotionVo findCurrentLatestPromotion(@Param("queryVo") IndexPromotionQueryVo queryVo);
}