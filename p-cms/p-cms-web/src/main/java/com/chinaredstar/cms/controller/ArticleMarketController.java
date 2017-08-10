package com.chinaredstar.cms.controller;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.ArticleMarketService;
import com.chinaredstar.cms.api.vo.article.ArticleMarketQueryVo;
import com.chinaredstar.cms.api.vo.article.ArticleMarketVo;
import com.chinaredstar.cms.vo.Result;
import com.chinaredstar.cms.vo.ResultCode;
import com.codahale.metrics.annotation.Timed;
import com.redstar.digital.open.service.OmsMarketInfoService;
import com.redstar.digital.open.vo.OmsMarketInfoVo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
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
 * Created by sunny on 16-8-19.
 */
@RestController
@RequestMapping("/marketArticle")
@CacheConfig(cacheNames = "marketArticleCache")
@Api(value = "marketArticle", description = "商场文章接口")
@Timed
public class ArticleMarketController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleMarketController.class);

    @Autowired
    private ArticleMarketService articleMarketService;

    @Autowired
    private OmsMarketInfoService omsMarketInfoService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 404, message = "返回数据为空"),
            @ApiResponse(code = 415, message = "请求参数错误"),
            @ApiResponse(code = 422, message = "校验错误"),
            @ApiResponse(code = 500, message = "系统内部错误")
    })
    @RequestMapping(value = "/listByTag/{categoryTag}", method = RequestMethod.GET)
    @Cacheable(key = "'CMS:ARTICLE:MARKET:LISTBYTAG:'+#categoryTag",unless = "!#result.code.equals('200')")
    public Result<List<ArticleMarketVo>> listByTag(@ApiParam("分类标签") @PathVariable("categoryTag") String categoryTag) {

        Result<List<ArticleMarketVo>> result = new Result<>();

        if (StringUtils.isBlank(categoryTag)) {
            LOGGER.error("categoryTag must not be null");
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("categoryTag must not be null");
            return result;
        }
        ArticleMarketQueryVo queryVo = new ArticleMarketQueryVo();
        queryVo.setCategoryTag(categoryTag);
        queryVo.setCurrentTime(new Date());
        try{
            ServiceResult<List<ArticleMarketVo>> serviceResult = articleMarketService.listByCategoryTag(queryVo);
            LOGGER.info("商品文章服务返回结果:"+serviceResult);
            if(serviceResult != null && serviceResult.isSuccess()) {
                result.setCode(ResultCode.C200.getCode());
                result.setDataMap(serviceResult.getData());
            }
            if(serviceResult != null && !serviceResult.isSuccess()) {
                result.setCode(ResultCode.C500.getCode());
                result.setMessage(serviceResult.getMsg());
            }
        } catch (Exception e) {
            result.setCode(ResultCode.C500.getCode());
            result.setMessage(e.toString());
        }
        return result;
    }

    @RequestMapping(value = "/detail/{id}",method = RequestMethod.GET)
    //@Cacheable(key = "'CMS:ARTICLE:MARKET:GETDETAILBYID:'+#id",unless = "!#result.code.equals('200')")
    public Result<ArticleMarketVo> getDetailById(@PathVariable("id") Integer id) {
        Result<ArticleMarketVo> result = new Result<>();
        if (id == null) {
            LOGGER.error("id must not be null");
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("id must not be null");
            return result;
        }

        try {
            ServiceResult<ArticleMarketVo> serviceResult = articleMarketService.getDetailById(id);
            if (serviceResult != null && serviceResult.isSuccess()) {
                result.setCode(ResultCode.C200.getCode());
                ArticleMarketVo articleMarketVo = serviceResult.getData();
                if(articleMarketVo != null && StringUtils.isNotBlank(articleMarketVo.getMarketId())){
                    com.redstar.digital.open.vo.ServiceResult<OmsMarketInfoVo> omsMarketInfoVoResult = omsMarketInfoService.getMarketInfoByUuid(Integer.valueOf(articleMarketVo.getMarketId()));
                    if(omsMarketInfoVoResult != null && omsMarketInfoVoResult.isSuccess() && omsMarketInfoVoResult.getData() != null){
                        articleMarketVo.setMarketName(omsMarketInfoVoResult.getData().getMarketName());
                        // TODO 暂无数据
                        //articleMarketVo.setMarketPhone();
                        articleMarketVo.setMarketAddress(omsMarketInfoVoResult.getData().getMarketAddress());
                    }
                }
                result.setDataMap(articleMarketVo);
                return  result;
            }
            if(serviceResult != null) {
                LOGGER.error("通过id获取商场文章失败. e:{}", serviceResult.getMsg());
            }
        } catch (Exception e) {
            LOGGER.error("通过id获取商场文章失败. e:{}", e.getMessage());
        }
        result.setCode(ResultCode.C500.getCode());
        result.setMessage("通过id获取商场文章失败");
        return result;
    }
}
