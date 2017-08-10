package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.component.Page;
import com.chinaredstar.cms.api.model.ArticleHome;
import com.chinaredstar.cms.api.vo.article.ArticleHomeQueryVo;
import com.chinaredstar.cms.api.vo.article.ArticleHomeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleHomeMapper extends BaseMapper<ArticleHome>{

    List<ArticleHomeVo> getArticleHomeListByIds(@Param("list") List<Integer> idList);

    /**
     * 获取所有家装文章, 以置顶排序
     * @param
     * @return
     */
    List<ArticleHome> getArticleHomeList(@Param("articleHomeVo") ArticleHomeQueryVo queryVo,
                                           @Param("page") Page page);

    ArticleHomeVo getDetailById(Integer id);

    List<Integer> getAllIds();

    Integer updateViewCountById(@Param("id") Integer id,@Param("viewCount") Integer viewCount);
}