package com.chinaredstar.cms.task;

import com.chinaredstar.cms.api.vo.index.IndexAdvertisementQueryVo;
import com.chinaredstar.cms.api.vo.index.IndexAdvertisementVo;
import com.chinaredstar.cms.mapper.CmsIndexAdvertisementMapper;
import com.mmall.job.core.handler.IJobHandler;
import com.mmall.job.core.handler.annotation.JobHander;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by yixin.sun on 2017/4/11.
 */
@JobHander(value = "indexOfflineAdJobHandler")
@Service
public class IndexOfflineAdJob extends IJobHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexOfflineAdJob.class);
    @Autowired
    private CmsIndexAdvertisementMapper indexAdvertisementMapper;
    @Override
    public JobHandleStatus execute(String... strings) throws Exception {

        IndexAdvertisementQueryVo queryVo = new IndexAdvertisementQueryVo();
        queryVo.setCurrentTime(new Date());
        try {

            //获取所有上线且上线时间和下线时间介于当前时间的广告列表
            List<IndexAdvertisementVo> advertisementVos = indexAdvertisementMapper.getOnlineAdvertisement(queryVo);

            //将相同positionId的广告分组
            Map<Integer, List<IndexAdvertisementVo>> positonAdMap = convertToPositonAdMap(advertisementVos);

            //对已经分组的广告map进行处理，得到需要下线的广告id（重复sequence取最新的上线，其余下线）
            List<Integer> offlineIdList = processPositonAdMapToOfflineIdList(positonAdMap);

            //根据offlineIdList批量下线广告
            if(offlineIdList == null || offlineIdList.isEmpty()) {
                return JobHandleStatus.SUCCESS;
            }
            Integer updateCount = indexAdvertisementMapper.batchOfflineByAdList(offlineIdList);
            return JobHandleStatus.SUCCESS;
        } catch (Exception e) {
            LOGGER.error("下线广告失败：{}",e);
            return  JobHandleStatus.FAIL;
        }
    }

    private List<Integer> processPositonAdMapToOfflineIdList(Map<Integer, List<IndexAdvertisementVo>> positonAdMap) {
        if (positonAdMap == null || positonAdMap.isEmpty()) {
            return null;
        }
        List<Integer> offlineIdList = new ArrayList<>();
        for (Integer positionId: positonAdMap.keySet()) {
            List<IndexAdvertisementVo> adList = positonAdMap.get(positionId);
            for (int i = 1; i < adList.size(); i++) {
                if (adList.get(i).getSequence() == adList.get(i - 1).getSequence()) {
                    offlineIdList.add(adList.get(i-1).getId());
                }
            }
        }
        return offlineIdList;
    }

    private Map<Integer,List<IndexAdvertisementVo>> convertToPositonAdMap(List<IndexAdvertisementVo> advertisementVos) {
        if (advertisementVos == null || advertisementVos.isEmpty()) {
            return null;
        }
        Map<Integer, List<IndexAdvertisementVo>> positonAdMap = new HashMap<>();
        for (IndexAdvertisementVo advertisementVo : advertisementVos) {
            Integer positionId = advertisementVo.getPositionId();
            if (positonAdMap.get(positionId) == null) {
                positonAdMap.put(positionId, new ArrayList<IndexAdvertisementVo>());
            }
            positonAdMap.get(positionId).add(advertisementVo);
        }

        return positonAdMap;
    }
}
