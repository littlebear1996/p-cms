package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.CmsThreeDimensional;

import java.util.List;
import java.util.Map;

public interface CmsThreeDimensionalMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CmsThreeDimensional record);

    int insertSelective(CmsThreeDimensional record);

    CmsThreeDimensional selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CmsThreeDimensional record);

    int updateByPrimaryKey(CmsThreeDimensional record);

    CmsThreeDimensional queryDataBySpu(Map<String,Object> paramMap);

    List<CmsThreeDimensional> queryDataByBrandId(Map<String,Object> paramMap);
}