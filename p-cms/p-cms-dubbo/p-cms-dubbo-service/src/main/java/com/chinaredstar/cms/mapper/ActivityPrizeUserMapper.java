package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.ActivityPrizeUser;

import java.util.Map;

public interface ActivityPrizeUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityPrizeUser record);

    int insertSelective(ActivityPrizeUser record);

    ActivityPrizeUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityPrizeUser record);

    int updateByPrimaryKey(ActivityPrizeUser record);

    int queryUserPrize(Map<String,Object> params);

    int queryPrizeCount(Map<String,Object> params);
}