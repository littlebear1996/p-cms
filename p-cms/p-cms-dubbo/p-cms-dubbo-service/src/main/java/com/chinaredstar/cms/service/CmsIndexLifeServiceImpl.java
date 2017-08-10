package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.CmsIndexLifeService;
import com.chinaredstar.cms.api.vo.index.IndexLifeGoodVo;
import com.chinaredstar.cms.api.vo.index.IndexLifeQueryVo;
import com.chinaredstar.cms.api.vo.index.IndexLifeVo;
import com.chinaredstar.cms.mapper.CmsIndexLifeMapper;
import com.redstar.digital.open.service.ProductShopGoodsService;
import com.redstar.digital.open.vo.ProductShopGoodsRelation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by yixin.sun on 2017/3/26.
 */
@Service("cmsIndexLifeService")
public class CmsIndexLifeServiceImpl implements CmsIndexLifeService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CmsIndexLifeServiceImpl.class);

    @Autowired
    private CmsIndexLifeMapper indexLifeMapper;

    @Autowired
    private ProductShopGoodsService productShopGoodsService;

    @Override
    public ServiceResult<IndexLifeVo> findById(Integer id) {
        ServiceResult<IndexLifeVo> serviceResult = new ServiceResult<>(false);
        if (id == null) {
            serviceResult.setMsg("id不能为空");
            return serviceResult;
        }
        try {
            IndexLifeVo indexLifeVo = indexLifeMapper.findById(id);
            getGoodPrice(indexLifeVo);
            serviceResult.setSuccess(true);
            serviceResult.setData(indexLifeVo);
        } catch (Exception e) {
            LOGGER.error("通过id查询生活家数据出错:", e);
            serviceResult.setMsg("通过id查询生活家数据出错");
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<IndexLifeVo>> findByIdList(List<Integer> idList) {
        ServiceResult<List<IndexLifeVo>> serviceResult = new ServiceResult<>(false);
        if (CollectionUtils.isEmpty(idList)) {
            serviceResult.setMsg("id集合不能为空");
            return serviceResult;
        }
        try {
            List<IndexLifeVo> indexLifeVos = indexLifeMapper.findByIdList(idList);
            serviceResult.setSuccess(true);
            serviceResult.setData(indexLifeVos);
        } catch (Exception e) {
            LOGGER.error("通过id集合查询生活家集合出错:", e);
            serviceResult.setMsg("通过id集合查询生活家集合出错");
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<IndexLifeVo>> findPageList(IndexLifeQueryVo queryVo) {
        ServiceResult<List<IndexLifeVo>> serviceResult = new ServiceResult<>(false);
        if (queryVo == null) {
            serviceResult.setMsg("参数不能为空");
            return serviceResult;
        }
        try {
            List<IndexLifeVo> indexLifeVos = indexLifeMapper.findPageList(queryVo);
            getGoodPrice(indexLifeVos);
            serviceResult.setSuccess(true);
            serviceResult.setData(indexLifeVos);
        } catch (Exception e) {
            LOGGER.error("查询列表数据出错:", e);
            serviceResult.setMsg("查询列表数据出错");
        }
        return serviceResult;
    }

    private void getGoodPrice(IndexLifeVo indexLifeVos) {
        List<IndexLifeVo> list = new ArrayList<>();
        list.add(indexLifeVos);
        getGoodPrice(list);
    }

    /**
     * 调用商品接口获得商品价格
     *
     * @param indexLifeVos
     */
    private void getGoodPrice(List<IndexLifeVo> indexLifeVos) {
        if (indexLifeVos != null) {
            List<IndexLifeGoodVo> indexLifeGoodVos = new ArrayList<>();
            for (IndexLifeVo indexLifeVo : indexLifeVos) {
                indexLifeGoodVos.addAll(indexLifeVo.getGoodList());
            }
            if (indexLifeGoodVos != null && indexLifeGoodVos.size() != 0) {
                List<String> goodIds = new ArrayList<>();
                for (IndexLifeGoodVo goodVo : indexLifeGoodVos) {
                    if (StringUtils.isNotBlank(goodVo.getGoodId())) {
                        goodIds.add(goodVo.getGoodId());
                    }
                }
                com.redstar.digital.open.vo.ServiceResult<List<ProductShopGoodsRelation>> goodServiceResult = productShopGoodsService.getOnShopGoodsInfosBySkus(goodIds);
                Map<String, BigDecimal> goodPriceMap = new HashMap<>();
                List<ProductShopGoodsRelation> goodsRelationList = null;

                if (goodServiceResult != null && goodServiceResult.isSuccess()) {
                    goodsRelationList = goodServiceResult.getData();
                    if (goodsRelationList != null && goodsRelationList.size() != 0) {
                        for (ProductShopGoodsRelation productShopGoodsRelation : goodsRelationList) {
                            goodPriceMap.put(productShopGoodsRelation.getPdtSku(), productShopGoodsRelation.getSalePrice());
                        }
                    }
                }
                for (IndexLifeGoodVo goodVo : indexLifeGoodVos) {
                    if (StringUtils.isNotBlank(goodVo.getGoodId())) {
                        goodVo.setPrice(goodPriceMap.get(goodVo.getGoodId()));
                    }
                }

                removeNotExistGoods(indexLifeVos, goodsRelationList);
            }

        }
    }

    /**
     * 在调用商品接口返回商品信息中, 如果配置的商品不在返回的结果中则把该配置的商品删除, 不返回前端(说明配置的商品已下架或其它原因从龙果中拿不到信息)
     *
     * @param indexLifeVos
     * @param goodsRelationList
     */
    private void removeNotExistGoods(List<IndexLifeVo> indexLifeVos, List<ProductShopGoodsRelation> goodsRelationList) {
        Set<String> goodSkus = new HashSet<>();
        if (goodsRelationList != null) {
            for (ProductShopGoodsRelation goodsRelation : goodsRelationList) {
                goodSkus.add(goodsRelation.getPdtSku());
            }
        }
        for (IndexLifeVo indexLifeVo : indexLifeVos) {
            List<IndexLifeGoodVo> goodList = indexLifeVo.getGoodList();
            if (CollectionUtils.isEmpty(goodList)) continue;
            Iterator<IndexLifeGoodVo> iterator = goodList.iterator();
            while (iterator.hasNext()) {
                IndexLifeGoodVo goodVo = iterator.next();
                if (!goodSkus.contains(goodVo.getGoodId())) {
                    iterator.remove();
                }
            }
        }
    }
}
