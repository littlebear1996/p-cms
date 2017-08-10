package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.CmsIndexLifeGood;
import com.chinaredstar.cms.api.vo.index.IndexLifeGoodVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CmsIndexLifeGoodMapper extends BaseMapper<CmsIndexLifeGood> {

    List<IndexLifeGoodVo> findListByLifeId(@Param("lifeId") Integer lifeId);
}