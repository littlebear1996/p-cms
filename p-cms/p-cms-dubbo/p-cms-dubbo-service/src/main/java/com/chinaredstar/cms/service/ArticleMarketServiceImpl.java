package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.ArticleMarketService;
import com.chinaredstar.cms.api.vo.article.ArticleMarketQueryVo;
import com.chinaredstar.cms.api.vo.article.ArticleMarketVo;
import com.chinaredstar.cms.mapper.ArticleMarketMapper;
import com.chinaredstar.perseus.utils.JsonUtil;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by sunny on 16-8-19.
 */
@Service("articleMarketService")
@Timed
public class ArticleMarketServiceImpl implements ArticleMarketService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleMarketServiceImpl.class);

    @Autowired
    private ArticleMarketMapper articleMarketMapper;

    @Override
    public ServiceResult<List<ArticleMarketVo>> listByCategoryTag(ArticleMarketQueryVo queryVo) {
        ServiceResult<List<ArticleMarketVo>> serviceResult = new ServiceResult<>(false);
        if (queryVo == null) {
            serviceResult.setMsg("查询参数不能为空");
            return serviceResult;
        }
        try {
            List<ArticleMarketVo> articleMarketVos = articleMarketMapper.listByCategoryTag(queryVo);
            serviceResult.setSuccess(true);
            serviceResult.setData(articleMarketVos);
        } catch (Exception e) {
            LOGGER.error("通过分类标签获取商品文章异常，params:{}", JsonUtil.toJson(queryVo, false), e);
            serviceResult.setMsg("通过分类标签获取商品文章异常");
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<ArticleMarketVo>> listByCategoryTagAndCity(ArticleMarketQueryVo queryVo) {
        ServiceResult<List<ArticleMarketVo>> serviceResult = new ServiceResult<>(false);
        if (queryVo == null) {
            serviceResult.setMsg("查询参数不能为空");
            return serviceResult;
        }
        try {
            List<ArticleMarketVo> articleMarketVos = articleMarketMapper.listByCategoryTagAndCity(queryVo);
            serviceResult.setSuccess(true);
            serviceResult.setData(articleMarketVos);
        } catch (Exception e) {
            LOGGER.error("通过分类标签获取商品文章异常，params:{}", JsonUtil.toJson(queryVo, false), e);
            serviceResult.setMsg("通过分类标签获取商品文章异常");
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<ArticleMarketVo>> getArticleMarketListByIds(List<Integer> idList) {
        ServiceResult<List<ArticleMarketVo>> serviceResult = new ServiceResult<>(false);
        if (idList == null || idList.isEmpty()) {
            serviceResult.setMsg("id集合不能为空");
            return serviceResult;
        }
        try {
            List<ArticleMarketVo> articleMarketVoList = articleMarketMapper.getArticleMarketListByIds(idList);
            serviceResult.setData(articleMarketVoList);
            serviceResult.setSuccess(true);
        } catch (Exception e) {
            LOGGER.error("通过id集合查询文章列表异常, params:{}", JsonUtil.toJson(idList, false), e);
            serviceResult.setMsg("查询文章列表异常");
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<ArticleMarketVo> getDetailById(Integer id) {
        ServiceResult<ArticleMarketVo> serviceResult = new ServiceResult<>(false);
        if (id == null) {
            serviceResult.setMsg("文章id不能为空");
            return serviceResult;
        }
        try {
            ArticleMarketVo articleMarketVo = articleMarketMapper.getDetailById(id);
            serviceResult.setSuccess(true);
            serviceResult.setData(articleMarketVo);
        } catch (Exception e) {
            LOGGER.error("通过id获取商场文章失败，params:{}", id, e);
            serviceResult.setMsg("查询商场文章详情异常");
        }
        return serviceResult;
    }
}
