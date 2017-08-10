package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.MdmMarketInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MdmMarketInfoMapper extends BaseMapper<MdmMarketInfo> {

    List<MdmMarketInfo> queryByMdGuid(@Param("mdGuid")String mdGuid);

    int deleteByMdGuid(@Param("mdGuid")String mdGuid);

    int updateByMdGuid(MdmMarketInfo mdmMarketInfo);


}