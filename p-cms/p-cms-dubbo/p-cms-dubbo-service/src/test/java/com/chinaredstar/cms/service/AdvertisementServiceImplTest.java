package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.AdvertisementService;
import com.chinaredstar.cms.api.vo.advertisement.AdvertisementQueryVo;
import com.chinaredstar.cms.api.vo.advertisement.AdvertisementVo;
import com.chinaredstar.perseus.utils.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by sunny on 16-8-31.
 */
public class AdvertisementServiceImplTest extends BaseTest {
    @Autowired
    private AdvertisementService advertisementService;

    @Test
    public void listADsByPositionCode() {
        AdvertisementQueryVo queryVo = new AdvertisementQueryVo();
        // queryVo.setCurrentTime(new Date());
        queryVo.setPositionCode("133");
        ServiceResult<List<AdvertisementVo>> serviceResult = advertisementService.listADsByPositionCode(queryVo);
        System.out.println(serviceResult);

        AdvertisementQueryVo queryVo1 = new AdvertisementQueryVo();
        // queryVo.setCurrentTime(new Date());
        //queryVo1.setPositionCode("中国");
        ServiceResult<List<AdvertisementVo>> serviceResult1 = advertisementService.listADsByPositionCode(null);
        System.out.println(serviceResult1);

    }

    @Test
    public void getMarketPromotionAdvert(){
        ServiceResult<List<AdvertisementVo>> serviceResult = advertisementService.getMarketPromotionAdvert();
        if(serviceResult.isSuccess()){
            List<AdvertisementVo> list = serviceResult.getData();
        }

    }

    @Test
    public void getAdvertByFirst(){
        AdvertisementQueryVo queryVo = new AdvertisementQueryVo();
        // queryVo.setCurrentTime(new Date());
        queryVo.setPositionCode("133");
        ServiceResult<AdvertisementVo> serviceResult = advertisementService.getAdvertByFirst(queryVo);
        if(serviceResult.isSuccess()){
            System.out.println(serviceResult.getData());
        }
    }


}
