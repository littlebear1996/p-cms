package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.CmsTopic;
import com.chinaredstar.cms.api.model.CmsTopicType;
import com.chinaredstar.cms.api.vo.topic.TopicTypeQueryVo;

import java.util.List;

public interface CmsTopicTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CmsTopic record);

    int insertSelective(CmsTopic record);

    CmsTopic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CmsTopic record);

    int updateByPrimaryKey(CmsTopic record);

    List<CmsTopicType> find(TopicTypeQueryVo vo);
}