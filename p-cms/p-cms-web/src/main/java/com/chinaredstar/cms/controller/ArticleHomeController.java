package com.chinaredstar.cms.controller;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.model.ArticleHome;
import com.chinaredstar.cms.api.service.ArticleHomeService;
import com.chinaredstar.cms.api.vo.article.ArticleHomeQueryVo;
import com.chinaredstar.cms.api.vo.article.ArticleHomeVo;
import com.chinaredstar.cms.vo.ResultCode;
import com.chinaredstar.cms.api.component.Page;
import com.chinaredstar.cms.vo.Result;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 家装文章Crontroller
 */
@Api(value = "article", description = "家装文章接口")
@RestController()
@RequestMapping("/article/home")
@CacheConfig(cacheNames = "articleHomeCache")
@Timed
public class ArticleHomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleHomeController.class);

    @Autowired
    private ArticleHomeService articleHomeService;


    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 404, message = "返回数据为空"),
            @ApiResponse(code = 415, message = "请求参数错误"),
            @ApiResponse(code = 422, message = "校验错误"),
            @ApiResponse(code = 500, message = "系统内部错误")
    })
    @ApiOperation(value = "list", notes = "分页查询家装文章数据")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result<List<ArticleHome>> getArticleHomeList(@ModelAttribute() Page page) {
        Result<List<ArticleHome>> result = new Result<>();
        try {
            ArticleHomeQueryVo articleHomeVo = new ArticleHomeQueryVo();
            ServiceResult<List<ArticleHome>> serviceResult = articleHomeService.getArticleHomeList(articleHomeVo, page);
            if (serviceResult != null && serviceResult.isSuccess()) {
                result.setCode(ResultCode.C200.getCode());
                result.setDataMap(serviceResult.getData());
                return result;
            }
            if(serviceResult != null) {
                LOGGER.error("获取家装文章集合失败. e:{}", serviceResult.getMsg());
            }
        } catch (Exception e) {
            LOGGER.error("获取家装文章集合失败. e:{}", e.getMessage());
        }
        result.setCode(ResultCode.C500.getCode());
        result.setMessage("获取家装文章集合失败");
        return result;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 404, message = "返回数据为空"),
            @ApiResponse(code = 415, message = "请求参数错误"),
            @ApiResponse(code = 422, message = "校验错误"),
            @ApiResponse(code = 500, message = "系统内部错误")
    })
    @ApiOperation(value = "detail", notes = "通过id获取家装文章详情")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    @Cacheable(key = "'CMS:ARTICLE:HOME:GETDETAILBYID:'+#id",unless = "!#result.code.equals('200')")
    public Result<ArticleHomeVo> getDetailById(@PathVariable("id") Integer id) {
        Result<ArticleHomeVo> result = new Result<>();
        if (id == null) {
            LOGGER.error("id must not be null");
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("id must not be null");
            return result;
        }

        try {
            ServiceResult<ArticleHomeVo> serviceResult = articleHomeService.getDetailById(id);
            if (serviceResult != null && serviceResult.isSuccess()) {
                result.setCode(ResultCode.C200.getCode());
                result.setDataMap(serviceResult.getData());
                return  result;
            }
            if(serviceResult != null) {
                LOGGER.error("通过文章id获取家装文章失败. e:{}", serviceResult.getMsg());
            }
        } catch (Exception e) {
            LOGGER.error("通过文章id获取家装文章失败. e:{}", e.getMessage());
        }
        result.setCode(ResultCode.C500.getCode());
        result.setMessage("通过id获取家装文章失败");
        return result;
    }

}
