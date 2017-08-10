package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.ActivityTemplateMarket;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityTemplateMarketMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityTemplateMarket record);

    int insertSelective(ActivityTemplateMarket record);

    ActivityTemplateMarket selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityTemplateMarket record);

    int updateByPrimaryKeyWithBLOBs(ActivityTemplateMarket record);

    int updateByPrimaryKey(ActivityTemplateMarket record);

    List<ActivityTemplateMarket> findByCityAndActivityId(@Param("city") String cityName, @Param("activityId") int activityId);

    List<String> findByActivityId(@Param("activityId") int activityId);

    ActivityTemplateMarket findByActivityIdAndMarketId(@Param("activityId") int activityId,@Param("marketId") int marketId);
}