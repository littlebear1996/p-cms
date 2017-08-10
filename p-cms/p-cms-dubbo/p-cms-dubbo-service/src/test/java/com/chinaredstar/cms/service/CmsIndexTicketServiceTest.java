package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.CmsIndexTicketService;
import com.chinaredstar.cms.api.vo.index.IndexTicketVo;
import com.chinaredstar.perseus.utils.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by pengfei.wang on 2017/4/1.
 */
public class CmsIndexTicketServiceTest extends BaseTest {

    @Autowired
    private CmsIndexTicketService cmsIndexTicketService;

    @Test
    public void getLatestByCityId(){
        ServiceResult<IndexTicketVo> serviceResult = cmsIndexTicketService.getLatestByCityId("1");
        System.out.println(JsonUtil.toJson(serviceResult, false));
    }
}
