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
public class ArticleCustomControllerTest extends BaseTest {

    @Autowired
    private ArticleCustomController articleCustomController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(AopTestUtils.getTargetObject(articleCustomController))
                .build();
    }

    @Test
    public void testGetDetailById() throws Exception {
        String id = "1";
        mockMvc.perform(get("/article/custom/detail/{id}", id))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
