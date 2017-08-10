package com.chinaredstar.cms.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.AopTestUtils;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by pengfei.wang on 2016/12/1.
 */
public class CampaignControllerTest extends BaseTest {

    @Autowired
    private CampaignController campaignController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(AopTestUtils.getTargetObject(campaignController))
                .build();
    }

    @Test
    public void testIndexPage() throws Exception {
        String openId = "1";
        mockMvc.perform(get("/campaign/index/{openId}", openId))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testMarketProductList() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.set("campaignMarketId", "1");
        params.set("type", "");
        params.set("isRecommendIndex", "");
        params.set("isRecommendBrand", "");
        params.set("brandId", "");
        params.set("pageNo", "1");
        params.set("pageSize", "10");

        mockMvc.perform(post("/campaign/market/product").params(params))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testMarketCouponList() throws Exception {
        String campaignMarketId = "1";
        String openId = "1";
        mockMvc.perform(get("/campaign/market/coupon/{campaignMarketId}/{openId}", campaignMarketId, openId))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
