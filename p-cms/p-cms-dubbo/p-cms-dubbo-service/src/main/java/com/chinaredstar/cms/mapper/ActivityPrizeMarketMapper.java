package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.ActivityPrizeMarket;

import java.util.List;
import java.util.Map;

public interface ActivityPrizeMarketMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityPrizeMarket record);

    int insertSelective(ActivityPrizeMarket record);

    ActivityPrizeMarket selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityPrizeMarket record);

    int updateByPrimaryKey(ActivityPrizeMarket record);

    List<ActivityPrizeMarket> queryPrizeList(Map<String,Object> params);

    List<ActivityPrizeMarket> queryByDataMap(Map<String,Object> params);

    int queryPrizeCount(Map<String,Object> params);
}