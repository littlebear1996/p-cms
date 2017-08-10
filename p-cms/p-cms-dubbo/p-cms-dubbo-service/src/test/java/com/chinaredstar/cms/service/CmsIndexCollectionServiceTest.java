package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.model.CmsCollection;
import com.chinaredstar.cms.api.service.CmsIndexCollectionService;
import com.chinaredstar.cms.api.vo.collection.CmsCollectionQueryVo;
import com.chinaredstar.cms.api.vo.index.IndexCollectionVo;
import com.chinaredstar.perseus.utils.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by pengfei.wang on 2017/3/29.
 */
public class CmsIndexCollectionServiceTest extends BaseTest {

    @Autowired
    private CmsIndexCollectionService cmsIndexCollectionService;

    @Test
    public void findCollectionByType() {
        CmsCollectionQueryVo queryVo = new CmsCollectionQueryVo();
        queryVo.setTop(true);
        queryVo.setType(CmsCollection.TYPE_MARKET);
        queryVo.setSubType(CmsCollection.SUBTYPE_MARKET_BRAND);
        queryVo.setCityId("310100");
        ServiceResult<List<IndexCollectionVo>> serviceResult = cmsIndexCollectionService.findCollectionByType(queryVo);
        System.out.println(JsonUtil.toJson(serviceResult, false));
    }
}
