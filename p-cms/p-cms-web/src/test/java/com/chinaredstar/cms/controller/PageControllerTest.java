package com.chinaredstar.cms.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.AopTestUtils;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by pengfei.wang on 2016/12/1.
 */
public class PageControllerTest extends BaseTest {

    @Autowired
    private PageController pageController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(AopTestUtils.getTargetObject(pageController))
                .build();
    }

    @Test
    public void testIndexPageController() throws Exception {
        mockMvc.perform(get("/page/index"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testHousePropertyPageController() throws Exception {
        mockMvc.perform(get("/page/houseProperty"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testHomeDecorationPageController() throws Exception {
        mockMvc.perform(get("/page/homeDecoration"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testMarketPage() throws Exception {
        mockMvc.perform(get("/page/market"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testMarketDetailPage() throws Exception {
        String marketId = "1";
        mockMvc.perform(get("/page/market/{marketId}/detail", marketId))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testSearchPage() throws Exception {
        mockMvc.perform(get("/page/search/condition/cateoryTypeProduct"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testCmsClassMarket() throws Exception {
        mockMvc.perform(get("/page/cmsClass/categoryTypeMarket"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
