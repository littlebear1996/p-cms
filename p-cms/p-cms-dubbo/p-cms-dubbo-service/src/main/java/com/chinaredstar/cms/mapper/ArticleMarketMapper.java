package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.ArticleMarket;
import com.chinaredstar.cms.api.vo.article.ArticleMarketQueryVo;
import com.chinaredstar.cms.api.vo.article.ArticleMarketVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMarketMapper extends BaseMapper<ArticleMarket>{

    List<ArticleMarketVo> listByCategoryTag(ArticleMarketQueryVo queryVo);

    List<ArticleMarketVo> listByCategoryTagAndCity(ArticleMarketQueryVo queryVo);

    List<ArticleMarketVo> getArticleMarketListByIds(@Param("list") List<Integer> idList);

    ArticleMarketVo getDetailById(Integer id);

    List<Integer> getAllIds();

    Integer updateViewCountById(@Param("id") Integer id, @Param("viewCount") Integer viewCount);
}
