package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.CmsIndexBrandService;
import com.chinaredstar.cms.api.vo.index.IndexBrandQueryVo;
import com.chinaredstar.cms.api.vo.index.IndexBrandVo;
import com.chinaredstar.perseus.utils.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengfei.wang on 2017/3/26.
 */
public class CmsIndexBrandServiceTest extends BaseTest {

    @Autowired
    private CmsIndexBrandService cmsIndexBrandService;

    @Test
    public void findPageList(){
        IndexBrandQueryVo indexBrandQueryVo = new IndexBrandQueryVo();
        indexBrandQueryVo.setOrderByHottest(true);
        indexBrandQueryVo.setPageNo(1);
        indexBrandQueryVo.setPageSize(10);
        ServiceResult<List<IndexBrandVo>> brandServiceResult = cmsIndexBrandService.findPageList(indexBrandQueryVo);
        System.out.println(JsonUtil.toJson(brandServiceResult, false));
    }

    @Test
    public void findById(){
        ServiceResult<IndexBrandVo> serviceResult = cmsIndexBrandService.findById(1);
        System.out.println(JsonUtil.toJson(serviceResult, false));
    }

    @Test
    public void findByIds(){
        List<Integer> ids = new ArrayList<>();
        ids.add(4);
        ids.add(3);
        ids.add(2);
        ServiceResult<List<IndexBrandVo>> serviceResult = cmsIndexBrandService.findByIds(ids);
        System.out.println(JsonUtil.toJson(serviceResult, false));
    }
}
