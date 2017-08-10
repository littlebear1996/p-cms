package com.chinaredstar.cms.controller;

import com.chinaredstar.cms.api.component.Page;
import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.model.Advertisement;
import com.chinaredstar.cms.api.model.CmsCollection;
import com.chinaredstar.cms.api.service.*;
import com.chinaredstar.cms.api.vo.advertisement.AdvertisementQueryVo;
import com.chinaredstar.cms.api.vo.advertisement.AdvertisementVo;
import com.chinaredstar.cms.api.vo.article.ArticleMarketQueryVo;
import com.chinaredstar.cms.api.vo.article.ArticleMarketVo;
import com.chinaredstar.cms.api.vo.collection.CmsCollectionBrandVo;
import com.chinaredstar.cms.api.vo.collection.CmsCollectionQueryVo;
import com.chinaredstar.cms.api.vo.collection.CmsCollectionTargetVo;
import com.chinaredstar.cms.api.vo.index.*;
import com.chinaredstar.cms.bean.IndexBean;
import com.chinaredstar.cms.bean.MarketIndexBean;
import com.chinaredstar.cms.vo.Result;
import com.chinaredstar.cms.vo.ResultCode;
import com.chinaredstar.perseus.utils.JsonUtil;
import com.redstar.digital.open.service.OmsMarketInfoService;
import com.redstar.digital.open.vo.MarketPositionVo;
import com.wordnik.swagger.annotations.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Ykk on 2017/3/24.
 */
@RestController
@RequestMapping(value = "/home")
@Api(value = "home",description = "主APP首页接口")
public class IndexController {


    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    //定义线程池大小
    public static final int THREAD_SIZE= 8;

    @Autowired
    private AdvertisementService advertisementService;

    @Autowired
    private CmsIndexBrandService cmsIndexBrandService;

    @Autowired
    private CmsIndexTopicService cmsIndexTopicService;

    @Autowired
    private CmsIndexPromotionService cmsIndexPromotionService;

    @Autowired
    private CmsIndexLifeService cmsIndexLifeService;
    
    @Autowired
    private CmsIndexModuleConfigService cmsIndexModuleConfigService;

    @Autowired
    private OmsMarketInfoService omsMarketInfoService;

    @Autowired
    private CmsIndexCategoryService cmsIndexCategoryService;

    @Autowired
    private CmsIndexAdvertisementService cmsIndexAdvertisementService;

    @Autowired
    private CmsIndexCollectionService cmsIndexCollectionService;

    @Autowired
    private ArticleMarketService articleMarketService;

    @Autowired
    private CmsIndexTicketService cmsIndexTicketService;

    //创建固定大小的线程池
    public static ExecutorService executorService = Executors.newFixedThreadPool(THREAD_SIZE);

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 404, message = "返回数据为空"),
            @ApiResponse(code = 415, message = "请求参数错误"),
            @ApiResponse(code = 422, message = "校验错误"),
            @ApiResponse(code = 500, message = "系统内部错误")
    })
    @ApiOperation(value = "APP首页接口", notes = "APP首页接口", response = IndexBean.class)
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public Result<IndexBean> index() {

        Result<IndexBean> result = new Result<>(ResultCode.C200.code);

        Map<String, Object> dataMap = new HashMap<>();
        //APP首页接口返回实体对象
        IndexBean indexBean = new IndexBean();
        long start = System.currentTimeMillis();
        try{
            //获取模板配置
            ServiceResult<List<IndexModuleConfigVo>> onlineConfigServiceResult = cmsIndexModuleConfigService.getOnlineConfig();
            if(onlineConfigServiceResult.isSuccess()){
                indexBean.setConfigList(onlineConfigServiceResult.getData());
            }
            //获取广告位数据
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
                indexBean.setAdvertisementList(firstAdvertisementVos);

            }


            //获取领券中心配置

            //获取限时购数据
            IndexPromotionQueryVo promotionQueryVo = new IndexPromotionQueryVo();
            promotionQueryVo.setCurrentTime(new Date());
            ServiceResult<IndexPromotionVo> ongoingPromotionServiceResult = cmsIndexPromotionService.getOngoingPromotion(promotionQueryVo);
            if (ongoingPromotionServiceResult.isSuccess()){
                indexBean.setPromotionVo(ongoingPromotionServiceResult.getData());

            }

            //设计家坑位
            //直播坑位
            //获取生活家数据
            IndexLifeQueryVo lifeQueryVo = new IndexLifeQueryVo();
            lifeQueryVo.setRecommend(true);
            ServiceResult<List<IndexLifeVo>> lifeServiceResult = cmsIndexLifeService.findPageList(lifeQueryVo);
            if(lifeServiceResult.isSuccess()){
                indexBean.setLifeVoList(lifeServiceResult.getData());
            }


            //获取大牌数据
            IndexBrandQueryVo indexBrandQueryVo = new IndexBrandQueryVo();
            indexBrandQueryVo.setRecommend(true);
            ServiceResult<List<IndexBrandVo>> brandServiceResult = cmsIndexBrandService.findPageList(indexBrandQueryVo);
            if(brandServiceResult.isSuccess()){
                indexBean.setBrandList(brandServiceResult.getData());
            }
            //精选主题数据
            IndexTopicQueryVo indexTopicQueryVo = new IndexTopicQueryVo();
            indexTopicQueryVo.setPageSize(5);
            ServiceResult<List<IndexTopicVo>> topicServiceResult = cmsIndexTopicService.findPageList(indexTopicQueryVo);
            if (topicServiceResult.isSuccess()){
                indexBean.setTopicList(topicServiceResult.getData());
            }

//            cmsIndexTicketService.getLatestByCityId();


            long cost = System.currentTimeMillis() - start;
            LOGGER.info("获取首页数据花费:"+cost);

            result.setDataMap(indexBean);
        }catch (Exception e) {
            LOGGER.error("获取app首页数据异常：{}", e);
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("获取app首页数据异常");
        }
        return result;
    }



    @ApiOperation(value = "限时购接口", notes = "限时购接口")
    @RequestMapping(value = "/promotion", method = RequestMethod.GET)
    public Result<IndexPromotionVo> promotion() {
        Result<IndexPromotionVo> result = new Result<>(ResultCode.C200.code);
        try{
            IndexPromotionQueryVo promotionQueryVo = new IndexPromotionQueryVo();
            promotionQueryVo.setCurrentTime(new Date());
            ServiceResult<IndexPromotionVo> ongoingPromotionServiceResult = cmsIndexPromotionService.getOngoingPromotion(promotionQueryVo);
            if (ongoingPromotionServiceResult.isSuccess()){
                result.setDataMap(ongoingPromotionServiceResult.getData());
            }
        }catch (Exception e) {
            LOGGER.error("获取限时购接口异常：{}", e);
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("获取限时购接口异常");
        }
        return result;
    }


    @ApiOperation(value = "生活家列表分页接口", notes = "生活家列表分页接口")
    @RequestMapping(value = "/life/list", method = RequestMethod.POST)
    public Result<List<IndexLifeVo>> lifeList(@ApiParam(value = "是否最热排序,true是;false否") @RequestParam boolean isHottest,@ModelAttribute() Page page) {
        Result<List<IndexLifeVo>> result = new Result<>(ResultCode.C200.code);
        try{
            IndexLifeQueryVo lifeQueryVo = new IndexLifeQueryVo();
            lifeQueryVo.setNewestOrHotest(isHottest);
            lifeQueryVo.setPageNo(page.getPageNo());
            lifeQueryVo.setPageSize(page.getPageSize());
//            lifeQueryVo.setRecommend(true);
            ServiceResult<List<IndexLifeVo>> lifeServiceResult = cmsIndexLifeService.findPageList(lifeQueryVo);
            if(lifeServiceResult.isSuccess()){
                result.setDataMap(lifeServiceResult.getData());
            }
        }catch (Exception e) {
            LOGGER.error("获取生活家分页列表异常, isHottest:{}, page:{}", isHottest, JsonUtil.toJson(page, false), e);
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("获取生活家分页列表异常");
        }
        return result;
    }

    @ApiOperation(value = "生活家详情接口", notes = "生活家详情接口")
    @RequestMapping(value = "/life/detail/{id}", method = RequestMethod.GET)
    public Result<IndexLifeVo> lifeDetail(@ApiParam(value = "生活家id") @PathVariable("id") Integer id) {
        Result<IndexLifeVo> result = new Result<>(ResultCode.C200.code);
        try{
            ServiceResult<IndexLifeVo> lifeServiceResult = cmsIndexLifeService.findById(id);
            if(lifeServiceResult.isSuccess()){
                result.setDataMap(lifeServiceResult.getData());
            }
        }catch (Exception e) {
            LOGGER.error("获取生活家详情接口异常, id:{}", id, e);
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("获取生活家详情接口异常");
        }
        return result;
    }

    @ApiOperation(value = "大牌列表分页接口", notes = "大牌列表分页接口")
    @RequestMapping(value = "/brand/list", method = RequestMethod.POST)
    public Result<List<IndexBrandVo>> brandList(@ApiParam(value = "是否最热排序,true是;false否") @RequestParam boolean isHottest,@ModelAttribute() Page page) {
        Result<List<IndexBrandVo>> result = new Result<>(ResultCode.C200.code);
        try{
            IndexBrandQueryVo indexBrandQueryVo = new IndexBrandQueryVo();
            indexBrandQueryVo.setOrderByHottest(isHottest);
            indexBrandQueryVo.setPageNo(page.getPageNo());
            indexBrandQueryVo.setPageSize(page.getPageSize());
//            indexBrandQueryVo.setRecommend(true);
            ServiceResult<List<IndexBrandVo>> brandServiceResult = cmsIndexBrandService.findPageList(indexBrandQueryVo);
            if(brandServiceResult.isSuccess()){
                result.setDataMap(brandServiceResult.getData());
            }
        }catch (Exception e) {
            LOGGER.error("大牌列表分页接口异常, isHottest:{}, page:{}", isHottest, JsonUtil.toJson(page, false), e);
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("大牌列表分页接口异常");
        }
        return result;
    }


    @ApiOperation(value = "大牌详情接口", notes = "大牌详情接口")
    @RequestMapping(value = "/brand/detail/{id}", method = RequestMethod.GET)
    public Result<IndexBrandVo> brandDetail(@ApiParam(value = "大牌id") @PathVariable("id") Integer id) {
        Result<IndexBrandVo> result = new Result<>(ResultCode.C200.code);
        try{
            ServiceResult<IndexBrandVo> brandServiceResult = cmsIndexBrandService.findById(id);
            if (brandServiceResult.isSuccess()){
                result.setDataMap(brandServiceResult.getData());
            }
        }catch (Exception e) {
            LOGGER.error("获取大牌详情接口异常, id:{}", id, e);
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("获取大牌详情接口异常");
        }
        return result;
    }

    @ApiOperation(value = "精选专题分页接口", notes = "精选专题分页接口")
    @RequestMapping(value = "/topic/list", method = RequestMethod.POST)
    public Result<List<IndexTopicVo>> topicList(@ModelAttribute() Page page) {
        Result<List<IndexTopicVo>> result = new Result<>(ResultCode.C200.code);
        try{
            IndexTopicQueryVo queryVo = new IndexTopicQueryVo();
            queryVo.setPageSize(page.getPageSize());
            queryVo.setPageNo(page.getPageNo());
            ServiceResult<List<IndexTopicVo>> topicServiceResult = cmsIndexTopicService.findPageList(queryVo);
            if (topicServiceResult.isSuccess()){
                result.setDataMap(topicServiceResult.getData());
            }
        }catch (Exception e) {
            LOGGER.error("获取精选专题分页接口异常, page:{}", JsonUtil.toJson(page, false), e);
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("获取精选专题分页接口异常");
        }
        return result;
    }


    @ApiOperation(value = "开通城市列表接口", notes = "开通城市列表接口")
    @RequestMapping(value = "/city/list", method = RequestMethod.GET)
    public Result<List<MarketPositionVo>> cityList() {
        Result<List<MarketPositionVo>> result = new Result<>(ResultCode.C200.code);
        try{
            com.redstar.digital.open.vo.ServiceResult<List<MarketPositionVo>> marketServiceResult = omsMarketInfoService.getMarketPositionList();
            if (marketServiceResult != null && marketServiceResult.isSuccess()) {
                //手动添加全国数据
                MarketPositionVo marketPositionVo = new MarketPositionVo();
                marketPositionVo.setCityId("-1");
                marketPositionVo.setCityName("全国");
                marketServiceResult.getData().add(0,marketPositionVo);
                result.setDataMap(marketServiceResult.getData());
            }
        }catch (Exception e) {
            LOGGER.error("开通城市列表接口异常, :{}", e);
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("开通城市列表接口异常");
        }
        return result;
    }

    @ApiOperation(value = "分类列表接口", notes = "分类列表接口")
    @RequestMapping(value = "/category/list", method = RequestMethod.GET)
    public Result<List<IndexCategoryVo>> categoryList() {
        Result<List<IndexCategoryVo>> result = new Result<>(ResultCode.C200.code);
        try{
            ServiceResult<List<IndexCategoryVo>> allServiceResult = cmsIndexCategoryService.getAll();
            if(allServiceResult.isSuccess()){
                List<IndexCategoryVo> categoryList = allServiceResult.getData();
                if(null!=categoryList && categoryList.size()>0){
                    IndexCategoryVo index = categoryList.get(0);
                    ServiceResult<IndexCategoryVo> categoryServiceResult = cmsIndexCategoryService.findById(index.getId());
                    if (categoryServiceResult.isSuccess()){
                        categoryList.set(0,categoryServiceResult.getData());
                    }
                }
                result.setDataMap(categoryList);
            }
        }catch (Exception e) {
            LOGGER.error("分类列表接口异常, :{}", e);
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("分类列表接口异常");
        }
        return result;
    }


    @ApiOperation(value = "获取分类详情", notes = "获取分类详情")
    @RequestMapping(value = "/category/detail/{id}", method = RequestMethod.GET)
    public Result<IndexCategoryVo> categoryDetail(@ApiParam(value = "分类id") @PathVariable("id") Integer id) {
        Result<IndexCategoryVo> result = new Result<>(ResultCode.C200.code);
        try{
            if(null==id){
                result.setCode(ResultCode.C500.getCode());
                result.setMessage("id不能为空");
                return result;
            }
            ServiceResult<IndexCategoryVo> categoryServiceResult = cmsIndexCategoryService.findById(id);
            if (categoryServiceResult.isSuccess()){
                result.setDataMap(categoryServiceResult.getData());
            }
        }catch (Exception e) {
            LOGGER.error("获取分类详情异常, :{}", e);
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("获取分类详情异常");
        }
        return result;
    }

    @ApiOperation(value = "获取商场首页数据", notes = "获取商场首页数据")
    @RequestMapping(value = "/market/index", method = RequestMethod.GET)
    public Result<MarketIndexBean> marketIndex(@ApiParam(value = "城市id") @RequestParam String cityId) {
        Result<MarketIndexBean> result = new Result<>(ResultCode.C200.code);
        MarketIndexBean marketBean = new MarketIndexBean();
        try{
            //查询商场首页广告
            IndexAdvertisementQueryVo adQueryVo = new IndexAdvertisementQueryVo();
            adQueryVo.setCityId(cityId);
            adQueryVo.setCurrentTime(new Date());
            adQueryVo.setPositionCode(Advertisement.MARKET_INDEX_FIRST);
            ServiceResult<List<IndexAdvertisementVo>> adServiceResult = cmsIndexAdvertisementService.listADsByPositionCodeAndCityId(adQueryVo);
            if (adServiceResult.isSuccess()){
                marketBean.setAdvertisementList(adServiceResult.getData());
            }
            //查询品牌合辑
            CmsCollectionQueryVo collectionQueryVo = new CmsCollectionQueryVo();
            collectionQueryVo.setTop(true);
            collectionQueryVo.setType(CmsCollection.TYPE_MARKET);
            collectionQueryVo.setSubType(CmsCollection.SUBTYPE_MARKET_BRAND);
            collectionQueryVo.setCityId(cityId);
            ServiceResult<List<IndexCollectionVo>> collectionServiceResult = cmsIndexCollectionService.findCollectionByType(collectionQueryVo);
            if (collectionServiceResult.isSuccess()) {
                List<IndexCollectionVo> data = collectionServiceResult.getData();
                if (CollectionUtils.isNotEmpty(data)) {
                    IndexCollectionVo titleBrand = data.get(0);
                    List<CmsCollectionTargetVo> brandTargetVos = titleBrand.getCmsCollectionTargetVoList();
                    if (CollectionUtils.isNotEmpty(brandTargetVos)) {
                        CmsCollectionBrandVo brandVo = (CmsCollectionBrandVo) brandTargetVos.get(0);
                        brandVo.setBrandLogo(titleBrand.getCoverUrl());
                    }
                }
                marketBean.setBrandCollectionList(data);
            }

            //查询商场  独特体验 文章
            ArticleMarketQueryVo articleMarketQueryVo = new ArticleMarketQueryVo();
            articleMarketQueryVo.setCityId(cityId);
            articleMarketQueryVo.setCategoryTag("独特体验");
            articleMarketQueryVo.setCurrentTime(new Date());
            articleMarketQueryVo.setOnline(true);
            articleMarketQueryVo.setRecommand(true);
            articleMarketQueryVo.setPageNo(1);
            articleMarketQueryVo.setPageSize(10);
            ServiceResult<List<ArticleMarketVo>> serviceResultMarket = articleMarketService.listByCategoryTagAndCity(articleMarketQueryVo);
            if (serviceResultMarket != null && serviceResultMarket.isSuccess()) {
                marketBean.setArticleMarketList(serviceResultMarket.getData());
            }
            result.setDataMap(marketBean);
        }catch (Exception e) {
            LOGGER.error("获取商场首页数据异常, :{}", e);
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("获取商场首页数据异常");
        }
        return result;
    }

    @ApiOperation(value = "获取商场首页广告数据详情", notes = "获取商场首页广告数据详情")
    @RequestMapping(value = "/market/ad/detail/{id}", method = RequestMethod.GET)
    public Result<IndexAdvertisementVo> marketIndexAdDetail(@ApiParam(value = "id") @PathVariable Integer id) {
        Result<IndexAdvertisementVo> result = new Result<>(ResultCode.C200.code);
        try {
            ServiceResult<IndexAdvertisementVo> advertisementDetailById = cmsIndexAdvertisementService.getAdvertisementDetailById(id);
            if (null != advertisementDetailById && advertisementDetailById.isSuccess()) {
                result.setDataMap(advertisementDetailById.getData());
            } else{
                LOGGER.error("获取商场首页广告数据详情失败 id:{}",id);
                result.setCode(ResultCode.C500.getCode());
                result.setMessage("获取商场首页广告数据详情失败");
            }
        } catch (Exception e) {
            LOGGER.error("获取商场首页广告数据详情异常 id:{},exception :{}", id, e);
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("获取商场首页广告数据详情异常");
        }
        return result;
    }

    @ApiOperation(value = "获取商场首页分页文章", notes = "获取商场首页分页文章")
    @RequestMapping(value = "/market/article/list", method = RequestMethod.GET)
    public Result<List<ArticleMarketVo>> marketArticleList(@ApiParam(value = "城市id") @RequestParam String cityId,@ModelAttribute() Page page) {
        Result<List<ArticleMarketVo>> result = new Result<>(ResultCode.C200.code);
        try{

            //查询商场  独特体验 文章
            ArticleMarketQueryVo articleMarketQueryVo = new ArticleMarketQueryVo();
            articleMarketQueryVo.setCityId(cityId);
            articleMarketQueryVo.setCategoryTag("独特体验");
            articleMarketQueryVo.setCurrentTime(new Date());
            articleMarketQueryVo.setOnline(true);
            articleMarketQueryVo.setRecommand(true);
            articleMarketQueryVo.setPageNo(page.getPageNo());
            articleMarketQueryVo.setPageSize(page.getPageSize());
            ServiceResult<List<ArticleMarketVo>> serviceResultMarket = articleMarketService.listByCategoryTagAndCity(articleMarketQueryVo);
            if (serviceResultMarket != null && serviceResultMarket.isSuccess()) {
                result.setDataMap(serviceResultMarket.getData());
            }
            //result.setDataMap(marketBean);
        }catch (Exception e) {
            LOGGER.error("获取商场首页数据异常, :{}", e);
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("获取商场首页数据异常");
        }
        return result;
    }

    @ApiOperation(value = "获取领券中心banner", notes = "获取领券中心banner")
    @RequestMapping(value = "/ticket/banner", method = RequestMethod.GET)
    public Result<IndexTicketVo> getTicketBanner(@ApiParam(value = "城市id") @RequestParam String cityId) {
        Result<IndexTicketVo> result = new Result<>(ResultCode.C200.code);
        try{
            if(StringUtils.isBlank(cityId)){
                return result;
            }
            ServiceResult<IndexTicketVo> serviceResult = cmsIndexTicketService.getLatestByCityId(cityId);
            if (serviceResult != null && serviceResult.isSuccess()) {
                result.setDataMap(serviceResult.getData());
            }
        }catch (Exception e) {
            LOGGER.error("获取领券中心banner数据异常, cityId:{}", cityId, e);
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("获取商场首页数据异常");
        }
        return result;
    }
}
