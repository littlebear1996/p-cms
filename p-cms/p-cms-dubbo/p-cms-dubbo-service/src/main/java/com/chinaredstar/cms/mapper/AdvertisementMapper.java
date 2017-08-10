package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.Advertisement;
import com.chinaredstar.cms.api.vo.advertisement.AdvertisementQueryVo;
import com.chinaredstar.cms.api.vo.advertisement.AdvertisementVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdvertisementMapper extends BaseMapper<Advertisement> {
    List<AdvertisementVo> listADsByPositionCode(AdvertisementQueryVo queryVo);

    List<AdvertisementVo> getMarketPromotionAdvert();

    AdvertisementVo getAdvertByFirst(AdvertisementQueryVo queryVo);

    List<AdvertisementVo> getOnlineAdvertisement(AdvertisementQueryVo queryVo);

    Integer batchOfflineByAdList(@Param("list") List<Integer> offlineIdList);

    /**
     * 根据广告ID集合查询广告信息
     * @param idList
     * @return
     */
    List<AdvertisementVo> getAdvertisementListByIds(@Param("list") List<Integer> idList);
}