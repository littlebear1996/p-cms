package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.ActivityTemplate;

public interface ActivityTemplateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityTemplate record);

    int insertSelective(ActivityTemplate record);

    ActivityTemplate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityTemplate record);

    int updateByPrimaryKeyWithBLOBs(ActivityTemplate record);

    int updateByPrimaryKey(ActivityTemplate record);
}