package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.CmsIndexBrand;
import com.chinaredstar.cms.api.vo.index.IndexBrandQueryVo;
import com.chinaredstar.cms.api.vo.index.IndexBrandVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CmsIndexBrandMapper extends BaseMapper<CmsIndexBrand> {

    List<IndexBrandVo> findPageList(IndexBrandQueryVo queryVo);

    IndexBrandVo findById(Integer id);

    List<IndexBrandVo> findByIds(@Param("ids") List<Integer> ids);
}