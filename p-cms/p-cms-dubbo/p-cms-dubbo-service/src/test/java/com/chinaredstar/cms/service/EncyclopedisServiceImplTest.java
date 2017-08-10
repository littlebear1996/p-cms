package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.EncyclopediaService;
import com.chinaredstar.cms.api.vo.encyclopedia.EncyclopediaQueryVo;
import com.chinaredstar.cms.api.vo.encyclopedia.EncyclopediaVo;
import com.chinaredstar.perseus.utils.JsonUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunny on 16-8-30.
 */
public class EncyclopedisServiceImplTest extends BaseTest {
    @Autowired
    private EncyclopediaService encyclopediaService;

    @Test
    public void getEncyclopediaListByIds() {
        List<Integer> idList = new ArrayList<>();
        idList.add(1);
        idList.add(2);
        ServiceResult<List<EncyclopediaVo>> serviceResult = encyclopediaService.getEncyclopediaListByIds(idList);
        System.out.println(JsonUtil.toJson(serviceResult, false));

        serviceResult = encyclopediaService.getEncyclopediaListByIds(null);
        Assert.assertTrue(!serviceResult.isSuccess());
    }

    @Test
    public void getDetailById() {
        Integer id = 1;
        ServiceResult<EncyclopediaVo> serviceResult = encyclopediaService.getDetailById(id);
        System.out.println(JsonUtil.toJson(serviceResult, false));

        serviceResult = encyclopediaService.getDetailById(null);
        Assert.assertTrue(!serviceResult.isSuccess());
    }

    @Test
    public void getEncyclopediaListByTags() {
        String tags = "test,hello";
        ServiceResult<List<EncyclopediaVo>> serviceResult = encyclopediaService.getEncyclopediaListByTags(tags);
        System.out.println(JsonUtil.toJson(serviceResult, false));

        serviceResult = encyclopediaService.getEncyclopediaListByTags(null);
        Assert.assertTrue(!serviceResult.isSuccess());
    }

    @Test
    public void listByType() {
        EncyclopediaQueryVo queryVo = new EncyclopediaQueryVo();
        queryVo.setType(1);
        ServiceResult<List<EncyclopediaVo>> serviceResult = encyclopediaService.listByType(queryVo);
        System.out.println(JsonUtil.toJson(serviceResult,false));

        serviceResult = encyclopediaService.listByType(null);
        Assert.assertTrue(!serviceResult.isSuccess());
    }
}
