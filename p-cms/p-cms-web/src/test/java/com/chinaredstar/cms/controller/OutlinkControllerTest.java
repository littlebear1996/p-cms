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
public class OutlinkControllerTest extends BaseTest {

    @Autowired
    private OutlinkController outlinkController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(AopTestUtils.getTargetObject(outlinkController))
                .build();
    }

    @Test
    public void testListByType() throws Exception {
        String ids = "1,2,3,4,5,6,7,8,9,11,22,33,44,55,66,77,88,99";
        mockMvc.perform(get("/outlink/listByIds/{ids}", ids))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
