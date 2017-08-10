package com.chinaredstar.cms.api.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.vo.index.IndexModuleConfigVo;

import java.util.List;

/**
 * Created by pengfei.wang on 2017/3/26.
 */
public interface CmsIndexModuleConfigService {

    /**
     * 查询线上配置
     *
     * @return
     */
    ServiceResult<List<IndexModuleConfigVo>> getOnlineConfig();
}
