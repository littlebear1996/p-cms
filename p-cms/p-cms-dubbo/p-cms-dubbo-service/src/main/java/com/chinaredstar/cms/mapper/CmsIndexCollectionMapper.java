package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.CmsIndexCollection;
import com.chinaredstar.cms.api.vo.collection.CmsCollectionQueryVo;
import com.chinaredstar.cms.api.vo.index.IndexCollectionVo;

import java.util.List;

public interface CmsIndexCollectionMapper extends BaseMapper<CmsIndexCollection> {
    /**
     * 通过合辑类型编码查询合辑列表
     * @param queryVo
     * @return
     */
    List<IndexCollectionVo> findCollectionByType(CmsCollectionQueryVo queryVo);
}