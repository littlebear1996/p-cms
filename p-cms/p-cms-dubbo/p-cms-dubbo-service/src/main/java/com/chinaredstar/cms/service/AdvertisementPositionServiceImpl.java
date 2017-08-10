package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.model.AdvertisementPosition;
import com.chinaredstar.cms.api.service.AdvertisementPositionService;
import com.chinaredstar.cms.mapper.AdvertisementPositionMapper;
import com.codahale.metrics.annotation.Timed;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 广告位置服务接口实现
 */
@Service("advertisementPositionService")
@Timed
public class AdvertisementPositionServiceImpl implements AdvertisementPositionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdvertisementPositionServiceImpl.class);

    @Autowired
    private AdvertisementPositionMapper advertisementPositionMapper;

    @Override
    public ServiceResult<AdvertisementPosition> getAdvertisementPositionByCode(String code) {
        ServiceResult<AdvertisementPosition> serviceResult = new ServiceResult<>(true);
        if(StringUtils.isBlank(code)){
            serviceResult.setSuccess(false);
            serviceResult.setMsg("参数错误");
            return serviceResult;
        }

        try {
            AdvertisementPosition advertisementPosition = advertisementPositionMapper.getAdvertisementPositionByCode(code);
            serviceResult.setData(advertisementPosition);
            return serviceResult;
        }catch (Exception e){
            serviceResult.setSuccess(false);
            serviceResult.setMsg("系统异常. " + e.getMessage());
            LOGGER.error("获取广告位置失败. Casue by : ", e);
            return serviceResult;
        }
    }
}
