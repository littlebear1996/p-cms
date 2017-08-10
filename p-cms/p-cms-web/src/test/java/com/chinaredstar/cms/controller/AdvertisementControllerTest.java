package com.chinaredstar.cms.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.AopTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by pengfei.wang on 2016/12/1.
 */
public class AdvertisementControllerTest extends BaseTest {

    @Autowired
    private AdvertisementController advertisementController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(AopTestUtils.getTargetObject(advertisementController))
                .build();
    }

    @Test
    public void testListByPostionCode() throws Exception {
        String positionCode = "88";
        mockMvc.perform(get("/ad/list/{positionCode}", positionCode))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllShopRecommAdvertByMarketId() throws Exception {
        String marketId = "1";
        mockMvc.perform(get("/ad/list/{marketId}/shop", marketId))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
