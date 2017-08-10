package com.chinaredstar.cms.api.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.vo.index.IndexCategoryBrandVo;

import java.util.List;

/**
 * Created by pengfei.wang on 2017/3/27.
 */
public interface CmsIndexCategoryBrandService {

    /**
     * 根据分类查询品牌列表
     *
     * @param categoryId
     * @return
     */
    ServiceResult<List<IndexCategoryBrandVo>> findByCategoryId(Integer categoryId);
}
