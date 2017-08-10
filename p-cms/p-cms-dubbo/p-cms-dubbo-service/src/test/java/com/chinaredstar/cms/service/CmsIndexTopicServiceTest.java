package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.CmsIndexTopicService;
import com.chinaredstar.cms.api.vo.index.IndexTopicQueryVo;
import com.chinaredstar.cms.api.vo.index.IndexTopicVo;
import com.chinaredstar.perseus.utils.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by pengfei.wang on 2017/3/26.
 */
public class CmsIndexTopicServiceTest extends BaseTest {

    @Autowired
    private CmsIndexTopicService cmsIndexTopicService;

    @Test
    public void findPageList(){
        IndexTopicQueryVo queryVo = new IndexTopicQueryVo();
//        queryVo.setRecommend(true);
        ServiceResult<List<IndexTopicVo>> serviceResult = cmsIndexTopicService.findPageList(queryVo);
        System.out.println(JsonUtil.toJson(serviceResult, false));
    }
}
