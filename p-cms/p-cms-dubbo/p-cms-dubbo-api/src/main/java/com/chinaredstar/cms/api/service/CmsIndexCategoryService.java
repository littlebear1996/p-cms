package com.chinaredstar.cms.api.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.vo.index.IndexCategoryVo;

import java.util.List;

/**
 * Created by pengfei.wang on 2017/3/27.
 */
public interface CmsIndexCategoryService {

    /**
     * 查询主app分类列表
     *
     * @return
     */
    ServiceResult<List<IndexCategoryVo>> getAll();

    /**
     * 查询主app分类详情及其所有分类/品牌
     *
     * @param id
     * @return
     */
    ServiceResult<IndexCategoryVo> findById(Integer id);
}
