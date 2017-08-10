package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.vo.atlas.AtlasQueryVo;
import com.chinaredstar.cms.api.vo.atlas.AtlasVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AtlasMapper extends BaseMapper<AtlasVo> {
    List<AtlasVo> listByType(AtlasQueryVo queryVo);

    List<AtlasVo> getAtlasByIds(@Param("list") List<Integer> idList);

    AtlasVo getDetailById(Integer id);

    void delete(@Param("designerId") String designerId, @Param("caseId") String caseId, @Param("openId") String openId);

    AtlasVo getAtlasByDesignerCaseId(@Param("designerId") String designerId, @Param("caseId") String caseId);

    void deleteAtlasByVo(AtlasVo queryVo);

    List<Integer> getAllIdsByType(@Param("type") Integer type);

    Integer updateViewCountById(@Param("id") Integer id, @Param("viewCount") Integer viewCount);
}