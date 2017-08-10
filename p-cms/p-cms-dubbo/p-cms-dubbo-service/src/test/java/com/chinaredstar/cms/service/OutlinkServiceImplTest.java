package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.OutlinkService;
import com.chinaredstar.cms.api.vo.outlink.OutlinkVo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunny on 16-8-31.
 */
public class OutlinkServiceImplTest extends BaseTest {
    @Autowired
    private OutlinkService outlinkService;

    @Test
    public void testGetOutLinkByIds() {
        List<Integer> idList = new ArrayList<>();
        for(int i=1;i<=100;i++) {
            idList.add(i);
        }
        ServiceResult<List<OutlinkVo>> result = outlinkService.getOutLinkByIds(idList);
        System.out.println(result);
        result = outlinkService.getOutLinkByIds(null);
        Assert.assertTrue(!result.isSuccess());
    }
}
