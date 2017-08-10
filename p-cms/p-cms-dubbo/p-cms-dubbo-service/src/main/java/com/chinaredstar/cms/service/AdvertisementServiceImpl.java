package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.AdvertisementService;
import com.chinaredstar.cms.api.vo.advertisement.AdvertisementQueryVo;
import com.chinaredstar.cms.api.vo.advertisement.AdvertisementVo;
import com.chinaredstar.cms.mapper.AdvertisementMapper;
import com.chinaredstar.ilvb.api.bean.ZbRoom;
import com.chinaredstar.ilvb.api.service.IZbRoomService;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by sunny on 16-8-16.
 */
@Service("advertisementService")
@Timed
public class AdvertisementServiceImpl implements AdvertisementService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdvertisementServiceImpl.class);

    @Autowired
    private AdvertisementMapper advertisementMapper;

    @Autowired
    private IZbRoomService zbRoomService;
    private List<AdvertisementVo> advertisements;

    @Override
    public ServiceResult<List<AdvertisementVo>> listADsByPositionCode(AdvertisementQueryVo queryVo) {
        long start = System.currentTimeMillis();
        LOGGER.info("listADsByPositionCode请求参数：" + queryVo);

        ServiceResult<List<AdvertisementVo>> serviceResult = new ServiceResult<List<AdvertisementVo>>();
        if (queryVo == null) {
            LOGGER.info("queryVo must not be null");
            serviceResult.setSuccess(false);
            serviceResult.setMsg("queryVo must not be null");
            return serviceResult;
        }
        try {
            List<AdvertisementVo> advertisements = advertisementMapper.listADsByPositionCode(queryVo);
            serviceResult.setSuccess(true);
            serviceResult.setData(advertisements);
            Map<String, Set<AdvertisementVo>> zbMap = new HashMap<String, Set<AdvertisementVo>>();//存储直播外链
            List<Long> roomIds = new ArrayList<>();

            if (advertisements != null && !advertisements.isEmpty()) {

                for (AdvertisementVo advertisementVo : advertisements) {
                    if (advertisementVo.getLinkType() != null && advertisementVo.getLinkType().equals("7")) {
                        roomIds.add(Long.valueOf(advertisementVo.getLinkUrl()));
                        if (zbMap.get(advertisementVo.getLinkUrl()) == null) {
                            zbMap.put(advertisementVo.getLinkUrl(), new HashSet());
                        }
                        zbMap.get(advertisementVo.getLinkUrl()).add(advertisementVo);

                    }
                }
                if (!roomIds.isEmpty()) {
                    Long[] idArray = (Long[]) roomIds.toArray(new Long[roomIds.size()]);
                    List<ZbRoom> zbRooms = zbRoomService.selectById(idArray);
                    if (zbRooms != null && !zbRooms.isEmpty()) {
                        for (ZbRoom zbRoom : zbRooms) {
                            Set<AdvertisementVo> result = zbMap.get(zbRoom.getId().toString());
                            if (result != null && !result.isEmpty()) {
                                for (AdvertisementVo advertisementVo : result) {
                                    advertisementVo.setZbRoom(zbRoom);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("通过广告位置编码获取广告失败, {}", e);
            e.printStackTrace();
            serviceResult.setSuccess(false);
            serviceResult.setMsg(e.toString());
        }

        long end = System.currentTimeMillis();
        long time = end - start;
        LOGGER.info("listADsByPositionCode返回数据：" + serviceResult + ",耗时：" + (time / 1000) + "秒" + (time % 1000) + "毫秒");
        return serviceResult;
    }

    @Override
    public ServiceResult<List<AdvertisementVo>> getMarketPromotionAdvert() {

        ServiceResult<List<AdvertisementVo>> serviceResult = new ServiceResult<List<AdvertisementVo>>();
        try {
            List<AdvertisementVo> advertisements = advertisementMapper.getMarketPromotionAdvert();
            serviceResult.setSuccess(true);
            serviceResult.setData(advertisements);
        } catch (Exception e) {
            LOGGER.error("通过广告位置编码获取广告失败, {}", e);
            e.printStackTrace();
            serviceResult.setSuccess(false);
            serviceResult.setMsg(e.toString());
        }

        return serviceResult;
    }

    @Override
    public ServiceResult<AdvertisementVo> getAdvertByFirst(AdvertisementQueryVo queryVo) {
        LOGGER.info("listADsByPositionCode请求参数：" + queryVo);

        ServiceResult<AdvertisementVo> serviceResult = new ServiceResult<>();
        if (queryVo == null) {
            LOGGER.info("queryVo must not be null");
            serviceResult.setSuccess(false);
            serviceResult.setMsg("queryVo must not be null");
            return serviceResult;
        }
        try {
            AdvertisementVo advertisement = advertisementMapper.getAdvertByFirst(queryVo);
            serviceResult.setSuccess(true);
            serviceResult.setData(advertisement);
            return serviceResult;
        } catch (Exception e) {
            LOGGER.error("通过广告位置编码获取广告失败, {}", e);
            e.printStackTrace();
            serviceResult.setSuccess(false);
            serviceResult.setMsg(e.toString());
            return serviceResult;
        }
    }

}
