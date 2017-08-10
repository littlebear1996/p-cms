package com.chinaredstar.cms.api.service;

import com.chinaredstar.cms.api.model.*;

import java.util.List;
import java.util.Map;

/**
 * 活动接口
 * Created by mdc on 2017/2/14.
 */
public interface ActivityService {

   List<String> getCityByActivityAndCode(int activityId , String code);

   List<ActivityTemplateMarket> getMarketByCityNameAndActivityId(String cityName,int activityId);

   ActivityTemplate getActivityById(Integer id);

   ActivityTemplateMarket getMarketByActivityIdAndMarketId(int activityId,int marketId);

   CmsThreeDimensional queryDataBySpu(Map<String,Object> paramMap);

   List<CmsThreeDimensional> queryDataByBrandId(Map<String,Object> paramMap);

   int addShare(ActivityPrizeShare activityPrizeShare);

   int queryShareCount(Map<String,Object> params);

   List<ActivityPrizeMarket> getPrizeList(Map<String,Object> params);

   int addUserPrize (ActivityPrizeUser activityPrizeUser);

   ActivityPrizeMarket getActivityPrizeMarketById(Integer id);

   int queryPrizeCount(Map<String,Object> params);

   List<ActivityPrizeMarket> queryByDataMap(Map<String,Object> params);

}
