package com.chinaredstar.cms.api.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.vo.article.ArticleCustomVo;

import java.util.List;

/**
 * 商品文章服务接口
 */
public interface ArticleCustomService {

    /**
     * 根据商品文章ID集合查询商品文章信息
     *
     * @param customIdList
     * @return
     */
    ServiceResult<List<ArticleCustomVo>> getArticleCustomListByIds(List<Integer> customIdList);

    ServiceResult<ArticleCustomVo> getDetailById(Integer id);
}
