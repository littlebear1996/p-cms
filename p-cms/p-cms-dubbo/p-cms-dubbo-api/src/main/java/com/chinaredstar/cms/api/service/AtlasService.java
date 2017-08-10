package com.chinaredstar.cms.api.service;

import com.chinaredstar.cms.api.vo.atlas.AtlasQueryVo;
import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.vo.atlas.AtlasVo;

import java.util.List;

/**
 * Created by sunny on 16-8-17.
 */
public interface AtlasService {
    /**
     * 根据图集类型获取图集列表
     * @param queryVo
     * @return
     */
    ServiceResult<List<AtlasVo>> listByType(AtlasQueryVo queryVo);

    /**
     * 根据图集id获取指定id的图集
     * @param id
     * @return
     */
    ServiceResult<AtlasVo> getAtlasById(Integer id);

    /**
     * 根据图集ID集合查询图集信息
     * @param idList
     * @return
     */
    ServiceResult<List<AtlasVo>> getAtlasByIds(List<Integer> idList);


    /**
     * 根据objType 和  objValue 删除图集
     * @param atlasVo
     * @return
     */
    ServiceResult deleteAtlasByVo(AtlasVo atlasVo);

    ServiceResult insertAtlas(AtlasVo atlasVo);

    ServiceResult deleteAtlas(String designerId,String caseId,String openId);

    ServiceResult<AtlasVo> getAtlasByDesignerCaseId(String designerId,String caseId);
}
