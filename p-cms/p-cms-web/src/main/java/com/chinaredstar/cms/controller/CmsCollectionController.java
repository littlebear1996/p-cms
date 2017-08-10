package com.chinaredstar.cms.controller;

import com.chinaredstar.cms.api.component.Page;
import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.model.CmsCollection;
import com.chinaredstar.cms.api.model.CmsCollectionData;
import com.chinaredstar.cms.api.service.ArticleMarketService;
import com.chinaredstar.cms.api.service.CmsCollectionService;
import com.chinaredstar.cms.api.service.OutlinkService;
import com.chinaredstar.cms.api.vo.article.ArticleMarketVo;
import com.chinaredstar.cms.api.vo.collection.*;
import com.chinaredstar.cms.api.vo.outlink.OutlinkVo;
import com.chinaredstar.cms.vo.Result;
import com.chinaredstar.cms.vo.ResultCode;
import com.chinaredstar.jiazhuang.api.CaseServiceApi;
import com.chinaredstar.jiazhuang.api.DesignerServiceApi;
import com.chinaredstar.jiazhuang.api.dto.JzCaseListDto;
import com.chinaredstar.jiazhuang.api.dto.JzDesignerListDto;
import com.chinaredstar.jiazhuang.api.qo.JzCaseQuery;
import com.chinaredstar.jiazhuang.api.qo.JzDesignerQuery;
import com.chinaredstar.perseus.utils.JsonUtil;
import com.codahale.metrics.annotation.Timed;
import com.redstar.digital.open.bean.OmsBrandInfo;
import com.redstar.digital.open.service.OmsBrandInfoService;
import com.redstar.digital.open.service.OmsShopInfoService;
import com.redstar.digital.open.vo.ShopperExtVo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 合辑Controller
 */
@RestController
@RequestMapping("/collection")
@Api(value = "cmsCollection", description = "合辑相关接口")
@Timed
public class CmsCollectionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CmsCollectionController.class);

    @Autowired
    private CmsCollectionService cmsCollectionService;

    @Autowired
    private ArticleMarketService articleMarketService;

    @Autowired
    private OutlinkService outlinkService;

    @Autowired
    private DesignerServiceApi designerServiceApi;

    @Autowired
    private OmsBrandInfoService omsBrandInfoService;

    @Autowired
    private CaseServiceApi caseServiceApi;

    @Autowired
    private OmsShopInfoService omsShopInfoService;


    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 404, message = "返回数据为空"),
            @ApiResponse(code = 415, message = "请求参数错误"),
            @ApiResponse(code = 422, message = "校验错误"),
            @ApiResponse(code = 500, message = "系统内部错误")
    })
    @ApiOperation(value = "list", notes = "通过合辑类型编码获取合辑信息")
    @RequestMapping(value = "/list/{type}/{subType}", method = RequestMethod.GET)
    public Result<List<CmsCollectionVo>> listByType(@PathVariable("type") Integer type,
                                                    @PathVariable("subType") Integer subType) {
        Result<List<CmsCollectionVo>> result = new Result<>();

        try {
            CmsCollectionQueryVo queryVo = new CmsCollectionQueryVo();
            queryVo.setType(type);
            queryVo.setSubType(subType);
            ServiceResult<List<CmsCollectionVo>> serviceResult = cmsCollectionService.findCollectionByType(queryVo);

            if (serviceResult != null && serviceResult.isSuccess()) {
                List<CmsCollectionVo> cmsCollectionVoList = serviceResult.getData();
                result.setCode(ResultCode.C200.getCode());
                for (CmsCollectionVo cmsCollectionVo : cmsCollectionVoList) {
                    processCollectionData(cmsCollectionVo, cmsCollectionVo.getCollectionDataList());
                }
                result.setDataMap(cmsCollectionVoList);
                return result;
            }
            result.setCode("数据为空");
            result.setMessage(serviceResult.getMsg());
            return result;
        } catch (Exception e) {
            LOGGER.error("获取合辑列表失败. Cause by : {}", e.getMessage());
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
    @ApiOperation(value = "detail", notes = "通过合辑ID获取合辑信息")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public Result<CmsCollectionVo> getCollectionDetailById(@PathVariable("id") Integer id) {
        Result<CmsCollectionVo> result = new Result<>();

        try {
            ServiceResult<CmsCollectionVo> serviceResult = cmsCollectionService.getCmsCollectionListById(id);

            if (serviceResult != null && serviceResult.isSuccess()) {
                CmsCollectionVo cmsCollectionVo = serviceResult.getData();
                result.setCode(ResultCode.C200.getCode());
                processCollectionData(cmsCollectionVo, cmsCollectionVo.getCollectionDataList());
                result.setDataMap(cmsCollectionVo);
                return result;
            }
            result.setCode(ResultCode.C500.getCode());
            if(serviceResult != null) {
                result.setMessage(serviceResult.getMsg());
            }
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
    @ApiOperation(value = "cms collection data", notes = "通过合辑ID分页获取合辑子信息")
    @RequestMapping(value = "/detail/{id}/page", method = RequestMethod.GET)
    public Result<CmsCollectionVo> getCollectionDataListByIdWithPage(@PathVariable("id") Integer id,
                                                                     @ModelAttribute Page page) {
        Result<CmsCollectionVo> result = new Result<>();

        try {
            ServiceResult<CmsCollectionVo> cmsCollectionServiceResult = cmsCollectionService.getCmsCollectionById(id);
            if (cmsCollectionServiceResult == null || !cmsCollectionServiceResult.isSuccess() || cmsCollectionServiceResult.getData() == null) {
                result.setCode(ResultCode.C500.getCode());
                result.setMessage("数据为空");
                return result;
            }

            CmsCollectionVo cmsCollectionVo = cmsCollectionServiceResult.getData();
            ServiceResult<List<CmsCollectionData>> serviceResult = cmsCollectionService.getCmsCollectionDataListById(cmsCollectionVo.getId(), page);


            if (serviceResult != null && serviceResult.isSuccess()) {
                List<CmsCollectionData> cmsCollectionDataVoList = serviceResult.getData();
                result.setCode(ResultCode.C200.getCode());
                processCollectionData(cmsCollectionVo, cmsCollectionDataVoList);
                result.setDataMap(cmsCollectionVo);
                return result;
            }
            result.setCode(ResultCode.C500.getCode());
            if(serviceResult != null) {
                result.setMessage(serviceResult.getMsg());
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(ResultCode.C500.getCode());
            result.setMessage(e.getMessage());
            return result;
        }
    }


    /**
     * 封装合辑数据 将合辑中存放的Id转化成相关的信息
     * 家装公司31，设计师32，方案33；学校合辑41，小区合辑42，楼盘合辑43；品牌合辑21，导购员合辑22，商场外链23，商场文章24
     *
     * @param cmsCollectionVo
     * @return
     */
    private void processCollectionData(CmsCollectionVo cmsCollectionVo, List<CmsCollectionData> cmsCollectionDataList) {
        if (cmsCollectionVo == null || cmsCollectionDataList == null) {
            return;
        }

        List<Integer> idList = new ArrayList<>();

        for (CmsCollectionData cmsCollectionData : cmsCollectionDataList) {
            idList.add(Integer.valueOf(cmsCollectionData.getTargetId()));
        }

        if (idList.isEmpty()) {
            return;
        }

        switch (cmsCollectionVo.getSubType()) {
            case CmsCollection.SUBTYPE_HOME_DECORATION_DESIGNER: // 32 设计师合辑
                cmsCollectionVo.setCmsCollectionTargetVoList(processDesignerCollectionData(idList));
                break;
            case CmsCollection.SUBTYPE_HOME_DECORATION_CASE: // 33 方案
                cmsCollectionVo.setCmsCollectionTargetVoList(processCaseCollectionData(idList));
                break;
            case CmsCollection.SUBTYPE_HOUSE_PROPERTY_SCHOOL: // 41 学校合辑
                cmsCollectionVo.setCmsCollectionTargetVoList(processSchoolCollection(idList));
                break;
            case CmsCollection.SUBTYPE_HOUSE_PROPERTY_COMMUNITY: // 42 小区合辑
                cmsCollectionVo.setCmsCollectionTargetVoList(processCommunityCollection(idList));
                break;
            case CmsCollection.SUBTYPE_HOUSE_PROPERTY_HOUSES: // 43 楼盘合辑
                cmsCollectionVo.setCmsCollectionTargetVoList(processHousesCollection(idList));
                break;
            case CmsCollection.SUBTYPE_MARKET_BRAND: // 21 品牌合辑
                cmsCollectionVo.setCmsCollectionTargetVoList(processBrandCollectionData(idList));
                break;
            case CmsCollection.SUBTYPE_MARKET_SALES: // 22 导购员合辑
                cmsCollectionVo.setCmsCollectionTargetVoList(processSalesCollectionData(idList));
                break;
            case CmsCollection.SUBTYPE_MARKET_OUTLINK: // 23 商场外链
                cmsCollectionVo.setCmsCollectionTargetVoList(processMarketOutlinkCollectionData(idList));
                break;
            case CmsCollection.SUBTYPE_MARKET_ARTICLE: // 24 商场文章
                cmsCollectionVo.setCmsCollectionTargetVoList(processMarketArticleCollectionData(idList));
                break;
            default:
                break;
        }
    }

    /**
     * 封装学校合辑数据
     *
     * @param idList
     * @return
     */
    private List<CmsCollectionTargetVo> processSchoolCollection(List<Integer> idList) {

        return null;
    }

    /**
     * 封装小区合辑数据
     *
     * @param idList
     * @return
     */
    private List<CmsCollectionTargetVo> processCommunityCollection(List<Integer> idList) {

        return null;
    }

    /**
     * 封装楼盘合辑数据
     *
     * @param idList
     * @return
     */
    private List<CmsCollectionTargetVo> processHousesCollection(List<Integer> idList) {

        return null;
    }

    /**
     * 封装导购员合辑数据
     *
     * @param idList
     * @return
     */
    private List<CmsCollectionTargetVo> processSalesCollectionData(List<Integer> idList) {
        if (idList == null || idList.isEmpty()) {
            return null;
        }

        List<Long> ids = new ArrayList<>();

        for (Integer id : idList) {
            if (id == null) {
                continue;
            }
            ids.add(Long.valueOf(id.toString()));
        }

        com.redstar.digital.open.vo.ServiceResult<List<ShopperExtVo>> salesServiceResult = omsShopInfoService.getOnShopperInfosByShoperIds(ids);
//        if (LOGGER.isInfoEnabled()) {
//            LOGGER.info("调用导购员信息接口 --> Request : {}, Response : {}", idList, JsonUtil.toJson(salesServiceResult, false));
//        }

        if (!salesServiceResult.isSuccess()) {
            LOGGER.error("调用导购员信息接口失败. Parameter : {}, Cause by : {}", JsonUtil.toJson(idList, false), JsonUtil.toJson(salesServiceResult, false));
            return null;
        }

        List<CmsCollectionTargetVo> salesList = new ArrayList<>();

        for (ShopperExtVo shopperExtVo : salesServiceResult.getData()) {
            if (shopperExtVo == null) {
                continue;
            }
            CmsCollectionSalesVo salesVo = new CmsCollectionSalesVo();
            BeanUtils.copyProperties(shopperExtVo, salesVo);
            salesList.add(salesVo);
        }
        return salesList;
    }

    /**
     * 封装商场外链合辑数据
     *
     * @param idList
     * @return
     */
    private List<CmsCollectionTargetVo> processMarketOutlinkCollectionData(List<Integer> idList) {
        if (idList == null || idList.isEmpty()) {
            return null;
        }
        ServiceResult<List<OutlinkVo>> serviceResultOutlink = outlinkService.getOutLinkByIds(idList);
        if (!serviceResultOutlink.isSuccess()) {
            LOGGER.error("获取外链信息失败. Parameter : {}, Cause by : {}", JsonUtil.toJson(idList, false), JsonUtil.toJson(serviceResultOutlink, false));
            return null;
        }

        List<CmsCollectionTargetVo> outlinkVoList = new ArrayList<>();

        for (OutlinkVo outlinkVo : serviceResultOutlink.getData()) {
            if (outlinkVo == null) {
                continue;
            }
            CmsCollectionOutlinkVo cmsCollectionOutlinkVo = new CmsCollectionOutlinkVo();
            BeanUtils.copyProperties(outlinkVo, cmsCollectionOutlinkVo);
            outlinkVoList.add(cmsCollectionOutlinkVo);
        }
        return outlinkVoList;
    }

    /**
     * 封装商场文章合辑数据
     *
     * @param idList
     * @return
     */
    private List<CmsCollectionTargetVo> processMarketArticleCollectionData(List<Integer> idList) {
        if (idList == null || idList.isEmpty()) {
            return null;
        }

        ServiceResult<List<ArticleMarketVo>> serviceResultMarket = articleMarketService.getArticleMarketListByIds(idList);
        if (!serviceResultMarket.isSuccess()) {
            LOGGER.error("获取商场外链信息失败. Parameter : {}, Cause by : {}", JsonUtil.toJson(idList, false), JsonUtil.toJson(serviceResultMarket, false));
            return null;
        }

        List<CmsCollectionTargetVo> articleMarketVoList = new ArrayList<>();


        for (ArticleMarketVo articleMarketVo : serviceResultMarket.getData()) {
            if (articleMarketVo == null) {
                continue;
            }
            CmsCollectionArticleMarketVo cmsCollectionArticleMarketVo = new CmsCollectionArticleMarketVo();
            BeanUtils.copyProperties(articleMarketVo, cmsCollectionArticleMarketVo);
            articleMarketVoList.add(cmsCollectionArticleMarketVo);
        }
        return articleMarketVoList;
    }

    /**
     * 装方案合辑数据
     *
     * @param idList
     * @return
     */
    private List<CmsCollectionTargetVo> processCaseCollectionData(List<Integer> idList) {
        if (idList == null || idList.isEmpty()) {
            return null;
        }

        JzCaseQuery jzCaseQuery = new JzCaseQuery();
        jzCaseQuery.setCaseIds(idList);

        com.chinaredstar.jiazhuang.api.bean.Result<List<JzCaseListDto>> jzCaseResult = caseServiceApi.getListByQuery(jzCaseQuery, 1, Integer.MAX_VALUE);

//        if (LOGGER.isInfoEnabled()) {
//            LOGGER.info("调用方案接口 --> Request : {}, Response : {}", JsonUtil.toJson(jzCaseQuery, false), JsonUtil.toJson(jzCaseResult, false));
//        }

        if (!ResultCode.C200.getCode().equals(jzCaseResult.getCode())) {
            LOGGER.error("获取方案数据失败. Parameter : {}, Cause by : {}", JsonUtil.toJson(idList, false), JsonUtil.toJson(jzCaseResult, false));
            return null;
        }

        List<CmsCollectionTargetVo> caseVoList = new ArrayList<>();

        for (JzCaseListDto jzCaseListDto : jzCaseResult.getDataMap()) {
            if (jzCaseListDto == null) {
                continue;
            }
            CmsCollectionCaseVo cmsCollectionCaseVo = new CmsCollectionCaseVo();
            BeanUtils.copyProperties(jzCaseListDto, cmsCollectionCaseVo);
            caseVoList.add(cmsCollectionCaseVo);
        }

        return caseVoList;
    }

    /**
     * 封装品牌合辑数据
     *
     * @param brandIdList
     * @return
     */
    private List<CmsCollectionTargetVo> processBrandCollectionData(List<Integer> brandIdList) {
        if (brandIdList == null || brandIdList.isEmpty()) {
            LOGGER.error("brandIdList must be not null");
            return null;
        }

        com.redstar.digital.open.vo.ServiceResult<List<OmsBrandInfo>> brandServiceResult = omsBrandInfoService.getOmsBrandInfoById(brandIdList);
//        if (LOGGER.isInfoEnabled()) {
//            LOGGER.error("调用品牌服务 --> Request : {},  Response : {}", brandIdList, JsonUtil.toJson(brandServiceResult, false));
//        }

        if (!brandServiceResult.isSuccess()) {
            LOGGER.error("获取品牌信息失败. Parameter : {},  Casue by : {}", JsonUtil.toJson(brandIdList, false), JsonUtil.toJson(brandServiceResult, false));
            return null;
        }

        List<OmsBrandInfo> brandInfoList = brandServiceResult.getData();

        List<CmsCollectionTargetVo> brandVoList = new ArrayList<>();
        for (OmsBrandInfo omsBrandInfo : brandInfoList) {
            CmsCollectionBrandVo brandVo = new CmsCollectionBrandVo();
            BeanUtils.copyProperties(omsBrandInfo, brandVo);
            brandVoList.add(brandVo);
        }

        return brandVoList;
    }

    /**
     * 封装设计师数据
     *
     * @param designerIdList
     * @return
     */
    private List<CmsCollectionTargetVo> processDesignerCollectionData(List<Integer> designerIdList) {

        if (designerIdList == null || designerIdList.isEmpty()) {
            return null;
        }

        JzDesignerQuery jzDesignerQuery = new JzDesignerQuery();
        jzDesignerQuery.setDesignerIds(designerIdList);
        com.chinaredstar.jiazhuang.api.bean.Result<List<JzDesignerListDto>> result = designerServiceApi.getListByQuery(jzDesignerQuery, 0, Integer.MAX_VALUE);
//        if (LOGGER.isInfoEnabled()) {
//            LOGGER.error("调用设计师服务 --> Request : {},  Response : {}", JsonUtil.toJson(jzDesignerQuery, false), JsonUtil.toJson(result, false));
//        }
        if (!result.getCode().equals(ResultCode.C200.getCode())) {
            LOGGER.error("获取设计师列表接口失败. param : {}, serviceResult:{}", JsonUtil.toJson(designerIdList, false), JsonUtil.toJson(result, false));
            return null;
        }

        List<CmsCollectionTargetVo> designerVoList = new ArrayList<>();
        List<JzDesignerListDto> designerList = result.getDataMap();
        for (JzDesignerListDto designerDto : designerList) {
            CmsCollectionDesignerVo designerVo = new CmsCollectionDesignerVo();
            BeanUtils.copyProperties(designerDto, designerVo);
            designerVoList.add(designerVo);
        }
        return designerVoList;
    }
}
