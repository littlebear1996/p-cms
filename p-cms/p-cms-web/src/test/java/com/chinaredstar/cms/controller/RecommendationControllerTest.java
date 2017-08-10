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
public class RecommendationControllerTest extends BaseTest {

    @Autowired
    private RecommendationController recommendationController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(AopTestUtils.getTargetObject(recommendationController))
                .build();
    }

    @Test
    public void testGetRecommendationListByType() throws Exception {
        String type = "1";
        String subType1 = "1";
        String subType2 = "1";
        mockMvc.perform(get("/page/discovery/{type}/{subType1}/{subType2}", type, subType1, subType2))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
