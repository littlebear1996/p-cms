package com.chinaredstar.cms.api.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.vo.collection.CmsCollectionQueryVo;
import com.chinaredstar.cms.api.vo.index.IndexCollectionVo;

import java.util.List;

/**
 * Created by pengfei.wang on 2017/3/29.
 */
public interface CmsIndexCollectionService {

    /**
     * 通过合辑类型编码查询合辑列表
     * @param queryVo
     * @return
     */
    ServiceResult<List<IndexCollectionVo>> findCollectionByType(CmsCollectionQueryVo queryVo);

}
