package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.CmsCampaignMarketProduct;
import com.chinaredstar.cms.api.vo.campaign.CampaignMarketProductQueryVo;

import java.util.List;

public interface CmsCampaignMarketProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CmsCampaignMarketProduct record);

    int insertSelective(CmsCampaignMarketProduct record);

    CmsCampaignMarketProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CmsCampaignMarketProduct record);

    int updateByPrimaryKey(CmsCampaignMarketProduct record);

    List<CmsCampaignMarketProduct> find(CampaignMarketProductQueryVo vo);
}