package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.CmsCampaignAdvertisement;

public interface CmsCampaignAdvertisementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CmsCampaignAdvertisement record);

    int insertSelective(CmsCampaignAdvertisement record);

    CmsCampaignAdvertisement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CmsCampaignAdvertisement record);

    int updateByPrimaryKey(CmsCampaignAdvertisement record);
}