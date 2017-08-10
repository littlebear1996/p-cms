package com.chinaredstar.cms.api.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.vo.index.IndexTopicQueryVo;
import com.chinaredstar.cms.api.vo.index.IndexTopicVo;

import java.util.List;

/**
 * Created by pengfei.wang on 2017/3/24.
 */
public interface CmsIndexTopicService {

    /**
     * 分页查询精选专题列表
     *
     * @param queryVo
     * @return
     */
    ServiceResult<List<IndexTopicVo>> findPageList(IndexTopicQueryVo queryVo);
}
