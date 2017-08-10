package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.TbCmsIndexPromotionGoods;
import org.apache.ibatis.annotations.Param;

public interface TbCmsIndexPromotionGoodsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TbCmsIndexPromotionGoods record);

    int insertSelective(TbCmsIndexPromotionGoods record);

    TbCmsIndexPromotionGoods selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbCmsIndexPromotionGoods record);

    int updateByPrimaryKey(TbCmsIndexPromotionGoods record);

    int deleteByPromotionIdAndGoodsId(@Param("promotionId") String promotionId, @Param("goodsId") String goodsId);
}