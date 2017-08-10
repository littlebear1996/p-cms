package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.CmsIndexModuleConfigService;
import com.chinaredstar.cms.api.vo.index.IndexModuleConfigVo;
import com.chinaredstar.perseus.utils.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by pengfei.wang on 2017/3/26.
 */
public class CmsIndexModuleConfigServiceTest extends BaseTest {

    @Autowired
    private CmsIndexModuleConfigService cmsIndexModuleConfigService;

    @Test
    public void getOnlineConfig(){
        ServiceResult<List<IndexModuleConfigVo>> serviceResult = cmsIndexModuleConfigService.getOnlineConfig();
        System.out.println(JsonUtil.toJson(serviceResult, false));
    }
}
