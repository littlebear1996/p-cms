package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.model.CmsTopicDetail;
import com.chinaredstar.cms.api.vo.topic.TopicDetailQueryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CmsTopicDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CmsTopicDetail record);

    int insertSelective(CmsTopicDetail record);

    CmsTopicDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CmsTopicDetail record);

    int updateByPrimaryKey(CmsTopicDetail record);

    List<CmsTopicDetail> find(TopicDetailQueryVo vo);

    List<CmsTopicDetail> findByTopicId(@Param("topicId") Integer topicId);
}