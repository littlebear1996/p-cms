package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.component.Page;
import com.chinaredstar.cms.api.model.CmsJzCase;
import com.chinaredstar.cms.api.model.Recommendation;
import com.chinaredstar.cms.api.vo.recommend.RecommendationQueryVo;
import com.chinaredstar.cms.api.vo.recommend.RecommendationVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecommendationMapper extends BaseMapper<Recommendation> {

    /**
     * 通过类型获取推荐内容
     *
     * @param queryVo
     * @return
     */
    List<RecommendationVo> getRecommendationListByType(@Param("queryVo") RecommendationQueryVo queryVo,
                                                       @Param("page") Page page);


    List<CmsJzCase> getCmsJzCaseListByIds(@Param("list") List<String> ids);
}