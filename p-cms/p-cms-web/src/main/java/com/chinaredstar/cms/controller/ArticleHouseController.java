package com.chinaredstar.cms.controller;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.ArticleHouseService;
import com.chinaredstar.cms.api.vo.article.ArticleHouseQueryVo;
import com.chinaredstar.cms.api.vo.article.ArticleHouseVo;
import com.chinaredstar.cms.vo.Result;
import com.chinaredstar.cms.vo.ResultCode;
import com.codahale.metrics.annotation.Timed;
import com.wordnik.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by sunny on 16-8-24.
 */
@RestController
@RequestMapping("/article/house")
@Cacheable(cacheNames = "articleHouseCache")
@Api(value = "houseArticle", description = "房产文章相关接口")
@Timed
public class ArticleHouseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleHouseController.class);

    @Autowired
    private ArticleHouseService articleHouseService;

    @RequestMapping(value = "/listByTag/{categoryTag}", method = RequestMethod.GET)
    @Cacheable(key = "'CMS:ARTICLE:HOUSE:LISTBYTAG:'+#categoryTag + ':' +#queryVo.pageNum +':'+#queryVo.recordCount", unless = "!#result.code.equals('200')")
    public Result<List<ArticleHouseVo>> listByTag(@PathVariable("categoryTag") String categoryTag, ArticleHouseQueryVo queryVo) {
        LOGGER.info("listByTag通过分类标签获取房产文章接口，请求参数：categoryTag --> " + categoryTag);
        long start = System.currentTimeMillis();
        Result<List<ArticleHouseVo>> result = new Result<List<ArticleHouseVo>>();
        if (StringUtils.isBlank(categoryTag)) {
            LOGGER.error("categoryTag must not be null");
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("categoryTag must not be null");
            return result;
        }
        if (queryVo == null) {
            queryVo = new ArticleHouseQueryVo();
        }
        queryVo.setCategoryTag(categoryTag);
        queryVo.setCurrentTime(new Date());
        try {
            ServiceResult<List<ArticleHouseVo>> serviceResult = articleHouseService.listByCategoryTag(queryVo);
            if (serviceResult != null && serviceResult.isSuccess()) {
                result.setCode(ResultCode.C200.getCode());
                result.setDataMap(serviceResult.getData());
            } else {
                result.setCode(ResultCode.C500.getCode());
                result.setMessage("服务端返回错误");
            }
        } catch (Exception e) {
            LOGGER.error("listByTag接口调用dubbo服务发生异常：" + e.getMessage());
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("系统内部错误");
        }

        long end = System.currentTimeMillis();
        long time = end - start;
        LOGGER.info("listByTag通过分类标签获取房产文章接口,返回结果：result --> " + result + ",耗时："
                + (time / 1000) + "秒" + (time % 1000) + "毫秒");
        return result;
    }

    @Cacheable(key = "'CMS:ARTICLE:HOUSE:GETDETAILBYID:'+#id", unless = "!#result.code.equals('200')")
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public Result<ArticleHouseVo> getDetailById(@PathVariable("id") Integer id) {
        Result<ArticleHouseVo> result = new Result<>();
        if (id == null) {
            LOGGER.error("id must not be null");
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("id must not be null");
            return result;
        }

        try {
            ServiceResult<ArticleHouseVo> serviceResult = articleHouseService.getDetailById(id);
            if (serviceResult != null && serviceResult.isSuccess()) {
                result.setCode(ResultCode.C200.getCode());
                result.setDataMap(serviceResult.getData());
                return result;
            }
            if (serviceResult != null) {
                LOGGER.error("通过id获取房产文章失败. e:{}", serviceResult.getMsg());
            }
        } catch (Exception e) {
            LOGGER.error("通过id获取房产文章失败. e:{}", e.getMessage());
        }
        result.setCode(ResultCode.C500.getCode());
        result.setMessage("通过id获取房产文章失败");
        return result;
    }
}
