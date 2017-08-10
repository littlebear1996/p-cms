package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.service.CmsClassService;
import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.vo.cmsClass.CmsClassQueryVo;
import com.chinaredstar.cms.api.vo.cmsClass.CmsClassTagsVo;
import com.chinaredstar.cms.api.vo.cmsClass.CmsClassVo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/8/29.
 */
public class CmsClassServiceImplTest extends BaseTest{

    @Autowired
    private CmsClassService cmsClassService;

    @Test
    public void testGetCmsClassListByQuery() throws Exception {
        CmsClassQueryVo queryVo = new CmsClassQueryVo();
        queryVo.setCategorySubType(101);
        ServiceResult<List<CmsClassVo>> result = cmsClassService.getCmsClassListByQuery(queryVo);
        Assert.assertTrue(result.isSuccess());


    }

    @Test
    public void testGetCmsClassTagsByIdList(){
        List<Integer> ids = new ArrayList<>();
        for(int i=1;i<=100;i++){
            ids.add(i);
        }
        ServiceResult<List<CmsClassTagsVo>> result = cmsClassService.getCmsClassTagsByIdList(ids);
        Assert.assertTrue(result.isSuccess());


    }

}