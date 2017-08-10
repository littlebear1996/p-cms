package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.CmsIndexTicketService;
import com.chinaredstar.cms.api.vo.index.IndexTicketVo;
import com.chinaredstar.cms.mapper.CmsIndexTicketMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by pengfei.wang on 2017/4/1.
 */
@Service("cmsIndexTicketService")
public class CmsIndexTicketServiceImpl implements CmsIndexTicketService {

    private final Logger logger = LoggerFactory.getLogger(CmsIndexTicketServiceImpl.class);

    @Autowired
    private CmsIndexTicketMapper cmsIndexTicketMapper;

    @Override
    public ServiceResult<IndexTicketVo> getLatestByCityId(String cityId) {
        ServiceResult<IndexTicketVo> result = new ServiceResult<>(false);
        if (StringUtils.isBlank(cityId)) {
            result.setMsg("城市id不能为空");
            return result;
        }
        try {
            IndexTicketVo latest = cmsIndexTicketMapper.getLatestByCityId(cityId);
            result.setSuccess(true);
            result.setData(latest);
        } catch (Exception e) {
            logger.error("查询最新的领券中心banner配置异常, params:{}", cityId, e);
            result.setMsg("查询最新的领券中心banner配置异常");
        }
        return result;
    }
}
