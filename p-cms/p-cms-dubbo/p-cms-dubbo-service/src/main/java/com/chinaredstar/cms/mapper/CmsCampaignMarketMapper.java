package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.CmsCampaign;
import com.chinaredstar.cms.api.model.CmsCampaignMarket;
import com.chinaredstar.cms.api.vo.campaign.CampaignMarketQueryVo;

import java.util.List;

public interface CmsCampaignMarketMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CmsCampaignMarket record);

    int insertSelective(CmsCampaignMarket record);

    CmsCampaignMarket selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CmsCampaignMarket record);

    int updateByPrimaryKey(CmsCampaignMarket record);


    List<CmsCampaignMarket> find(CampaignMarketQueryVo vo);
}