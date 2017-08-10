package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.CmsIndexPromotionService;
import com.chinaredstar.cms.api.vo.index.IndexPromotionGoodsVo;
import com.chinaredstar.cms.api.vo.index.IndexPromotionQueryVo;
import com.chinaredstar.cms.api.vo.index.IndexPromotionVo;
import com.chinaredstar.cms.mapper.CmsIndexPromotionMapper;
import com.chinaredstar.cms.mapper.TbCmsIndexPromotionMapper;
import com.chinaredstar.mmc.constant.ItempromotionType;
import com.chinaredstar.mmc.req.client.ItemPromotionsByActivityReq;
import com.chinaredstar.mmc.resp.MmcResult;
import com.chinaredstar.mmc.resp.QueryResultInfo;
import com.chinaredstar.mmc.resp.client.ItemPromotionPriceListResp;
import com.chinaredstar.mmc.service.client.IItemPromotionClientService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by yixin.sun on 2017/3/26.
 */
@Service("cmsIndexPromotionService")
public class CmsIndexPromotionServiceImpl implements CmsIndexPromotionService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CmsIndexPromotionServiceImpl.class);

    @Autowired
    CmsIndexPromotionMapper indexPromotionMapper;

    @Autowired
    IItemPromotionClientService itemPromotionClientService;

    @Autowired
    private TbCmsIndexPromotionMapper cmsIndexPromotionMapper;

    @Override
    public ServiceResult<IndexPromotionVo> getOngoingPromotion(IndexPromotionQueryVo queryVo) {
        ServiceResult<IndexPromotionVo> serviceResult = new ServiceResult<>(false);
        if (queryVo == null) {
            queryVo = new IndexPromotionQueryVo();
        }
        if (queryVo.getCurrentTime() == null) {
            queryVo.setCurrentTime(new Date());
        }
        try {
            IndexPromotionVo indexPromotionVo = cmsIndexPromotionMapper.findOngoingPromotion(queryVo);
            if (indexPromotionVo == null) {
                indexPromotionVo = cmsIndexPromotionMapper.findCurrentLatestPromotion(queryVo);
            }
            if (indexPromotionVo == null) {
                serviceResult.setMsg("限时购促销信息不存在");
            } else{
                indexPromotionVo.setCurrentTime(new Date());
                processIndexPromotionGoods0(indexPromotionVo);
                serviceResult.setSuccess(true);
                serviceResult.setData(indexPromotionVo);
            }
        } catch (Exception e) {
            LOGGER.error("查询进行中的促销出错：", e);
            serviceResult.setMsg("查询进行中的促销出错");
        }
        return serviceResult;
    }

    private void processIndexPromotionGoods0(IndexPromotionVo indexPromotionVo) {
        if (indexPromotionVo == null) {
            return;
        }
        List<IndexPromotionGoodsVo> goodsList = indexPromotionVo.getGoodsList();
        List<String> skuList = new ArrayList<>();
        for (IndexPromotionGoodsVo goodsVo : goodsList) {
            skuList.add(goodsVo.getSku());
        }

//        MmcResult<List<ItemPromotionPriceListResp>> mmcResult = itemPromotionClientService.batchDisplayPromotionPrice(skuList, null);
        MmcResult<List<ItemPromotionPriceListResp>> mmcResult = itemPromotionClientService.getPromotionPrice((byte)29, Integer.parseInt(indexPromotionVo.getPromotionId()), skuList);
        if (!mmcResult.isSuccess() || mmcResult.getDataMap() == null) {
            indexPromotionVo.setGoodsList(null);
            return;
        }
        List<ItemPromotionPriceListResp> promotionPriceListResps = mmcResult.getDataMap();
        Map<String, ItemPromotionPriceListResp> resultMap = new HashMap<>();
        for (ItemPromotionPriceListResp itemPromotionPriceListResp : promotionPriceListResps) {
            resultMap.put(itemPromotionPriceListResp.getSkuId(), itemPromotionPriceListResp);
        }
        Iterator<IndexPromotionGoodsVo> iterator = goodsList.iterator();
        while(iterator.hasNext()){
            IndexPromotionGoodsVo goodsVo = iterator.next();
            ItemPromotionPriceListResp record = resultMap.get(goodsVo.getSku());
            // 如果是促销商品则返回, 否则不返回该商品
            if(record.getItemPromotion()) {
                goodsVo.setId(record.getItemPromotionId());
                goodsVo.setSku(record.getSkuId());
                goodsVo.setSkuStock(record.getSkuStock());
                goodsVo.setPromotionStock(record.getRemainingStock());
                goodsVo.setPrice(record.getSkuPrice());
                goodsVo.setPromotionPrice(record.getSkuPromotionPrice());
            }else{
                iterator.remove();
            }
        }
    }

    @Override
    public ServiceResult deleteByPromotionId(String promotionId) {
        ServiceResult serviceResult = new ServiceResult(false);
        if (StringUtils.isBlank(promotionId)) {
            serviceResult.setMsg("promotionId不能为空");
            return serviceResult;
        }
        try {
            cmsIndexPromotionMapper.deleteByPromotionId(promotionId);
            serviceResult.setSuccess(true);
        } catch (Exception e) {
            LOGGER.error("通过促销id删除数据出错:", e);
            serviceResult.setMsg("通过促销id删除数据出错");
        }
        return serviceResult;
    }

    private void processIndexPromotionGoods(IndexPromotionVo indexPromotionVo) {
        if (indexPromotionVo == null) {
            return;
        }
        List<IndexPromotionGoodsVo> goodList = new ArrayList<>();
        ItemPromotionsByActivityReq req = new ItemPromotionsByActivityReq(Integer.valueOf(indexPromotionVo.getPromotionId()));
        req.setItemPromotionTypeId(ItempromotionType.ITEMPROMOTION_LIMITED_PURCHASE.getValue());
        req.setPageNo(1);
        req.setPageSize(12);
        MmcResult<QueryResultInfo<ItemPromotionPriceListResp>> resp = itemPromotionClientService.getItemPromotionsByActivityId(req);
//        MmcResult<QueryResultInfo<ItemPromotionsByActivityResp>> resp = itemPromotionClientService.getItemPromotionsByActivityId(req);
        if (resp != null && resp.isSuccess()) {
            QueryResultInfo<ItemPromotionPriceListResp> dataMap = resp.getDataMap();
            List<ItemPromotionPriceListResp> records = dataMap.getRecords();
            for (ItemPromotionPriceListResp record : records) {
                IndexPromotionGoodsVo indexPromotionGoodsVo = new IndexPromotionGoodsVo();
                indexPromotionGoodsVo.setId(record.getItemPromotionId());
                indexPromotionGoodsVo.setSku(record.getSkuId());
                indexPromotionGoodsVo.setCover(record.getSkuImgUrl());
                indexPromotionGoodsVo.setName(record.getSkuName());
                indexPromotionGoodsVo.setSkuStock(record.getSkuStock());
                indexPromotionGoodsVo.setPromotionStock(record.getRemainingStock());
                indexPromotionGoodsVo.setPrice(record.getSkuPrice());
                indexPromotionGoodsVo.setPromotionPrice(record.getSkuPromotionPrice());
                goodList.add(indexPromotionGoodsVo);
            }
        }
        indexPromotionVo.setGoodsList(goodList);
    }
}
