package com.chinaredstar.cms.controller;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.EncyclopediaService;
import com.chinaredstar.cms.api.vo.encyclopedia.EncyclopediaQueryVo;
import com.chinaredstar.cms.api.vo.encyclopedia.EncyclopediaVo;
import com.chinaredstar.cms.vo.Result;
import com.chinaredstar.cms.vo.ResultCode;
import com.codahale.metrics.annotation.Timed;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by sunny on 16-8-23.
 */
@Api(value = "encyclopedia", description = "百科相关接口")
@RestController
@RequestMapping("/encyclopedia")
@Timed
public class EncyclopediaController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EncyclopediaController.class);

    @Autowired
    private EncyclopediaService encyclopediaService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 404, message = "返回数据为空"),
            @ApiResponse(code = 415, message = "请求参数错误"),
            @ApiResponse(code = 422, message = "校验错误"),
            @ApiResponse(code = 500, message = "系统内部错误")
    })
    @ApiOperation(value = "listByType", notes = "通过类型获取百科列表接口")
    @RequestMapping(value = "/listByType/{type}", method = RequestMethod.GET)
    public Result<List<EncyclopediaVo>> listByType(@PathVariable("type") Integer type, @ModelAttribute EncyclopediaQueryVo queryVo) {
        LOGGER.info("listByType通过类型获取百科接口，请求参数：type --> " + type + ",queryVo --> " + queryVo);
        long start = System.currentTimeMillis();
        Result<List<EncyclopediaVo>> result = new Result<>();
        if (type == null) {
            LOGGER.error("type must not be null");
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("type must not be null");
            return result;
        }
        if (queryVo == null) {
            queryVo = new EncyclopediaQueryVo();
        }
        queryVo.setType(type);
        queryVo.setTop(true);
        queryVo.setCurrentTime(new Date());
        if (queryVo.getPageNo() != null && queryVo.getPageSize() != null) {
            queryVo.setPageIndex((queryVo.getPageNo() - 1) * queryVo.getPageSize());
        }
        try {
            ServiceResult<List<EncyclopediaVo>> serviceResult = encyclopediaService.listByType(queryVo);

            if (serviceResult.isSuccess()) {
                result.setCode(ResultCode.C200.getCode());
                result.setDataMap(serviceResult.getData());
            } else {
                result.setCode(ResultCode.C500.getCode());
                result.setMessage("服务端返回错误");
            }
        } catch (Exception e) {
            LOGGER.error("listByType接口通过类型获取百科调用dubbo服务发生异常：" + e.getMessage());
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("系统内部错误");
        }

        long end = System.currentTimeMillis();
        long time = end - start;
        LOGGER.info("listByType通过类型获取图集接口,返回结果：result --> " + result + ",耗时："
                + (time / 1000) + "秒" + (time % 1000) + "毫秒");

        return result;
    }

    @ApiOperation(value = "detail", notes = "通过id获取百科详情")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public Result<EncyclopediaVo> getDetailById(@PathVariable("id") Integer id) {
        Result<EncyclopediaVo> result = new Result<>();
        if (id == null) {
            LOGGER.error("id must not be null");
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("id must not be null");
            return result;
        }

        try {
            ServiceResult<EncyclopediaVo> serviceResult = encyclopediaService.getDetailById(id);
            if (serviceResult != null && serviceResult.isSuccess()) {
                result.setCode(ResultCode.C200.getCode());
                result.setDataMap(serviceResult.getData());
                return result;
            }
            if (serviceResult != null) {
                LOGGER.error("通过id获取百科失败. e:{}", serviceResult.getMsg());
                result.setCode(ResultCode.C500.getCode());
                result.setMessage(serviceResult.getMsg());
                return result;
            }
        } catch (Exception e) {
            LOGGER.error("通过id获取百科失败. e:{}", e.getMessage());
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("通过id获取百科失败");
        }
        return result;
    }

    @ApiOperation(value = "category", notes = "获取百科类别")
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public Result<Map<String, Object>> getCategory() {
        Result<Map<String, Object>> result = new Result<>();
        result.setCode(ResultCode.C200.getCode());
        Map<String, Object> dataMap = new HashMap<>();

        List<String> design = new ArrayList<>();
        design.add("设计风格");
        design.add("设计空间");
        dataMap.put("设计", design);

        List<String> material = new ArrayList<>();
        material.add("家具");
        material.add("材料");
        material.add("软饰");
        dataMap.put("选材", material);

        List<String> construction = new ArrayList<>();
        construction.add("装修前");
        construction.add("装修中");
        construction.add("装修后");
        dataMap.put("施工", construction);

        List<String> fortune = new ArrayList<>();
        dataMap.put("风水", fortune);

        result.setDataMap(dataMap);
        return result;
    }

    @ApiOperation(value = "tags", notes = "通过tags获取百科列表")
    @RequestMapping(value = "/tags{tags}", method = RequestMethod.GET)
    Result<List<EncyclopediaVo>> getEncyclopediaListByTags(@PathVariable String tags) {
        Result<List<EncyclopediaVo>> result = new Result<>();
        if (StringUtils.isBlank(tags)) {
            LOGGER.error("tags must not be null");
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("tags must not be null");
            return result;
        }
        try {
            ServiceResult<List<EncyclopediaVo>> serviceResult = encyclopediaService.getEncyclopediaListByTags(tags);
            if (serviceResult != null && serviceResult.isSuccess()) {
                result.setCode(ResultCode.C200.getCode());
                result.setDataMap(serviceResult.getData());
                return result;
            }
            if (serviceResult != null) {
                LOGGER.error("通过tags获取百科列表服务端发生异常，{}", serviceResult.getMsg());
                result.setCode(ResultCode.C500.getCode());
                result.setMessage(serviceResult.getMsg());
                return result;
            }
        } catch (Exception e) {
            LOGGER.error("通过tags获取百科列表失败，{}", e);
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("通过tags获取百科列表失败");
        }
        return result;
    }
}
