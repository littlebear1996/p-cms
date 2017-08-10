package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.model.CmsTopicDetail;
import com.chinaredstar.cms.api.model.CmsTopicType;
import com.chinaredstar.cms.api.service.TopicService;
import com.chinaredstar.cms.api.vo.topic.TopicDetailQueryVo;
import com.chinaredstar.cms.api.vo.topic.TopicTypeQueryVo;
import com.chinaredstar.cms.api.vo.topic.TopicVo;
import com.chinaredstar.perseus.utils.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ykk on 2016/11/14.
 */
public class TopicServiceImplTest extends BaseTest{

    @Autowired
    private TopicService topicService;

    @Test
    public void testGetTopicDetail(){
        TopicDetailQueryVo vo = new TopicDetailQueryVo();
        vo.setTopicTypeId(2);
        ServiceResult<List<CmsTopicDetail>> topicDetail = topicService.getTopicDetail(vo);
        vo.setTopicTypeId(3);
        topicDetail = topicService.getTopicDetail(vo);
        vo.setTopicTypeId(4);
        topicDetail = topicService.getTopicDetail(vo);
        vo.setTopicTypeId(5);
        topicDetail = topicService.getTopicDetail(vo);
        vo.setTopicTypeId(7);
        topicDetail = topicService.getTopicDetail(vo);
        vo.setTopicTypeId(8);
        topicDetail = topicService.getTopicDetail(vo);
        vo.setTopicTypeId(9);
        topicDetail = topicService.getTopicDetail(vo);
        vo.setTopicTypeId(20);
        topicDetail = topicService.getTopicDetail(vo);

        try{
            vo = null;
            topicDetail = topicService.getTopicDetail(vo);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(topicDetail);
    }

    @Test
    public void testGetTopicType(){
        TopicTypeQueryVo vo = new TopicTypeQueryVo();
        vo.setTopicId(17);
        ServiceResult<List<CmsTopicType>> topicType = topicService.getTopicType(vo);
        System.out.println(topicType);
        try{
            vo = null;
            topicType = topicService.getTopicType(vo);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testGetTopicByIds(){
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ServiceResult<List<TopicVo>> serviceResult = topicService.getTopicByIds(ids);
        System.out.println(JsonUtil.toJson(serviceResult, false));
    }
}
