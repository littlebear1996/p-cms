package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.CmsIndexCategoryService;
import com.chinaredstar.cms.api.vo.index.IndexCategoryVo;
import com.chinaredstar.perseus.utils.JsonUtil;
import com.redstar.digital.open.bean.OmsBrandInfo;
import com.redstar.digital.open.service.OmsBrandInfoService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengfei.wang on 2017/3/27.
 */
public class CmsIndexCategoryServiceTest extends BaseTest {

    @Autowired
    private CmsIndexCategoryService cmsIndexCategoryService;

    @Autowired
    private OmsBrandInfoService omsBrandInfoService;

    @Test
    public void getAll() {
        ServiceResult<List<IndexCategoryVo>> serviceResult = cmsIndexCategoryService.getAll();
        System.out.println(JsonUtil.toJson(serviceResult, false));
    }

    @Test
    public void findById() {
        ServiceResult<IndexCategoryVo> serviceResult = cmsIndexCategoryService.findById(9);
        System.out.println(JsonUtil.toJson(serviceResult, false));
    }

    @Test
    public void findBrand() {
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        com.redstar.digital.open.vo.ServiceResult<List<OmsBrandInfo>> serviceResult = omsBrandInfoService.getOmsBrandInfoById(ids);
        System.out.println(JsonUtil.toJson(serviceResult, false));
    }
}
