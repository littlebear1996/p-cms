package com.chinaredstar.cms.api.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.vo.index.IndexBrandQueryVo;
import com.chinaredstar.cms.api.vo.index.IndexBrandVo;

import java.util.List;

/**
 * Created by pengfei.wang on 2017/3/24.
 */
public interface CmsIndexBrandService {

    /**
     * 分页查询大牌列表, 包括大牌下商品列表
     *
     * @param queryVo
     * @return
     */
    ServiceResult<List<IndexBrandVo>> findPageList(IndexBrandQueryVo queryVo);

    /**
     * 查询大牌详情, 包括大牌下的商品列表
     *
     * @param id
     * @return
     */
    ServiceResult<IndexBrandVo> findById(Integer id);

    /**
     * 根据id集合查询大牌列表
     *
     * @param ids
     * @return
     */
    ServiceResult<List<IndexBrandVo>> findByIds(List<Integer> ids);
}
