package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.component.Page;
import com.chinaredstar.cms.api.model.CmsCollectionData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CmsCollectionDataMapper extends BaseMapper<CmsCollectionData>{

    /**
     * 根据合辑ID查询合辑中的数据信息
     * @param collectionId
     * @param page
     * @return
     */
    List<CmsCollectionData> getCmsCollectionDataListBycollId(@Param("collectionId") Integer collectionId, @Param("page") Page page);

}