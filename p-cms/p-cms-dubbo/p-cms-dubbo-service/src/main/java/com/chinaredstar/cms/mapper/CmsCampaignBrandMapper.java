package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.CmsCampaignBrand;
import com.chinaredstar.cms.api.vo.campaign.CampaignBrandQueryVo;

import java.util.List;

public interface CmsCampaignBrandMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CmsCampaignBrand record);

    int insertSelective(CmsCampaignBrand record);

    List<CmsCampaignBrand> getCampaignBrand(CampaignBrandQueryVo vo);

    int updateByPrimaryKeySelective(CmsCampaignBrand record);

    int updateByPrimaryKey(CmsCampaignBrand record);
}