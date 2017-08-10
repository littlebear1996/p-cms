package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.CmsIndexLife;
import com.chinaredstar.cms.api.vo.index.IndexLifeQueryVo;
import com.chinaredstar.cms.api.vo.index.IndexLifeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CmsIndexLifeMapper extends BaseMapper<CmsIndexLife> {
    List<IndexLifeVo> findPageList(IndexLifeQueryVo queryVo);

    IndexLifeVo findById(@Param("id") Integer id);

    List<IndexLifeVo> findByIdList(@Param("idList") List<Integer> idList);
}