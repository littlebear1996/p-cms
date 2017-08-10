package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.model.CmsCampaign;
import com.chinaredstar.cms.api.model.CmsCampaignBrand;
import com.chinaredstar.cms.api.model.CmsCampaignMarket;
import com.chinaredstar.cms.api.model.CmsCampaignMarketProduct;
import com.chinaredstar.cms.api.service.CampaignService;
import com.chinaredstar.cms.api.vo.campaign.CampaignBrandQueryVo;
import com.chinaredstar.cms.api.vo.campaign.CampaignMarketProductQueryVo;
import com.chinaredstar.cms.api.vo.campaign.CampaignQueryVo;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2016/8/21.
 */
public class CampaignServiceImplTest extends BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CampaignServiceImplTest.class);

//    @Autowired
//    private IPromotionService promotionService;

    @Autowired
    private CampaignService campaignService;

    @Test
    public void testCheapOfProduct() throws Exception {

    }

//    //@Test
//    public void getCouponOfProduct() throws Exception {
//        System.out.println("success");
//        List<Integer> goodsList = new ArrayList<Integer>();
//        goodsList.add(8000187);
//        Map<Integer, BigDecimal> map = promotionService.queryPromotionsBySubPromotionIdAndSkuIds(301, goodsList);
//        for (BigDecimal cheap : map.values() ) {
//            System.out.println("value=" + cheap);
//        }
//    }

    @Test
    public void testGetCampaign(){
        CampaignQueryVo vo = new CampaignQueryVo();
        vo.setCurrentTime(new Date());
        ServiceResult<CmsCampaign> result = campaignService.getCampaign(vo);
        Assert.assertTrue(result.isSuccess());


    }

    @Test
    public void testGetCampaignBrand(){
        CampaignBrandQueryVo vo = new CampaignBrandQueryVo();
        vo.setCampaignId(1);
        ServiceResult<List<CmsCampaignBrand>> result = campaignService.getCampaignBrand(vo);
        Assert.assertTrue(result.isSuccess());

        result = campaignService.getCampaignBrand(null);
        Assert.assertTrue(!result.isSuccess());
    }

    @Test
    public void testGetCampaignMarket(){
        ServiceResult<List<CmsCampaignMarket>> result = campaignService.getCampaignMarket(1);
        Assert.assertTrue(result.isSuccess());


    }

    @Test
    public void testGetCampaignMarketProduct(){
        CampaignMarketProductQueryVo vo = new CampaignMarketProductQueryVo();
        vo.setBrandId("1");
        vo.setPageNo(1);
        vo.setPageSize(10);
        ServiceResult<List<CmsCampaignMarketProduct>> result = campaignService.getCampaignMarketProduct(vo);
        Assert.assertTrue(result.isSuccess());


    }

//    @Test
//    public void testGetCampaignMarketCoupon(){
//        ServiceResult<List<CmsCampaignMarketCoupon>> result = campaignService.getCampaignMarketCoupon(1, "1");
//        Assert.assertTrue(result.isSuccess());
//
//
//    }

}
