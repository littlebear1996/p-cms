package com.chinaredstar.cms.api.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.vo.advertisement.AdvertisementQueryVo;
import com.chinaredstar.cms.api.vo.index.IndexAdvertisementQueryVo;
import com.chinaredstar.cms.api.vo.index.IndexAdvertisementVo;

import java.util.List;

/**
 * Created by yixin.sun on 2017/3/29.
 */
public interface CmsIndexAdvertisementService {
    /**
     * 通过广告位置编码和城市id获取指定广告
     * @param queryVo
     * @return
     */
    ServiceResult<List<IndexAdvertisementVo>> listADsByPositionCodeAndCityId(IndexAdvertisementQueryVo queryVo);

    /**
     * 通过id 获取广告详情
     * @param id
     * @return
     */
    ServiceResult<IndexAdvertisementVo> getAdvertisementDetailById(Integer id);
}
