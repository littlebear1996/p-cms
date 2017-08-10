package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.CmsIndexAdvertisementService;
import com.chinaredstar.cms.api.vo.index.IndexAdvertisementQueryVo;
import com.chinaredstar.cms.api.vo.index.IndexAdvertisementVo;
import com.chinaredstar.perseus.utils.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by yixin.sun on 2017/3/29.
 */
public class CmsIndexAdvertisementServiceTest extends BaseTest {
    @Autowired
    private CmsIndexAdvertisementService indexAdvertisementService;

    @Test
    public void listADsByPositionCodeAndCityId() {
        IndexAdvertisementQueryVo queryVo = new IndexAdvertisementQueryVo();
        queryVo.setCityId("8");
        queryVo.setCurrentTime(new Date());
        queryVo.setPositionCode("133");
        ServiceResult<List<IndexAdvertisementVo>> serviceResult = indexAdvertisementService.listADsByPositionCodeAndCityId(queryVo);
        System.out.println("结果:" + JsonUtil.toJson(serviceResult, false));
    }

}
