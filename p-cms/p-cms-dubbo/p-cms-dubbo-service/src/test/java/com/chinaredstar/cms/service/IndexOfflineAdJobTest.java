package com.chinaredstar.cms.service;

import com.chinaredstar.cms.mapper.CmsIndexAdvertisementMapper;
import com.chinaredstar.cms.task.IndexOfflineAdJob;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yixin.sun on 2017/4/11.
 */
public class IndexOfflineAdJobTest extends BaseTest {
    @Autowired
    private IndexOfflineAdJob offlineAdJob;

    @Autowired
    private CmsIndexAdvertisementMapper indexAdvertisementMapper;

    @Test
    public void testExecute() throws Exception {
        offlineAdJob.execute(null);
    }

    @Test
    public void batchOfflineByAdListTest() {
        List<Integer> idList = new ArrayList<>();
        idList.add(4);
        Integer updateCount = indexAdvertisementMapper.batchOfflineByAdList(idList);
        System.out.println("updateCount:" + updateCount);
    }
}
