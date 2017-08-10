package com.chinaredstar.cms.service;

import com.chinaredstar.cms.task.ViewCountSynchronizerJob;
import com.mmall.job.core.handler.IJobHandler;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by pengfei.wang on 2017/7/3.
 */
public class ViewCountSynchronizerJobTest extends BaseTest {

    @Autowired
    private ViewCountSynchronizerJob viewCountSynchronizerJob;

    @Test
    public void test() throws Exception {
        IJobHandler.JobHandleStatus execute = viewCountSynchronizerJob.execute(null);
        System.out.println(execute);
    }
}
