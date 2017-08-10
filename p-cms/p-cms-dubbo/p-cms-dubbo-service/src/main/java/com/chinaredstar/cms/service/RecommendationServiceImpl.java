package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.Page;
import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.RecommendationService;
import com.chinaredstar.cms.api.vo.recommend.RecommendationQueryVo;
import com.chinaredstar.cms.api.vo.recommend.RecommendationVo;
import com.chinaredstar.cms.mapper.*;
import com.chinaredstar.perseus.utils.JsonUtil;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 推荐服务实现类
 */
@Service("recommendationService")
@Timed
public class RecommendationServiceImpl implements RecommendationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecommendationServiceImpl.class);

    @Autowired
    private RecommendationMapper recommendationMapper;

    @Autowired
    private AtlasMapper atlasMapper;

    @Autowired
    private OutlinksMapper outlinksMapper;

    @Autowired
    private ArticleCustomMapper articleCustomMapper;

    @Autowired
    private ArticleHomeMapper articleHomeMapper;

    @Autowired
    private ArticleHouseMapper articleHouseMapper;

    @Autowired
    private ArticleMarketMapper articleMarketMapper;

    @Autowired
    private CmsCollectionMapper cmsCollectionMapper;

    @Override
    public ServiceResult<List<RecommendationVo>> getRecommendationListByType(RecommendationQueryVo queryVo, Page page) {
        ServiceResult<List<RecommendationVo>> serviceResult = new ServiceResult<>(true);
        if (queryVo == null) {
            LOGGER.error("queryVo must be not null.");
            serviceResult.setSuccess(false);
            serviceResult.setMsg("queryVo must be not null.");
            return serviceResult;
        }

        try {
            List<RecommendationVo> recommendationList = recommendationMapper.getRecommendationListByType(queryVo, page);

            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("获取推荐数据 --> Request : {}, Response : {}", JsonUtil.toJson(queryVo, false), JsonUtil.toJson(recommendationList, false));
            }
            serviceResult.setData(recommendationList);
            return serviceResult;
        } catch (Exception e) {
            LOGGER.error("获取推荐数据异常. Cause by : {}", e.getMessage());
            e.printStackTrace();
            serviceResult.setSuccess(false);
            serviceResult.setMsg(e.getMessage());
            return serviceResult;
        }
    }

    //通过dubbo接口,把数据从缓存中清除并更新缓存
    @CacheEvict(value="CMS:RECOMCACHE",allEntries = true,beforeInvocation=true)
    //@CachePut(value="CMS:RECOMCACHE",key = "'CMS:RECOMM:GETRECOMMENDATIONLISTBYTYPE:'+#type+':'+#subType1+':'+#subType2")
    public void removeRecommendationCache(Integer type,
                                   Integer subType1,
                                   Integer subType2){
        //updateRecommendationCache(type,subType1,subType2);
    }

    //更新缓存
    @CachePut(value="CMS:RECOMCACHE",key = "'CMS:RECOMM:GETRECOMMENDATIONLISTBYTYPE:'+#type+':'+#subType1+':'+#subType2")
    public ServiceResult<List<RecommendationVo>> updateRecommendationCache(Integer type,
                                                                           Integer subType1,
                                                                           Integer subType2){
        //手动更新缓存
        //addRecommendationCache(type,subType1,subType2);
        RecommendationQueryVo queryVo = new RecommendationQueryVo();
        queryVo.setType(type);
        queryVo.setSubType1(subType1);
        queryVo.setSubType2(subType2);
        ServiceResult<List<RecommendationVo>> serviceResult = getRecommendationListByType(queryVo, null);
        return serviceResult;
    }


    //添加数据到缓存中
    @Cacheable(value="CMS:RECOMCACHE",key = "'CMS:RECOMM:GETRECOMMENDATIONLISTBYTYPE:'+#type+':'+#subType1+':'+#subType2")
    @Override
    public ServiceResult<List<RecommendationVo>> addRecommendationCache(Integer type, Integer subType1, Integer subType2) {
        RecommendationQueryVo queryVo = new RecommendationQueryVo();
        queryVo.setType(type);
        queryVo.setSubType1(subType1);
        queryVo.setSubType2(subType2);
        ServiceResult<List<RecommendationVo>> serviceResult = getRecommendationListByType(queryVo, null);
        return serviceResult;
    }


}