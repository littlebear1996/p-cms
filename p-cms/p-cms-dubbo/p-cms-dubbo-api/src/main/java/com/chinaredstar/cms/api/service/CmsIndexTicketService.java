package com.chinaredstar.cms.api.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.vo.index.IndexTicketVo;

/**
 * Created by pengfei.wang on 2017/4/1.
 */
public interface CmsIndexTicketService {

    /**
     * 根据城市查询最新的banner图
     * @param cityId
     * @return
     */
    ServiceResult<IndexTicketVo> getLatestByCityId(String cityId);
}
