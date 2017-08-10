package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.AdvertisementPosition;

public interface AdvertisementPositionMapper extends BaseMapper<AdvertisementPosition>{

    /**
     * 根据广告编号查询广告位置信息
     * @param code
     * @return
     */
    AdvertisementPosition getAdvertisementPositionByCode(String code);

}