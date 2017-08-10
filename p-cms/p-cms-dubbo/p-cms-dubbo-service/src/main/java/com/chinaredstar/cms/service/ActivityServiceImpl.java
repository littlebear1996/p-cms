package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.model.*;
import com.chinaredstar.cms.api.service.ActivityService;
import com.chinaredstar.cms.mapper.*;
import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by mdc on 2017/2/15.
 */
@Service("activityService")
@Timed
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityTemplateMapper activityTemplateMapper;


    @Autowired
    private ActivityTemplateMarketMapper activityTemplateMarketMapper;

    @Autowired
    CmsThreeDimensionalMapper cmsThreeDimensionalMapper;

    /**
     * 根据活动id查询活动下参加的商场所在城市列表，返回城市名称（直接查询活动库）
     * @param activityId
     * @param code
     * @return
     */
    @Override
    public List<String> getCityByActivityAndCode(int activityId, String code) {
        return activityTemplateMarketMapper.findByActivityId(activityId);
    }


    @Override
    public List<ActivityTemplateMarket> getMarketByCityNameAndActivityId(String cityName, int activityId) {
        return activityTemplateMarketMapper.findByCityAndActivityId(cityName, activityId);
    }


    @Override
    public ActivityTemplate getActivityById(Integer id) {
        return activityTemplateMapper.selectByPrimaryKey(id);
    }


    @Override
    public ActivityTemplateMarket getMarketByActivityIdAndMarketId(int activityId, int marketId) {
        return activityTemplateMarketMapper.findByActivityIdAndMarketId(activityId,marketId);
    }
    @Override
    public CmsThreeDimensional queryDataBySpu(Map<String,Object> paramMap){
        return cmsThreeDimensionalMapper.queryDataBySpu(paramMap);
    }

    @Override
    public List<CmsThreeDimensional> queryDataByBrandId(Map<String, Object> paramMap) {
        return cmsThreeDimensionalMapper.queryDataByBrandId(paramMap);
    }

    /**************************以下内容为一次性需求***********************************/

    @Autowired
    ActivityPrizeShareMapper activityPrizeShareMapper;

    @Autowired
    ActivityPrizeMarketMapper activityPrizeMarketMapper;

    @Autowired ActivityPrizeUserMapper activityPrizeUserMapper;

    /**
     * 添加分享记录
     * @param activityPrizeShare
     * @return
     */

    @Override
    public int addShare(ActivityPrizeShare activityPrizeShare) {
        Map<String,Object> params = Maps.newHashMap();
        params.put("openId",activityPrizeShare.getOpenId());

        if (activityPrizeShareMapper.queryShareCount(params)>=10){
            return 10;
        }

        return activityPrizeShareMapper.insertSelective(activityPrizeShare);
    }

    @Override
    public int queryShareCount(Map<String,Object> params){
        if (params.get("gameRound")!=null){
            return  activityPrizeUserMapper.queryUserPrize(params);
        }
        return activityPrizeShareMapper.queryShareCount(params);
    }

    @Override
    public List<ActivityPrizeMarket> getPrizeList(Map<String,Object> params){
        return  activityPrizeMarketMapper.queryPrizeList(params);
    }

    @Override
    public int addUserPrize(ActivityPrizeUser activityPrizeUser) {


/*        Map<String,Object> tempParams= Maps.newHashMap();

        tempParams.put("openId",activityPrizeUser.getOpenId());
        tempParams.put("gameRound",activityPrizeUser.getGameRound());

        if (activityPrizeUserMapper.queryUserPrize(tempParams)>0){
            return 500;
        }*/

/*        if (activityPrizeUser.getGameRound()==1){
            if (activityPrizeUserMapper.queryUserPrize(tempParams)>0){
                return 500;
            }
        }else{
            tempParams.put("prizeId",activityPrizeUser.getPrizeId());
            tempParams.put("marketCode",activityPrizeUser.getMarketCode());
            if (activityPrizeUserMapper.queryUserPrize(tempParams)>0){
                return 500;
            }
        }*/






/*        if (activityPrizeUser.getGameRound()==1){

            Map<String,Object> tempParams= Maps.newHashMap();
            tempParams.put("marketCode",activityPrizeUser.getMarketCode());
            tempParams.put("openId",activityPrizeUser.getOpenId());
            tempParams.put("gameRound",activityPrizeUser.getOpenId());
            if (activityPrizeUserMapper.queryUserPrize(tempParams)>0){
                return 500;
            }

        }else if (activityPrizeUser.getGameRound()==2){
            if (activityPrizeUserMapper.queryUserPrize(params)>0){
                return 500;
            }
        }*/


        if (activityPrizeUser.getGameRound()==2){
            Map<String,Object> params = Maps.newHashMap();
            params.put("prizeId",activityPrizeUser.getPrizeId());
            //params.put("openId",activityPrizeUser.getOpenId());
            if (activityPrizeMarketMapper.queryPrizeCount(params)<=0){
                return 401;
            }
        }

        activityPrizeUser.setCreateDate(new Date());
        return  activityPrizeUserMapper.insertSelective(activityPrizeUser);
    }

    @Override
    public ActivityPrizeMarket getActivityPrizeMarketById(Integer id) {
        return  activityPrizeMarketMapper.selectByPrimaryKey(id);
    }

    @Override
    public int queryPrizeCount(Map<String, Object> params) {
        return activityPrizeUserMapper.queryPrizeCount(params);
    }

    @Override
    public List<ActivityPrizeMarket> queryByDataMap(Map<String, Object> params) {
        return activityPrizeMarketMapper.queryByDataMap(params);
    }


}
