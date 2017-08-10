package com.chinaredstar.cms.api.service;

import com.chinaredstar.cms.api.component.Page;
import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.model.ArticleHouse;
import com.chinaredstar.cms.api.vo.article.ArticleHouseQueryVo;
import com.chinaredstar.cms.api.vo.article.ArticleHouseVo;

import java.util.List;
import java.util.Set;

/**
 * Created by sunny on 16-8-24.
 */
public interface ArticleHouseService {
    /**
     * 根据分类标签获取房产文章集合
     *
     * @param queryVo
     * @return
     */
    ServiceResult<List<ArticleHouseVo>> listByCategoryTag(ArticleHouseQueryVo queryVo);

    /**
     * 获取指定id的房产文章
     *
     * @param id
     * @return
     */
    ServiceResult<ArticleHouseVo> getDetailById(Integer id);

    /**
     * 根据房产文章ID集合查询房产文章信息
     *
     * @param idList
     * @return
     */
    ServiceResult<List<ArticleHouseVo>> getArticleHouseListByIds(List<Integer> idList);


    /**
     * 分页查询房产文章, 通过is_top倒序, create_time倒序
     *
     * @param page
     * @return
     */
    ServiceResult<List<ArticleHouseVo>> getArticleHouseWithPage(Page page);

    /**
     * 通过分类标签查询所有标签
     *
     * @param queryVo
     * @return
     */
    ServiceResult<Set<String>> getAllTags(ArticleHouseQueryVo queryVo);

    /**
     * 保存文章
     *
     * @param articleHouse
     * @return
     */
    ServiceResult<ArticleHouse> save(ArticleHouse articleHouse);

    /**
     * 通过id删除房产文章
     *
     * @param id
     * @return
     */
    ServiceResult deleteById(Integer id,String token,long timeStamp);

    /**
     * 更新房产文章
     *
     * @param articleHouse
     * @return
     */
    ServiceResult updateById(ArticleHouse articleHouse);
}
