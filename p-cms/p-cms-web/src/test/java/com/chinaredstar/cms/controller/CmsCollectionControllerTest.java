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
public class CmsCollectionControllerTest extends BaseTest {

    @Autowired
    private CmsCollectionController cmsCollectionController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(AopTestUtils.getTargetObject(cmsCollectionController))
                .build();
    }

    @Test
    public void testIndexPage() throws Exception {
        String type = "1";
        String subType = "1";
        mockMvc.perform(get("/collection/list/{type}/{subType}", type, subType))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetCollectionDetailById() throws Exception {
        String id = "1";
        mockMvc.perform(get("/collection/detail/{id}", id))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetCollectionDataListByIdWithPage() throws Exception {
        String id = "1";
        mockMvc.perform(get("/collection/detail/{id}/page", id))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
