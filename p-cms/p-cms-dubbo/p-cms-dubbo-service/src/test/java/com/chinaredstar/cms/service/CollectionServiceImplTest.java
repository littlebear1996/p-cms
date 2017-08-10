package com.chinaredstar.cms.service;



import com.chinaredstar.cms.api.component.Page;
import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.model.CmsCollectionData;
import com.chinaredstar.cms.api.service.CmsCollectionService;
import com.chinaredstar.cms.api.vo.collection.CmsCollectionQueryVo;
import com.chinaredstar.cms.api.vo.collection.CmsCollectionVo;
import com.chinaredstar.perseus.utils.JsonUtil;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;

public class CollectionServiceImplTest extends BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CollectionServiceImplTest.class);

    @Autowired

    private CmsCollectionService collectionService;

    @Test
    public void testFindCollectionByType() {
        CmsCollectionQueryVo queryVo = new CmsCollectionQueryVo();
        queryVo.setType(3);
        queryVo.setSubType(32);
        ServiceResult<List<CmsCollectionVo>> result = collectionService.findCollectionByType(queryVo);
        Assert.assertTrue(result.isSuccess());


    }

    @Test
    public void testGetCmsCollectionListByIds() {
        List<Integer> ids = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            ids.add(i);
        }
        ServiceResult<List<CmsCollectionVo>> result = collectionService.getCmsCollectionListByIds(ids);
        Assert.assertTrue(result.isSuccess());


    }

    @Test
    public void testGetCmsCollectionById() {
        ServiceResult<CmsCollectionVo> result = collectionService.getCmsCollectionById(8);
        Assert.assertTrue(result.isSuccess());

        result = collectionService.getCmsCollectionById(null);
        Assert.assertTrue(!result.isSuccess());
    }

    @Test
    public void testGetCmsCollectionDataListById() {
        Page page = new Page();
        page.setPageNo(1);
        page.setPageSize(10);
        ServiceResult<List<CmsCollectionData>> result = collectionService.getCmsCollectionDataListById(9, page);
        Assert.assertTrue(result.isSuccess());

        result = collectionService.getCmsCollectionDataListById(null, null);
        Assert.assertTrue(!result.isSuccess());
    }

    @Test
    public void testGetCmsCollectionListById() {
        ServiceResult<CmsCollectionVo> result = collectionService.getCmsCollectionListById(5);
        Assert.assertTrue(result.isSuccess());

        result = collectionService.getCmsCollectionListById(null);
        Assert.assertTrue(!result.isSuccess());
    }

    @Test
    public void testGetCmsSalesCollectionByMarketId() {
        ServiceResult<CmsCollectionVo> result = collectionService.getCmsSalesCollectionByMarketId(133);
        System.out.println(JsonUtil.toJson(result, false));
        Assert.assertTrue(result.isSuccess());

        result = collectionService.getCmsSalesCollectionByMarketId(null);
        Assert.assertTrue(!result.isSuccess());
    }


}
