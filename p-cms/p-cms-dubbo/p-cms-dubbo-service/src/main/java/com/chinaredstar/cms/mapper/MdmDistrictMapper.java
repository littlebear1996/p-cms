package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.MdmDistrict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MdmDistrictMapper extends BaseMapper<MdmDistrict> {


    List<MdmDistrict> queryByDistrictCode(@Param("districtCode")String districtCode);

    List<MdmDistrict> queryByMdGuid(@Param("mdGuid")String mdGuid);

    int deleteByMdGuid(@Param("mdGuid")String mdGuid);

    int updateByMdGuid(MdmDistrict mdmDistrict);

}