package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.CmsCampaign;
import com.chinaredstar.cms.api.vo.campaign.CampaignQueryVo;

import java.util.List;

public interface CmsCampaignMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CmsCampaign record);

    int insertSelective(CmsCampaign record);

    CmsCampaign selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CmsCampaign record);

    int updateByPrimaryKeyWithBLOBs(CmsCampaign record);

    int updateByPrimaryKey(CmsCampaign record);

    List<CmsCampaign> find(CampaignQueryVo vo);
}