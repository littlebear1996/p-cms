package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.CmsIndexModuleConfigService;
import com.chinaredstar.cms.api.vo.index.IndexModuleConfigVo;
import com.chinaredstar.cms.mapper.CmsIndexModuleConfigMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pengfei.wang on 2017/3/26.
 */
@Service("cmsIndexModuleConfigService")
public class CmsIndexModuleConfigServiceImpl implements CmsIndexModuleConfigService {

    private final Logger logger = LoggerFactory.getLogger(CmsIndexModuleConfigServiceImpl.class);

    @Autowired
    private CmsIndexModuleConfigMapper cmsIndexModuleConfigMapper;

    @Override
    public ServiceResult<List<IndexModuleConfigVo>> getOnlineConfig() {
        ServiceResult<List<IndexModuleConfigVo>> result = new ServiceResult<>(true);
        try {
            List<IndexModuleConfigVo> onlineConfig = cmsIndexModuleConfigMapper.getOnlineConfig();
            result.setData(onlineConfig);
        } catch (Exception e) {
            logger.error("查询App线上配置信息异常", e);
            result.setSuccess(false);
            result.setMsg("查询App线上配置信息异常");
        }
        return result;
    }
}
