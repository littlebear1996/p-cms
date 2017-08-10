package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.model.CmsIndexAdvertisement;
import com.chinaredstar.cms.api.vo.advertisement.AdvertisementQueryVo;
import com.chinaredstar.cms.api.vo.index.IndexAdvertisementQueryVo;
import com.chinaredstar.cms.api.vo.index.IndexAdvertisementVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CmsIndexAdvertisementMapper extends BaseMapper<CmsIndexAdvertisement> {

    List<IndexAdvertisementVo> listADsByPositionCodeAndCityId(IndexAdvertisementQueryVo queryVo);

    Integer batchOfflineByAdList(@Param("idList") List<Integer> offlineIdList);

    List<IndexAdvertisementVo> getOnlineAdvertisement(IndexAdvertisementQueryVo queryVo);

    IndexAdvertisementVo getAdvertisementDetailById(@Param("id") Integer id);
}