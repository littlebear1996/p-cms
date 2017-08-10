package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.component.Page;
import com.chinaredstar.cms.api.model.ArticleHouse;
import com.chinaredstar.cms.api.vo.article.ArticleHouseQueryVo;
import com.chinaredstar.cms.api.vo.article.ArticleHouseVo;
import org.apache.ibatis.annotations.Param;
import org.apache.zookeeper.data.Id;

import java.util.List;

public interface ArticleHouseMapper extends BaseMapper<ArticleHouse>{

    List<ArticleHouseVo> getArticleHouseListByIds(@Param("list") List<Integer> idList);

    List<ArticleHouseVo> listByCategoryTag(ArticleHouseQueryVo queryVo);

    ArticleHouseVo getDetailById(Integer id);

    /**
     * 分页查询房产文章, 通过is_top倒序, create_time倒序
     * @param page
     * @return
     */
    List<ArticleHouseVo> getArticleHouseListWithPage(Page page);

    List<Integer> getAllIds();

    Integer updateViewCountById(@Param("id") Integer id, @Param("viewCount") Integer viewCount);

    /**
     * 新增文章
     * @param articleHouse
     * @return
     */
    Integer save(ArticleHouse articleHouse);

    /**
     * 通过文章删除文章
     * @param id
     * @return
     */
    Integer deleteById(@Param("id") Integer id);

    /**
     * 通过id更新文章
     * @param articleHouse
     * @return
     */
    Integer updateById(ArticleHouse articleHouse);
}