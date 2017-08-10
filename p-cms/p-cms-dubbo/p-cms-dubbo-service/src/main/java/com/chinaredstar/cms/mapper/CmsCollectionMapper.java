package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.vo.collection.CmsCollectionQueryVo;
import com.chinaredstar.cms.api.vo.collection.CmsCollectionVo;
import com.chinaredstar.cms.api.model.CmsCollection;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 合辑Mapper
 */
public interface CmsCollectionMapper extends BaseMapper<CmsCollection>{

    /**
     * 通过合辑类型编码查询合辑列表
     * @param queryVo
     * @return
     */
    List<CmsCollectionVo> findCollectionByType(CmsCollectionQueryVo queryVo);

    /**
     * 根据合辑ID集合查询合辑信息
     * @param idList
     * @return
     */
    List<CmsCollectionVo> getCollectionListByIds(@Param("list") List<Integer> idList);

    /**
     * 根据合辑ID查询合辑信息
     * @param id
     * @return
     */
    CmsCollectionVo getCollectionVoById(Integer id);
}