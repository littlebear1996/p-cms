package com.chinaredstar.cms.controller;

import com.chinaredstar.cms.api.vo.atlas.AtlasQueryVo;
import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.AtlasService;
import com.chinaredstar.cms.api.vo.atlas.AtlasVo;
import com.chinaredstar.cms.vo.Result;
import com.chinaredstar.cms.vo.ResultCode;
import com.codahale.metrics.annotation.Timed;
import com.wordnik.swagger.annotations.*;
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
 * Created by sunny on 16-8-17.
 */
@RestController
@RequestMapping("/atlas")
@CacheConfig(cacheNames = "atlasCache")
@Api(value = "atlas", description = "图集相关接口")
@Timed
public class AtlasController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AtlasController.class);

    @Autowired
    private AtlasService atlasService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 404, message = "返回数据为空"),
            @ApiResponse(code = 415, message = "请求参数错误"),
            @ApiResponse(code = 422, message = "校验错误"),
            @ApiResponse(code = 500, message = "系统内部错误")
    })
    @ApiOperation(value = "list", notes = "通过类型获取图集列表接口")
    @RequestMapping(value = "/listByType/{subType}", method = RequestMethod.GET)
    public Result<List<AtlasVo>> listByType(@PathVariable Integer subType, AtlasQueryVo queryVo) {
        LOGGER.info("listByType通过类型获取图集接口，请求参数：subType --> " + subType);
        long start = System.currentTimeMillis();
        Result<List<AtlasVo>> result = new Result<List<AtlasVo>>();
        if (subType == null) {
            LOGGER.error("type must not be null");
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("type must not be null");
            return result;
        }
        if (queryVo == null) {
            queryVo = new AtlasQueryVo();
        }
        queryVo.setSubType(subType);
        queryVo.setCurrentTime(new Date());
        queryVo.setOrderBy(" atlas.is_top desc, atlas.set_top_time DESC , atlas.create_time DESC");
        try {
            ServiceResult<List<AtlasVo>> serviceResult = atlasService.listByType(queryVo);
            if (serviceResult != null && serviceResult.isSuccess()) {
                result.setCode(ResultCode.C200.getCode());
                result.setDataMap(serviceResult.getData());
            } else {
                result.setCode(ResultCode.C500.getCode());
                result.setMessage("服务端返回错误");
            }
        } catch (Exception e) {
            LOGGER.error("listByType接口调用dubbo服务发生异常：" + e.getMessage());
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("系统内部错误");
        }

        long end = System.currentTimeMillis();
        long time = end - start;
        LOGGER.info("listByType通过类型获取图集接口,返回结果：result --> " + result + ",耗时："
                + (time / 1000) + "秒" + (time % 1000) + "毫秒");
        return result;
    }

    @ApiOperation(value = "detail", notes = "通过id获取图集详情接口")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    @Cacheable(key = "'CMS:ATLAS:GETDETAILBYID:' +#id",unless = "!#result.code.equals('200')")
    public Result<AtlasVo> getDetailById(@ApiParam("图集id") @PathVariable("id") Integer id) {
        LOGGER.info("getDetailById通过id获取图集详情接口，请求参数：id --> " + id);
        long start = System.currentTimeMillis();
        Result<AtlasVo> result = new Result<>(ResultCode.C200.getCode());

        if (id == null) {
            LOGGER.error("id must not be null");
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("d must not be null");
            return result;
        }
        try {

            ServiceResult<AtlasVo> serviceResult = atlasService.getAtlasById(id);
            if (serviceResult != null && serviceResult.isSuccess()) {
                result.setDataMap(serviceResult.getData());
            } else {
                result.setCode(ResultCode.C500.getCode());
                result.setMessage("服务端返回错误");
            }

        } catch (Exception e) {
            LOGGER.error("getDetailById接口调用dubbo服务发生异常：" + e.getMessage());
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("系统内部错误");
        }

        long end = System.currentTimeMillis();
        long time = end - start;
        LOGGER.info("getDetailById通过id获取图集详情接口,返回结果：result --> " + result + ",耗时："
                + (time / 1000) + "秒" + (time % 1000) + "毫秒");
        return result;
    }
}
