package com.chinaredstar.cms.api.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.vo.encyclopedia.EncyclopediaQueryVo;
import com.chinaredstar.cms.api.vo.encyclopedia.EncyclopediaVo;

import java.util.List;

/**
 * Created by sunny on 16-8-23.
 */
public interface EncyclopediaService {
    /**
     * 通过类型数值获取百科集合
     * @param queryVo
     * @return
     */
    ServiceResult<List<EncyclopediaVo>> listByType(EncyclopediaQueryVo queryVo);

    /**
     * 通过id获取指定百科
     * @param id
     * @return
     */
    ServiceResult<EncyclopediaVo> getDetailById(Integer id);

    /**
     * 通过id集合获取百科集合
     * @param idList
     * @return
     */
    ServiceResult<List<EncyclopediaVo>> getEncyclopediaListByIds(List<Integer> idList);

    ServiceResult<List<EncyclopediaVo>> getEncyclopediaListByTags(String tags);
}
