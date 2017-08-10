package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.OutlinkService;
import com.chinaredstar.cms.api.vo.outlink.OutlinkVo;
import com.chinaredstar.cms.mapper.OutlinksMapper;
import com.chinaredstar.ilvb.api.bean.ZbRoom;
import com.chinaredstar.ilvb.api.service.IZbRoomService;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by sunny on 16-8-26.
 */
@Service("outlinkService")
@Timed
public class OutlinkServiceImpl implements OutlinkService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OutlinkServiceImpl.class);
    @Autowired
    private OutlinksMapper outlinksMapper;

    @Autowired
    private IZbRoomService zbRoomService;
    @Override
    public ServiceResult<List<OutlinkVo>> getOutLinkByIds(List<Integer> idList) {
        long start = System.currentTimeMillis();
        LOGGER.info("getOutLinkByIds请求参数：" + idList);
        ServiceResult<List<OutlinkVo>> serviceResult = new ServiceResult<List<OutlinkVo>>();
        if (idList == null || idList.isEmpty()) {
            LOGGER.error("idList must not be null");
            serviceResult.setSuccess(false);
            serviceResult.setMsg("idList must not be null");
            return serviceResult;
        }
        try {
            List<OutlinkVo> outlinkVos = outlinksMapper.getOutlinksListByIds(idList);
            serviceResult.setData(outlinkVos);
            serviceResult.setSuccess(true);
            Map<String, Set<OutlinkVo>> zbMap = new HashMap<String, Set<OutlinkVo>>();//存储直播外链
            List<Long> roomIds = new ArrayList<>();

            if (outlinkVos != null && !outlinkVos.isEmpty()) {

                for (OutlinkVo outlinkVo : outlinkVos) {
                    if (outlinkVo.getLinkType() != null && outlinkVo.getLinkType().equals("7")) {
                        roomIds.add(Long.valueOf(outlinkVo.getOutlink()));
                        if (zbMap.get(outlinkVo.getOutlink()) == null) {
                            zbMap.put(outlinkVo.getOutlink(),new HashSet());
                        }
                        zbMap.get(outlinkVo.getOutlink()).add(outlinkVo);

                    }
                }
                if (!roomIds.isEmpty()) {
                    Long[] idArray = (Long[]) roomIds.toArray(new Long[roomIds.size()]);
                    List<ZbRoom> zbRooms = zbRoomService.selectById(idArray);
                    if (zbRooms != null && !zbRooms.isEmpty()) {
                        for (ZbRoom zbRoom : zbRooms) {
                            Set<OutlinkVo> result = zbMap.get(zbRoom.getId().toString());
                            if (result != null && !result.isEmpty()) {
                                for(OutlinkVo outlinkVo : result) {
                                    outlinkVo.setZbRoom(zbRoom);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("通过idList列表获取外链, {}", e);
            serviceResult.setSuccess(false);
            serviceResult.setMsg(e.toString());
        }
        long end = System.currentTimeMillis();
        long time = end - start;
        LOGGER.info("getOutLinkByIds返回数据：" + serviceResult + ",耗时：" + (time / 1000) + "秒" + (time % 1000) + "毫秒");
        return serviceResult;
    }
}
