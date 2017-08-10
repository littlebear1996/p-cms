package com.chinaredstar.cms.controller;

import com.chinaredstar.cms.api.component.CmsClassVoComparator;
import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.model.*;
import com.chinaredstar.cms.api.service.*;
import com.chinaredstar.cms.api.vo.advertisement.AdvertisementQueryVo;
import com.chinaredstar.cms.api.vo.advertisement.AdvertisementVo;
import com.chinaredstar.cms.api.vo.article.ArticleHouseQueryVo;
import com.chinaredstar.cms.api.vo.article.ArticleHouseVo;
import com.chinaredstar.cms.api.vo.article.ArticleMarketQueryVo;
import com.chinaredstar.cms.api.vo.article.ArticleMarketVo;
import com.chinaredstar.cms.api.vo.atlas.AtlasQueryVo;
import com.chinaredstar.cms.api.vo.atlas.AtlasVo;
import com.chinaredstar.cms.api.vo.cmsClass.*;
import com.chinaredstar.cms.api.vo.collection.*;
import com.chinaredstar.cms.vo.Result;
import com.chinaredstar.cms.vo.ResultCode;
import com.chinaredstar.jiazhuang.api.DesignerServiceApi;
import com.chinaredstar.jiazhuang.api.dto.JzDesignerListDto;
import com.chinaredstar.jiazhuang.api.qo.JzDesignerQuery;
import com.codahale.metrics.annotation.Timed;
import com.redstar.digital.open.bean.OmsBrandInfo;
import com.redstar.digital.open.service.OmsBrandInfoService;
import com.redstar.digital.open.service.OmsMarketInfoService;
import com.redstar.digital.open.service.ShowCategoryService;
import com.redstar.digital.open.vo.MarketPositionVo;
import com.redstar.digital.open.vo.ShowCategoryVo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 组织页面数据Controller
 */
@RestController
@RequestMapping("/page")
@Api(value = "page", description = "页面相关接口")
@Timed
public class PageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PageController.class);
    // 分类
    private static final String CMS_CLASS_TAGS = "TAGS";
    private static final String CMS_CLASS_CATEGORY = "CATEGORY";
    private static final String CMS_CLASS_BRAND = "BRAND";
    private static final String CMS_CLASS_SELECTION = "SELECTION";
    private static final String CMS_CLASS_PICTURES = "PICTURES";
    @Autowired
    private AdvertisementService advertisementService;
    @Autowired
    private CmsCollectionService cmsCollectionService;
    @Autowired
    private AtlasService atlasService;
    @Autowired
    private ArticleMarketService articleMarketService;
    @Autowired
    private ArticleHouseService articleHouseService;
    @Autowired
    private CmsClassService cmsClassService;
    @Autowired
    private DesignerServiceApi designerServiceApi;
    @Autowired
    private OmsBrandInfoService omsBrandInfoService;
    @Autowired
    private ShowCategoryService showCategoryService;
    @Autowired
    private OmsMarketInfoService omsMarketInfoService;
    @Autowired
    private AdvertisementPositionService advertisementPositionService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 404, message = "返回数据为空"),
            @ApiResponse(code = 415, message = "请求参数错误"),
            @ApiResponse(code = 422, message = "校验错误"),
            @ApiResponse(code = 500, message = "系统内部错误")
    })
    @ApiOperation(value = "index", notes = "主APP首页数据接口")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public Result<Map<String, Object>> indexPageController() {

        Result<Map<String, Object>> result = new Result<>();
        Map<String, Object> dataMap = new HashMap<>();
        try {
            AdvertisementQueryVo adQueryVo = new AdvertisementQueryVo();
            adQueryVo.setCurrentTime(new Date());
            adQueryVo.setPositionCode(Advertisement.MAIN_APP_INDEX);

            ServiceResult<List<AdvertisementVo>> adServiceResult = advertisementService.listADsByPositionCode(adQueryVo);
            if (adServiceResult != null && adServiceResult.isSuccess()) {
                List<AdvertisementVo> firstAdvertisementVos = adServiceResult.getData();
                if (firstAdvertisementVos != null && firstAdvertisementVos.size() != 0) {
                    if (firstAdvertisementVos.size() > 1 && firstAdvertisementVos.get(0).getSequence() == 0) {
                        firstAdvertisementVos.remove(0);
                    }
                }
                dataMap.put("ad_first", firstAdvertisementVos);

            } else {
                result.setCode(ResultCode.C500.getCode());
                if (adServiceResult != null) {
                    result.setMessage(adServiceResult.getMsg());
                }
                return result;
            }

            adQueryVo.setPositionCode(Advertisement.MAIN_APP_INDEX_BRAND);
            adServiceResult = advertisementService.listADsByPositionCode(adQueryVo);
            if (adServiceResult != null && adServiceResult.isSuccess()) {
                List<AdvertisementVo> brandAdvertisementVos = adServiceResult.getData();
                if (brandAdvertisementVos != null && brandAdvertisementVos.size() != 0) {
                    if (brandAdvertisementVos.size() > 1 && brandAdvertisementVos.get(0).getSequence() == 0) {
                        brandAdvertisementVos.remove(0);
                    }
                }
                dataMap.put("ad_brandList", brandAdvertisementVos);
            } else {
                result.setCode(ResultCode.C500.getCode());
                if (adServiceResult != null) {
                    result.setMessage(adServiceResult.getMsg());
                }
                return result;
            }

            //品牌合辑
            CmsCollectionQueryVo collectionQueryVo = new CmsCollectionQueryVo();
            collectionQueryVo.setTop(true);
            collectionQueryVo.setType(CmsCollection.TYPE_MARKET);
            collectionQueryVo.setSubType(CmsCollection.SUBTYPE_MARKET_BRAND);
            ServiceResult<List<CmsCollectionVo>> collectionServiceResult = cmsCollectionService.findCollectionByType(collectionQueryVo);
            if (collectionServiceResult != null && collectionServiceResult.isSuccess()) {
                dataMap.put("collection_brand", processBrandCollectionData(collectionServiceResult.getData()));
            } else {
                result.setCode(ResultCode.C500.getCode());
                if (collectionServiceResult != null) {
                    result.setMessage(collectionServiceResult.getMsg());
                }
                return result;
            }
            //家装图集
            AtlasQueryVo atlasQueryVo = new AtlasQueryVo();
            atlasQueryVo.setCurrentTime(new Date());
            atlasQueryVo.setSubType(Atlas.SUBTYPE_HOME_DECORATION);
            atlasQueryVo.setRecommand(true);
            atlasQueryVo.setOrderBy(" atlas.set_recommand_time DESC , atlas.create_time DESC");
            ServiceResult<List<AtlasVo>> atlasServiceResult = atlasService.listByType(atlasQueryVo);
            if (atlasServiceResult != null && atlasServiceResult.isSuccess()) {
                dataMap.put("atlas_homeDecoration", atlasServiceResult.getData());
            } else {
                result.setCode(ResultCode.C500.getCode());
                if (atlasServiceResult != null) {
                    result.setMessage(atlasServiceResult.getMsg());
                }
                return result;
            }

            atlasQueryVo.setSubType(Atlas.SUBTYPE_HOUSEHOLD);
            atlasServiceResult = atlasService.listByType(atlasQueryVo);
            if (atlasServiceResult != null && atlasServiceResult.isSuccess()) {
                dataMap.put("atlas_household", atlasServiceResult.getData());
            } else {
                result.setCode(ResultCode.C500.getCode());
                if (atlasServiceResult != null) {
                    result.setMessage(atlasServiceResult.getMsg());
                }
                return result;
            }

            atlasQueryVo.setSubType(Atlas.SUBTYPE_SINGLEGOODS);
            atlasServiceResult = atlasService.listByType(atlasQueryVo);
            if (atlasServiceResult != null && atlasServiceResult.isSuccess()) {
                dataMap.put("atlas_singleGoods", atlasServiceResult.getData());
            } else {
                result.setCode(ResultCode.C500.getCode());
                if (atlasServiceResult != null) {
                    result.setMessage(atlasServiceResult.getMsg());
                }
                return result;
            }


            result.setCode(ResultCode.C200.getCode());
            result.setDataMap(dataMap);
        } catch (Exception e) {
            LOGGER.error("获取app首页数据异常：{}", e);
            e.printStackTrace();
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("获取app首页数据异常");
        }
        return result;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 404, message = "返回数据为空"),
            @ApiResponse(code = 415, message = "请求参数错误"),
            @ApiResponse(code = 422, message = "校验错误"),
            @ApiResponse(code = 500, message = "系统内部错误")
    })
    @ApiOperation(value = "houseProperty", notes = "房产首页数据接口")
    @RequestMapping(value = "/houseProperty", method = RequestMethod.GET)
    public Result<Map<String, Object>> housePropertyPageController() {


        Result<Map<String, Object>> result = new Result<>();

        Map<String, Object> dataMap = new HashMap<>();
        try {
            // 广告
            AdvertisementQueryVo advertisementQueryVo = new AdvertisementQueryVo();
            advertisementQueryVo.setPositionCode(Advertisement.HOUSE_INDEX_FIRST);
            advertisementQueryVo.setCurrentTime(new Date());
            ServiceResult<List<AdvertisementVo>> adServiceResult = advertisementService.listADsByPositionCode(advertisementQueryVo);
            if (adServiceResult != null && adServiceResult.isSuccess()) {
                List<AdvertisementVo> firstAdvertisementVos = adServiceResult.getData();
                if (firstAdvertisementVos != null && firstAdvertisementVos.size() != 0) {
                    if (firstAdvertisementVos.size() > 1 && firstAdvertisementVos.get(0).getSequence() == 0) {
                        firstAdvertisementVos.remove(0);
                    }
                }
                dataMap.put("ad_first", firstAdvertisementVos);
            } else {
                result.setCode(ResultCode.C500.getCode());
                if (adServiceResult != null) {
                    result.setMessage(adServiceResult.getMsg());
                }
                return result;
            }

            // 名校探班
            ArticleHouseQueryVo articleHouseQueryVo = new ArticleHouseQueryVo();
            articleHouseQueryVo.setCategoryTag(ArticleHouse.FAMOUS_SCHOOL);
            articleHouseQueryVo.setTop(true);
            articleHouseQueryVo.setPageIndex(0);
            articleHouseQueryVo.setPageSize(5);
            articleHouseQueryVo.setCurrentTime(new Date());
            ServiceResult<List<ArticleHouseVo>> houseServiceResult = articleHouseService.listByCategoryTag(articleHouseQueryVo);
            if (houseServiceResult != null && houseServiceResult.isSuccess()) {
                dataMap.put("famous_school", houseServiceResult.getData());
            } else {
                result.setCode(ResultCode.C500.getCode());
                if (houseServiceResult != null) {
                    result.setMessage(houseServiceResult.getMsg());
                }
                return result;
            }


            // 学区攻略
            articleHouseQueryVo.setCategoryTag(ArticleHouse.SCHOOL_DISTRICT);
            articleHouseQueryVo.setCurrentTime(new Date());
            houseServiceResult = articleHouseService.listByCategoryTag(articleHouseQueryVo);
            if (houseServiceResult != null && houseServiceResult.isSuccess()) {
                dataMap.put("school_district", houseServiceResult.getData());
            } else {
                result.setCode(ResultCode.C500.getCode());
                if (houseServiceResult != null) {
                    result.setMessage(houseServiceResult.getMsg());
                }
                return result;
            }

            // 头条
            articleHouseQueryVo.setCategoryTag(ArticleHouse.HEADLINE);
            articleHouseQueryVo.setCurrentTime(new Date());
            houseServiceResult = articleHouseService.listByCategoryTag(articleHouseQueryVo);
            if (houseServiceResult != null && houseServiceResult.isSuccess()) {
                dataMap.put("headline", houseServiceResult.getData());
            } else {
                result.setCode(ResultCode.C500.getCode());
                if (houseServiceResult != null) {
                    result.setMessage(houseServiceResult.getMsg());
                }
                return result;
            }

            result.setCode(ResultCode.C200.getCode());
            result.setDataMap(dataMap);

        } catch (Exception e) {
            LOGGER.error("获取房产首页数据异常：{}", e);
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("获取房产首页数据异常");
        }


        return result;

    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 404, message = "返回数据为空"),
            @ApiResponse(code = 415, message = "请求参数错误"),
            @ApiResponse(code = 422, message = "校验错误"),
            @ApiResponse(code = 500, message = "系统内部错误")
    })
    @ApiOperation(value = "homeDecoration", notes = "家装首页数据接口")
    @RequestMapping(value = "/homeDecoration", method = RequestMethod.GET)
//    @Cacheable()
    public Result<Map<String, Object>> homeDecorationPageController() {
        Result<Map<String, Object>> result = new Result<>(ResultCode.C200.getCode());

        /**
         * 广告 get /ad/list/homeDecoIndex_first
         * 设计师合辑 get /collection/list/3/32
         * 灵感集锦   get  http://172.16.106.50:8080/page/discovery/5/1/1
         */

        /**
         * 广告 key --> advert
         * 设计师合辑 key --> designer
         * 灵感集锦  key --> afflatus
         */

        try {
            Map<String, Object> dataMap = new HashMap<>();
            // 家装首页广告
            AdvertisementQueryVo advertisementQueryVo = new AdvertisementQueryVo();
            advertisementQueryVo.setPositionCode(Advertisement.HOME_DECO_INDEX_FIRST);
            advertisementQueryVo.setCurrentTime(new Date());
            advertisementQueryVo.setOnline(true);
            ServiceResult<List<AdvertisementVo>> serviceResultAdvert = advertisementService.listADsByPositionCode(advertisementQueryVo);
            if (serviceResultAdvert != null && serviceResultAdvert.isSuccess()) {
                List<AdvertisementVo> firstAdvertisementVos = serviceResultAdvert.getData();
                if (firstAdvertisementVos != null && firstAdvertisementVos.size() != 0) {
                    if (firstAdvertisementVos.size() > 1 && firstAdvertisementVos.get(0).getSequence() == 0) {
                        firstAdvertisementVos.remove(0);
                    }
                }
                dataMap.put("advert", firstAdvertisementVos);
            }

            // 设计师合辑
            CmsCollectionQueryVo cmsCollectionVo = new CmsCollectionQueryVo();
            cmsCollectionVo.setType(CmsCollection.TYPE_HOME_DECORATION);
            cmsCollectionVo.setSubType(CmsCollection.SUBTYPE_HOME_DECORATION_DESIGNER);
            ServiceResult<List<CmsCollectionVo>> serviceResultColl = cmsCollectionService.findCollectionByType(cmsCollectionVo);
            if (serviceResultColl != null && serviceResultColl.isSuccess()) {
                dataMap.put("designer", processDesignerCollection(serviceResultColl.getData()));
            }

            // 灵感集锦(家装推荐)
            // RecommendationQueryVo recommendationQueryVo = new RecommendationQueryVo();
            //  recommendationQueryVo.setType(Recommendation.TYPE_MAIN_APP);
            //  recommendationQueryVo.setSubType1(Recommendation.SUBTYPE1_RECOMM_APP);
            //  recommendationQueryVo.setSubType2(Recommendation.SUBTYPE2_HOME_DECORATION_INDEX);
            //  ServiceResult<List<RecommendationVo>> serviceResultRecomm = recommendationService.getRecommendationListByType(recommendationQueryVo);
            //  if (serviceResultRecomm != null && serviceResultRecomm.isSuccess()) {
            //      dataMap.put("afflatus", serviceResultRecomm.getData());
            //  }

            result.setDataMap(dataMap);
            return result;
        } catch (Exception e) {
            result.setCode(ResultCode.C500.getCode());
            result.setMessage(e.getMessage());
            return result;
        }
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 404, message = "返回数据为空"),
            @ApiResponse(code = 415, message = "请求参数错误"),
            @ApiResponse(code = 422, message = "校验错误"),
            @ApiResponse(code = 500, message = "系统内部错误")
    })
    @ApiOperation(value = "market", notes = "商场首页数据接口")
    @RequestMapping(value = "/market", method = RequestMethod.GET)
    public Result<Map<String, Object>> marketPage(HttpServletRequest request) {
        Result<Map<String, Object>> result = new Result<>(ResultCode.C200.getCode());

        /**
         * 广告 get /ad/list/marketIndex_first
         * 独特体验 get /marketArticle/listByTag/独特体验  (取推荐数据)
         * 特色品牌 get /marketArticle/listByTag/特色品牌  (取推荐数据)
         * 特色品类 get /marketArticle/listByTag/特色品类
         * 特色商品 get /marketArticle/listByTag/特色商品  (取推荐数据)
         * 促销活动 get /ad/list/marketIndex_sale
         */

        /**
         * 广告  key --> advert
         * 独特体验 key --> experience
         * 特色品牌 key --> brand
         * 特色品类 key --> category
         * 特色商品 key --> product
         * 促销活动 key --> promotion
         * 商场位置 key --> market_position
         */

        try {
            Map<String, Object> dataMap = new HashMap<>();
            // 广告
            AdvertisementQueryVo advertisementQueryVo = new AdvertisementQueryVo();
            advertisementQueryVo.setPositionCode(Advertisement.MARKET_INDEX_FIRST);
            advertisementQueryVo.setOnline(true);
            advertisementQueryVo.setCurrentTime(new Date());
            ServiceResult<List<AdvertisementVo>> serviceResultAdvert = advertisementService.listADsByPositionCode(advertisementQueryVo);
            if (serviceResultAdvert != null && serviceResultAdvert.isSuccess()) {
                List<AdvertisementVo> firstAdvertisementVos = serviceResultAdvert.getData();
                if (firstAdvertisementVos != null && firstAdvertisementVos.size() != 0) {
                    if (firstAdvertisementVos.size() > 1 && firstAdvertisementVos.get(0).getSequence() == 0) {
                        firstAdvertisementVos.remove(0);
                    }
                }
                dataMap.put("advert", firstAdvertisementVos);
            }

            // 商场描述
            ServiceResult<AdvertisementPosition> serviceResultAdvertPosition = advertisementPositionService.getAdvertisementPositionByCode(Advertisement.MARKET_INDEX_FIRST);
            if (serviceResultAdvertPosition != null && serviceResultAdvertPosition.isSuccess()) {
                if (serviceResultAdvertPosition.getData() != null) {
                    dataMap.put("positionTitle", serviceResultAdvertPosition.getData().getTitle());
                    dataMap.put("positionDesc", serviceResultAdvertPosition.getData().getPositionDesc());
                } else {
                    dataMap.put("positionTitle", "");
                    dataMap.put("positionDesc", "");
                }
            }

            // 独特体验
            ArticleMarketQueryVo articleMarketQueryVo = new ArticleMarketQueryVo();
            articleMarketQueryVo.setCategoryTag("独特体验");
            articleMarketQueryVo.setCurrentTime(new Date());
            articleMarketQueryVo.setOnline(true);
            articleMarketQueryVo.setRecommand(true);
            ServiceResult<List<ArticleMarketVo>> serviceResultMarket = articleMarketService.listByCategoryTag(articleMarketQueryVo);
            if (serviceResultMarket != null && serviceResultMarket.isSuccess()) {
                dataMap.put("experience", serviceResultMarket.getData());
            }

            // 特色品牌
            articleMarketQueryVo.setCategoryTag("特色品牌");
            articleMarketQueryVo.setCurrentTime(new Date());
            serviceResultMarket = articleMarketService.listByCategoryTag(articleMarketQueryVo);
            if (serviceResultMarket != null && serviceResultMarket.isSuccess()) {
                dataMap.put("brand", serviceResultMarket.getData());
            }

            // 特色品类
            articleMarketQueryVo.setCategoryTag("特色品类");
            articleMarketQueryVo.setCurrentTime(new Date());
            serviceResultMarket = articleMarketService.listByCategoryTag(articleMarketQueryVo);
            if (serviceResultMarket != null && serviceResultMarket.isSuccess()) {
                dataMap.put("category", serviceResultMarket.getData());
            }

            AdvertisementQueryVo adQueryVo = new AdvertisementQueryVo();
            adQueryVo.setCurrentTime(new Date());
            adQueryVo.setPositionCode(Advertisement.MAIN_APP_INDEX_BRAND);
            ServiceResult<List<AdvertisementVo>> adServiceResult = new ServiceResult<List<AdvertisementVo>>();
            adServiceResult = advertisementService.listADsByPositionCode(adQueryVo);
            if (adServiceResult != null && adServiceResult.isSuccess()) {
                List<AdvertisementVo> brandAdvertisementVos = adServiceResult.getData();
                if (brandAdvertisementVos != null && brandAdvertisementVos.size() != 0) {
                    if (brandAdvertisementVos.size() > 1 && brandAdvertisementVos.get(0).getSequence() == 0) {
                        brandAdvertisementVos.remove(0);
                    }
                }
                dataMap.put("ad_brandList", brandAdvertisementVos);
            } else {
                result.setCode(ResultCode.C500.getCode());
                if (adServiceResult != null) {
                    result.setMessage(adServiceResult.getMsg());
                }
                return result;
            }

            //品牌合辑
            CmsCollectionQueryVo collectionQueryVo = new CmsCollectionQueryVo();
            collectionQueryVo.setTop(true);
            collectionQueryVo.setType(CmsCollection.TYPE_MARKET);
            collectionQueryVo.setSubType(CmsCollection.SUBTYPE_MARKET_BRAND);
            ServiceResult<List<CmsCollectionVo>> collectionServiceResult = cmsCollectionService.findCollectionByType(collectionQueryVo);
            if (collectionServiceResult != null && collectionServiceResult.isSuccess()) {
                dataMap.put("collection_brand", processBrandCollectionData(collectionServiceResult.getData()));
            } else {
                result.setCode(ResultCode.C500.getCode());
                if (collectionServiceResult != null) {
                    result.setMessage(collectionServiceResult.getMsg());
                }
                return result;
            }
            // 特色商品
            articleMarketQueryVo.setCategoryTag("特色商品");
            articleMarketQueryVo.setCurrentTime(new Date());
            serviceResultMarket = articleMarketService.listByCategoryTag(articleMarketQueryVo);
            if (serviceResultMarket != null && serviceResultMarket.isSuccess()) {
                dataMap.put("product", serviceResultMarket.getData());
            }

            //特色场馆
            articleMarketQueryVo.setCategoryTag("特色场馆");
            articleMarketQueryVo.setCurrentTime(new Date());
            serviceResultMarket = articleMarketService.listByCategoryTag(articleMarketQueryVo);
            if (serviceResultMarket != null && serviceResultMarket.isSuccess()) {
                dataMap.put("venue", serviceResultMarket.getData());
            }

            // 促销活动
//            advertisementQueryVo.setPositionCode(Advertisement.MARKET_INDEX_SALE);
//            advertisementQueryVo.setCurrentTime(new Date());
//            advertisementQueryVo.setHome(true);
//            serviceResultAdvert = advertisementService.listADsByPositionCode(advertisementQueryVo);
//            if (serviceResultAdvert != null && serviceResultAdvert.isSuccess()) {
//                if (serviceResultAdvert.getData() == null || serviceResultAdvert.getData().isEmpty()) {
//                    AdvertisementQueryVo firstAdvertQueryVo = new AdvertisementQueryVo();
//                    firstAdvertQueryVo.setPositionCode(Advertisement.MARKET_INDEX_SALE);
//                    firstAdvertQueryVo.setCurrentTime(new Date());
//                    firstAdvertQueryVo.setSequence(0);
//                    ServiceResult<AdvertisementVo> serviceResultFirst = advertisementService.getAdvertByFirst(firstAdvertQueryVo);
//                    if(serviceResultFirst != null && serviceResultFirst.isSuccess()){
//                        dataMap.put("promotion", serviceResultFirst.getData());
//                    }
//                } else {
//                    dataMap.put("promotion", serviceResultAdvert.getData());
//                }
//            }


            serviceResultAdvert = advertisementService.getMarketPromotionAdvert();
            if (serviceResultAdvert != null && serviceResultAdvert.isSuccess()) {
                if (serviceResultAdvert.getData() == null || serviceResultAdvert.getData().isEmpty()) {
                    AdvertisementQueryVo firstAdvertQueryVo = new AdvertisementQueryVo();
                    firstAdvertQueryVo.setPositionCode(Advertisement.MARKET_INDEX_SALE);
                    firstAdvertQueryVo.setCurrentTime(new Date());
                    firstAdvertQueryVo.setSequence(0);
                    ServiceResult<AdvertisementVo> serviceResultFirst = advertisementService.getAdvertByFirst(firstAdvertQueryVo);
                    if (serviceResultFirst != null && serviceResultFirst.isSuccess()) {
                        List<AdvertisementVo> advertVoList = new ArrayList<>();
                        if(null!=serviceResultFirst.getData()){
                            advertVoList.add(serviceResultFirst.getData());
                        }
                        dataMap.put("promotion", advertVoList);
                    }
                } else {
                    dataMap.put("promotion", serviceResultAdvert.getData());
                }
            }
            // 商场位置
            com.redstar.digital.open.vo.ServiceResult<List<MarketPositionVo>> marketServiceResult = omsMarketInfoService.getMarketPositionList();
//            if (LOGGER.isInfoEnabled()) {
//                LOGGER.info("调用商场位置接口 --> Request : {}, Response : {}", "", JsonUtil.toJson(marketServiceResult, false));
//            }
            if (marketServiceResult != null && marketServiceResult.isSuccess()) {
                dataMap.put("market_position", marketServiceResult.getData());
            }
            result.setDataMap(dataMap);
            return result;
        } catch (Exception e) {
            LOGGER.error("{}", e.getMessage());
            result.setCode(ResultCode.C500.getCode());
            result.setMessage(e.getMessage());
            return result;
        }
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 404, message = "返回数据为空"),
            @ApiResponse(code = 415, message = "请求参数错误"),
            @ApiResponse(code = 422, message = "校验错误"),
            @ApiResponse(code = 500, message = "系统内部错误")
    })
    @ApiOperation(value = "market detail page", notes = "商场详情页数据接口")
    @RequestMapping(value = "/market/{marketId}/detail", method = RequestMethod.GET)
    public Result<Map<String, Object>> marketDetailPage(@PathVariable Integer marketId) {
        Result<Map<String, Object>> result = new Result<>(ResultCode.C200.getCode());

        /**
         * 广告  key --> advert
         * 特色场馆 key --> venue
         * 独特体验 key --> experience
         * 特色商品 key --> product
         * 导购员合辑 key --> sales_collection
         */

        try {
            Map<String, Object> dataMap = new HashMap<>();
            // 广告
            AdvertisementQueryVo advertisementQueryVo = new AdvertisementQueryVo();
            advertisementQueryVo.setPositionCode(String.valueOf(marketId));
            advertisementQueryVo.setOnline(true);
            advertisementQueryVo.setCurrentTime(new Date());
            ServiceResult<List<AdvertisementVo>> serviceResultAdvert = advertisementService.listADsByPositionCode(advertisementQueryVo);
            if (serviceResultAdvert != null && serviceResultAdvert.isSuccess()) {
                List<AdvertisementVo> firstAdvertisementVos = serviceResultAdvert.getData();
                if (firstAdvertisementVos != null && firstAdvertisementVos.size() != 0) {
                    if (firstAdvertisementVos.size() > 1 && firstAdvertisementVos.get(0).getSequence() == 0) {
                        firstAdvertisementVos.remove(0);
                    }
                }
                dataMap.put("advert", firstAdvertisementVos);
            }

            // 独特体验
            ArticleMarketQueryVo articleMarketQueryVo = new ArticleMarketQueryVo();
            articleMarketQueryVo.setCategoryTag("独特体验");
            articleMarketQueryVo.setOnline(true);
            articleMarketQueryVo.setTop(true);
            articleMarketQueryVo.setMarketId(marketId);
            ServiceResult<List<ArticleMarketVo>> serviceResultMarket = articleMarketService.listByCategoryTag(articleMarketQueryVo);
            if (serviceResultMarket != null && serviceResultMarket.isSuccess()) {
                dataMap.put("experience", serviceResultMarket.getData());
            }

            // 特色商品
            articleMarketQueryVo.setCategoryTag("特色商品");
            articleMarketQueryVo.setCurrentTime(new Date());
            serviceResultMarket = articleMarketService.listByCategoryTag(articleMarketQueryVo);
            if (serviceResultMarket != null && serviceResultMarket.isSuccess()) {
                dataMap.put("product", serviceResultMarket.getData());
            }

            //特色场馆
            articleMarketQueryVo.setCategoryTag("特色场馆");
            articleMarketQueryVo.setCurrentTime(new Date());
            serviceResultMarket = articleMarketService.listByCategoryTag(articleMarketQueryVo);
            if (serviceResultMarket != null && serviceResultMarket.isSuccess()) {
                dataMap.put("venue", serviceResultMarket.getData());
            }

            // 导购员合辑
            ServiceResult<CmsCollectionVo> salesCollectionServiceResult = cmsCollectionService.getCmsSalesCollectionByMarketId(marketId);
//            if (LOGGER.isInfoEnabled()) {
//                LOGGER.error("调用获取导购员合辑信息接口 -->  Request : {}, Response : ", marketId, JsonUtil.toJson(salesCollectionServiceResult, false));
//            }

            if (salesCollectionServiceResult == null || !salesCollectionServiceResult.isSuccess()) {
                LOGGER.error("获取导购员合辑失败. Cause by : {}", salesCollectionServiceResult.getMsg());
            }

            if (salesCollectionServiceResult != null && salesCollectionServiceResult.isSuccess()) {
                dataMap.put("sales_collection", salesCollectionServiceResult.getData());
            }

            result.setDataMap(dataMap);
            return result;
        } catch (Exception e) {
            LOGGER.error("{}", e.getMessage());
            result.setCode(ResultCode.C500.getCode());
            result.setMessage(e.getMessage());
            return result;
        }
    }

//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "请求成功"),
//            @ApiResponse(code = 404, message = "返回数据为空"),
//            @ApiResponse(code = 415, message = "请求参数错误"),
//            @ApiResponse(code = 422, message = "校验错误"),
//            @ApiResponse(code = 500, message = "系统内部错误")
//    })
//    @ApiOperation(value = "discovery", notes = "发现页面数据接口")
//    @RequestMapping(value = "/discovery/{type}/{subType1}/{subType2}", method = RequestMethod.GET)
//    public Result<List<RecommendationVo>> discoveryPageController(@PathVariable("type") Integer type,
//                                                                  @PathVariable("subType1") Integer subType1,
//                                                                  @PathVariable("subType2") Integer subType2,
//                                                                  @ModelAttribute Page page) {
//        Result<List<RecommendationVo>> result = new Result<>();
//        RecommendationQueryVo queryVo = new RecommendationQueryVo();
//        queryVo.setType(type);
//        queryVo.setSubType1(subType1);
//        queryVo.setSubType2(subType2);
//        try {
//            ServiceResult<List<RecommendationVo>> serviceResult = recommendationService.getRecommendationListByType(queryVo, page);
//            if (serviceResult != null && serviceResult.isSuccess()) {
//                result.setCode(ResultCode.C200.getCode());
//                result.setDataMap(serviceResult.getData());
//                return result;
//            }
//
//            result.setCode(ResultCode.C500.getCode());
//            result.setMessage(serviceResult.getMsg());
//            return result;
//        } catch (Exception e) {
//            LOGGER.error("获取推荐数据失败. Cause by : {}", e.getMessage());
//            result.setCode(ResultCode.C500.getCode());
//            result.setMessage("获取推荐数据失败. Cause by : " + e.getMessage());
//            return result;
//        }
//    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 404, message = "返回数据为空"),
            @ApiResponse(code = 415, message = "请求参数错误"),
            @ApiResponse(code = 422, message = "校验错误"),
            @ApiResponse(code = 500, message = "系统内部错误")
    })
    @ApiOperation(value = "selection product", notes = "精选/美图")
    @RequestMapping(value = "/search/condition/cateoryTypeProduct", method = RequestMethod.GET)
    public Result<List<CmsClassVo>> searchPage() {
        Result<List<CmsClassVo>> result = new Result<>(ResultCode.C200.getCode());
        try {
            // 分类 --> 商品 --> 美图
            CmsClassQueryVo picturesQueryVo = new CmsClassQueryVo();
            picturesQueryVo.setCategoryType(CmsClass.CATEGORY_TYPE_PRODUCT);
            picturesQueryVo.setCategorySubType(CmsClass.CATEGORY_SUB_TYPE_PRODUCT_PICTURES);
            ServiceResult<List<CmsClassVo>> picturesServiceResult = cmsClassService.getCmsClassListByQuery(picturesQueryVo);

            // 分类 --> 商品 --> 精选
            CmsClassQueryVo productQueryVo = new CmsClassQueryVo();
            productQueryVo.setCategoryType(CmsClass.CATEGORY_TYPE_PRODUCT);
            productQueryVo.setCategorySubType(CmsClass.CATEGORY_SUB_TYPE_PRODUCT_SELECTION);
            ServiceResult<List<CmsClassVo>> productServiceResult = cmsClassService.getCmsClassListByQuery(productQueryVo);
            if (picturesServiceResult != null && picturesServiceResult.isSuccess()
                    && productServiceResult != null && productServiceResult.isSuccess()) {
                List<CmsClassVo> cmsClassVoList = processCategoryData(productServiceResult.getData());
                cmsClassVoList.addAll(processCategoryData(picturesServiceResult.getData()));
                // 排序
                Collections.sort(cmsClassVoList, new CmsClassVoComparator());
                result.setDataMap(cmsClassVoList);
                return result;
            }
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("数据为空");
            return result;
        } catch (Exception e) {
            LOGGER.error("获取分类数据失败. Cause by : {}", e.getMessage());
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("获取分类数据失败. Cause by : " + e.getMessage());
            return result;
        }
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 404, message = "返回数据为空"),
            @ApiResponse(code = 415, message = "请求参数错误"),
            @ApiResponse(code = 422, message = "校验错误"),
            @ApiResponse(code = 500, message = "系统内部错误")
    })
    @ApiOperation(value = "brand category", notes = "商品品牌/商品类目")
    @RequestMapping(value = "/cmsClass/categoryTypeMarket", method = RequestMethod.GET)
    public Result<List<CmsClassVo>> cmsClassMarket() {
        Result<List<CmsClassVo>> result = new Result<>(ResultCode.C200.getCode());
        try {
            // 分类 --> 商场 --> 商品类目
            CmsClassQueryVo picturesQueryVo = new CmsClassQueryVo();
            picturesQueryVo.setCategoryType(CmsClass.CATEGORY_TYPE_MARKET);
            picturesQueryVo.setCategorySubType(CmsClass.CATEGORY_SUB_TYPE_MARKET_PRODUCT_CATEGORY);
            ServiceResult<List<CmsClassVo>> categoryServiceResult = cmsClassService.getCmsClassListByQuery(picturesQueryVo);

            // 分类 --> 商场 --> 商品品牌
            CmsClassQueryVo productQueryVo = new CmsClassQueryVo();
            productQueryVo.setCategoryType(CmsClass.CATEGORY_TYPE_MARKET);
            productQueryVo.setCategorySubType(CmsClass.CATEGORY_SUB_TYPE_MARKET_PRODUCT_BRAND);
            ServiceResult<List<CmsClassVo>> brandServiceResult = cmsClassService.getCmsClassListByQuery(productQueryVo);
            if (categoryServiceResult != null && categoryServiceResult.isSuccess()
                    && brandServiceResult != null && brandServiceResult.isSuccess()) {
                List<CmsClassVo> cmsClassVoList = processCategoryData(brandServiceResult.getData());
                cmsClassVoList.addAll(processCategoryData(categoryServiceResult.getData()));
                //Collections.sort(cmsClassVoList, new CmsClassVoComparator());
                result.setDataMap(cmsClassVoList);
                return result;
            }
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("数据为空");
            return result;
        } catch (Exception e) {
            LOGGER.error("获取分类数据失败. Cause by  : {}", e.getMessage());
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("获取分类数据失败. Cause by : " + e.getMessage());
            return result;
        }
    }

    /**
     * 处理分类数据
     *
     * @param cmsClassVoList
     * @return
     */
    private List<CmsClassVo> processCategoryData(List<CmsClassVo> cmsClassVoList) {
        if (cmsClassVoList == null || cmsClassVoList.isEmpty()) {
            return cmsClassVoList;
        }

        Map<String, List<Integer>> idMap = getCmsClassData(cmsClassVoList);

        Map<String, Object> dataMap = getCmsClassDatasByIdMap(idMap);

        Map<String, CmsClassVo> cmsClassVoMap = processCmsClassVo(cmsClassVoList, dataMap);

        cmsClassVoList = new ArrayList<>();

        for (Map.Entry<String, CmsClassVo> entry : cmsClassVoMap.entrySet()) {
            cmsClassVoList.add(entry.getValue());
        }

        return cmsClassVoList;
    }

    /**
     * 获取分类中的数据
     *
     * @param cmsClassVoList
     * @return
     */
    private Map<String, List<Integer>> getCmsClassData(List<CmsClassVo> cmsClassVoList) {

        Map<String, List<Integer>> idMap = new HashMap<>();

        for (CmsClassVo cmsClassVo : cmsClassVoList) {

            if (cmsClassVo == null || cmsClassVo.getDatas() == null) {
                continue;
            }

            switch (cmsClassVo.getCategorySubType()) {
                case CmsClass.CATEGORY_SUB_TYPE_PRODUCT_SELECTION:
//                    List<Integer> tagIdList1 = idMap.get(CMS_CLASS_TAGS);
//                    if (tagIdList1 == null) {
//                        tagIdList1 = new ArrayList<>();
//                    }
//                    tagIdList1.add(Integer.valueOf(cmsClassVo.getDatas()));
//                    idMap.put(CMS_CLASS_TAGS, tagIdList1);
                    break;
                case CmsClass.CATEGORY_SUB_TYPE_PRODUCT_PICTURES:
//                    List<Integer> tagIdList = idMap.get(CMS_CLASS_TAGS);
//                    if (tagIdList == null) {
//                        tagIdList = new ArrayList<>();
//                    }
//                    tagIdList.add(Integer.valueOf(cmsClassVo.getDatas()));
//                    idMap.put(CMS_CLASS_TAGS, tagIdList);
                    break;
                case CmsClass.CATEGORY_SUB_TYPE_MARKET_PRODUCT_CATEGORY:
                    List<Integer> categoryIdList = idMap.get(CMS_CLASS_CATEGORY);
                    if (categoryIdList == null) {
                        categoryIdList = new ArrayList<>();
                    }
                    categoryIdList.add(Integer.valueOf(cmsClassVo.getDatas()));
                    idMap.put(CMS_CLASS_CATEGORY, categoryIdList);
                    break;
                case CmsClass.CATEGORY_SUB_TYPE_MARKET_PRODUCT_BRAND:
                    List<Integer> brandIdList = idMap.get(CMS_CLASS_BRAND);
                    if (brandIdList == null) {
                        brandIdList = new ArrayList<>();
                    }
                    brandIdList.add(Integer.valueOf(cmsClassVo.getDatas()));
                    idMap.put(CMS_CLASS_BRAND, brandIdList);
                    break;
                default:
                    break;
            }
        }
        return idMap;
    }

    /**
     * 根据CmsClass中配置的Id, 批量查询Id对应的数据
     *
     * @param idMap
     * @return
     */
    private Map<String, Object> getCmsClassDatasByIdMap(Map<String, List<Integer>> idMap) {

        Map<String, Object> dataMap = new HashMap<>();

        for (Map.Entry<String, List<Integer>> entry : idMap.entrySet()) {

            List<Integer> idList = entry.getValue();

            if (idList == null || idList.isEmpty()) {
                continue;
            }

            if (CMS_CLASS_TAGS.equals(entry.getKey())) { // 标签
//                ServiceResult<List<CmsClassTagsVo>> tagsServiceResult = cmsClassService.getCmsClassTagsByIdList(idList);
//                if (!tagsServiceResult.isSuccess()) {
//                    LOGGER.error("调用获取标签接口失败. Cause by : {}", tagsServiceResult.getMsg());
//                    continue;
//                }
//
//                for (CmsClassTagsVo cmsClassTagsVo : tagsServiceResult.getData()) {
//                    if (cmsClassTagsVo == null) {
//                        continue;
//                    }
//
//                    dataMap.put(CMS_CLASS_TAGS + cmsClassTagsVo.getId(), cmsClassTagsVo);
//                }

            } else if (CMS_CLASS_CATEGORY.equals(entry.getKey())) { // 分类
                com.redstar.digital.open.vo.ServiceResult<List<ShowCategoryVo>> showCategoryServiceResult = showCategoryService.getShowCategoryListByCateIds(idList);
//                if (LOGGER.isInfoEnabled()) {
//                    LOGGER.info("调用获取分类数据接口 --> Request : {}, Response : {}", idList, JsonUtil.toJson(showCategoryServiceResult, false));
//                }

                if (!showCategoryServiceResult.isSuccess()) {
                    LOGGER.error("调用获取分类数据接口失败. Cause by : {}", showCategoryServiceResult.getMsg());
                    continue;
                }

                for (ShowCategoryVo showCategoryVo : showCategoryServiceResult.getData()) {
                    if (showCategoryVo == null) {
                        continue;
                    }

                    dataMap.put(CMS_CLASS_CATEGORY + showCategoryVo.getCategoryId(), showCategoryVo);
                }

            } else if (CMS_CLASS_BRAND.equals(entry.getKey())) { // 品牌

                com.redstar.digital.open.vo.ServiceResult<List<OmsBrandInfo>> brandServiceResult = omsBrandInfoService.getOmsBrandInfoById(idList);
//                if (LOGGER.isInfoEnabled()) {
//                    LOGGER.error("调用品牌服务 --> Request : {},  Response : {}", idList, JsonUtil.toJson(brandServiceResult, false));
//                }

                if (!brandServiceResult.isSuccess()) {
                    LOGGER.error("获取品牌信息失败. Request : {},  Casue by : {}", idList, brandServiceResult.getMsg());
                    continue;
                }

                for (OmsBrandInfo omsBrandInfo : brandServiceResult.getData()) {
                    if (omsBrandInfo == null) {
                        continue;
                    }
                    dataMap.put(CMS_CLASS_BRAND + omsBrandInfo.getId(), omsBrandInfo);
                }
            }
        }

        return dataMap;
    }


    /**
     * 封装CmsClass数据
     *
     * @param cmsClassVoList
     * @param dataMap
     * @return
     */
    private Map<String, CmsClassVo> processCmsClassVo(List<CmsClassVo> cmsClassVoList, Map<String, Object> dataMap) {
        Map<String, CmsClassVo> cmsClassVoMap = new HashMap<>();

        if (cmsClassVoList == null || cmsClassVoList.isEmpty()) {
            return cmsClassVoMap;
        }


        for (CmsClassVo cmsClassVo : cmsClassVoList) {
            if (cmsClassVo == null || cmsClassVo.getDatas() == null) {
                continue;
            }
            String data = cmsClassVo.getDatas();
            switch (cmsClassVo.getCategorySubType()) {
                case CmsClass.CATEGORY_SUB_TYPE_PRODUCT_SELECTION:
                    CmsClassVo selection = cmsClassVoMap.get(CMS_CLASS_SELECTION);
                    if (selection == null) {
                        cmsClassVo.setCategoryName("灵感专栏");
                        cmsClassVoMap.put(CMS_CLASS_SELECTION, cmsClassVo);
                        selection = cmsClassVo;
                    }

                    List<CmsClassDataVo> classDataSelection = selection.getClassData();
                    if (classDataSelection == null) {
                        classDataSelection = new ArrayList<>();
                    }

                    CmsClassTagsVo selectionCmsClass = new CmsClassTagsVo();
                    selectionCmsClass.setName(data);
                    classDataSelection.add(selectionCmsClass);

                    cmsClassVo.setClassData(classDataSelection);

                    break;
                case CmsClass.CATEGORY_SUB_TYPE_PRODUCT_PICTURES:
                    CmsClassVo pictrues = cmsClassVoMap.get(CMS_CLASS_PICTURES);
                    if (pictrues == null) {
                        cmsClassVo.setCategoryName("家居美图");
                        cmsClassVoMap.put(CMS_CLASS_PICTURES, cmsClassVo);
                        pictrues = cmsClassVo;
                    }

                    List<CmsClassDataVo> classDataPictrues = pictrues.getClassData();
                    if (classDataPictrues == null) {
                        classDataPictrues = new ArrayList<>();
                    }

                    CmsClassTagsVo pictruesCmsClass = new CmsClassTagsVo();
                    pictruesCmsClass.setName(data);
                    classDataPictrues.add(pictruesCmsClass);

                    pictrues.setClassData(classDataPictrues);
                    break;
                case CmsClass.CATEGORY_SUB_TYPE_MARKET_PRODUCT_CATEGORY:
                    CmsClassVo category = cmsClassVoMap.get(CMS_CLASS_CATEGORY);
                    if (category == null) {
                        cmsClassVo.setCategoryName("商品类目");
                        cmsClassVoMap.put(CMS_CLASS_CATEGORY, cmsClassVo);
                        category = cmsClassVo;
                    }
                    List<CmsClassDataVo> classDataProduct = category.getClassData();
                    if (classDataProduct == null) {
                        classDataProduct = new ArrayList<>();
                    }
//                    for (Object showCategoryVoObj : dataMap.values()) {
//                        ShowCategoryVo showCategoryVo = (ShowCategoryVo) showCategoryVoObj;
//                        CmsClassCategoryVo categoryVo = new CmsClassCategoryVo();
//                        BeanUtils.copyProperties(showCategoryVo, categoryVo);
//                        classDataProduct.add(categoryVo);
//                    }
                    ShowCategoryVo showCategoryVo = (ShowCategoryVo) dataMap.get(CMS_CLASS_CATEGORY + data);
                    if (showCategoryVo == null) {
                        continue;
                    }
                    CmsClassCategoryVo cmsClassCategoryVo = new CmsClassCategoryVo();
                    BeanUtils.copyProperties(showCategoryVo, cmsClassCategoryVo);
                    classDataProduct.add(cmsClassCategoryVo);
                    category.setClassData(classDataProduct);
                    break;
                case CmsClass.CATEGORY_SUB_TYPE_MARKET_PRODUCT_BRAND:
                    CmsClassVo brand = cmsClassVoMap.get(CMS_CLASS_BRAND);
                    if (brand == null) {
                        cmsClassVo.setCategoryName("热销品牌");
                        cmsClassVoMap.put(CMS_CLASS_BRAND, cmsClassVo);
                        brand = cmsClassVo;
                    }

                    OmsBrandInfo omsBrandInfo = (OmsBrandInfo) dataMap.get(CMS_CLASS_BRAND + data);
                    if (omsBrandInfo == null) {
                        continue;
                    }

                    List<CmsClassDataVo> classDataBrandList = brand.getClassData();
                    if (classDataBrandList == null) {
                        classDataBrandList = new ArrayList<>();
                    }

                    CmsClassBrandVo cmsClassBrandInfo = new CmsClassBrandVo();
                    BeanUtils.copyProperties(omsBrandInfo, cmsClassBrandInfo);
                    classDataBrandList.add(cmsClassBrandInfo);

                    brand.setClassData(classDataBrandList);
                    break;
                default:
                    break;
            }
        }
        return cmsClassVoMap;
    }

    /**
     * 处理品牌合辑数据
     *
     * @return
     */
    private List<CmsCollectionVo> processBrandCollectionData(List<CmsCollectionVo> cmsCollectionVoList) {

        if (cmsCollectionVoList == null || cmsCollectionVoList.isEmpty()) {
            return cmsCollectionVoList;
        }


        for (CmsCollectionVo cmsCollectionVo : cmsCollectionVoList) {

            if (cmsCollectionVo == null) {
                continue;
            }

            List<Integer> brandIdList = new ArrayList<>();

            List<CmsCollectionData> collectionDataList = cmsCollectionVo.getCollectionDataList();

            if (collectionDataList == null || collectionDataList.isEmpty()) {
                continue;
            }

            for (CmsCollectionData cmsCollectionData : collectionDataList) {
                if (cmsCollectionData == null || cmsCollectionData.getTargetId() == null) {
                    continue;
                }
                brandIdList.add(Integer.valueOf(cmsCollectionData.getTargetId()));
            }


            com.redstar.digital.open.vo.ServiceResult<List<OmsBrandInfo>> brandServiceResult = omsBrandInfoService.getOmsBrandInfoById(brandIdList);
//            if (LOGGER.isInfoEnabled()) {
//                LOGGER.info("调用品牌服务 --> Request : {},  Response : {}", brandIdList, JsonUtil.toJson(brandServiceResult, false));
//            }

            if (!brandServiceResult.isSuccess()) {
                LOGGER.error("获取品牌信息失败. Parameter : {},  Casue by : {}", brandIdList, brandServiceResult.getMsg());
                continue;
            }

            List<OmsBrandInfo> brandInfoList = brandServiceResult.getData();

            List<CmsCollectionTargetVo> brandVoList = new ArrayList<>();
            Map<Integer, CmsCollectionBrandVo> cmsCollectionBrandVoMap = new HashMap<>();
            for (OmsBrandInfo omsBrandInfo : brandInfoList) {
                CmsCollectionBrandVo brandVo = new CmsCollectionBrandVo();
                BeanUtils.copyProperties(omsBrandInfo, brandVo);
                //brandVoList.add(brandVo);
                cmsCollectionBrandVoMap.put(brandVo.getId(), brandVo);
            }

            // 品牌排序
            for (CmsCollectionData cmsCollectionData : collectionDataList) {
                if (cmsCollectionData == null || cmsCollectionData.getTargetId() == null) {
                    continue;
                }
                Integer targetId = Integer.valueOf(cmsCollectionData.getTargetId());
                if(null != targetId && null != cmsCollectionBrandVoMap.get(targetId)){
                    brandVoList.add(cmsCollectionBrandVoMap.get(targetId));
                }
            }

            cmsCollectionVo.setCmsCollectionTargetVoList(brandVoList);
        }
        return cmsCollectionVoList;
    }

    /**
     * 处理设计师合辑数据
     *
     * @return
     */
    private List<CmsCollectionVo> processDesignerCollection(List<CmsCollectionVo> cmsCollectionVoList) {

        if (cmsCollectionVoList == null || cmsCollectionVoList.isEmpty()) {
            return cmsCollectionVoList;
        }

        for (CmsCollectionVo cmsCollectionVo : cmsCollectionVoList) {
            if (cmsCollectionVo == null) {
                continue;
            }

            List<Integer> designerIdList = new ArrayList<>();

            List<CmsCollectionData> cmsCollectionDataList = cmsCollectionVo.getCollectionDataList();
            for (CmsCollectionData cmsCollectionData : cmsCollectionDataList) {
                if (cmsCollectionData == null || StringUtils.isBlank(cmsCollectionData.getTargetId())) {
                    continue;
                }

                designerIdList.add(Integer.valueOf(cmsCollectionData.getTargetId()));
            }

            if (designerIdList == null || designerIdList.isEmpty()) {
                continue;
            }

            JzDesignerQuery jzDesignerQuery = new JzDesignerQuery();
            jzDesignerQuery.setDesignerIds(designerIdList);
            com.chinaredstar.jiazhuang.api.bean.Result<List<JzDesignerListDto>> designerResult = designerServiceApi.getListByQuery(jzDesignerQuery, 0, Integer.MAX_VALUE);
//            if (LOGGER.isInfoEnabled()) {
//                LOGGER.info("调用设计师服务 --> Request : {}, Response : {}.", JsonUtil.toJson(jzDesignerQuery, false), JsonUtil.toJson(designerResult, false));
//            }

            if (designerResult != null && designerResult.getCode().equals(ResultCode.C200.getCode())) {
//                if (LOGGER.isInfoEnabled()) {
//                    LOGGER.info("根据设计师ID集合[{}]获取设计师数据信息 : {}", designerIdList, JsonUtil.toJson(designerResult.getDataMap(), false));
//                }
                List<CmsCollectionTargetVo> designerVoList = new ArrayList<>();
                for (JzDesignerListDto jzDesignerListDto : designerResult.getDataMap()) {
                    CmsCollectionDesignerVo designerVo = new CmsCollectionDesignerVo();
                    BeanUtils.copyProperties(jzDesignerListDto, designerVo);
                    designerVoList.add(designerVo);
                }
                cmsCollectionVo.setCmsCollectionTargetVoList(designerVoList);
            } else {
                LOGGER.error("获取设计师数据失败. param : {}", designerIdList);
            }
        }
        return cmsCollectionVoList;
    }
}
