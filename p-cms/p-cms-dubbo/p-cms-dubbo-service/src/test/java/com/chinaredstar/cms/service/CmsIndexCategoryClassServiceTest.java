package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.CmsIndexCategoryClassService;
import com.chinaredstar.cms.api.vo.index.IndexCategoryClassVo;
import com.chinaredstar.perseus.utils.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by yixin.sun on 2017/3/27.
 */
public class CmsIndexCategoryClassServiceTest extends BaseTest {
    @Autowired
    private CmsIndexCategoryClassService indexCategoryClassService;

    @Test
    public void findListByCategoryId() {
        ServiceResult<List<IndexCategoryClassVo>> serviceResult = indexCategoryClassService.findListByCategoryId(1);
        System.out.println("结果:"+JsonUtil.toJson(serviceResult,false));
    }
}
