package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.MdmRegion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MdmRegionMapper extends BaseMapper<MdmRegion> {

    List<MdmRegion> queryByRegionCode(@Param("regionCode")String regionCode);

    List<MdmRegion> queryByMdGuid(@Param("mdGuid")String mdGuid);

    int deleteByMdGuid(@Param("mdGuid")String mdGuid);

    int updateByMdGuid(MdmRegion mdmRegion);

}