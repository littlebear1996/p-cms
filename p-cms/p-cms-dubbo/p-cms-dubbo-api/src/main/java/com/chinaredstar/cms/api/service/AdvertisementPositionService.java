package com.chinaredstar.cms.api.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.model.AdvertisementPosition;

/**
 * 广告位置服务接口
 */
public interface AdvertisementPositionService {

    /**
     * 根据广告编号查询广告位置信息
     * @param code
     * @return
     */
    ServiceResult<AdvertisementPosition> getAdvertisementPositionByCode(String code);
}
