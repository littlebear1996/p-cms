package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.CmsIndexTopic;
import com.chinaredstar.cms.api.vo.index.IndexTopicQueryVo;
import com.chinaredstar.cms.api.vo.index.IndexTopicVo;

import java.util.List;

public interface CmsIndexTopicMapper extends BaseMapper<CmsIndexTopic> {

    List<IndexTopicVo> findPageList(IndexTopicQueryVo queryVo);
}