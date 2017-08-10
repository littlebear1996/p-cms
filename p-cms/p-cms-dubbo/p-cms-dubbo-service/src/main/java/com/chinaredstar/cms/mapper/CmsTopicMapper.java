package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.CmsTopic;
import com.chinaredstar.cms.api.vo.topic.TopicVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CmsTopicMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CmsTopic record);

    int insertSelective(CmsTopic record);

    CmsTopic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CmsTopic record);

    int updateByPrimaryKey(CmsTopic record);

    List<TopicVo> getTopicByIds(@Param("ids") List<Integer> ids);
}