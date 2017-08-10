package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.CmsIndexPromotionGoodsService;
import com.chinaredstar.cms.mapper.TbCmsIndexPromotionGoodsMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yixin.sun on 2017/5/22.
 */
@Service("indexPromotionGoodsServiceImpl")
public class CmsIndexPromotionGoodsServiceImpl implements CmsIndexPromotionGoodsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CmsIndexPromotionGoodsServiceImpl.class);

    @Autowired
    private TbCmsIndexPromotionGoodsMapper cmsIndexPromotionGoodsMapper;

    @Override
    public ServiceResult offlineGoods(String promotionId,String goodsId) {
        ServiceResult serviceResult = new ServiceResult(false);
        if (StringUtils.isBlank(goodsId) ||StringUtils.isBlank(promotionId)) {
            serviceResult.setMsg("params must not null");
            return serviceResult;
        }
        try {
            cmsIndexPromotionGoodsMapper.deleteByPromotionIdAndGoodsId(promotionId,goodsId);
            serviceResult.setSuccess(true);
        } catch (Exception e) {
            LOGGER.error("通过promotionId,goodsId删除限时购商品异常：", e);
            serviceResult.setMsg("通过promotionId,goodsId删除限时购商品异常");
        }
        return serviceResult;
    }
}
