package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.ActivityPrizeShare;

import java.util.Map;

public interface ActivityPrizeShareMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityPrizeShare record);

    int insertSelective(ActivityPrizeShare record);

    ActivityPrizeShare selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityPrizeShare record);

    int updateByPrimaryKey(ActivityPrizeShare record);

    int queryShareCount(Map<String,Object> params);
}