package com.chinaredstar.cms.task;

import com.chinaredstar.cms.api.vo.advertisement.AdvertisementQueryVo;
import com.chinaredstar.cms.api.vo.advertisement.AdvertisementVo;
import com.chinaredstar.cms.mapper.AdvertisementMapper;
import com.mmall.job.core.handler.IJobHandler;
import com.mmall.job.core.handler.annotation.JobHander;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by sunny on 16-9-24.
 */
@JobHander(value = "offlineAdJobHandler")
@Service
public class OfflineAdTimerJob extends IJobHandler{
    private static final Logger LOGGER = LoggerFactory.getLogger(OfflineAdTimerJob.class);
    @Autowired
    private AdvertisementMapper advertisementMapper;
    @Override
    public JobHandleStatus execute(String... strings) throws Exception {

        AdvertisementQueryVo queryVo = new AdvertisementQueryVo();
        queryVo.setCurrentTime(new Date());
        queryVo.setOnline(true);
        try {

            //获取所有上线且上线时间和下线时间介于当前时间的广告列表
            List<AdvertisementVo> advertisementVos = advertisementMapper.getOnlineAdvertisement(queryVo);

            //将相同positionId的广告分组
            Map<Integer, List<AdvertisementVo>> positonAdMap = convertToPositonAdMap(advertisementVos);

            //对已经分组的广告map进行处理，得到需要下线的广告id（重复sequence取最新的上线，其余下线）
            List<Integer> offlineIdList = processPositonAdMapToOfflineIdList(positonAdMap);

            //根据offlineIdList批量下线广告
            if(offlineIdList == null || offlineIdList.isEmpty()) {
                return JobHandleStatus.SUCCESS;
            }
            Integer updateCount = advertisementMapper.batchOfflineByAdList(offlineIdList);
            return JobHandleStatus.SUCCESS;
        } catch (Exception e) {
            LOGGER.error("下线广告失败：{}",e);
            return  JobHandleStatus.FAIL;
        }
    }

    private List<Integer> processPositonAdMapToOfflineIdList(Map<Integer, List<AdvertisementVo>> positonAdMap) {
        if (positonAdMap == null || positonAdMap.isEmpty()) {
            return null;
        }
        List<Integer> offlineIdList = new ArrayList<>();
        for (Integer positionId: positonAdMap.keySet()) {
            List<AdvertisementVo> adList = positonAdMap.get(positionId);
            for (int i = 1; i < adList.size(); i++) {
                if (adList.get(i).getSequence() == adList.get(i - 1).getSequence()) {
                    offlineIdList.add(adList.get(i-1).getId());
                }
            }
        }
        return offlineIdList;
    }

    private Map<Integer,List<AdvertisementVo>> convertToPositonAdMap(List<AdvertisementVo> advertisementVos) {
        if (advertisementVos == null || advertisementVos.isEmpty()) {
            return null;
        }
        Map<Integer, List<AdvertisementVo>> positonAdMap = new HashMap<>();
        for (AdvertisementVo advertisementVo : advertisementVos) {
            Integer positionId = advertisementVo.getPositionId();
            if (positonAdMap.get(positionId) == null) {
                positonAdMap.put(positionId, new ArrayList<AdvertisementVo>());
            }
            positonAdMap.get(positionId).add(advertisementVo);
        }

        return positonAdMap;
    }
}
