package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.CmsIndexBrandService;
import com.chinaredstar.cms.api.vo.index.IndexBrandGoodVo;
import com.chinaredstar.cms.api.vo.index.IndexBrandQueryVo;
import com.chinaredstar.cms.api.vo.index.IndexBrandVo;
import com.chinaredstar.cms.mapper.CmsIndexBrandMapper;
import com.chinaredstar.perseus.utils.JsonUtil;
import com.google.common.collect.Lists;
import com.redstar.digital.open.service.ProductShopGoodsService;
import com.redstar.digital.open.vo.ProductShopGoodsRelation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by pengfei.wang on 2017/3/24.
 */
@Service("cmsIndexBrandService")
public class CmsIndexBrandServiceImpl implements CmsIndexBrandService {

    private final Logger logger = LoggerFactory.getLogger(CmsIndexBrandServiceImpl.class);

    @Autowired
    private CmsIndexBrandMapper cmsIndexBrandMapper;
    @Autowired
    private ProductShopGoodsService productShopGoodsService;

    @Override
    public ServiceResult<List<IndexBrandVo>> findPageList(IndexBrandQueryVo queryVo) {
        ServiceResult<List<IndexBrandVo>> result = new ServiceResult<>(true);
        if (queryVo == null) {
            queryVo = new IndexBrandQueryVo();
        }
        try {
            List<IndexBrandVo> indexBrandVos = cmsIndexBrandMapper.findPageList(queryVo);
            if (CollectionUtils.isNotEmpty(indexBrandVos)) {
                getGoodPrice(indexBrandVos);
            }
            result.setData(indexBrandVos);
        } catch (Exception e) {
            logger.error("查询大牌列表异常, params:{}", JsonUtil.toJson(queryVo, false), e);
            result.setSuccess(false);
            result.setMsg("查询大牌列表异常");
        }
        return result;
    }

    @Override
    public ServiceResult<IndexBrandVo> findById(Integer id) {
        ServiceResult<IndexBrandVo> result = new ServiceResult<>(false);
        if (id == null) {
            result.setMsg("大牌id不能为空");
            return result;
        }
        try {
            IndexBrandVo indexBrandVo = cmsIndexBrandMapper.findById(id);
            if (indexBrandVo != null) {
                getGoodPrice(Lists.newArrayList(indexBrandVo));
            }
            result.setSuccess(true);
            result.setData(indexBrandVo);
        } catch (Exception e) {
            logger.error("查询大牌详情异常, params:{}", id, e);
            result.setMsg("查询大牌异常");
        }
        return result;
    }

    @Override
    public ServiceResult<List<IndexBrandVo>> findByIds(List<Integer> ids) {
        ServiceResult<List<IndexBrandVo>> result = new ServiceResult<>(false);
        if (CollectionUtils.isEmpty(ids)) {
            result.setMsg("大牌id集合不能为空");
            return result;
        }
        try {
            List<IndexBrandVo> brandVos = cmsIndexBrandMapper.findByIds(ids);
            if (CollectionUtils.isNotEmpty(brandVos)) {
                Map<Integer, IndexBrandVo> brandVoMap = new HashMap<>();
                for (IndexBrandVo brandVo : brandVos) {
                    brandVoMap.put(brandVo.getId(), brandVo);
                }
                List<IndexBrandVo> orderedList = new ArrayList<>(brandVos.size());
                for (Integer id : ids) {
                    IndexBrandVo brandVo = brandVoMap.get(id);
                    if (brandVo != null) {
                        orderedList.add(brandVo);
                    }
                }
                brandVos = orderedList;
            }
            result.setSuccess(true);
            result.setData(brandVos);
        } catch (Exception e) {
            logger.error("根据id集合查询大牌列表异常, params:{}", ids, e);
            result.setMsg("查询磊大牌列表异常");
        }
        return result;
    }

    /**
     * 根据品牌中的商品调用商品接口获取商品价格
     *
     * @param brandVos
     */
    private void getGoodPrice(List<IndexBrandVo> brandVos) {
        Map<String, List<IndexBrandGoodVo>> goodVoMap = new HashMap<>(); // Map<商品id, List<大牌商品>> 防止同一个商品配置多个
        for (IndexBrandVo brandVo : brandVos) {
            List<IndexBrandGoodVo> brandGoods = brandVo.getGoods();
            if (CollectionUtils.isNotEmpty(brandGoods)) {
                for (IndexBrandGoodVo goodVo : brandGoods) {
                    List<IndexBrandGoodVo> goodVos = goodVoMap.get(goodVo.getGoodId());
                    if (goodVos == null) {
                        goodVos = new ArrayList<>();
                        goodVoMap.put(goodVo.getGoodId(), goodVos);
                    }
                    goodVos.add(goodVo);
                }
            }
        }
        if (MapUtils.isNotEmpty(goodVoMap)) {
            com.redstar.digital.open.vo.ServiceResult<List<ProductShopGoodsRelation>> serviceResult =
                    productShopGoodsService.getOnShopGoodsInfosBySkus(Lists.newArrayList(goodVoMap.keySet()));
            List<ProductShopGoodsRelation> data = null;
            if (serviceResult.isSuccess()) {
                data = serviceResult.getData();
                if (CollectionUtils.isNotEmpty(data)) {
                    for (ProductShopGoodsRelation productShopGoodsRelation : data) {
                        List<IndexBrandGoodVo> goodVos = goodVoMap.get(productShopGoodsRelation.getPdtSku());
                        for (IndexBrandGoodVo goodVo : goodVos) {
                            goodVo.setPrice(productShopGoodsRelation.getSalePrice());
                        }
                    }
                } else {
                    logger.error("通过dubbo调用查询商品接口(productShopGoodsService.getOnShopGoodsInfosBySkus(...))返回数据为空, params:{}, serviceResult:{}",
                            JsonUtil.toJson(goodVoMap.keySet(), false), JsonUtil.toJson(serviceResult, false));
                }
            } else {
                logger.error("通过dubbo调用查询商品接口(productShopGoodsService.getOnShopGoodsInfosBySkus(...))异常, params:{}, serviceResult:{}",
                        JsonUtil.toJson(goodVoMap.keySet(), false), JsonUtil.toJson(serviceResult, false));
            }

            removeNotExistGoods(brandVos, data);
        }
    }

    /**
     * 在调用商品接口返回商品信息中, 如果配置的商品不在返回的结果中则把该配置的商品删除, 不返回前端(说明配置的商品已下架或其它原因从龙果中拿不到信息)
     *
     * @param brandVos
     * @param data
     */
    private void removeNotExistGoods(List<IndexBrandVo> brandVos, List<ProductShopGoodsRelation> data) {
        Set<String> goodSkus = new HashSet<>();
        if (data != null) {
            for (ProductShopGoodsRelation productShopGoodsRelation : data) {
                goodSkus.add(productShopGoodsRelation.getPdtSku());
            }
        }
        for (IndexBrandVo brandVo : brandVos) {
            List<IndexBrandGoodVo> goods = brandVo.getGoods();
            if (CollectionUtils.isEmpty(goods)) continue;
            Iterator<IndexBrandGoodVo> iterator = goods.iterator();
            while (iterator.hasNext()) {
                IndexBrandGoodVo goodVo = iterator.next();
                if (!goodSkus.contains(goodVo.getGoodId())) {
                    iterator.remove();
                }
            }
        }
    }
}
