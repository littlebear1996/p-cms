package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.CmsIndexAdvertisementService;
import com.chinaredstar.cms.api.vo.index.IndexAdvertisementQueryVo;
import com.chinaredstar.cms.api.vo.index.IndexAdvertisementVo;
import com.chinaredstar.cms.mapper.CmsIndexAdvertisementMapper;
import com.chinaredstar.ilvb.api.bean.ZbRoom;
import com.chinaredstar.ilvb.api.service.IZbRoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by yixin.sun on 2017/3/29.
 */
@Service("cmsIndexAdvertisementService")
public class CmsIndexAdvertisementServiceImpl implements CmsIndexAdvertisementService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CmsIndexAdvertisementServiceImpl.class);

    @Autowired
    private IZbRoomService zbRoomService;

    @Autowired
    private CmsIndexAdvertisementMapper indexAdvertisementMapper;

    @Override
    public ServiceResult<List<IndexAdvertisementVo>> listADsByPositionCodeAndCityId(IndexAdvertisementQueryVo queryVo) {
        ServiceResult<List<IndexAdvertisementVo>> serviceResult = new ServiceResult<>(false);
        if (queryVo == null) {
            LOGGER.info("queryVo must not be null");
            serviceResult.setMsg("queryVo must not be null");
            return serviceResult;
        }
        try {
            List<IndexAdvertisementVo> indexAdvertisementVos = indexAdvertisementMapper.listADsByPositionCodeAndCityId(queryVo);
            processIndexAdvertisementVos(indexAdvertisementVos);
            serviceResult.setSuccess(true);
            serviceResult.setData(indexAdvertisementVos);
        } catch (Exception e) {
            LOGGER.error("通过广告位置编码获取广告失败, {}", e);
            e.printStackTrace();
            serviceResult.setSuccess(false);
            serviceResult.setMsg(e.toString());
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<IndexAdvertisementVo> getAdvertisementDetailById(Integer id) {
        ServiceResult<IndexAdvertisementVo> serviceResult = new ServiceResult<>(false);
        if(null == id){
            LOGGER.info("id 不可为空");
            serviceResult.setMsg("id 不可为空");
            return serviceResult;
        }
        try{
            IndexAdvertisementVo advertisementDetailById = indexAdvertisementMapper.getAdvertisementDetailById(id);
            serviceResult.setSuccess(true);
            serviceResult.setData(advertisementDetailById);
        } catch (Exception e) {
            LOGGER.error("通过id获取广告详情异常,id:{}, exception: {}", id ,e);
            serviceResult.setMsg(e.toString());
        }
        return serviceResult;
    }

    private void processIndexAdvertisementVos(List<IndexAdvertisementVo> indexAdvertisementVos) {
        Map<String, Set<IndexAdvertisementVo>> zbMap = new HashMap<String, Set<IndexAdvertisementVo>>();//存储直播外链
        List<Long> roomIds = new ArrayList<>();

        if (indexAdvertisementVos != null && !indexAdvertisementVos.isEmpty()) {

            for (IndexAdvertisementVo advertisementVo : indexAdvertisementVos) {
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
                        Set<IndexAdvertisementVo> result = zbMap.get(zbRoom.getId().toString());
                        if (result != null && !result.isEmpty()) {
                            for (IndexAdvertisementVo advertisementVo : result) {
                                advertisementVo.setZbRoom(zbRoom);
                            }
                        }
                    }
                }
            }
        }
    }
}
