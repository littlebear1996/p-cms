package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.CmsIndexLifeService;
import com.chinaredstar.cms.api.vo.index.IndexLifeQueryVo;
import com.chinaredstar.cms.api.vo.index.IndexLifeVo;
import com.chinaredstar.perseus.utils.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yixin.sun on 2017/3/26.
 */
public class CmsIndexLifeServiceTest extends BaseTest {
    @Autowired
    private CmsIndexLifeService indexLifeService;

    @Test
    public void findPageList() {
        IndexLifeQueryVo queryVo = new IndexLifeQueryVo();
//        queryVo.setRecommend(true);
        queryVo.setNewestOrHotest(true);
        queryVo.setPageNo(1);
        queryVo.setPageSize(10);
        ServiceResult<List<IndexLifeVo>> serviceResult = indexLifeService.findPageList(queryVo);
        System.out.println("最新结果:" + JsonUtil.toJson(serviceResult, false));

        queryVo.setNewestOrHotest(false);
        serviceResult = indexLifeService.findPageList(queryVo);
        System.out.println("最热结果:" + JsonUtil.toJson(serviceResult, false));

        queryVo.setRecommend(true);
        serviceResult = indexLifeService.findPageList(queryVo);
        System.out.println("首页推荐结果:" + JsonUtil.toJson(serviceResult, false));

    }

    @Test
    public void findById() {
        ServiceResult<IndexLifeVo> serviceResult = indexLifeService.findById(1);
        System.out.println("结果:" + JsonUtil.toJson(serviceResult, false));
    }

    @Test
    public void findByIdList() {
        List<Integer> idList = new ArrayList<>();
        idList.add(1);
        idList.add(3);
        idList.add(2);
        idList.add(4);
        ServiceResult<List<IndexLifeVo>> serviceResult = indexLifeService.findByIdList(idList);
        System.out.println("结果:" + JsonUtil.toJson(serviceResult, false));
    }
}
