package com.chinaredstar.cms.api.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.vo.advertisement.AdvertisementQueryVo;
import com.chinaredstar.cms.api.vo.advertisement.AdvertisementVo;

import java.util.List;

/**
 * Created by sunny on 16-8-16.
 */
public interface AdvertisementService {
    /**
     * 通过广告位置编码获取指定广告
     * @param queryVo
     * @return
     */
    ServiceResult<List<AdvertisementVo>> listADsByPositionCode(AdvertisementQueryVo queryVo);

    ServiceResult<List<AdvertisementVo>> getMarketPromotionAdvert();

    ServiceResult<AdvertisementVo> getAdvertByFirst(AdvertisementQueryVo queryVo);

}
