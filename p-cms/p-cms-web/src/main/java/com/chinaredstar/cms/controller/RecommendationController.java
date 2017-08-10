package com.chinaredstar.cms.controller;

import com.chinaredstar.cms.api.component.Page;
import com.chinaredstar.cms.api.component.RecommendationVoComparator;
import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.enums.ArticleTypeEnum;
import com.chinaredstar.cms.api.enums.RecommendationContentTypeEnum;
import com.chinaredstar.cms.api.model.CmsCollectionData;
import com.chinaredstar.cms.api.model.CmsJzCase;
import com.chinaredstar.cms.api.service.*;
import com.chinaredstar.cms.api.vo.article.*;
import com.chinaredstar.cms.api.vo.atlas.AtlasVo;
import com.chinaredstar.cms.api.vo.collection.CmsCollectionDesignerVo;
import com.chinaredstar.cms.api.vo.collection.CmsCollectionTargetVo;
import com.chinaredstar.cms.api.vo.collection.CmsCollectionVo;
import com.chinaredstar.cms.api.vo.outlink.OutlinkVo;
import com.chinaredstar.cms.api.vo.recommend.*;
import com.chinaredstar.cms.api.vo.topic.TopicVo;
import com.chinaredstar.cms.vo.Result;
import com.chinaredstar.cms.vo.ResultCode;
import com.chinaredstar.jiazhuang.api.CaseServiceApi;
import com.chinaredstar.jiazhuang.api.DesignerServiceApi;
import com.chinaredstar.jiazhuang.api.dto.JzCaseListDto;
import com.chinaredstar.jiazhuang.api.dto.JzDesignerListDto;
import com.chinaredstar.jiazhuang.api.qo.JzCaseQuery;
import com.chinaredstar.jiazhuang.api.qo.JzDesignerQuery;
import com.chinaredstar.perseus.utils.JsonUtil;
import com.chinaredstar.perseus.utils.PropertiesUtil;
import com.codahale.metrics.annotation.Timed;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 推荐服务实现类
 */
@RestController
@RequestMapping("/page")
@Api(value = "page", description = "推荐页面相关接口")
@CacheConfig(cacheNames = "recommendationCache")
@Timed
public class RecommendationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RecommendationController.class);

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private AtlasService atlasService;

    @Autowired
    private OutlinkService outlinkService;

    @Autowired
    private ArticleCustomService articleCustomService;

    @Autowired
    private ArticleHomeService articleHomeService;

    @Autowired
    private ArticleHouseService articleHouseService;

    @Autowired
    private ArticleMarketService articleMarketService;

    @Autowired
    private CmsCollectionService cmsCollectionService;

    @Autowired
    private CaseServiceApi caseServiceApi;

    @Autowired
    private DesignerServiceApi designerServiceApi;

    @Autowired
    private TopicService topicService;



    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 404, message = "返回数据为空"),
            @ApiResponse(code = 415, message = "请求参数错误"),
            @ApiResponse(code = 422, message = "校验错误"),
            @ApiResponse(code = 500, message = "系统内部错误")
    })
    @ApiOperation(value = "discovery", notes = "发现页面数据接口")
    @RequestMapping(value = "/discovery/{type}/{subType1}/{subType2}", method = RequestMethod.GET)
//    @Cacheable(value = "CMS:RECOMCACHE", key = "'CMS:RECOMM:GETRECOMMENDATIONLISTBYTYPE:'+#type+':'+#subType1+':'+#subType2+':'+#page.getPageNo()+':'+#page.getPageSize()", unless = "!#result.code.equals('200')")
    public Result<List<RecommendationDataVo>> getRecommendationListByType(@PathVariable("type") Integer type,
                                                                          @PathVariable("subType1") Integer subType1,
                                                                          @PathVariable("subType2") Integer subType2,
                                                                          @RequestParam(value = "isView", required = false) Boolean isView,
                                                                          @ModelAttribute Page page,
                                                                          HttpServletRequest request) {
        Result<List<RecommendationDataVo>> result = new Result<>(ResultCode.C200.getCode());
        String androidVersionCode = PropertiesUtil.getProperty("application.properties").get("android.version");//能够正常处理专题合集的安卓版本
        String iosVersionCode = PropertiesUtil.getProperty("application.properties").get("ios.version");;//能够正常处理专题合集的IOS版本
        String versionCode = null;
        boolean isAndroid = false;
        boolean isIOS = false;
        String versionName = request.getHeader("versionName");
        String userAgent = request.getHeader("User-Agent");
        if (StringUtils.isNotBlank(userAgent) && userAgent.contains("RedStarMain")) {
            isIOS = true;
            String temStr = userAgent.split("/")[1];
            versionCode = temStr.substring(0, temStr.indexOf("("));
        }
        if (StringUtils.isNotBlank(versionName)) {
            isAndroid = true;
            versionCode = versionName;
        }
        try {
            RecommendationQueryVo queryVo = new RecommendationQueryVo();
            queryVo.setType(type);
            queryVo.setSubType1(subType1);
            queryVo.setSubType2(subType2);
            if (isView != null) {
                queryVo.setView(isView);
            }

            ServiceResult<List<RecommendationVo>> serviceResult = recommendationService.addRecommendationCache(type, subType1, subType2);


            if (serviceResult == null || !serviceResult.isSuccess()) {
                LOGGER.error("获取推荐数据失败.");
                result.setCode(ResultCode.C500.getCode());
                result.setMessage("获取推荐数据失败");
                return result;
            }

            //进行分页数据处理

            int start = page.getStart();
            if (start >= serviceResult.getData().size()) {
                List<RecommendationVo> list = new ArrayList<RecommendationVo>();
                serviceResult.setData(list);
            } else {
                int end = page.getStart() + page.getPageSize() > serviceResult.getData().size() ? serviceResult.getData().size() : page.getStart() + page.getPageSize();
                LOGGER.info("recommenddata:start:" + start + "  end:" + end + "  list size is:" + serviceResult.getData().size());

                List<RecommendationVo> list = serviceResult.getData().subList(start, end);
                serviceResult.setData(list);
            }

            //1,方案、2，设计师合辑，3，文章,4, 图集，5，外链, 6,专题
            // 1, 方案
            List<Integer> caseIdList = new ArrayList<>();
            // 2, 设计师合辑
            List<Integer> colldesignerIdList = new ArrayList<>();
            // 5, 外链
            List<Integer> outlinkIdList = new ArrayList<>();
            // 3, 文章
            List<String> articleIdList = new ArrayList<>();
            // 4, 图集
            List<Integer> atlasIdList = new ArrayList<>();
            // 6, 专题
            List<Integer> topicIdList = new ArrayList<>();

            List<RecommendationDataVo> recommendationVoList = new ArrayList<>();

            List<RecommendationVo> recommendationList = serviceResult.getData();

            getTargetIdList(recommendationList, caseIdList, colldesignerIdList, articleIdList, atlasIdList, outlinkIdList, topicIdList);

            Map<Integer, AtlasVo> atlasMap = processRecommendationAtlas(atlasIdList);
            Map<Integer, OutlinkVo> outlinksMap = processRecommendationOutlink(outlinkIdList);
            Map<Integer, CmsJzCase> cmsJzCaseMap = processRecommendationCase(caseIdList);
            Map<String, ArticleVo> articleMap = processRecommendationArticle(articleIdList);
            Map<Integer, CmsCollectionVo> cmsCollectionVoMap = processRecommendationDesigner(colldesignerIdList);
            Map<Integer, TopicVo> topicsMap = processRecommendationTopics(topicIdList);

            for (RecommendationVo recommendation : recommendationList) {
                if (recommendation == null) {
                    continue;
                }

                Integer id = Integer.valueOf(recommendation.getContent());

                switch (RecommendationContentTypeEnum.valueOf(recommendation.getContentType())) {
                    case CASE: //方案
                        if (cmsJzCaseMap != null && cmsJzCaseMap.get(id) != null) {
                            RecommendationCaseVo caseVo = new RecommendationCaseVo();
                            caseVo.setRecommendation(recommendation);
                            caseVo.setRecommendationData(cmsJzCaseMap.get(id));
                            recommendationVoList.add(caseVo);
                        }
                        break;
                    case DESIGNER: //设计师
                        if (cmsCollectionVoMap != null && cmsCollectionVoMap.get(id) != null) {
                            RecommendationDesignerVo recommendationDesignerVo = new RecommendationDesignerVo();
                            recommendationDesignerVo.setRecommendation(recommendation);
                            recommendationDesignerVo.setRecommendationData(cmsCollectionVoMap.get(id));
                            recommendationVoList.add(recommendationDesignerVo);
                        }
                        break;
                    case ARTICLE: //文章
                        if (articleMap != null
                                && articleMap.get(id + "#" + recommendation.getArticleType()) != null) {
                            RecommendationArticleVo articleVo = new RecommendationArticleVo();
                            articleVo.setRecommendation(recommendation);
                            articleVo.setRecommendationData(articleMap.get(id + "#" + recommendation.getArticleType()));
                            recommendationVoList.add(articleVo);
                        }
                        break;
                    case ATLAS: //图集
                        if (atlasMap != null && atlasMap.get(id) != null) {
                            RecommendationAtlasVo atlasVo = new RecommendationAtlasVo();
                            atlasVo.setRecommendation(recommendation);
                            atlasVo.setRecommendationData(atlasMap.get(id));
                            recommendationVoList.add(atlasVo);
                        }
                        break;
                    case OUTLINK: //外链
                        if (outlinksMap != null && outlinksMap.get(id) != null) {
                            RecommendationOutlinkVo outlinkVo = new RecommendationOutlinkVo();
                            outlinkVo.setRecommendation(recommendation);
                            outlinkVo.setRecommendationData(outlinksMap.get(id));
                            outlinkIdList.add(Integer.valueOf(recommendation.getContent()));
                        }
                        break;
                    case TOPIC: //专题
                        //处理由于app过低导出的数据不兼容问题
                        if (type == 1 && subType1 == 1 && subType2 == 1 &&
                                ((isIOS && versionCode.compareTo(iosVersionCode) < 0)
                                        || (isAndroid && versionCode.compareTo(androidVersionCode) < 0))) {
                            continue;
                        }
                        if (topicsMap != null && topicsMap.get(id) != null) {
                            RecommendationTopicVo topicVo = new RecommendationTopicVo();
                            topicVo.setRecommendation(recommendation);
                            topicVo.setRecommendationData(topicsMap.get(id));
                            recommendationVoList.add(topicVo);
                        }
                        break;
                    default:
                        break;
                }
            }

            Collections.sort(recommendationVoList, new RecommendationVoComparator());
            result.setDataMap(recommendationVoList);
            return result;
        } catch (Exception e) {
            LOGGER.error("获取推荐信息失败. Cause by : {}", e);
            result.setCode(ResultCode.C500.getCode());
            result.setMessage(e.getMessage());
            return result;
        }
    }

    /**
     * 分别获取推荐的ID集合
     *
     * @param recommendationList
     * @param caseIdList
     * @param colldesignerIdList
     * @param articleIdList
     * @param atlasIdList
     * @param outlinkIdList
     * @param topicIdList
     */
    private void getTargetIdList(List<RecommendationVo> recommendationList, List<Integer> caseIdList,
                                 List<Integer> colldesignerIdList, List<String> articleIdList, List<Integer> atlasIdList,
                                 List<Integer> outlinkIdList, List<Integer> topicIdList) {
        if (recommendationList == null || recommendationList.isEmpty()) {
            return;
        }

        for (RecommendationVo recommendation : recommendationList) {
            if (recommendation == null) {
                continue;
            }

            switch (RecommendationContentTypeEnum.valueOf(recommendation.getContentType())) {
                case CASE:
                    caseIdList.add(Integer.valueOf(recommendation.getContent()));
                    break;
                case DESIGNER:
                    colldesignerIdList.add(Integer.valueOf(recommendation.getContent()));
                    break;
                case ARTICLE:
                    articleIdList.add(recommendation.getContent() + "#" + recommendation.getArticleType());
                    break;
                case ATLAS:
                    atlasIdList.add(Integer.valueOf(recommendation.getContent()));
                    break;
                case OUTLINK:
                    outlinkIdList.add(Integer.valueOf(recommendation.getContent()));
                    break;
                case TOPIC:
                    topicIdList.add(Integer.valueOf(recommendation.getContent()));
                    break;
                default:
                    break;
            }
        }
    }

    // 推荐文章
    private Map<String, ArticleVo> processRecommendationArticle(List<String> articleIdList) {
        if (articleIdList == null || articleIdList.isEmpty()) {
            return null;
        }

        Map<String, List<Integer>> articleIdMap = new HashMap<>();

        for (String idAndType : articleIdList) {
            String[] idAndTypeArray = idAndType.split("#");
            String id = idAndTypeArray[0];
            String type = idAndTypeArray[1];
            if (articleIdMap.get(type) == null) {
                List<Integer> idList = new ArrayList<>();
                idList.add(Integer.valueOf(id));
                articleIdMap.put(type, idList);
            } else {
                List<Integer> idList = articleIdMap.get(type);
                idList.add(Integer.valueOf(id));
                articleIdMap.put(type, idList);
            }
        }

        Map<String, ArticleVo> articleMap = new HashMap<>();

        for (Map.Entry<String, List<Integer>> entry : articleIdMap.entrySet()) {
            String type = entry.getKey();
            switch (ArticleTypeEnum.valueOf(Integer.parseInt(entry.getKey()))) {
                case PRODUCT_ARTICLE: //商品文章
                    List<Integer> customIdList = entry.getValue();
                    if (customIdList == null || customIdList.isEmpty()) {
                        break;
                    }
                    ServiceResult<List<ArticleCustomVo>> articleCustomResult = articleCustomService.getArticleCustomListByIds(customIdList);
                    if (!articleCustomResult.isSuccess()) {
                        LOGGER.error("获取商品文章失败. Case by : {}", articleCustomResult.getMsg());
                        break;
                    }
                    for (ArticleCustomVo articleCustom : articleCustomResult.getData()) {
                        articleMap.put(articleCustom.getId() + "#" + type, articleCustom);
                    }
                    break;
                case MARKET_ARTICLE: //商场文章
                    List<Integer> marketIdList = entry.getValue();
                    if (marketIdList == null || marketIdList.isEmpty()) {
                        break;
                    }
                    ServiceResult<List<ArticleMarketVo>> articleMarketResult = articleMarketService.getArticleMarketListByIds(marketIdList);
                    if (!articleMarketResult.isSuccess()) {
                        LOGGER.error("获取商场文章失败. Case by : {}", articleMarketResult.getMsg());
                        break;
                    }
                    for (ArticleMarketVo ArticleMarketVo : articleMarketResult.getData()) {
                        articleMap.put(ArticleMarketVo.getId() + "#" + type, ArticleMarketVo);
                    }
                    break;
                case HOME_CASE_ARTICLE: //家装方案文章
                    List<Integer> homeCaseIdList = entry.getValue();
                    if (homeCaseIdList == null || homeCaseIdList.isEmpty()) {
                        break;
                    }
                    ServiceResult<List<ArticleHomeVo>> articleHomeResult = articleHomeService.getArticleHomeListByIds(homeCaseIdList);
                    if (!articleHomeResult.isSuccess()) {
                        LOGGER.error("获取家装文章失败. Case by : {}", articleHomeResult.getMsg());
                        break;
                    }
                    for (ArticleHomeVo articleHome : articleHomeResult.getData()) {
                        articleMap.put(articleHome.getId() + "#" + type, articleHome);
                    }
                    break;
                case HOUSE_PROPERTY_ARTICLE: //房产文章
                    List<Integer> houseIdList = entry.getValue();
                    if (houseIdList == null || houseIdList.isEmpty()) {
                        break;
                    }
                    ServiceResult<List<ArticleHouseVo>> articleHouseResult = articleHouseService.getArticleHouseListByIds(houseIdList);
                    if (!articleHouseResult.isSuccess()) {
                        LOGGER.error("获取房产文章失败. Case by : {}", articleHouseResult.getMsg());
                        break;
                    }
                    for (ArticleHouseVo articleHouse : articleHouseResult.getData()) {
                        articleMap.put(articleHouse.getId() + "#" + type, articleHouse);
                    }
                    break;
                default:
                    break;
            }
        }

        return articleMap;
    }

    // 推荐方案
    private Map<Integer, CmsJzCase> processRecommendationCase(List<Integer> caseIdList) {
        if (caseIdList == null || caseIdList.isEmpty()) {
            return null;
        }

        JzCaseQuery jzCaseQuery = new JzCaseQuery();
        jzCaseQuery.setCaseIds(caseIdList);

        com.chinaredstar.jiazhuang.api.bean.Result<List<JzCaseListDto>> jzCaseResult = caseServiceApi.getListByQuery(jzCaseQuery, 0, Integer.MAX_VALUE);

        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("调用方案接口 --> Request : {}, Response : {}", JsonUtil.toJson(jzCaseQuery, false), JsonUtil.toJson(jzCaseResult, false));
        }

        if (!ResultCode.C200.getCode().equals(jzCaseResult.getCode())) {
            LOGGER.error("获取方案数据失败. Cause by : {}", jzCaseResult.getMessage());
            return null;
        }

        Map<Integer, CmsJzCase> cmsJzCaseMap = new HashMap<>();

        for (JzCaseListDto jzCaseListDto : jzCaseResult.getDataMap()) {
            if (jzCaseListDto == null) {
                continue;
            }
            CmsJzCase cmsJzCase = new CmsJzCase();
            BeanUtils.copyProperties(jzCaseListDto, cmsJzCase);
            cmsJzCaseMap.put(jzCaseListDto.getCaseId(), cmsJzCase);
        }

        return cmsJzCaseMap;
    }

    // 推荐外链
    private Map<Integer, OutlinkVo> processRecommendationOutlink(List<Integer> outlinkIdList) {
        if (outlinkIdList == null || outlinkIdList.isEmpty()) {
            return null;
        }

        ServiceResult<List<OutlinkVo>> outlinkResult = outlinkService.getOutLinkByIds(outlinkIdList);
        if (!outlinkResult.isSuccess()) {
            LOGGER.error("获取外链数据失败. Cause by : {}", outlinkResult.getMsg());
            return null;
        }

        Map<Integer, OutlinkVo> outlinksMap = new HashMap<>();
        for (OutlinkVo outlinks : outlinkResult.getData()) {
            outlinksMap.put(outlinks.getId(), outlinks);
        }

        return outlinksMap;
    }

    // 推荐图集
    private Map<Integer, AtlasVo> processRecommendationAtlas(List<Integer> atlasIdList) {
        if (atlasIdList == null || atlasIdList.isEmpty()) {
            return null;
        }

        ServiceResult<List<AtlasVo>> atlasServiceResult = atlasService.getAtlasByIds(atlasIdList);

        if (!atlasServiceResult.isSuccess()) {
            LOGGER.error("获取图集数据失败. Cause by : {}", atlasServiceResult.getMsg());
            return null;
        }

        Map<Integer, AtlasVo> atlasMap = new HashMap<>();

        for (AtlasVo atlas : atlasServiceResult.getData()) {
            atlasMap.put(atlas.getId(), atlas);
        }

        return atlasMap;
    }

    private Map<Integer, TopicVo> processRecommendationTopics(List<Integer> topicIdList) {
        if (topicIdList == null || topicIdList.isEmpty()) {
            return null;
        }
        ServiceResult<List<TopicVo>> serviceResult = topicService.getTopicByIds(topicIdList);
        if (!serviceResult.isSuccess()) {
            LOGGER.error("获取专题数据失败. Cause by : {}", serviceResult.getMsg());
            return null;
        }
        Map<Integer, TopicVo> topicMap = new HashMap<>();
        for (TopicVo topicVo : serviceResult.getData()) {
            topicMap.put(topicVo.getId(), topicVo);
        }
        return topicMap;
    }

    // 推荐设计师合辑
    private Map<Integer, CmsCollectionVo> processRecommendationDesigner(List<Integer> colldesignerIdList) {
        if (colldesignerIdList == null || colldesignerIdList.isEmpty()) {
            return null;
        }

        // 获取设计师合辑信息
        ServiceResult<List<CmsCollectionVo>> collectionServiceResult = cmsCollectionService.getCmsCollectionListByIds(colldesignerIdList);

        if (!collectionServiceResult.isSuccess()) {
            LOGGER.error("获取合辑数据失败. Cause by : {}", collectionServiceResult.getMsg());
            return null;
        }

        // 获取设计师信息
        List<Integer> designerIdList = new ArrayList<>();

        Map<Integer, CmsCollectionVo> cmsCollectionVoMap = new HashMap<>();

        for (CmsCollectionVo collectionVo : collectionServiceResult.getData()) {
            List<CmsCollectionData> cmsCollectionDataList = collectionVo.getCollectionDataList();
            if (cmsCollectionDataList == null || cmsCollectionDataList.isEmpty()) {
                continue;
            }

            for (CmsCollectionData cmsCollectionData : cmsCollectionDataList) {
                if (cmsCollectionData == null) {
                    continue;
                }
                designerIdList.add(Integer.valueOf(cmsCollectionData.getTargetId()));
            }

            // 通过设计师ID集合查询设计师信息
            JzDesignerQuery jzDesignerQuery = new JzDesignerQuery();
            jzDesignerQuery.setDesignerIds(designerIdList);
            com.chinaredstar.jiazhuang.api.bean.Result<List<JzDesignerListDto>> designerServiceResult = designerServiceApi.getListByQuery(jzDesignerQuery, 0, Integer.MAX_VALUE);
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("调用设计师信息接口 --> Request : {}, Response : {}", JsonUtil.toJson(jzDesignerQuery, false), JsonUtil.toJson(designerServiceResult, false));
            }

            if (designerServiceResult == null || !ResultCode.C200.getCode().equals(designerServiceResult.getCode())) {
                LOGGER.error("获取设计师数据失败. }");
                return null;
            }

            List<CmsCollectionTargetVo> cmsCollectionDesignerVoList = new ArrayList<>();

            for (JzDesignerListDto jzDesignerListDto : designerServiceResult.getDataMap()) {
                if (jzDesignerListDto == null) {
                    continue;
                }

                CmsCollectionDesignerVo designerVo = new CmsCollectionDesignerVo();
                BeanUtils.copyProperties(jzDesignerListDto, designerVo);
                cmsCollectionDesignerVoList.add(designerVo);
            }

            collectionVo.setCmsCollectionTargetVoList(cmsCollectionDesignerVoList);
            cmsCollectionVoMap.put(collectionVo.getId(), collectionVo);
        }
        return cmsCollectionVoMap;
    }

    public static void main(String[] args) {
//        String str = "1.3.4";
//        String str1 = "1.3.5";
//        System.out.println(str.compareTo(str1));
        String versionCode = PropertiesUtil.getProperty("application.properties").get("android.version");
        System.out.println(versionCode);
    }
}
