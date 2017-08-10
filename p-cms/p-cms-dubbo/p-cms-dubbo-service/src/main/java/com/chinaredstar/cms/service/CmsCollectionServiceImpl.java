package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.Page;
import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.model.CmsCollection;
import com.chinaredstar.cms.api.model.CmsCollectionData;
import com.chinaredstar.cms.api.service.CmsCollectionService;
import com.chinaredstar.cms.api.vo.collection.CmsCollectionQueryVo;
import com.chinaredstar.cms.api.vo.collection.CmsCollectionSalesVo;
import com.chinaredstar.cms.api.vo.collection.CmsCollectionTargetVo;
import com.chinaredstar.cms.api.vo.collection.CmsCollectionVo;
import com.chinaredstar.cms.mapper.CmsCollectionDataMapper;
import com.chinaredstar.cms.mapper.CmsCollectionMapper;
import com.chinaredstar.perseus.utils.JsonUtil;
import com.codahale.metrics.annotation.Timed;
import com.redstar.digital.open.service.OmsShopInfoService;
import com.redstar.digital.open.vo.ShopperExtVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 合辑服务实现类
 */
@Service("collectionService")
@Timed
public class CmsCollectionServiceImpl implements CmsCollectionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CmsCollectionServiceImpl.class);

    @Autowired
    private CmsCollectionMapper cmsCollectionMapper;

    @Autowired
    private CmsCollectionDataMapper cmsCollectionDataMapper;

    @Autowired
    private OmsShopInfoService omsShopInfoService;

    @Override
    public ServiceResult<List<CmsCollectionVo>> findCollectionByType(CmsCollectionQueryVo queryVo) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("queryVo : {}", queryVo.toString());
        }

        ServiceResult<List<CmsCollectionVo>> serviceResult = new ServiceResult<>(Boolean.TRUE);
        if (queryVo == null) {
            LOGGER.error("queryVo must be not null");
            serviceResult.setSuccess(Boolean.FALSE);
            serviceResult.setMsg("queryVo must be not null");
            return serviceResult;
        }

        try {
            // 获取合辑数据
            List<CmsCollectionVo> cmsCollectionList = cmsCollectionMapper.findCollectionByType(queryVo);
            // 通过合辑数据, 获取合辑中的元素数据
            serviceResult.setData(cmsCollectionList);
            return serviceResult;
        } catch (Exception e) {
            LOGGER.error("获取合辑信息失败. {}", e);
            serviceResult.setSuccess(Boolean.FALSE);
            serviceResult.setMsg(e.toString());
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<CmsCollectionVo>> getCmsCollectionListByIds(List<Integer> colldesignerIdList) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("colldesignerIdList : {}", colldesignerIdList.toString());
        }

        ServiceResult<List<CmsCollectionVo>> serviceResult = new ServiceResult<>(Boolean.TRUE);
        if (colldesignerIdList == null || colldesignerIdList.isEmpty()) {
            LOGGER.error("colldesignerIdList must be not null");
            serviceResult.setSuccess(Boolean.FALSE);
            serviceResult.setMsg("colldesignerIdList must be not null");
            return serviceResult;
        }

        try {
            List<CmsCollectionVo> cmsCollectionList = cmsCollectionMapper.getCollectionListByIds(colldesignerIdList);
            serviceResult.setData(cmsCollectionList);
            return serviceResult;
        } catch (Exception e) {
            LOGGER.error("获取合辑信息失败. {}", e);
            serviceResult.setSuccess(Boolean.FALSE);
            serviceResult.setMsg(e.toString());
            return serviceResult;
        }
    }

    @Override
    public ServiceResult<CmsCollectionVo> getCmsCollectionListById(Integer collectionId) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("collectionId : {}", collectionId);
        }

        ServiceResult<CmsCollectionVo> serviceResult = new ServiceResult<>(Boolean.TRUE);
        if (collectionId == null) {
            LOGGER.error("collectionId must be not null");
            serviceResult.setSuccess(Boolean.FALSE);
            serviceResult.setMsg("collectionId must be not null");
            return serviceResult;
        }

        List<Integer> idList = new ArrayList<>();
        idList.add(collectionId);

        List<CmsCollectionVo> cmsCollectionList = cmsCollectionMapper.getCollectionListByIds(idList);
        if (cmsCollectionList != null && !cmsCollectionList.isEmpty()) {
            serviceResult.setData(cmsCollectionList.get(0));
            return serviceResult;
        }

        return serviceResult;
    }

    @Override
    public ServiceResult<CmsCollectionVo> getCmsCollectionById(Integer id) {
        ServiceResult<CmsCollectionVo> serviceResult = new ServiceResult<>(true);
        if (id == null) {
            LOGGER.error("id must be not null");
            serviceResult.setSuccess(false);
            serviceResult.setMsg("id must be not null");
            return serviceResult;
        }

        try {
            CmsCollectionVo cmsCollectionVo = cmsCollectionMapper.getCollectionVoById(id);
            serviceResult.setData(cmsCollectionVo);
            return serviceResult;
        } catch (Exception e) {
            LOGGER.error("获取合辑数据失败. Casue by : {}", e.getMessage());
            serviceResult.setSuccess(false);
            serviceResult.setMsg("获取合辑数据失败");
            return serviceResult;
        }
    }

    @Override
    public ServiceResult<List<CmsCollectionData>> getCmsCollectionDataListById(Integer collectionId, Page page) {
        ServiceResult<List<CmsCollectionData>> serviceResult = new ServiceResult<>(true);
        if (collectionId == null) {
            LOGGER.error("collectionId must be not null");
            serviceResult.setSuccess(false);
            serviceResult.setMsg("collectionId must be not null");
            return serviceResult;
        }
        try {
            List<CmsCollectionData> cmsCollectionDataList = cmsCollectionDataMapper.getCmsCollectionDataListBycollId(collectionId, page);
            serviceResult.setData(cmsCollectionDataList);
            return serviceResult;
        } catch (Exception e) {
            LOGGER.error("获取合辑数据失败. Casue by : {}", e.getMessage());
            serviceResult.setSuccess(false);
            serviceResult.setMsg("获取合辑数据失败");
            return serviceResult;
        }
    }

    @Override
    public ServiceResult<CmsCollectionVo> getCmsSalesCollectionByMarketId(Integer marketId) {
        ServiceResult<CmsCollectionVo> serviceResult = new ServiceResult<>(true);
        if (marketId == null) {
            LOGGER.error("marketId must be not null");
            serviceResult.setSuccess(false);
            serviceResult.setMsg("无效参数. marketId is null");
            return serviceResult;
        }


        try {
            CmsCollectionQueryVo queryVo = new CmsCollectionQueryVo();
            queryVo.setTop(true);
            queryVo.setType(CmsCollection.TYPE_MARKET);
            queryVo.setSubType(CmsCollection.SUBTYPE_MARKET_SALES);
            queryVo.setMarketId(String.valueOf(marketId));
            List<CmsCollectionVo> cmsCollectionList = cmsCollectionMapper.findCollectionByType(queryVo);
            if (cmsCollectionList == null || cmsCollectionList.isEmpty()) {
                serviceResult.setData(null);
                return serviceResult;
            }

            CmsCollectionVo cmsCollectionVo = cmsCollectionList.get(0);

            List<CmsCollectionData> collectionDataList = cmsCollectionVo.getCollectionDataList();
            if (collectionDataList == null || collectionDataList.isEmpty()) {
                serviceResult.setData(cmsCollectionVo);
                return serviceResult;
            }

            List<Integer> idList = new ArrayList<>();

            for (CmsCollectionData cmsCollectionData : collectionDataList) {
                if (cmsCollectionData == null) continue;
                idList.add(Integer.valueOf(cmsCollectionData.getTargetId()));
            }

            cmsCollectionVo.setCmsCollectionTargetVoList(processSalesCollectionData(idList));
            serviceResult.setData(cmsCollectionVo);
            return serviceResult;
        } catch (Exception e) {
            LOGGER.error("获取导购员合辑数据失败. Casue by : {}", e.getMessage());
            serviceResult.setSuccess(false);
            serviceResult.setMsg("获取导购员合辑数据失败");
            return serviceResult;
        }
    }


    /**
     * 封装导购员合辑数据
     *
     * @param idList
     * @return
     */
    private List<CmsCollectionTargetVo> processSalesCollectionData(List<Integer> idList) {
        if (idList == null || idList.isEmpty()) {
            return null;
        }

        List<Long> ids = new ArrayList<>();

        for (Integer id : idList) {
            if (id == null) {
                continue;
            }
            ids.add(Long.valueOf(id.toString()));
        }

        com.redstar.digital.open.vo.ServiceResult<List<ShopperExtVo>> salesServiceResult = omsShopInfoService.getOnShopperInfosByShoperIds(ids);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("调用导购员信息接口 --> Request : {}, Response : {}", idList, JsonUtil.toJson(salesServiceResult, false));
        }

        if (!salesServiceResult.isSuccess()) {
            LOGGER.info("调用导购员信息接口失败. Cause by : {}", salesServiceResult.getMsg());
            return null;
        }

        List<CmsCollectionTargetVo> salesList = new ArrayList<>();

        for (ShopperExtVo shopperExtVo : salesServiceResult.getData()) {
            if (shopperExtVo == null) {
                continue;
            }
            CmsCollectionSalesVo salesVo = new CmsCollectionSalesVo();
            BeanUtils.copyProperties(shopperExtVo, salesVo);
            salesList.add(salesVo);
        }
        return salesList;
    }

}
