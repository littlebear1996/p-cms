package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.Encyclopedia;
import com.chinaredstar.cms.api.vo.encyclopedia.EncyclopediaQueryVo;
import com.chinaredstar.cms.api.vo.encyclopedia.EncyclopediaVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EncyclopediaMapper extends BaseMapper<Encyclopedia>{

    List<EncyclopediaVo> listByType(EncyclopediaQueryVo queryVo);

    EncyclopediaVo getDetailById(Integer id);

    List<EncyclopediaVo> getEncyclopediaListByIds(@Param("list") List<Integer> idList);

    List<EncyclopediaVo> getEncyclopediaListByTags(String tags);
}