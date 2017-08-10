package com.chinaredstar.cms.api.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.model.*;
import com.chinaredstar.cms.api.vo.campaign.CampaignBrandQueryVo;
import com.chinaredstar.cms.api.vo.campaign.CampaignMarketProductQueryVo;
import com.chinaredstar.cms.api.vo.campaign.CampaignQueryVo;
//import com.chinaredstar.p_tx_cupon.api.vo.Cupon;

import java.util.List;

/**
 * 活动接口
 * Created by Ykk on 16/10/16.
 */
public interface CampaignService {


    /**
     * 获取活动信息接口
     * @param vo
     * @return
     */
    ServiceResult<CmsCampaign> getCampaign(CampaignQueryVo vo);

    /**
     * 活动品牌接口
     * @param vo
     * @return
     */
    ServiceResult<List<CmsCampaignBrand>> getCampaignBrand(CampaignBrandQueryVo vo);


    /**
     * 活动商场接口
     * @param campaignId 活动id
     * @return
     */
    ServiceResult<List<CmsCampaignMarket>> getCampaignMarket(Integer campaignId);


    /**
     * 获取活动商场商品信息
     * @param vo
     * @return
     */
    ServiceResult<List<CmsCampaignMarketProduct>>  getCampaignMarketProduct(CampaignMarketProductQueryVo vo);


    /**
     * 获取活动商场优惠券信息
     * @param campaignMarketId  活动商场id
     * @return
     */
//    ServiceResult< List<CmsCampaignMarketCoupon>> getCampaignMarketCoupon(Integer campaignMarketId,String openId);

}
