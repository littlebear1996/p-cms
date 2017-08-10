package com.chinaredstar.cms.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.AopTestUtils;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by pengfei.wang on 2016/12/1.
 */
public class TopicControllerTest extends BaseTest{

    @Autowired
    private TopicController topicController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(AopTestUtils.getTargetObject(topicController))
                .build();
    }

    @Test
    public void testGetTopicDetail() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.set("topicTypeId", "9");
        params.set("pageNo", "1");
        params.set("pageSize", "10");

        mockMvc.perform(post("/topic/detail").params(params))
                .andDo(print())
                .andExpect(status().isOk());

//        mockMvc.perform(post("/topic/detail").params(null))
//                .andDo(print())
//                .andExpect(status().isOk());
    }

    @Test
    public void testGetTopicType() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.set("topicId", "9");
        params.set("pageNo", "1");
        params.set("pageSize", "10");

        mockMvc.perform(post("/topic/type").params(params))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
