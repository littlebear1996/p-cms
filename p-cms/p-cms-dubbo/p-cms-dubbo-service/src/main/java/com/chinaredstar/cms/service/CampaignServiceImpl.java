package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.model.*;
import com.chinaredstar.cms.api.service.CampaignService;
import com.chinaredstar.cms.api.vo.campaign.CampaignBrandQueryVo;
import com.chinaredstar.cms.api.vo.campaign.CampaignMarketProductQueryVo;
import com.chinaredstar.cms.api.vo.campaign.CampaignMarketQueryVo;
import com.chinaredstar.cms.api.vo.campaign.CampaignQueryVo;
import com.chinaredstar.cms.mapper.*;
//import com.chinaredstar.p_trade_promotion.api.ICuponQueryService;
//import com.chinaredstar.p_trade_promotion.api.vo.Coupon;
import com.chinaredstar.perseus.utils.JsonUtil;
import com.codahale.metrics.annotation.Timed;
import com.redstar.digital.open.bean.OmsBrandInfo;
import com.redstar.digital.open.service.OmsBrandInfoService;
import com.redstar.digital.open.service.OmsMarketInfoService;
import com.redstar.digital.open.service.ProductShopGoodsService;
import com.redstar.digital.open.vo.OmsMarketInfoRelation;
import com.redstar.digital.open.vo.ProductShopGoodsRelation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ykk on 16/10/16.
 */
@Service("campaignService")
@Timed
public class CampaignServiceImpl implements CampaignService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AtlasServiceImpl.class);

    @Autowired
    private CmsCampaignMapper cmsCampaignMapper;

    @Autowired
    private CmsCampaignBrandMapper cmsCampaignBrandMapper;


    @Autowired
    private CmsCampaignMarketMapper cmsCampaignMarketMapper;

    @Autowired
    private OmsBrandInfoService omsBrandInfoService;


    @Autowired
    private OmsMarketInfoService omsMarketInfoService;

    @Autowired
    private CmsCampaignMarketProductMapper cmsCampaignMarketProductMapper;

    @Autowired
    private ProductShopGoodsService productShopGoodsService;

//    @Autowired
//    private ICuponQueryService cuponQueryService;

//    @Autowired
//    private CmsCampaignMarketCouponMapper cmsCampaignMarketCouponMapper;


    @Override
    public ServiceResult<CmsCampaign> getCampaign(CampaignQueryVo vo) {
        long start = System.currentTimeMillis();
        ServiceResult<CmsCampaign> serviceResult = new ServiceResult<CmsCampaign>();
        try {
            List<CmsCampaign> list = cmsCampaignMapper.find(vo);
            if (!list.isEmpty()){
                serviceResult.setData(list.get(0));
                serviceResult.setSuccess(true);
            }else{
                serviceResult.setData(null);
                serviceResult.setMsg("数据为空");
                serviceResult.setSuccess(true);
            }
        }catch (Exception e){
            LOGGER.error("查询活动信息失败, {}", e);
            serviceResult.setSuccess(false);
            serviceResult.setMsg(e.toString());
        }
        long end = System.currentTimeMillis();
        long time = end - start;
        LOGGER.info("查询活动信息成功,返回数据：" + serviceResult + ",耗时：" + (time / 1000) + "秒" + (time % 1000) + "毫秒");
        return serviceResult;
    }

    @Override
    public ServiceResult<List<CmsCampaignBrand>> getCampaignBrand(CampaignBrandQueryVo vo) {
        long start = System.currentTimeMillis();
        ServiceResult<List<CmsCampaignBrand>> serviceResult = new ServiceResult<List<CmsCampaignBrand>>();
        if (vo == null) {
            LOGGER.error("queryVo must not be null");
            serviceResult.setSuccess(false);
            serviceResult.setMsg("queryVo must not be null");
            return serviceResult;
        }
        try {
            List<CmsCampaignBrand> brandList = cmsCampaignBrandMapper.getCampaignBrand(vo);

            if (null!=brandList && !brandList.isEmpty()){
                List<Integer> brandIdList = new ArrayList<Integer>();

                for (CmsCampaignBrand campaignBrand:brandList
                        ) {
                    LOGGER.info("品牌id:" + campaignBrand.getBrandId());
                    if(!StringUtils.isEmpty(campaignBrand.getBrandId())){
                        Integer brandId = Integer.parseInt(campaignBrand.getBrandId());
                        brandIdList.add(brandId);
                    }

                }
                List<OmsBrandInfo> omsBrandInfoList = getBrandInfoWithDubbo(brandIdList);
                if (null!=omsBrandInfoList && !omsBrandInfoList.isEmpty()){
                    Map<Integer,OmsBrandInfo> map = new HashMap<Integer,OmsBrandInfo>();
                    for (OmsBrandInfo brandInfo:omsBrandInfoList
                            ) {
                        map.put(brandInfo.getId(),brandInfo);
                    }
                    for (CmsCampaignBrand campaignBrand:brandList
                            ) {
                        LOGGER.info("品牌id:" + campaignBrand.getBrandId());
                        if(!StringUtils.isEmpty(campaignBrand.getBrandId())) {
                            Integer brandId = Integer.parseInt(campaignBrand.getBrandId());
                            OmsBrandInfo omsBrandInfo = map.get(brandId);
                            if (null != omsBrandInfo) {
                                campaignBrand.setBrandInfo(omsBrandInfo);
                            }
                        }
                    }
                }
            }



            serviceResult.setData(brandList);
            serviceResult.setSuccess(true);
        } catch (Exception e) {
            LOGGER.error("通过活动获取品牌数据失败, {}", e);
            serviceResult.setSuccess(false);
            serviceResult.setMsg(e.toString());
        }
        long end = System.currentTimeMillis();
        long time = end - start;
        LOGGER.info("通过活动获取品牌数据成功,返回数据：" + serviceResult + ",耗时：" + (time / 1000) + "秒" + (time % 1000) + "毫秒");
        return serviceResult;
    }

    @Override
    public ServiceResult<List<CmsCampaignMarket>> getCampaignMarket(Integer campaignId) {

        long start = System.currentTimeMillis();
        ServiceResult<List<CmsCampaignMarket>> serviceResult= new ServiceResult<>();
        try {
            CampaignMarketQueryVo vo = new CampaignMarketQueryVo();
            vo.setCampaignId(campaignId);
            List<CmsCampaignMarket> list = cmsCampaignMarketMapper.find(vo);
            if(null!=list && !list.isEmpty()){
                List<Integer> marketIdList = new ArrayList<Integer>();
                for (CmsCampaignMarket campaignMarket:list
                        ) {
                    if(!StringUtils.isEmpty(campaignMarket.getMarketId())){
                        marketIdList.add(Integer.parseInt(campaignMarket.getMarketId()));
                    }
                }
                Map<Integer,OmsMarketInfoRelation> map = new HashMap<Integer,OmsMarketInfoRelation>();
                List<OmsMarketInfoRelation> marketInfoList = getMarketInfoWithDubbo(marketIdList);
                if (null!=marketInfoList && !marketInfoList.isEmpty()){

                    for (OmsMarketInfoRelation omsMarketInfo:marketInfoList
                            ) {
                        map.put(omsMarketInfo.getIdUuid(),omsMarketInfo);
                    }
                }
                for (CmsCampaignMarket campaignMarket:list
                        ) {
                    if(!StringUtils.isEmpty(campaignMarket.getMarketId())){
                        Integer marketId = Integer.parseInt(campaignMarket.getMarketId());
                        OmsMarketInfoRelation omsMarketInfo = map.get(marketId);
                        if(null!=omsMarketInfo){
                            campaignMarket.setOmsMarketInfo(omsMarketInfo);
                        }
                    }
                }



            }
            serviceResult.setData(list);
            serviceResult.setSuccess(true);

        }catch (Exception e){
            LOGGER.error("查询活动商场信息失败, {}", e);
            serviceResult.setSuccess(false);
            serviceResult.setMsg(e.toString());
        }
        long end = System.currentTimeMillis();
        long time = end - start;
        LOGGER.info("查询活动商场信息成功,返回数据：" + serviceResult + ",耗时：" + (time / 1000) + "秒" + (time % 1000) + "毫秒");
        return serviceResult;
    }

    @Override
    public ServiceResult<List<CmsCampaignMarketProduct>> getCampaignMarketProduct(CampaignMarketProductQueryVo vo) {

        long start = System.currentTimeMillis();
        ServiceResult<List<CmsCampaignMarketProduct>> serviceResult = new ServiceResult<List<CmsCampaignMarketProduct>>();
        try {
            List<CmsCampaignMarketProduct> list = cmsCampaignMarketProductMapper.find(vo);
            if(null!=list && !list.isEmpty()){
                List<String> productIdList = new ArrayList<String>();
                for (CmsCampaignMarketProduct marketProduct:list
                        ) {
                    if(!StringUtils.isEmpty(marketProduct.getProductId())){
                        productIdList.add(marketProduct.getProductId());
                    }
                }
                Map<String,ProductShopGoodsRelation> map = new HashMap<String,ProductShopGoodsRelation>();
                List<ProductShopGoodsRelation> productInfoList = getProductInfoWithDubbo(productIdList);
                if (null!=productInfoList && !productInfoList.isEmpty()){

                    for (ProductShopGoodsRelation omsProductInfo:productInfoList
                            ) {
                        map.put(omsProductInfo.getPdtSku(),omsProductInfo);
                    }
                }
                for (CmsCampaignMarketProduct marketProduct:list
                        ) {
                    if(!StringUtils.isEmpty(marketProduct.getProductId())){
                        String productId = marketProduct.getProductId();
                        ProductShopGoodsRelation omsProductInfo = map.get(productId);
                        if(null!=omsProductInfo){
                            marketProduct.setProductInfo(omsProductInfo);
                        }
                    }
                }



            }
            serviceResult.setData(list);
            serviceResult.setSuccess(true);
        }catch (Exception e){
            LOGGER.error("查询活动商品信息失败, {}", e);
            serviceResult.setSuccess(false);
            serviceResult.setMsg(e.toString());
        }
        long end = System.currentTimeMillis();
        long time = end - start;
        LOGGER.info("查询活动商品信息成功,返回数据：" + serviceResult + ",耗时：" + (time / 1000) + "秒" + (time % 1000) + "毫秒");
        return serviceResult;
    }

//    @Override
//    public ServiceResult<List<CmsCampaignMarketCoupon>> getCampaignMarketCoupon(Integer campaignMarketId,String openId) {
//
//        long start = System.currentTimeMillis();
//        ServiceResult<List<CmsCampaignMarketCoupon>> serviceResult = new ServiceResult<List<CmsCampaignMarketCoupon>>();
//        try {
//            List<CmsCampaignMarketCoupon> list = cmsCampaignMarketCouponMapper.find(campaignMarketId);
//            if(null!=list && !list.isEmpty()){
//                List<Integer> idList = new ArrayList<Integer>();
//                for (CmsCampaignMarketCoupon marketCoupon:list
//                        ) {
//                    if(!StringUtils.isEmpty(marketCoupon.getCouponId())){
//                        idList.add(Integer.parseInt(marketCoupon.getCouponId()));
//                    }
//                }
//                Map<Integer,Coupon> map = new HashMap<Integer,Coupon>();
//                List<Coupon> cuponInfoList = getCouponInfoWithDubbo(idList,openId);
//                if (null!=cuponInfoList && !cuponInfoList.isEmpty()){
//
//                    for (Coupon cupon:cuponInfoList
//                            ) {
//                        map.put(cupon.getId(),cupon);
//                    }
//                }
//                for (CmsCampaignMarketCoupon marketCoupon:list
//                        ) {
//                    if(!StringUtils.isEmpty(marketCoupon.getCouponId())){
//                        Integer cuponId = Integer.parseInt(marketCoupon.getCouponId());
//                        Coupon cupon = map.get(cuponId);
//                        if(null!=cupon){
//                            marketCoupon.setCoupon(cupon);
//                        }
//                    }
//                }
//
//
//
//            }
//            serviceResult.setData(list);
//            serviceResult.setSuccess(true);
//        }catch (Exception e){
//            LOGGER.error("查询活动优惠券信息失败, {}", e);
//            serviceResult.setSuccess(false);
//            serviceResult.setMsg(e.toString());
//        }
//        long end = System.currentTimeMillis();
//        long time = end - start;
//        LOGGER.info("查询活动优惠券信息成功,返回数据：" + serviceResult + ",耗时：" + (time / 1000) + "秒" + (time % 1000) + "毫秒");
//        return serviceResult;
//
//    }


    /**
     * 通过dubbo获取品牌信息
     * @param brandIdList
     * @return
     */
    private List<OmsBrandInfo> getBrandInfoWithDubbo(List<Integer> brandIdList) {
        if (brandIdList == null || brandIdList.isEmpty()) {
            LOGGER.error("brandIdList must be not null");
            return null;
        }

        com.redstar.digital.open.vo.ServiceResult<List<OmsBrandInfo>> brandServiceResult = omsBrandInfoService.getOmsBrandInfoById(brandIdList);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.error("调用品牌服务Dubbo接口 --> Request : {},  Response : {}", brandIdList, JsonUtil.toJson(brandServiceResult, false));
        }

        if (!brandServiceResult.isSuccess()) {
            LOGGER.error("通过dubbo接口获取品牌信息失败. Parameter : {},  Casue by : {}", brandIdList, brandServiceResult.getMsg());
            return null;
        }

        List<OmsBrandInfo> brandInfoList = brandServiceResult.getData();

        return brandInfoList;
    }


    /**
     * 通过dubbo获取商场信息
     * @param marketIdList
     * @return
     */
    private List<OmsMarketInfoRelation> getMarketInfoWithDubbo(List<Integer> marketIdList) {

        com.redstar.digital.open.vo.ServiceResult<List<OmsMarketInfoRelation>> marketServiceResult = omsMarketInfoService.getOnMarketInfosByUuids(marketIdList);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.error("调用商场服务Dubbo接口 --> Request : {},  Response : {}", marketIdList, JsonUtil.toJson(marketServiceResult, false));
        }

        if (!marketServiceResult.isSuccess()) {
            LOGGER.error("通过dubbo接口获取商场信息失败. Parameter : {},  Casue by : {}", marketIdList, marketServiceResult.getMsg());
            return null;
        }

        List<OmsMarketInfoRelation> marketInfoList = marketServiceResult.getData();

        return marketInfoList;
    }


    /**
     * 通过dubbo获取商品信息
     * @param productIdList
     * @return
     */
    private List<ProductShopGoodsRelation> getProductInfoWithDubbo(List<String> productIdList) {

        com.redstar.digital.open.vo.ServiceResult<List<ProductShopGoodsRelation>> productServiceResult = productShopGoodsService.getOnShopGoodsInfosBySkus(productIdList);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.error("调用商品服务Dubbo接口 --> Request : {},  Response : {}", productIdList, JsonUtil.toJson(productServiceResult, false));
        }

        if (!productServiceResult.isSuccess()) {
            LOGGER.error("通过dubbo接口获取商品信息失败. Parameter : {},  Casue by : {}", productIdList, productServiceResult.getMsg());
            return null;
        }

        List<ProductShopGoodsRelation> productInfoList = productServiceResult.getData();

        return productInfoList;
    }


    /**
     * 通过dubbo获取优惠券信息
     * @param idList
     * @return
     */
//    private List<Coupon> getCouponInfoWithDubbo(List<Integer> idList,String openId) {
//
//       List<Coupon> list = new ArrayList<Coupon>();
//        try{
//            list = cuponQueryService.getCouponsByIdList(idList,openId);
//            LOGGER.error("调用商品服务Dubbo接口 --> Request : {},  Response : {}", idList, JsonUtil.toJson(list, false));
//        }catch (Exception e){
//
//            LOGGER.error("通过dubbo接口获取商品信息失败. Parameter : {},  Casue by : {}", idList, e.toString());
//        }
//
//
//        return list;
//    }
}
