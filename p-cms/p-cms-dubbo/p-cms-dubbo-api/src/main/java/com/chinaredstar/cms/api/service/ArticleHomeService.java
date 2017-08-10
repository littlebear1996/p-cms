package com.chinaredstar.cms.api.service;

import com.chinaredstar.cms.api.component.Page;
import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.model.ArticleHome;
import com.chinaredstar.cms.api.vo.article.ArticleHomeQueryVo;
import com.chinaredstar.cms.api.vo.article.ArticleHomeVo;

import java.util.List;

/**
 * 家装文章服务接口
 */
public interface ArticleHomeService {

    /**
     * 获取所有家装文章, 以置顶排序
     * @param
     * @return
     */
    ServiceResult<List<ArticleHome>> getArticleHomeList(ArticleHomeQueryVo queryVo, Page page);

    /**
     * 根据家装文章ID集合查询家装信息
     * @param idList
     * @return
     */
    ServiceResult<List<ArticleHomeVo>> getArticleHomeListByIds(List<Integer> idList);

    /**
     * 获取指定id家装文章
     * @param id
     * @return
     */
    ServiceResult<ArticleHomeVo> getDetailById(Integer id);

}
