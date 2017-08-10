package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.vo.cmsClass.CmsClassTagsVo;
import com.chinaredstar.cms.api.vo.cmsClass.CmsClassVo;
import com.chinaredstar.cms.api.model.CmsClass;
import com.chinaredstar.cms.api.vo.cmsClass.CmsClassQueryVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CmsClassMapper extends BaseMapper<CmsClass>{

    List<CmsClassVo> getCmsClassListByQuery(CmsClassQueryVo queryVo);


    List<CmsClassTagsVo> getCmsClassTagsByIdList(@Param("list") List<Integer> idList);

}