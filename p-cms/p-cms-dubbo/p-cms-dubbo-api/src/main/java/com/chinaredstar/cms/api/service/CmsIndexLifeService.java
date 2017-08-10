package com.chinaredstar.cms.api.service;

import com.chinaredstar.cms.api.component.Page;
import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.vo.index.IndexLifeQueryVo;
import com.chinaredstar.cms.api.vo.index.IndexLifeVo;

import java.util.List;

/**
 * Created by yixin.sun on 2017/3/24.
 */
public interface CmsIndexLifeService {
    /**
     * 通过参数查询生活家集合，分页接口
     * @param queryVo 查询条件和分页参数
     * @return
     */
    ServiceResult<List<IndexLifeVo>> findPageList(IndexLifeQueryVo queryVo);

    /**
     * 通过id查询生活家详情
     * @param id 生活家id
     * @return
     */
    ServiceResult<IndexLifeVo> findById(Integer id);

    /**
     * 通过生活家id集合查询对应的生活家集合
     * @param idList id集合
     * @return
     */
    ServiceResult<List<IndexLifeVo>> findByIdList(List<Integer> idList);
}
