package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.CmsIndexCategory;
import com.chinaredstar.cms.api.vo.index.IndexCategoryVo;

import java.util.List;

public interface CmsIndexCategoryMapper extends BaseMapper<CmsIndexCategory> {

    List<IndexCategoryVo> getAll();

    IndexCategoryVo findById(Integer id);
}