package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.CmsIndexCollectionData;
import com.chinaredstar.cms.api.vo.index.IndexCollectionDataVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CmsIndexCollectionDataMapper extends BaseMapper<CmsIndexCollectionData> {

    List<IndexCollectionDataVo>  findByCollectionId(@Param("collectionId") Integer collectionId);

    List<IndexCollectionDataVo>  findByCollectionIds(@Param("collectionIds") List<Integer> collectionIds);
}