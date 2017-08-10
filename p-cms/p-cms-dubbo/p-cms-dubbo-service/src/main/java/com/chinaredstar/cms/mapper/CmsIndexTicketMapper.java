package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.CmsIndexTicket;
import com.chinaredstar.cms.api.vo.index.IndexTicketVo;

public interface CmsIndexTicketMapper extends BaseMapper<CmsIndexTicket> {

    IndexTicketVo getLatestByCityId(String cityId);
}