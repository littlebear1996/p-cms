package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.ArticleCustom;
import com.chinaredstar.cms.api.vo.article.ArticleCustomVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleCustomMapper extends BaseMapper<ArticleCustom>{

    List<ArticleCustomVo> getArticleCustomListByIds(@Param("list") List<Integer> idList);

    ArticleCustomVo getDetailById(Integer id);

    List<Integer> getAllIds();

    Integer updateViewCountById(@Param("id") Integer id, @Param("viewCount") Integer viewCount);
}