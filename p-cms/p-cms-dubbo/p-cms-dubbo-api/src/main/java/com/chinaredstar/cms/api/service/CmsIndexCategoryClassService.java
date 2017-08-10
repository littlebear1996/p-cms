package com.chinaredstar.cms.api.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.vo.index.IndexCategoryClassVo;

import java.util.List;

/**
 * Created by yixin.sun on 2017/3/27.
 */
public interface CmsIndexCategoryClassService {
    ServiceResult<List<IndexCategoryClassVo>> findListByCategoryId(Integer categoryId);
}
