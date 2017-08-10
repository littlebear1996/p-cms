package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.enums.IndexTopicTypeEnum;
import com.chinaredstar.cms.api.service.CmsIndexTopicService;
import com.chinaredstar.cms.api.vo.article.ArticleCustomVo;
import com.chinaredstar.cms.api.vo.article.ArticleHomeVo;
import com.chinaredstar.cms.api.vo.article.ArticleHouseVo;
import com.chinaredstar.cms.api.vo.article.ArticleMarketVo;
import com.chinaredstar.cms.api.vo.index.IndexTopicQueryVo;
import com.chinaredstar.cms.api.vo.index.IndexTopicVo;
import com.chinaredstar.cms.mapper.*;
import com.chinaredstar.perseus.utils.JsonUtil;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pengfei.wang on 2017/3/24.
 */
@Service("cmsIndexTopicService")
public class CmsIndexTopicServiceImpl implements CmsIndexTopicService {

    private final Logger logger = LoggerFactory.getLogger(CmsIndexTopicServiceImpl.class);

    @Autowired
    private CmsIndexTopicMapper cmsIndexTopicMapper;
    @Autowired
    private ArticleCustomMapper articleCustomMapper; // 商品文章
    @Autowired
    private ArticleHomeMapper articleHomeMapper; // 家装文章
    @Autowired
    private ArticleHouseMapper articleHouseMapper; // 房产文章
    @Autowired
    private ArticleMarketMapper articleMarketMapper; // 商场文章

    @Override
    public ServiceResult<List<IndexTopicVo>> findPageList(IndexTopicQueryVo queryVo) {
        ServiceResult<List<IndexTopicVo>> result = new ServiceResult<>(true);
        if (queryVo == null) {
            queryVo = new IndexTopicQueryVo();
        }
        try {
            List<IndexTopicVo> topicVos = cmsIndexTopicMapper.findPageList(queryVo);
            if (CollectionUtils.isNotEmpty(topicVos)) {
                Map<Integer, Map<String, Object>> articleTypeMap = genArticleTypeMap(topicVos);
                for (Integer articleType : articleTypeMap.keySet()) {
                    Map<String, Object> articleMap = articleTypeMap.get(articleType);
                    switch (IndexTopicTypeEnum.valueOf(articleType)) {
                        case ARTICLE_CUSTOM:
                            getArticleCustomData((List<Integer>) articleMap.get("ids"), (List<IndexTopicVo>) articleMap.get("vos"));
                            break;
                        case ARTICLE_MARKET:
                            getArticleMarketData((List<Integer>) articleMap.get("ids"), (List<IndexTopicVo>) articleMap.get("vos"));
                            break;
                        case ARTICLE_HOME:
                            getArticleHomeData((List<Integer>) articleMap.get("ids"), (List<IndexTopicVo>) articleMap.get("vos"));
                            break;
                        case ARTICLE_HOUSE:
                            getArticleHouseData((List<Integer>) articleMap.get("ids"), (List<IndexTopicVo>) articleMap.get("vos"));
                            break;
                        default:
                            break;
                    }
                }
            }
            result.setData(topicVos);
        } catch (Exception e) {
            logger.error("分页查询精选专题列表异常, params:{}", JsonUtil.toJson(queryVo, false), e);
            result.setSuccess(false);
            result.setMsg("分页查询精选专题列表异常");
        }
        return result;
    }

    private Map<Integer, Map<String, Object>> genArticleTypeMap(List<IndexTopicVo> topicVos) {
        Map<Integer, Map<String, Object>> result = new HashMap<>();
        for (IndexTopicVo topicVo : topicVos) {
            Map<String, Object> map = result.get(topicVo.getArticleType());
            if (map == null) {
                map = new HashMap<>();
                map.put("ids", new ArrayList<Integer>());
                map.put("vos", new ArrayList<IndexTopicVo>());
                result.put(topicVo.getArticleType(), map);
            }
            ((List<Integer>) map.get("ids")).add(topicVo.getArticleId());
            ((List<IndexTopicVo>) map.get("vos")).add(topicVo);
        }
        return result;
    }

    private void getArticleCustomData(List<Integer> customIds, List<IndexTopicVo> topicVos) {
        if (CollectionUtils.isEmpty(customIds)) return;
        List<ArticleCustomVo> articleCustomVos = articleCustomMapper.getArticleCustomListByIds(customIds);
        if (CollectionUtils.isNotEmpty(articleCustomVos)) {
            for (IndexTopicVo topicVo : topicVos) {
                for (ArticleCustomVo customVo : articleCustomVos) {
                    if (topicVo.getArticleId().equals(customVo.getId())) {
                        topicVo.setTitle(customVo.getTitle());
                        topicVo.setCover(customVo.getCoverImgUrl());
                        break;
                    }
                }
            }
        }
    }

    private void getArticleMarketData(List<Integer> marketIds, List<IndexTopicVo> topicVos) {
        if (CollectionUtils.isEmpty(marketIds)) return;
        List<ArticleMarketVo> articleMarketVos = articleMarketMapper.getArticleMarketListByIds(marketIds);
        if (CollectionUtils.isNotEmpty(articleMarketVos)) {
            for (IndexTopicVo topicVo : topicVos) {
                for (ArticleMarketVo marketVo : articleMarketVos) {
                    if (topicVo.getArticleId().equals(marketVo.getId())) {
                        topicVo.setTitle(marketVo.getTitle());
                        topicVo.setCover(marketVo.getCoverImgUrl());
                        break;
                    }
                }
            }
        }
    }

    private void getArticleHomeData(List<Integer> homeIds, List<IndexTopicVo> topicVos) {
        if (CollectionUtils.isEmpty(homeIds)) return;
        List<ArticleHomeVo> articleHomeVos = articleHomeMapper.getArticleHomeListByIds(homeIds);
        if (CollectionUtils.isNotEmpty(articleHomeVos)) {
            for (IndexTopicVo topicVo : topicVos) {
                for (ArticleHomeVo homeVo : articleHomeVos) {
                    if (topicVo.getArticleId().equals(homeVo.getId())) {
                        topicVo.setTitle(homeVo.getTitle());
                        topicVo.setCover(homeVo.getCoverImgUrl());
                        break;
                    }
                }
            }
        }
    }

    private void getArticleHouseData(List<Integer> houseIds, List<IndexTopicVo> topicVos) {
        if (CollectionUtils.isEmpty(houseIds)) return;
        List<ArticleHouseVo> articleHouseList = articleHouseMapper.getArticleHouseListByIds(houseIds);
        if (CollectionUtils.isNotEmpty(articleHouseList)) {
            for (IndexTopicVo topicVo : topicVos) {
                for (ArticleHouseVo houseVo : articleHouseList) {
                    if (topicVo.getArticleId().equals(houseVo.getId())) {
                        topicVo.setTitle(houseVo.getTitle());
                        topicVo.setCover(houseVo.getCoverImgUrl());
                        break;
                    }
                }
            }
        }
    }
}
