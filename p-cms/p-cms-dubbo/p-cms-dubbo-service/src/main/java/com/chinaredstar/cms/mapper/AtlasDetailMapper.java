package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.AtlasDetail;
import com.chinaredstar.cms.api.vo.atlas.AtlasDetailVo;
import com.chinaredstar.cms.api.vo.atlas.AtlasVo;
import org.apache.ibatis.annotations.Param;

public interface AtlasDetailMapper extends BaseMapper<AtlasDetailVo>{
    public Integer delete(@Param("designerId")String designerId,@Param("caseId") String caseId,@Param("openId") String openId);

    public Integer deleteByAtlasId(AtlasVo vo);
}