package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.CmsIndexModuleConfig;
import com.chinaredstar.cms.api.vo.index.IndexModuleConfigVo;

import java.util.List;

public interface CmsIndexModuleConfigMapper extends BaseMapper<CmsIndexModuleConfig> {

    List<IndexModuleConfigVo> getOnlineConfig();
}