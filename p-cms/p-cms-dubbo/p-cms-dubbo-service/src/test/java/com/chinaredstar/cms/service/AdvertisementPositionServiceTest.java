package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.model.AdvertisementPosition;
import com.chinaredstar.cms.api.service.AdvertisementPositionService;
import com.chinaredstar.cms.api.service.AdvertisementService;
import com.chinaredstar.cms.api.vo.advertisement.AdvertisementQueryVo;
import com.chinaredstar.cms.api.vo.advertisement.AdvertisementVo;
import com.chinaredstar.perseus.utils.JsonUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by sunny on 16-8-31.
 */
public class AdvertisementPositionServiceTest extends BaseTest{

    @Autowired
    private AdvertisementPositionService advertisementPositionService;

    @Test
    public void getAdvertisementPositionByCode() throws Exception {
        String code = "133";
        ServiceResult<AdvertisementPosition> serviceResult = advertisementPositionService.getAdvertisementPositionByCode(code);
        Assert.assertTrue("getAdvertisementPositionByCode Error",serviceResult.isSuccess());

        ServiceResult<AdvertisementPosition> serviceResult1 = advertisementPositionService.getAdvertisementPositionByCode(null);
        Assert.assertFalse("getAdvertisementPositionByCode Error",serviceResult1.isSuccess());

    }

}