package com.chinaredstar.cms.api.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.vo.article.ArticleMarketVo;
import com.chinaredstar.cms.api.vo.article.ArticleMarketQueryVo;

import java.util.List;

/**
 * Created by sunny on 16-8-19.
 */
public interface ArticleMarketService {
    /**
     * 根据分类标签获取商场文章列表
     * @param queryVo
     * @return
     */
    ServiceResult<List<ArticleMarketVo>> listByCategoryTag(ArticleMarketQueryVo queryVo);

    /**
     * App首页改版-根据分类标签获取商场文章列表
     * @param queryVo
     * @since 1.2.4
     * @return
     */
    ServiceResult<List<ArticleMarketVo>> listByCategoryTagAndCity(ArticleMarketQueryVo queryVo);

    /**
     * 根据商场文章ID集合查询商场文章信息
     * @param idList
     * @return
     */
    ServiceResult<List<ArticleMarketVo>> getArticleMarketListByIds(List<Integer> idList);

    /**
     * 获取指定id的商场文章
     * @param id
     * @return
     */
    ServiceResult<ArticleMarketVo> getDetailById(Integer id);
}
