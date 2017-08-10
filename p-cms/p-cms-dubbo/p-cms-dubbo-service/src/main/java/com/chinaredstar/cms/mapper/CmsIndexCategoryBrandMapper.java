package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.CmsIndexCategoryBrand;
import com.chinaredstar.cms.api.vo.index.IndexCategoryBrandVo;

import java.util.List;

public interface CmsIndexCategoryBrandMapper extends BaseMapper<CmsIndexCategoryBrand> {

    List<IndexCategoryBrandVo> findByCategoryId(Integer categoryId);
}