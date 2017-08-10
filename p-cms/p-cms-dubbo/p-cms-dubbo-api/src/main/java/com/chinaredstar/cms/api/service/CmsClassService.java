package com.chinaredstar.cms.api.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.vo.cmsClass.CmsClassQueryVo;
import com.chinaredstar.cms.api.vo.cmsClass.CmsClassTagsVo;
import com.chinaredstar.cms.api.vo.cmsClass.CmsClassVo;

import java.util.List;

/**
 * 分类接口
 */
public interface CmsClassService {


    /**
     * 根据条件查询分类信息
     * @param queryVo
     * @return
     */
    ServiceResult<List<CmsClassVo>> getCmsClassListByQuery(CmsClassQueryVo queryVo);


    /**
     * 根据
     * @param idList
     * @return
     */
    ServiceResult<List<CmsClassTagsVo>> getCmsClassTagsByIdList(List<Integer> idList);
}
