package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.TbCmsIndexPromotion;
import com.chinaredstar.cms.api.vo.index.IndexPromotionQueryVo;
import com.chinaredstar.cms.api.vo.index.IndexPromotionVo;
import org.apache.ibatis.annotations.Param;

public interface TbCmsIndexPromotionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbCmsIndexPromotion record);

    int insertSelective(TbCmsIndexPromotion record);

    TbCmsIndexPromotion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbCmsIndexPromotion record);

    int updateByPrimaryKey(TbCmsIndexPromotion record);

    int deleteByPromotionId(@Param("promotionId") String promotionId);

    IndexPromotionVo findOngoingPromotion(@Param("queryVo") IndexPromotionQueryVo queryVo);

    IndexPromotionVo findCurrentLatestPromotion(@Param("queryVo") IndexPromotionQueryVo queryVo);
}