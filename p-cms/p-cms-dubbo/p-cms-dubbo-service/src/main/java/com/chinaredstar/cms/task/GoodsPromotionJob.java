//package com.chinaredstar.cms.task;
//
//import com.chinaredstar.cms.api.model.CmsCampaignMarket;
//import com.chinaredstar.cms.api.model.CmsCampaignMarketProduct;
//import com.chinaredstar.cms.api.vo.campaign.CampaignMarketProductQueryVo;
//import com.chinaredstar.cms.mapper.CmsCampaignMarketProductMapper;
//import com.chinaredstar.p_trade_promotion.api.IPromotionService;
//import com.mmall.job.core.handler.IJobHandler;
//import com.mmall.job.core.handler.annotation.JobHander;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by Ykk on 16/10/25.
// * 获取双十一每个商品的促销价格
// */
//@JobHander(value = "goodsJobHandler")
//@Service
//public class GoodsPromotionJob extends IJobHandler{
//
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(OfflineAdTimerJob.class);
//
//    @Autowired
//    private CmsCampaignMarketProductMapper cmsCampaignMarketProductMapper;
//
//    @Autowired
//    private IPromotionService promotionService;
//
//    @Override
//    public JobHandleStatus execute(String... strings) throws Exception {
//        try{
//            CampaignMarketProductQueryVo queryVo = new CampaignMarketProductQueryVo();
//            List<CmsCampaignMarketProduct> list = cmsCampaignMarketProductMapper.find(queryVo);
//            List<Integer> goodsList = new ArrayList<Integer>();
//            for (CmsCampaignMarketProduct product:list
//                    ) {
//                if(!StringUtils.isEmpty(product.getProductId())){
//                    goodsList.add(Integer.parseInt(product.getProductId()));
//                }
//            }
//            if(StringUtils.isEmpty(strings) || StringUtils.isEmpty(strings[0])){
//                LOGGER.error("输入参数,促销id不能为空");
//                return  JobHandleStatus.FAIL;
//            }else {
//                Map<Integer, BigDecimal> map = promotionService.queryPromotionsBySubPromotionIdAndSkuIds(Integer.parseInt(strings[0]),goodsList);
//                for (CmsCampaignMarketProduct product:list
//                        ) {
//                    if(!StringUtils.isEmpty(product.getProductId())){
//                        BigDecimal price = map.get(Integer.parseInt(product.getProductId()));
//
//                        if (null!=price && price.compareTo(BigDecimal.valueOf(0.0))>0){
//                            //批量修改
//                            product.setCheap(price);
//                            cmsCampaignMarketProductMapper.updateByPrimaryKey(product);
//                        }
//
//                    }
//                }
//                return JobHandleStatus.SUCCESS;
//
//
//            }
//
//
//        }catch(Exception e){
//            LOGGER.error("获取双十一每个商品的促销价格失败：{}",e);
//            return  JobHandleStatus.FAIL;
//        }
//
//
//    }
//}
