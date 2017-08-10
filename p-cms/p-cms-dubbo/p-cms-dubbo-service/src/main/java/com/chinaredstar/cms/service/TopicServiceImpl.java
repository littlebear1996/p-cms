package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.model.CmsTopicDetail;
import com.chinaredstar.cms.api.model.CmsTopicType;
import com.chinaredstar.cms.api.service.TopicService;
import com.chinaredstar.cms.api.vo.advertisement.AdvertisementVo;
import com.chinaredstar.cms.api.vo.article.ArticleCustomVo;
import com.chinaredstar.cms.api.vo.article.ArticleHomeVo;
import com.chinaredstar.cms.api.vo.article.ArticleHouseVo;
import com.chinaredstar.cms.api.vo.article.ArticleMarketVo;
import com.chinaredstar.cms.api.vo.atlas.AtlasVo;
import com.chinaredstar.cms.api.vo.collection.CmsCollectionVo;
import com.chinaredstar.cms.api.vo.encyclopedia.EncyclopediaVo;
import com.chinaredstar.cms.api.vo.outlink.OutlinkVo;
import com.chinaredstar.cms.api.vo.topic.TopicDetailQueryVo;
import com.chinaredstar.cms.api.vo.topic.TopicTypeQueryVo;
import com.chinaredstar.cms.api.vo.topic.TopicVo;
import com.chinaredstar.cms.mapper.*;
import com.chinaredstar.jiazhuang.api.AnswerServiceApi;
import com.chinaredstar.jiazhuang.api.bean.Result;
import com.chinaredstar.jiazhuang.api.dto.JzAskListDto;
import com.chinaredstar.perseus.utils.JsonUtil;
import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Ykk on 2016/11/11.
 */
@Service("topicService")
@Timed
public class TopicServiceImpl implements TopicService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TopicServiceImpl.class);

    @Autowired
    private CmsTopicDetailMapper cmsTopicDetailMapper;

    @Autowired
    private CmsCollectionMapper cmsCollectionMapper;

    @Autowired
    private CmsTopicTypeMapper cmsTopicTypeMapper;

    @Autowired
    private CmsTopicMapper cmsTopicMapper;

    @Autowired
    private EncyclopediaMapper encyclopediaMapper;

    @Autowired
    private AdvertisementMapper advertisementMapper;

    @Autowired
    private ArticleHomeMapper articleHomeMapper;

    @Autowired
    private ArticleMarketMapper articleMarketMapper;

    @Autowired
    private ArticleCustomMapper articleCustomMapper;

    @Autowired
    private ArticleHouseMapper articleHouseMapper;

    @Autowired
    private AtlasMapper atlasMapper;

    @Autowired
    private OutlinksMapper outlinksMapper;

    @Autowired
    private AnswerServiceApi answerServiceApi;

    /**
     * 查询专题详情
     *
     * @param vo
     * @return
     */
    @Override
    public ServiceResult<List<CmsTopicDetail>> getTopicDetail(TopicDetailQueryVo vo) {

        long start = System.currentTimeMillis();
        ServiceResult<List<CmsTopicDetail>> serviceResult = new ServiceResult<List<CmsTopicDetail>>();
        if (vo == null) {
            LOGGER.error("queryVo must not be null");
            serviceResult.setSuccess(false);
            serviceResult.setMsg("queryVo must not be null");
            return serviceResult;
        }
        try {
            List<CmsTopicDetail> topDicList = cmsTopicDetailMapper.find(vo);

            //1,合辑
            Map<Integer, Integer> collectionIdMap = new LinkedHashMap<>();
            //2,百科
            Map<Integer, Integer> encyclopedIdMap = new LinkedHashMap<>();
            //3,广告
            Map<Integer, Integer> adIdMap = new LinkedHashMap<>();
            //4,家装文章
            Map<Integer, Integer> articleHomeIdMap = new LinkedHashMap<>();
            //4,商场文章
            Map<Integer, Integer> articleMarketIdMap = new LinkedHashMap<>();
            //4,商品文章
            Map<Integer, Integer> articleCustomeIdMap = new LinkedHashMap<>();
            //4,房产文章
            Map<Integer, Integer> articleHouseIdMap = new LinkedHashMap<>();
            //5,图集
            Map<Integer, Integer> atlasIdMap = new LinkedHashMap<>();
            //6,外链
            Map<Integer, Integer> outLinkIdMap = new LinkedHashMap<>();
            //7,家装问答
            Map<Integer, Integer> homeQuestionIdMap = new HashMap<>();


            if (null != topDicList && !topDicList.isEmpty()) {
                //数据处理、排序
                for (CmsTopicDetail topicDetail : topDicList) {
                    //分组
                    try {
                        switch (topicDetail.getContentType()) {
                            case 11:
                                homeQuestionIdMap.put(topicDetail.getId(), Integer.valueOf(topicDetail.getContent()));
                                break;
                            case 12://设计师合辑
                                collectionIdMap.put(topicDetail.getId(), Integer.valueOf(topicDetail.getContent()));
                                break;
                            case 13://家装百科合辑
                                collectionIdMap.put(topicDetail.getId(), Integer.valueOf(topicDetail.getContent()));
                                break;
                            case 14://家装百科
                                encyclopedIdMap.put(topicDetail.getId(), Integer.valueOf(topicDetail.getContent()));
                                break;
                            case 15://家装文章
                                articleHomeIdMap.put(topicDetail.getId(), Integer.valueOf(topicDetail.getContent()));
                                break;
                            case 16://家装广告
                                adIdMap.put(topicDetail.getId(), Integer.valueOf(topicDetail.getContent()));
                                break;
                            case 17://家装图集
                                atlasIdMap.put(topicDetail.getId(), Integer.valueOf(topicDetail.getContent()));
                                break;
                            case 18://家装外链
                                outLinkIdMap.put(topicDetail.getId(), Integer.valueOf(topicDetail.getContent()));
                                break;
                            case 21://商品问答

                                break;
                            case 22://商品百科
                                encyclopedIdMap.put(topicDetail.getId(), Integer.valueOf(topicDetail.getContent()));
                                break;
                            case 23://商品文章
                                articleCustomeIdMap.put(topicDetail.getId(), Integer.valueOf(topicDetail.getContent()));
                                break;
                            case 24://商品广告位
                                adIdMap.put(topicDetail.getId(), Integer.valueOf(topicDetail.getContent()));
                                break;
                            case 25://商品图集
                                atlasIdMap.put(topicDetail.getId(), Integer.valueOf(topicDetail.getContent()));
                                break;
                            case 26://商品外链
                                outLinkIdMap.put(topicDetail.getId(), Integer.valueOf(topicDetail.getContent()));
                                break;
                            case 31://房产问答

                                break;
                            case 32://学校合辑
                                collectionIdMap.put(topicDetail.getId(), Integer.valueOf(topicDetail.getContent()));
                                break;
                            case 33://小区合辑
                                collectionIdMap.put(topicDetail.getId(), Integer.valueOf(topicDetail.getContent()));
                                break;
                            case 34://楼盘合辑
                                collectionIdMap.put(topicDetail.getId(), Integer.valueOf(topicDetail.getContent()));
                                break;
                            case 35://房产百科
                                encyclopedIdMap.put(topicDetail.getId(), Integer.valueOf(topicDetail.getContent()));
                                break;
                            case 36://房产文章
                                articleHouseIdMap.put(topicDetail.getId(), Integer.valueOf(topicDetail.getContent()));
                                break;
                            case 37://房产广告
                                adIdMap.put(topicDetail.getId(), Integer.valueOf(topicDetail.getContent()));
                                break;
                            case 38://房产图集
                                atlasIdMap.put(topicDetail.getId(), Integer.valueOf(topicDetail.getContent()));
                                break;
                            case 41://品牌合辑
                                collectionIdMap.put(topicDetail.getId(), Integer.valueOf(topicDetail.getContent()));
                                break;
                            case 42://导购员合辑
                                collectionIdMap.put(topicDetail.getId(), Integer.valueOf(topicDetail.getContent()));
                                break;
                            case 43://商场外链合辑
                                outLinkIdMap.put(topicDetail.getId(), Integer.valueOf(topicDetail.getContent()));
                                break;
                            case 44://商场文章合辑
                                collectionIdMap.put(topicDetail.getId(), Integer.valueOf(topicDetail.getContent()));
                                break;
                            case 45://商场文章
                                articleMarketIdMap.put(topicDetail.getId(), Integer.valueOf(topicDetail.getContent()));
                                break;
                            case 46://商场广告位
                                adIdMap.put(topicDetail.getId(), Integer.valueOf(topicDetail.getContent()));
                                break;
                            default:
                                break;
                        }
                    } catch (NumberFormatException e) {
                        LOGGER.error("数字格式错误:", e);
                        continue;
                    }
                }

                Map<Integer, Object> allData = new HashMap<>();
                allData.putAll(getCollectionData(collectionIdMap));
                allData.putAll(getEncyclopedData(encyclopedIdMap));
                allData.putAll(getAdvertisementData(adIdMap));
                allData.putAll(getArticleHomeData(articleHomeIdMap));
                allData.putAll(getArticleMarketData(articleMarketIdMap));
                allData.putAll(getArticleCustomData(articleCustomeIdMap));
                allData.putAll(getArticleHouseData(articleHouseIdMap));
                allData.putAll(getAtlasData(atlasIdMap));
                allData.putAll(getOutlinksMapperData(outLinkIdMap));
                allData.putAll(getHomeQuestionData(homeQuestionIdMap));
                for (CmsTopicDetail topicDetail : topDicList) {
                    String content = topicDetail.getContent();
                    if (NumberUtils.isNumber(content)) {
                        Object obj = allData.get(topicDetail.getId());
                        if (obj != null) {
                            topicDetail.setTargerVo(obj);
                        }
                    }
                }
            }
            serviceResult.setData(topDicList);
            serviceResult.setSuccess(true);
        } catch (Exception e) {
            LOGGER.error("获取专题详情信息数据失败, {}", e);
            serviceResult.setSuccess(false);
            serviceResult.setMsg(e.toString());
        }
        long end = System.currentTimeMillis();
        long time = end - start;
        LOGGER.info("获取专题详情信息数据成功,返回数据：" + JsonUtil.toJson(serviceResult, false) + ",耗时：" + (time / 1000) + "秒" + (time % 1000) + "毫秒");
        return serviceResult;
    }

    private Map<Integer, JzAskListDto> getHomeQuestionData(Map<Integer, Integer> idMap) {
        Map<Integer, JzAskListDto> data = new HashedMap();
        if (idMap == null || idMap.isEmpty()) {
            return data;
        }
        Result<List<JzAskListDto>> result = answerServiceApi.getAskListByIds(Lists.newArrayList(idMap.values()));
        if (result != null && result.getCode().equals("200")) {
            for (Integer key : idMap.keySet()) {
                for (JzAskListDto jzAskList : result.getDataMap()) {
                    if (jzAskList.getAskId().equals(idMap.get(key))) {
                        data.put(key, jzAskList);
                        break;
                    }
                }
            }
        }
        return data;
    }

    @Override
    public ServiceResult<List<CmsTopicType>> getTopicType(TopicTypeQueryVo vo) {
        long start = System.currentTimeMillis();
        ServiceResult<List<CmsTopicType>> serviceResult = new ServiceResult<>();
        if (vo == null) {
            LOGGER.error("queryVo must not be null");
            serviceResult.setSuccess(false);
            serviceResult.setMsg("queryVo must not be null");
            return serviceResult;
        }
        try {
            List<CmsTopicType> topTypeList = cmsTopicTypeMapper.find(vo);
            serviceResult.setData(topTypeList);
            serviceResult.setSuccess(true);
        } catch (Exception e) {
            LOGGER.error("获取专题分类数据失败, {}", e);
            serviceResult.setSuccess(false);
            serviceResult.setMsg(e.toString());
        }
        long end = System.currentTimeMillis();
        long time = end - start;
        LOGGER.info("获取专题分类信息数据成功,返回数据：" + serviceResult + ",耗时：" + (time / 1000) + "秒" + (time % 1000) + "毫秒");
        return serviceResult;
    }

    @Override
    public ServiceResult<List<TopicVo>> getTopicByIds(List<Integer> ids) {
        ServiceResult<List<TopicVo>> serviceResult = new ServiceResult<>(true);
        try {
            if (CollectionUtils.isNotEmpty(ids)) {
                List<TopicVo> topics = cmsTopicMapper.getTopicByIds(ids);
                if (topics != null && !topics.isEmpty()) {
                    for (TopicVo topicVo : topics) {
                        if (topicVo.getType() == 1) { //家装专题需要topicDetailList和topicTypeList
                            TopicTypeQueryVo queryVo = new TopicTypeQueryVo();
                            queryVo.setTopicId(topicVo.getId());
                            topicVo.setTopicTypeList(cmsTopicTypeMapper.find(queryVo));
                            List<CmsTopicDetail> topicDetails = cmsTopicDetailMapper.findByTopicId(topicVo.getId());
                            if (topicDetails != null && !topicDetails.isEmpty()) {
                                for (CmsTopicDetail topicDetail : topicDetails) {
                                    if (topicDetail.getContentType() == 17) {
                                        AtlasVo atlasVo = atlasMapper.getDetailById(Integer.valueOf(topicDetail.getContent()));
                                        if (atlasVo != null) {
                                            topicDetail.setPicCount(atlasVo.getCount());
                                        }
                                    }
                                }
                            }
                            topicVo.setTopicDetailList(topicDetails);
                        }
                    }
                }
                serviceResult.setData(topics);
            }
        } catch (Exception e) {
            LOGGER.error("根据id集合查询专题列表出错, ids:{}", JsonUtil.toJson(ids, false), e);
            serviceResult.setSuccess(false);
            serviceResult.setMsg("根据id集合查询专题列表出错");
        }
        return serviceResult;
    }


    /**
     * 获取合辑数据
     *
     * @param idMap
     * @return
     */
    private Map<Integer, CmsCollectionVo> getCollectionData(Map<Integer, Integer> idMap) {
        Map<Integer, CmsCollectionVo> map = new HashMap();
        if (MapUtils.isNotEmpty(idMap)) {
            List<CmsCollectionVo> cmsCollectionList =
                    cmsCollectionMapper.getCollectionListByIds(Lists.newArrayList(idMap.values()));
            for (Integer key : idMap.keySet()) {
                for (CmsCollectionVo cmsCollectionVo : cmsCollectionList) {
                    if (cmsCollectionVo.getId().equals(idMap.get(key))) {
                        map.put(key, cmsCollectionVo);
                        break;
                    }
                }
            }
        }
        return map;
    }

    /**
     * 获取百科数据
     *
     * @param idMap
     * @return
     */
    private Map<Integer, EncyclopediaVo> getEncyclopedData(Map<Integer, Integer> idMap) {
        Map<Integer, EncyclopediaVo> map = new HashMap();
        if (MapUtils.isNotEmpty(idMap)) {
            List<EncyclopediaVo> list =
                    encyclopediaMapper.getEncyclopediaListByIds(Lists.newArrayList(idMap.values()));
            for (Integer key : idMap.keySet()) {
                for (EncyclopediaVo vo : list) {
                    if ((idMap.get(key).equals(vo.getId().intValue()))) {
                        map.put(key, vo);
                        break;
                    }
                }
            }
        }
        return map;
    }

    /**
     * 获得广告数据
     *
     * @param idMap
     * @return
     */
    private Map<Integer, AdvertisementVo> getAdvertisementData(Map<Integer, Integer> idMap) {
        Map<Integer, AdvertisementVo> map = new HashMap();
        if (MapUtils.isNotEmpty(idMap)) {
            List<AdvertisementVo> list =
                    advertisementMapper.getAdvertisementListByIds(Lists.newArrayList(idMap.values()));
            for (Integer key : idMap.keySet()) {
                for (AdvertisementVo vo : list) {
                    if (vo.getId().equals(idMap.get(key))) {
                        map.put(key, vo);
                        break;
                    }
                }
            }
        }
        return map;
    }

    /**
     * 获得家装文章数据
     *
     * @param idMap
     * @return
     */
    private Map<Integer, ArticleHomeVo> getArticleHomeData(Map<Integer, Integer> idMap) {
        Map<Integer, ArticleHomeVo> map = new HashMap();
        if (MapUtils.isNotEmpty(idMap)) {
            List<ArticleHomeVo> list =
                    articleHomeMapper.getArticleHomeListByIds(Lists.newArrayList(idMap.values()));
            for (Integer key : idMap.keySet()) {
                for (ArticleHomeVo vo : list) {
                    if (vo.getId().equals(idMap.get(key))) {
                        map.put(key, vo);
                        break;
                    }
                }
            }
        }
        return map;
    }

    /**
     * 获得商场文章数据
     *
     * @param idMap
     * @return
     */
    private Map<Integer, ArticleMarketVo> getArticleMarketData(Map<Integer, Integer> idMap) {
        Map<Integer, ArticleMarketVo> map = new HashMap();
        if (MapUtils.isNotEmpty(idMap)) {
            List<ArticleMarketVo> list =
                    articleMarketMapper.getArticleMarketListByIds(Lists.newArrayList(idMap.values()));
            for (Integer key : idMap.keySet()) {
                for (ArticleMarketVo vo : list) {
                    if (vo.getId().equals(idMap.get(key))) {
                        map.put(key, vo);
                        break;
                    }
                }
            }
        }
        return map;
    }

    /**
     * 获得商品文章数据
     *
     * @param idMap
     * @return
     */
    private Map<Integer, ArticleCustomVo> getArticleCustomData(Map<Integer, Integer> idMap) {
        Map<Integer, ArticleCustomVo> map = new HashMap();
        if (MapUtils.isNotEmpty(idMap)) {
            List<ArticleCustomVo> list =
                    articleCustomMapper.getArticleCustomListByIds(Lists.newArrayList(idMap.values()));
            for (Integer key : idMap.keySet()) {
                for (ArticleCustomVo vo : list) {
                    if (vo.getId().equals(idMap.get(key))) {
                        map.put(key, vo);
                        break;
                    }
                }
            }
        }
        return map;
    }

    /**
     * 获得房产文章数据
     *
     * @param idMap
     * @return
     */
    private Map<Integer, ArticleHouseVo> getArticleHouseData(Map<Integer, Integer> idMap) {
        Map<Integer, ArticleHouseVo> map = new HashMap();
        if (MapUtils.isNotEmpty(idMap)) {
            List<ArticleHouseVo> list =
                    articleHouseMapper.getArticleHouseListByIds(Lists.newArrayList(idMap.values()));
            for (Integer key : idMap.keySet()) {
                for (ArticleHouseVo vo : list) {
                    if (vo.getId().equals(idMap.get(key))) {
                        map.put(key, vo);
                        break;
                    }
                }
            }
        }
        return map;
    }

    /**
     * 获得图集数据
     *
     * @param idMap
     * @return
     */
    private Map<Integer, AtlasVo> getAtlasData(Map<Integer, Integer> idMap) {
        Map<Integer, AtlasVo> map = new HashMap();
        if (MapUtils.isNotEmpty(idMap)) {
            List<AtlasVo> list = atlasMapper.getAtlasByIds(Lists.newArrayList(idMap.values()));
            for (Integer key : idMap.keySet()) {
                for (AtlasVo vo : list) {
                    if (vo.getId().equals(idMap.get(key))) {
                        map.put(key, vo);
                        break;
                    }
                }
            }
        }
        return map;
    }

    /**
     * 获得外链数据
     *
     * @param idMap
     * @return
     */
    private Map<Integer, OutlinkVo> getOutlinksMapperData(Map<Integer, Integer> idMap) {
        Map<Integer, OutlinkVo> map = new HashMap();
        if (MapUtils.isNotEmpty(idMap)) {
            List<OutlinkVo> list = outlinksMapper.getOutlinksListByIds(Lists.newArrayList(idMap.values()));
            for (Integer key : idMap.keySet()) {
                for (OutlinkVo vo : list) {
                    if (vo.getId().equals(idMap.get(key))) {
                        map.put(key, vo);
                        break;
                    }
                }
            }
        }
        return map;
    }
}
