package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.Outlinks;
import com.chinaredstar.cms.api.vo.outlink.OutlinkVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OutlinksMapper extends BaseMapper<Outlinks>{

    List<OutlinkVo> getOutlinksListByIds(@Param("list") List<Integer> ids);
}