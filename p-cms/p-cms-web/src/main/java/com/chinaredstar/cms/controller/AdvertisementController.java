package com.chinaredstar.cms.controller;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.AdvertisementService;
import com.chinaredstar.cms.api.vo.advertisement.AdvertisementQueryVo;
import com.chinaredstar.cms.api.vo.advertisement.AdvertisementVo;
import com.chinaredstar.cms.vo.Result;
import com.chinaredstar.cms.vo.ResultCode;
import com.chinaredstar.perseus.utils.JsonUtil;
import com.codahale.metrics.annotation.Timed;
import com.wordnik.swagger.annotations.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by sunny on 16-8-16.
 */
@RestController
@RequestMapping("/ad")
@Api(value = "advertisement", description = "广告相关接口")
@CacheConfig(cacheNames = "advertCache")
@Timed
public class AdvertisementController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdvertisementController.class);

    @Autowired
    private AdvertisementService advertisementService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 404, message = "返回数据为空"),
            @ApiResponse(code = 415, message = "请求参数错误"),
            @ApiResponse(code = 422, message = "校验错误"),
            @ApiResponse(code = 500, message = "系统内部错误")
    })

    @ApiOperation(value = "list", notes = "通过广告位置编码获取单页广告")
    @RequestMapping(value = "/list/{positionCode}", method = RequestMethod.GET)
    @Cacheable(key = "'CMS:ADVERT:LISTBYPOSTIONCODE:'+#positionCode", unless = "!#result.code.equals('200')")
    public Result<List<AdvertisementVo>> listByPostionCode(@ApiParam("广告位置编码") @PathVariable("positionCode") String positionCode) {
        LOGGER.info("listByPostionCode:通过广告位置编码获取单页广告接口,请求参数：positionCode --> " + positionCode);

        Result<List<AdvertisementVo>> result = new Result<List<AdvertisementVo>>();
        if (positionCode == null || StringUtils.isBlank(positionCode)) {
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("请求参数positionCode数据为空");
            LOGGER.error("请求参数positionCode数据为空");
            return result;
        }

        AdvertisementQueryVo queryVo = new AdvertisementQueryVo();
        queryVo.setPositionCode(positionCode);
        queryVo.setCurrentTime(new Date());
        try {
            ServiceResult<List<AdvertisementVo>> serviceResult = advertisementService.listADsByPositionCode(queryVo);
            LOGGER.info("广告服务返回结果：" + serviceResult);
            if (serviceResult != null && serviceResult.isSuccess()) {
                List<AdvertisementVo> advertisements = serviceResult.getData();
                if (advertisements != null && advertisements.size() != 0) {
                    if (advertisements.size() > 1 && advertisements.get(0).getSequence() == 0) {
                        advertisements.remove(0);
                    }
                }
                result.setCode(ResultCode.C200.getCode());
                result.setDataMap(advertisements);
            } else {
                result.setCode(ResultCode.C500.getCode());
                result.setMessage("系统内部错误");
            }
        } catch (Exception e) {
            LOGGER.error("listByPostionCode:通过广告位置编码获取单页广告接口调用发生异常：" + e);
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("系统内部错误");
        }
        return result;
    }


    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 404, message = "返回数据为空"),
            @ApiResponse(code = 415, message = "请求参数错误"),
            @ApiResponse(code = 422, message = "校验错误"),
            @ApiResponse(code = 500, message = "系统内部错误")
    })
    @ApiOperation(value = "get all shop recommcadation advertisement", notes = "通过商场ID查询全店推广的广告")
    @RequestMapping(value = "/list/{marketId}/shop", method = RequestMethod.GET)
    @Cacheable(key = "'CMS:ADVERT:GETALLSHOPRECOMMADVERTBYMARKETID：'+#marketId",unless = "!#result.code.equals('200')")
    public Result<List<AdvertisementVo>> getAllShopRecommAdvertByMarketId(@PathVariable Integer marketId) {
        Result<List<AdvertisementVo>> result = new Result<>(ResultCode.C200.getCode());
        if (marketId == null) {
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("参数错误");
            LOGGER.error("marketId must not be null");
            return result;
        }
        AdvertisementQueryVo queryVo = new AdvertisementQueryVo();
        queryVo.setPositionCode(String.valueOf(marketId));
        queryVo.setShop(true);
        queryVo.setCurrentTime(new Date());
        ServiceResult<List<AdvertisementVo>> serviceResult = advertisementService.listADsByPositionCode(queryVo);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("请求广告接口 --> Request : {}, Response : {}", JsonUtil.toJson(queryVo, false), JsonUtil.toJson(serviceResult, false));
        }

        if (serviceResult == null || !serviceResult.isSuccess()) {
            String message = serviceResult != null ? serviceResult.getMsg() : "返回值为NULL";
            LOGGER.error("请求广告接口失败. Casue by : {}", message);
            result.setCode(ResultCode.C500.getCode());
            result.setMessage(message);
            return result;
        }
        result.setDataMap(serviceResult.getData());
        result.setMessage("请求成功");
        return result;
    }

}
