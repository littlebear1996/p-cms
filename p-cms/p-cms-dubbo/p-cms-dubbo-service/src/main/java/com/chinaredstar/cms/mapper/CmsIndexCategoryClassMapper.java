package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.CmsIndexCategoryClass;
import com.chinaredstar.cms.api.vo.index.IndexCategoryClassVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CmsIndexCategoryClassMapper extends BaseMapper<CmsIndexCategoryClass> {
    List<IndexCategoryClassVo> findPageListByCategoryId(@Param("categoryId") Integer categoryId);
}