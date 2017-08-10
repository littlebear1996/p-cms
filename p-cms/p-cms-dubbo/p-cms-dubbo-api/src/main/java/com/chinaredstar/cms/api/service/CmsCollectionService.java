package com.chinaredstar.cms.api.service;

import com.chinaredstar.cms.api.component.Page;
import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.model.CmsCollectionData;
import com.chinaredstar.cms.api.vo.collection.CmsCollectionQueryVo;
import com.chinaredstar.cms.api.vo.collection.CmsCollectionVo;

import java.util.List;

/**
 * 合辑服务接口
 */
public interface CmsCollectionService {

    /**
     * 通过合辑类型编码查询合辑列表
     * @param queryVo
     * @return
     */
    ServiceResult<List<CmsCollectionVo>> findCollectionByType(CmsCollectionQueryVo queryVo);


    /**
     * 根据合辑ID集合查询合辑信息
     * @param colldesignerIdList
     * @return
     */
    ServiceResult<List<CmsCollectionVo>> getCmsCollectionListByIds(List<Integer> colldesignerIdList);

    /**
     * 根据合辑ID查询合辑信息
     * @param collectionId
     * @return
     */
    ServiceResult<CmsCollectionVo> getCmsCollectionListById(Integer collectionId);

    /**
     * 根据合辑ID查询合辑信息
     * @param id
     * @return
     */
    ServiceResult<CmsCollectionVo> getCmsCollectionById(Integer id);

    /**
     * 根据合辑ID查询合辑的目标信息
     * @param collectionId
     * @param page
     * @return
     */
    ServiceResult<List<CmsCollectionData>> getCmsCollectionDataListById(Integer collectionId, Page page);

//    /**
//     * 根据合辑类开查询合辑信息
//     * @param queryVo
//     * @return
//     */
//    ServiceResult<List<CmsCollectionVo>> getCmsCollectionByQuery(CmsCollectionQueryVo queryVo);

    /**
     * 通过商场ID查询导购员合辑
     * @param marketId
     * @return
     */
    ServiceResult<CmsCollectionVo> getCmsSalesCollectionByMarketId(Integer marketId);
}
