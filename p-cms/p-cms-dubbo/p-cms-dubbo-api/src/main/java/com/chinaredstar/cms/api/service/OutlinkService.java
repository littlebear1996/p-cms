package com.chinaredstar.cms.api.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.vo.outlink.OutlinkVo;

import java.util.List;

/**
 * Created by sunny on 16-8-26.
 */
public interface OutlinkService {
    /**
     * 根据id集合获取外链集合
     * @param idList
     * @return
     */
    ServiceResult<List<OutlinkVo>> getOutLinkByIds(List<Integer> idList);
}
