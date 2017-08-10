//package com.chinaredstar.cms.mapper;
//
//import com.chinaredstar.cms.api.model.CmsCampaignMarketCoupon;
//import org.apache.ibatis.annotations.Param;
//
//import java.util.List;
//
//public interface CmsCampaignMarketCouponMapper {
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(CmsCampaignMarketCoupon record);
//
//    int insertSelective(CmsCampaignMarketCoupon record);
//
//    CmsCampaignMarketCoupon selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(CmsCampaignMarketCoupon record);
//
//    int updateByPrimaryKey(CmsCampaignMarketCoupon record);
//
//    List<CmsCampaignMarketCoupon> find(@Param("campaignMarketId") Integer campaignMarketId);
//}