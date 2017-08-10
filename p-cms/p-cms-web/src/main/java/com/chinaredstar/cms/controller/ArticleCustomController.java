package com.chinaredstar.cms.controller;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.ArticleCustomService;
import com.chinaredstar.cms.api.vo.article.ArticleCustomVo;
import com.chinaredstar.cms.api.vo.article.ArticleHouseVo;
import com.chinaredstar.cms.vo.Result;
import com.chinaredstar.cms.vo.ResultCode;
import com.codahale.metrics.annotation.Timed;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sunny on 16-9-7.
 */
@Api(value = "article", description = "商品文章接口")
@RestController
@RequestMapping("/article/custom")
@CacheConfig(cacheNames = "articleCustomCache")
@Timed
public class ArticleCustomController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleCustomController.class);

    @Autowired
    private ArticleCustomService articleCustomService;


    @RequestMapping(value = "/detail/{id}",method = RequestMethod.GET)
    @Cacheable(key = "'CMS:ARTICLE:CUSTOM:GETDETAILBYID:'+#id",unless = "!#result.code.equals('200')")
    public Result<ArticleCustomVo> getDetailById(@PathVariable("id") @ApiParam(value = "商品文章id") Integer id) {
        Result<ArticleCustomVo> result = new Result<>();
        if (id == null) {
            LOGGER.error("id must not be null");
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("id must not be null");
            return result;
        }

        try {
            ServiceResult<ArticleCustomVo> serviceResult = articleCustomService.getDetailById(id);
            if (serviceResult != null && serviceResult.isSuccess()) {
                result.setCode(ResultCode.C200.getCode());
                result.setDataMap(serviceResult.getData());
                return  result;
            }
            if(serviceResult != null) {
                LOGGER.error("通过id获取商品文章失败. e:{}", serviceResult.getMsg());
            }
        } catch (Exception e) {
            LOGGER.error("通过id获取商品文章失败. e:{}", e);
        }
        result.setCode(ResultCode.C500.getCode());
        result.setMessage("通过id获取商品文章失败");
        return result;
    }
}
