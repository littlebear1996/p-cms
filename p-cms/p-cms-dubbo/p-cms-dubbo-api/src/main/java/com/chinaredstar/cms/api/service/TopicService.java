package com.chinaredstar.cms.api.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.model.CmsTopicDetail;
import com.chinaredstar.cms.api.model.CmsTopicType;
import com.chinaredstar.cms.api.vo.topic.TopicDetailQueryVo;
import com.chinaredstar.cms.api.vo.topic.TopicTypeQueryVo;
import com.chinaredstar.cms.api.vo.topic.TopicVo;

import java.util.List;

/**
 * 专题接口
 * Created by Ykk on 2016/11/10.
 */
public interface TopicService {

    /**
     * 获取专题详情
     * @param vo
     * @return
     */
    ServiceResult<List<CmsTopicDetail>> getTopicDetail(TopicDetailQueryVo vo);

    /**
     * 获取专题分类
     * @param vo
     * @return
     */
    ServiceResult<List<CmsTopicType>> getTopicType(TopicTypeQueryVo vo);

    /**
     * 根据id集合查询
     * @param ids
     * @return
     */
    ServiceResult<List<TopicVo>> getTopicByIds(List<Integer> ids);
}
