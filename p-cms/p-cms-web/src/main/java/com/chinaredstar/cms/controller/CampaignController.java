package com.chinaredstar.cms.controller;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.model.*;
import com.chinaredstar.cms.api.service.CampaignService;
import com.chinaredstar.cms.api.vo.campaign.CampaignBrandQueryVo;
import com.chinaredstar.cms.api.vo.campaign.CampaignMarketProductQueryVo;
import com.chinaredstar.cms.api.vo.campaign.CampaignQueryVo;
import com.chinaredstar.cms.vo.Result;
import com.chinaredstar.cms.vo.ResultCode;
import com.codahale.metrics.annotation.Timed;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ykk on 16/10/16.
 */
@RestController
@RequestMapping("/campaign")
@Api(value = "campaign", description = "活动相关接口")
@CacheConfig(cacheNames = "campaign")
@Timed
public class CampaignController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PageController.class);

    @Autowired
    private CampaignService campaignService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 404, message = "返回数据为空"),
            @ApiResponse(code = 415, message = "请求参数错误"),
            @ApiResponse(code = 422, message = "校验错误"),
            @ApiResponse(code = 500, message = "系统内部错误")
    })
    @ApiOperation(value = "获取活动首页数据接口", notes = "获取活动首页数据接口")
    @RequestMapping(value = "/index/{openId}", method = RequestMethod.GET)
    public Result<Map<String, Object>> indexPage(@PathVariable("openId") String openId) {

        Result<Map<String, Object>> result = new Result<>();
        Map<String, Object> dataMap = new HashMap<>();
        CmsCampaign campaign = null;
        try {
            //获取活动信息
            CampaignQueryVo campaignQueryVo = new CampaignQueryVo();
            ServiceResult<CmsCampaign> campaignServiceResult = campaignService.getCampaign(campaignQueryVo);
            if (campaignServiceResult.isSuccess()){
                campaign = campaignServiceResult.getData();
                dataMap.put("campaign",campaign);
                //获取活动品牌数据
                CampaignBrandQueryVo vo = new CampaignBrandQueryVo();
                vo.setCampaignId(campaign.getId());
                ServiceResult<List<CmsCampaignBrand>> brandResult = campaignService.getCampaignBrand(vo);
                if (brandResult.isSuccess()){
                    dataMap.put("campaignBrand",brandResult.getData());
                }
                //获取活动商场信息
                ServiceResult<List<CmsCampaignMarket>> marketResult = campaignService.getCampaignMarket(campaign.getId());
                if (marketResult.isSuccess()){
                    dataMap.put("campaignMarket",marketResult.getData());


                    List<CmsCampaignMarket> marketList = marketResult.getData();
                    if (null!=marketList && !marketList.isEmpty()){
                        CmsCampaignMarket campaignMarket = marketList.get(0);
                        //获取商场商品信息
                        //1：家居馆
                        CampaignMarketProductQueryVo marketProductQueryVo = new CampaignMarketProductQueryVo();
                        marketProductQueryVo.setCampaignMarketId(campaignMarket.getId());
                        marketProductQueryVo.setType(1);
                        marketProductQueryVo.setIsRecommendIndex(1);
                        ServiceResult<List<CmsCampaignMarketProduct>> productResult = campaignService.getCampaignMarketProduct(marketProductQueryVo);
                        if (productResult.isSuccess()){
                            dataMap.put("campaignMarketProduct1",productResult.getData());
                        }
                        //2:建材馆
                        CampaignMarketProductQueryVo marketProductQueryVo2 = new CampaignMarketProductQueryVo();
                        marketProductQueryVo2.setCampaignMarketId(campaignMarket.getId());
                        marketProductQueryVo2.setType(2);
                        marketProductQueryVo2.setIsRecommendIndex(1);
                        ServiceResult<List<CmsCampaignMarketProduct>> productResult2 = campaignService.getCampaignMarketProduct(marketProductQueryVo2);
                        if (productResult2.isSuccess()){
                            dataMap.put("campaignMarketProduct2",productResult2.getData());
                        }

                        //获取商场优惠券信息
//                        ServiceResult<List<CmsCampaignMarketCoupon>> couponResult = campaignService.getCampaignMarketCoupon(campaignMarket.getId(),openId);
//                        if (productResult.isSuccess()){
//                            dataMap.put("campaignCupon",couponResult.getData());
//                        }

                    }
                }

                result.setCode(ResultCode.C200.getCode());
                result.setDataMap(dataMap);

            }




        } catch (Exception e) {
            LOGGER.error("获取活动首页数据异常：{}", e);
            e.printStackTrace();
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("获取活动首页数据异常");
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
    @ApiOperation(value = "获取商场商品数据列表", notes = "获取商场商品数据列表",response =CmsCampaignMarketProduct.class)

    @RequestMapping(value = "/market/product", method = RequestMethod.POST)
    //@Cacheable(value="CMS:CAMPAIGN",key = "'CMS:CAMPAIGNDATA:'+vo.getCampaignMarketId()+':'+vo.getType()+':'+#subType2+':'+#page.getPageNo()+':'+#page.getPageSize()", unless = "!#result.code.equals('200')")
    public Result<Map<String, Object>> marketProductList(@ModelAttribute() CampaignMarketProductQueryVo vo) {

        Result<Map<String, Object>> result = new Result<>();
        Map<String, Object> dataMap = new HashMap<>();
        try {

            ServiceResult<List<CmsCampaignMarketProduct>> productResult = campaignService.getCampaignMarketProduct(vo);
            if (productResult.isSuccess()){
                dataMap.put("campaignMarketProduct",productResult.getData());
            }
            result.setCode(ResultCode.C200.getCode());
            result.setDataMap(dataMap);




        } catch (Exception e) {
            LOGGER.error("获取商场商品数据列表异常：{}", e);
            e.printStackTrace();
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("获取商场商品数据列表异常");
        }
        return result;
    }


//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "请求成功"),
//            @ApiResponse(code = 404, message = "返回数据为空"),
//            @ApiResponse(code = 415, message = "请求参数错误"),
//            @ApiResponse(code = 422, message = "校验错误"),
//            @ApiResponse(code = 500, message = "系统内部错误")
//    })
//    @ApiOperation(value = "获取商场优惠券数据", notes = "获取商场优惠券数据",response =CmsCampaignMarketCoupon.class)
//    @RequestMapping(value = "/market/coupon/{campaignMarketId}/{openId}", method = RequestMethod.GET)
//    public Result<Map<String, Object>> marketCouponList(@PathVariable("campaignMarketId") Integer campaignMarketId,@PathVariable("openId") String openId) {
//
//        Result<Map<String, Object>> result = new Result<>();
//        Map<String, Object> dataMap = new HashMap<>();
//        try {
//
//            ServiceResult<List<CmsCampaignMarketCoupon>> couponResult = campaignService.getCampaignMarketCoupon(campaignMarketId,openId);
//            if (couponResult.isSuccess()){
//                dataMap.put("campaignCupon",couponResult.getData());
//            }
//            result.setCode(ResultCode.C200.getCode());
//            result.setDataMap(dataMap);
//
//
//
//
//        } catch (Exception e) {
//            LOGGER.error("获取商场优惠券数据异常：{}", e);
//            e.printStackTrace();
//            result.setCode(ResultCode.C500.getCode());
//            result.setMessage("获取商场优惠券数据异常");
//        }
//        return result;
//    }




}
