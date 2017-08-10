package com.chinaredstar.cms.controller;
import com.chinaredstar.cms.api.model.*;
import com.chinaredstar.cms.api.service.ActivityService;
import com.chinaredstar.cms.utils.ActivityUtil;
import com.chinaredstar.cms.utils.HttpClientUtil;
import com.chinaredstar.cms.utils.IpUtils;
import com.chinaredstar.cms.utils.PingYinUtil;
import com.chinaredstar.cms.vo.Result;
import com.chinaredstar.cms.vo.ResultCode;
import com.chinaredstar.ilvb.api.bean.ZbRoom;
import com.chinaredstar.ilvb.api.service.IZbRoomService;
import com.chinaredstar.p_trade_promotion.api.ICuponIssueService;
import com.chinaredstar.p_trade_promotion.api.ICuponQueryService;
import com.chinaredstar.p_trade_promotion.api.IPromotionService;
import com.chinaredstar.p_trade_promotion.api.view.CouponPriceListResp;
import com.chinaredstar.p_trade_promotion.api.view.ItemPromotionsByActivityResp;
import com.chinaredstar.p_trade_promotion.api.view.PocCouponDetailView;
import com.chinaredstar.p_trade_promotion.api.view.PocSkuInfoView;
import com.chinaredstar.p_trade_promotion.api.vo.CuponTakenResult;
import com.chinaredstar.p_trade_promotion.api.vo.ItemPromotionsByActivityReq;
import com.chinaredstar.p_trade_promotion.exception.*;
import com.chinaredstar.uc.dubbo.core.api.IUserService;
import com.chinaredstar.uc.dubbo.core.api.vo.UsersVo;
import com.codahale.metrics.annotation.Timed;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.redstar.digital.open.service.ProductShopGoodsService;
import com.redstar.digital.open.vo.GoodsInfoVo;
import com.redstar.digital.open.vo.ServiceResult;
import com.redstar.sms.api.MsgService;
import com.ryantenney.metrics.spring.IPUtils;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.Collator;
import java.text.SimpleDateFormat;
import java.util.*;

///**
// * 活动页面TO C
// * Created by mdc on 2017/2/14.
// */
@RestController
@RequestMapping("/activity")
//@Api(value = "activity", description = "活动页面TO C")
//@Timed
public class ActivityController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityController.class);

    @Autowired ActivityService activityService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 404, message = "返回数据为空"),
            @ApiResponse(code = 415, message = "请求参数错误"),
            @ApiResponse(code = 422, message = "校验错误"),
            @ApiResponse(code = 500, message = "系统内部错误")
    })
    @ApiOperation(value = "spu", notes = "根据spu获取三维模型信息")
    @RequestMapping(value = "/spu")
    Object getDataBySpu(String spu) {
        Result<CmsThreeDimensional> resultMap = new Result<>("200", "请求成功");
        if (StringUtils.isEmpty(spu)) {
            resultMap.setMessage("spu参数缺失");
            resultMap.setCode(ResultCode.C415.getCode());
            return resultMap;
        }
        Map<String, Object> params = Maps.newHashMap();
        params.put("spu", spu);
        params.put("status", "adopt");
        resultMap.setDataMap(activityService.queryDataBySpu(params));
        return resultMap;
    }


    @RequestMapping(value = "/spulist")
    Object spulist(String brandId,Integer pageSize) {

        pageSize=pageSize==null?4:pageSize;

        Result<List<CmsThreeDimensional>> resultMap = new Result<>("200", "请求成功");
        if (StringUtils.isEmpty(brandId)) {
            resultMap.setMessage("brandId参数缺失");
            resultMap.setCode(ResultCode.C415.getCode());
            return resultMap;
        }
        Map<String, Object> params = Maps.newHashMap();
        params.put("brandId", brandId);
        params.put("status", "adopt");
        params.put("pageSize",pageSize);
        resultMap.setDataMap(activityService.queryDataByBrandId(params));
        return resultMap;
    }

    /**
     * 根据经纬度获取城市信息
     *
     * @param longitude
     * @param latitude
     * @return
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 404, message = "返回数据为空"),
            @ApiResponse(code = 415, message = "请求参数错误"),
            @ApiResponse(code = 422, message = "校验错误"),
            @ApiResponse(code = 500, message = "系统内部错误")
    })
    @ApiOperation(value = "getCityByLocal", notes = "根据经纬度获取城市信息")
    @RequestMapping(value = "/getCityByLocal", method = RequestMethod.GET)
    public Result<Map<String, Object>> getCityByLongitudeAndLatitude(double longitude, double latitude,HttpServletRequest httpServletRequest) {
        Result<Map<String, Object>> resultMap = new Result<>();
        Map<String, Object> dataMap = new HashMap<>();

        String city = "";
        if(latitude>0 && latitude>0){
            String url = "http://api.map.baidu.com/geocoder/v2/?ak=EB77c29b7b9800e5804ef458fbf3ac67&location=" +
                    latitude + "," + longitude + "&output=json&pois=0";
            String result = HttpClientUtil.httpGet(url);
            if (!StringUtils.isEmpty(result)) {
                JSONObject jsonObject = new JSONObject(result);
                if(jsonObject.has("result")){
                    JSONObject jsonResult = jsonObject.getJSONObject("result");
                    JSONObject jsonAddress = jsonResult.getJSONObject("addressComponent");
                    city = jsonAddress.getString("city");
                }
            }
        }
        if(city==null || city.equals("")){
            //没有经纬度的情况  根据ip去获取城市
            String ip = IpUtils.getIpAddr(httpServletRequest);
//            ip="115.239.210.27";//杭州的
            if(ip !=null){
                String ipUrl = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?ip="+ip+"&format=js";
                String result = HttpClientUtil.httpGet(ipUrl);
                if (!StringUtils.isEmpty(result) && StringUtils.containsWhitespace("remote_ip_info")) {
                    JSONObject jsonObject = new JSONObject(result.split("=")[1]);
                    if(jsonObject!=null){
                        city = jsonObject.getString("city");
                        if(!city.contains("市")){
                            city = city+"市";
                        }
                    }
                }
            }
        }

        dataMap.put("city", city);
        resultMap.setCode(ResultCode.C200.getCode());
        resultMap.setDataMap(dataMap);
        resultMap.setMessage("查询成功");
        LOGGER.info("根据经纬度获取城市信息:" + longitude + "," + latitude + city + "查询成功");
        return resultMap;
    }



}
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityController.class);
//
//    @Autowired
//    private ActivityService activityService;
//
//    @Autowired
//    private IZbRoomService zbRoomService;
//
//    @Value("${h5.web.host}")
//    private String webHost;
//    @Autowired
//    private ICuponQueryService cuponQueryService;
//    @Autowired
//    private IPromotionService promotionService;
//    @Autowired
//    private ICuponIssueService cuponIssueService;
//    @Autowired
//    private ProductShopGoodsService productShopGoodsService;
//
//
//    /**
//     * 根据经纬度获取城市信息
//     *
//     * @param longitude
//     * @param latitude
//     * @return
//     */
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "请求成功"),
//            @ApiResponse(code = 404, message = "返回数据为空"),
//            @ApiResponse(code = 415, message = "请求参数错误"),
//            @ApiResponse(code = 422, message = "校验错误"),
//            @ApiResponse(code = 500, message = "系统内部错误")
//    })
//    @ApiOperation(value = "getCityByLocal", notes = "根据经纬度获取城市信息")
//    @RequestMapping(value = "/getCityByLocal", method = RequestMethod.GET)
//    public Result<Map<String, Object>> getCityByLongitudeAndLatitude(double longitude, double latitude,HttpServletRequest httpServletRequest) {
//        Result<Map<String, Object>> resultMap = new Result<>();
//        Map<String, Object> dataMap = new HashMap<>();
//
//        String city = "";
//        if(latitude>0 && latitude>0){
//            String url = "http://api.map.baidu.com/geocoder/v2/?ak=EB77c29b7b9800e5804ef458fbf3ac67&location=" +
//                    latitude + "," + longitude + "&output=json&pois=0";
//            String result = HttpClientUtil.httpGet(url);
//            if (!StringUtils.isEmpty(result)) {
//                JSONObject jsonObject = new JSONObject(result);
//                if(jsonObject.has("result")){
//                    JSONObject jsonResult = jsonObject.getJSONObject("result");
//                    JSONObject jsonAddress = jsonResult.getJSONObject("addressComponent");
//                    city = jsonAddress.getString("city");
//                }
//            }
//        }
//        if(city==null || city.equals("")){
//            //没有经纬度的情况  根据ip去获取城市
//            String ip = IpUtils.getIpAddr(httpServletRequest);
////            ip="115.239.210.27";//杭州的
//            if(ip !=null){
//                String ipUrl = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?ip="+ip+"&format=js";
//                String result = HttpClientUtil.httpGet(ipUrl);
//                if (!StringUtils.isEmpty(result) && StringUtils.containsWhitespace("remote_ip_info")) {
//                    JSONObject jsonObject = new JSONObject(result.split("=")[1]);
//                    if(jsonObject!=null){
//                        city = jsonObject.getString("city");
//                        if(!city.contains("市")){
//                            city = city+"市";
//                        }
//                    }
//                }
//            }
//        }
//
//        dataMap.put("city", city);
//        resultMap.setCode(ResultCode.C200.getCode());
//        resultMap.setDataMap(dataMap);
//        resultMap.setMessage("查询成功");
//        LOGGER.info("根据经纬度获取城市信息:" + longitude + "," + latitude + city + "查询成功");
//        return resultMap;
//    }
//
//    /**
//     * Task #17404: 根据活动id查询活动下参加的商场所在城市列表，返回城市名称和code（直接查询活动库）
//     *
//     * @param activityId
//     * @return
//     */
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "请求成功"),
//            @ApiResponse(code = 404, message = "返回数据为空"),
//            @ApiResponse(code = 415, message = "请求参数错误"),
//            @ApiResponse(code = 422, message = "校验错误"),
//            @ApiResponse(code = 500, message = "系统内部错误")
//    })
//    @ApiOperation(value = "getCityByActivity", notes = "获取参加活动的城市信息")
//    @RequestMapping(value = "/getCityByActivity", method = RequestMethod.GET)
//    public Result<Map<String, Object>> getCityByActivity(Integer activityId) {
//        Result<Map<String, Object>> resultMap = new Result<>();
//        if (activityId == null || activityId == 0) {
//            resultMap.setCode(ResultCode.C415.getCode());
//            return resultMap;
//        }
//        List<String> cities = activityService.getCityByActivityAndCode(activityId, "");
//        resultMap.setCode(ResultCode.C200.getCode());
//        resultMap.setDataMap(filterCities(cities));
//        resultMap.setMessage("查询成功");
//        return resultMap;
//    }
//
//    public static Map<String, Object> filterCities(List<String> cities) {
//        Map<String, Object> map = new HashMap<>();
//        //城市按照中文排序一遍
//        Comparator comparator = Collator.getInstance(java.util.Locale.CHINA);
//        String[] citiesArray = (String[]) cities.toArray(new String[cities.size()]);
//        Arrays.sort(citiesArray, comparator);
//        cities = Arrays.asList(citiesArray);
//        // 拆分数据给到前端式样
//        List<Map<String, Object>> listMaps = new ArrayList<>();
//        Map<String, Object> dataMap = new HashMap<>();
//        String tempPY = "";
//        List<String> includeCities = new ArrayList<>();
//        for (int i = 0; i < cities.size(); i++) {
//            String city = cities.get(i);
//            String py = PingYinUtil.getFirstSpell(city);
//            if (py.length() > 0) {
//                py = py.substring(0, 1);
//            }
//            if (!tempPY.equals(py)) {
//                tempPY = py;
//                if (includeCities.size() > 0) {
//                    dataMap.put("includeCities", includeCities);
//                    listMaps.add(dataMap);
//                }
//                dataMap = new HashMap<>();
//                dataMap.put("letter", py.toUpperCase());
//                includeCities = new ArrayList<>();
//            }
//            includeCities.add(city);
//
//            if (i + 1 == cities.size()) {
//                dataMap.put("includeCities", includeCities);
//                listMaps.add(dataMap);
//            }
//        }
//        map.put("data", listMaps);
//        return map;
//    }
//
//    /**
//     * Task #17404: 根据活动id查询活动详情（直接查询活动库）
//     *
//     * @param activityId
//     * @return
//     */
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "请求成功"),
//            @ApiResponse(code = 404, message = "返回数据为空"),
//            @ApiResponse(code = 415, message = "请求参数错误"),
//            @ApiResponse(code = 422, message = "校验错误"),
//            @ApiResponse(code = 500, message = "系统内部错误")
//    })
//    @ApiOperation(value = "getActivity", notes = "根据活动id查询活动详情")
//    @RequestMapping(value = "/getActivity", method = RequestMethod.GET)
//    public Result<Map<String, Object>> getActivity(Integer activityId) {
//        Result<Map<String, Object>> resultMap = new Result<>();
//        Map<String, Object> map = new HashMap<>();
//        if (activityId == null || activityId == 0) {
//            resultMap.setCode(ResultCode.C415.getCode());
//            resultMap.setMessage("活动ID不为空");
//            return resultMap;
//        }
//        ActivityTemplate activityTemplate = activityService.getActivityById(activityId);
//        map.put("activityTemplate", activityTemplate);
//        resultMap.setCode(ResultCode.C200.getCode());
//        resultMap.setDataMap(map);
//        resultMap.setMessage("查询成功");
//        return resultMap;
//    }
//
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "请求成功"),
//            @ApiResponse(code = 404, message = "返回数据为空"),
//            @ApiResponse(code = 415, message = "请求参数错误"),
//            @ApiResponse(code = 422, message = "校验错误"),
//            @ApiResponse(code = 500, message = "系统内部错误")
//    })
//    @ApiOperation(value = "getMarket", notes = "根据活动id查询参与活动城市下的商场列表")
//    @RequestMapping(value = "/getMarket", method = RequestMethod.GET)
//    public Result<Map<String, Object>> getMarketByActivityAndMarket(Integer activityId, String city) {
//        Result<Map<String, Object>> resultMap = new Result<>();
//        Map<String, Object> map = new HashMap<>();
//        if (activityId == null || activityId == 0) {
//            resultMap.setCode(ResultCode.C415.getCode());
//            resultMap.setMessage("活动ID不为空");
//            return resultMap;
//        }
//        if (StringUtils.isEmpty(city)) {
//            resultMap.setCode(ResultCode.C415.getCode());
//            resultMap.setMessage("城市名称不为空");
//            return resultMap;
//        }
//        List<ActivityTemplateMarket> markets = activityService.getMarketByCityNameAndActivityId(city, activityId);
//        map.put("markets", markets);
//
//        //获取活动页详情
//        ActivityTemplate activityTemplate = activityService.getActivityById(activityId);
//        if (activityTemplate != null) {
//            //获取促销活动的id
//            int promotionActivityId = activityTemplate.getPromotionActivityId();
//            map.put("promotionId", promotionActivityId);
//        }
//
//        map.put("webHost", webHost);
//
//        resultMap.setCode(ResultCode.C200.getCode());
//        resultMap.setDataMap(map);
//        resultMap.setMessage("查询成功");
//        return resultMap;
//    }
//
//    private static final String JSON_TYPE = "type";
//    private static final String JSON_MARKETCUSTOM = "marketCustom";
//    private static final String JSON_BANNER = "banner";
//    private static final String JSON_RULE = "rule";
//    private static final String JSON_ZB = "zb";
//    private static final String JSON_COUPON = "coupon";
//    private static final String JSON_MORECOUPON = "moreCoupon";
//    private static final String JSON_ACTIVITYTIME = "activityTime";
//    private static final String JSON_BK = "bk";
//
//    /**
//     * Task #17409
//     *
//     * @param activityId
//     * @param marketId
//     * @return
//     */
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "请求成功"),
//            @ApiResponse(code = 404, message = "返回数据为空"),
//            @ApiResponse(code = 415, message = "请求参数错误"),
//            @ApiResponse(code = 422, message = "校验错误"),
//            @ApiResponse(code = 500, message = "系统内部错误")
//    })
//    @ApiOperation(value = "getPageInfo", notes = "根据活动id和商场id查询整个页面的json")
//    @RequestMapping(value = "/getPageInfo", method = RequestMethod.GET)
//    public Result<Map<String, Object>> getPageInfo(Integer activityId, Integer marketId, String openId,Boolean init) {
//        Result<Map<String, Object>> resultMap = new Result<>();
//        Map<String, Object> map = new HashMap<>();
//        if (activityId == null || activityId == 0) {
//            resultMap.setCode(ResultCode.C415.getCode());
//            resultMap.setMessage("活动ID不为空");
//            return resultMap;
//        }
//        int mId = marketId;
//        //获取活动页详情
//        ActivityTemplate activityTemplate = activityService.getActivityById(activityId);
//
//        if ((marketId == null || marketId == 0)) {
//            if (activityTemplate.getMarketId() == null || activityTemplate.getMarketId() == 0) {
//                resultMap.setCode(ResultCode.C415.getCode());
//                resultMap.setMessage("商场ID不为空");
//                return resultMap;
//            } else {
//                mId = activityTemplate.getMarketId();
//            }
//        }
//
//        ActivityTemplateMarket activityTemplateMarket = null;
//        if (mId > 0) {
//            activityTemplateMarket = activityService.getMarketByActivityIdAndMarketId(activityId, mId);
//        }
//        JSONArray layouts = null;
//        if (activityTemplate.getActivityType() == 1) {
//            //商场自建活动
//            if (activityTemplate.getLayouts() != null) {
//                layouts = new JSONArray(activityTemplate.getLayouts());
//            }
//        } else if (activityTemplate.getActivityType() == 0) {
//            //平台
//
//            if (activityTemplateMarket != null) {
//                //商场平台活动
//                layouts = new JSONArray(activityTemplate.getLayouts());
//                JSONArray layoutsMarket = null;
//                if (activityTemplateMarket.getLayouts() != null) {
//                    layoutsMarket = new JSONArray(activityTemplateMarket.getLayouts());
//                }
//                if (layouts != null && layoutsMarket != null) {
//                    JSONArray resultJson = new JSONArray();
//                    int jsonIndex = 0;
//                    for (int i = 0; i < layouts.length(); i++) {
//                        JSONArray row = layouts.getJSONArray(i);
//                        resultJson.put(jsonIndex, row);
//                        boolean countAdd = true;//计数器加一
//                        for (int j = 0; j < row.length(); j++) {
//                            JSONObject col = row.getJSONObject(j);
//                            String type = col.getString(JSON_TYPE);
//                            if (type != null && type.equals(JSON_MARKETCUSTOM)) {
//                                resultJson.remove(jsonIndex);
//                                countAdd = false;
//                                for (int m = 0; m < layoutsMarket.length(); m++) {
//                                    JSONArray marketRow = layoutsMarket.getJSONArray(m);
//                                    if (marketRow != null) {
//                                        resultJson.put(jsonIndex++, marketRow);
//                                    }
//                                }
//                            }
//                        }
//                        if (countAdd) {
//                            //上面已经加过了，下面就不加了
//                            jsonIndex++;
//                        }
//                    }
//                    layouts = resultJson;
//                }
//            } else {
//                //平台活动页
//                layouts = new JSONArray(activityTemplate.getLayouts());
//            }
//        }
//        //layouts布局json 解析
//        if (layouts != null) {
//            if(!init){
//                this.resolveJson(layouts, activityTemplate, openId);
//            }
//            layouts = this.updateJson(layouts);
//            layouts = resolveCouponAndBkJson(layouts);
//            map.put("layouts", layouts.toString());
////            map.put("layouts",new JSONArray(layouts.toString()));
//        }
//        map.put("webHost",webHost);
//
//        resultMap.setDataMap(map);
//        resultMap.setCode(ResultCode.C200.getCode());
//        resultMap.setMessage("查询成功");
//        return resultMap;
//    }
//
//
//    /**
//     * 解析json
//     *
//     * @param layouts
//     * @return
//     */
//    private void resolveJson(JSONArray layouts, ActivityTemplate activityTemplate, String openId) {
//        for (int i = 0; i < layouts.length(); i++) {
//            JSONArray array = layouts.getJSONArray(i);
//
//            for (int j = 0; j < array.length(); j++) {
//                JSONObject item = array.getJSONObject(j);
//                String type = item.getString(JSON_TYPE);
//                if (type.equals(JSON_BANNER)) {
//                    //banner
//                    //TODO 第一期只做一张图片没有点击事件
//                } else if (type.equals(JSON_RULE)) {
//                    //活动规则
//                    //TODO 第一期只做一串文字说明
//                } else if (type.equals(JSON_ZB)) {
//                    //直播
//                    int zbId = item.getInt("zbId");
//                    if (zbId > 0) {
//                        List<ZbRoom> zbRooms = zbRoomService.selectById(Long.valueOf(zbId));
//                        if (zbRooms != null && zbRooms.size() > 0) {
//                            ZbRoom zbRoom = zbRooms.get(0);
//                            String imgBigUrl = zbRoom.getImgBigUrl();//直播封面大图
//                            String title = zbRoom.getTitle();
//                            String realName = zbRoom.getRealName();
//                            String userPicUrl = zbRoom.getUserPicUrl();//主播头像
//                            Long currentOnlineNum = zbRoom.getCurrentOnlineNum();//直播间在线人数
//
//                            String zbUrl = webHost + "/live?id=" + zbId;
//
//                            item.put("zbCover", imgBigUrl);
//                            item.put("zbTitle", title);
//                            item.put("zbAnchor", realName);
//                            item.put("zbAnchorPic", userPicUrl);
//                            item.put("zbOnline", currentOnlineNum);
//                            item.put("zbUrl", zbUrl);
//                        }
//                    }
//                } else if (type.equals(JSON_COUPON)) {
//                    //优惠券
//                    int couponId = item.getInt("couponId");
//                    if (couponId > 0) {
//                        //openId可以为null   根据优惠券id、用户openId查询优惠券详情，如果用户已经领券，couponCode不为空
//                        PocCouponDetailView couponDetail = cuponQueryService.getCouponDetailById(couponId, openId);
//                        if (couponDetail != null) {
//                            item.put("title", couponDetail.getOwnerName());
//                            item.put("remark", couponDetail.getConditions());
//                            item.put("price", couponDetail.getTitle());
//
//                            String couponCode = couponDetail.getCouponCode();
//                            int status = 0;// 0:可以领取，1：已领取，2：抢光了，3：已结束
//                            if (couponCode != null&&!couponCode.equals("")) {
//                                status = 1;
//                            }
//                            int remainingCount = couponDetail.getRemainingCount();//剩余件数
//                            if (remainingCount <= 0) {
//                                status = 2;
//                            }
//                            Date endT = couponDetail.getEndT();
//                            if (new Date().getTime() > endT.getTime()) {
//                                status = 3;
//                            }
//                            item.put("status", status);
//                        }
//                    }
//                } else if (type.equals(JSON_MORECOUPON)) {
//                    //更多优惠券
//                    //TODO 无需返回其他参数，点击跳转的地址，由前端控制
//                } else if (type.equals(JSON_ACTIVITYTIME)) {
//                    //活动倒计时
//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                    item.put("currentTime", sdf.format(new Date()));
//                    if (activityTemplate.getStartTime() != null) {
//                        item.put("startTime", sdf.format(activityTemplate.getStartTime()));
//                    }
//                    if (activityTemplate.getEndTime() != null) {
//                        item.put("endTime", sdf.format(activityTemplate.getEndTime()));
//                    }
//                } else if (type.equals(JSON_BK)) {
//                    //爆款
//                    int sku = item.getInt("bkId");
//                    PocSkuInfoView skuInfo = promotionService.getSubPromotionInfo(sku, null, null);//ownerIdList、idList传null根据商品sku查询爆品信息
//                    BigDecimal stock = skuInfo.getStock();
//                    item.put("stock", stock);
////                    item.put("totalStock",) //TODO 总库存
//                    BigDecimal promotion = skuInfo.getPromotion();//优惠的价格
//                    BigDecimal promotionPrice = skuInfo.getPromotionPrice();//促销价
//                    BigDecimal srcPrice = BigDecimal.ZERO;
//                    if (promotion.compareTo(BigDecimal.ZERO) > 0 && promotionPrice.compareTo(BigDecimal.ZERO) > 0) {
//                        srcPrice = promotion.add(promotionPrice);
//                    }
//                    Date endTime = skuInfo.getEndTime();//结束时间
//                    BigDecimal totalInventory = skuInfo.getTotalInventory();//活动总库存
//                    item.put("oldPrice", srcPrice);
//                    item.put("price", promotionPrice);
//                    item.put("goodStatus", "2");
//                    item.put("totalStock", totalInventory);
//
//                    String bkUrl = webHost + "/shopGoods?id=" + sku;
//                    item.put("url", bkUrl);
//
//                    int status = 0;//0：立即抢购；1：抢光了；2：提醒我；3：已设提醒；4：已结束
//                    if (stock.compareTo(BigDecimal.ZERO) <= 0) {
//                        //小于等于0
//                        status = 1;
//                    }
//                    if (endTime != null && endTime.compareTo(new Date()) < 0) {
//                        status = 4;
//                    }
//                    item.put("status", status);
//
//                    ServiceResult<List<GoodsInfoVo>> goodsInfoResult = productShopGoodsService.getOffShopGoodsInfoBySkus(String.valueOf(sku));
//                    if (goodsInfoResult.isSuccess()) {
//                        List<GoodsInfoVo> goodsInfos = goodsInfoResult.getData();
//                        if (goodsInfos != null && goodsInfos.size() > 0) {
//                            //取第一条数据
//                            GoodsInfoVo goodsInfo = goodsInfos.get(0);
//                            item.put("title", goodsInfo.getPdtName());
//                            item.put("remark", goodsInfo.getRemark());
//                            item.put("bkCover", goodsInfo.getFirstImgUrl());
//                        }
//                    } else {
//                        LOGGER.error("[resolveJson] productShopGoodsService.getOffShopGoodsInfoBySkus 调用失败，param=" + String.valueOf(sku) + "  [result]=" + goodsInfoResult.getMsg());
//                    }
//
//                }
//            }
//
//        }
//
//    }
//
//    /**
//     * 更新大json
//     * @param layouts
//     */
//    private JSONArray updateJson(JSONArray layouts){
//        JSONArray firstArray = new JSONArray();
//        JSONArray secondArray = new JSONArray();
//
//        if(layouts!=null){
//            for(int i=0;i<layouts.length();i++){
//                JSONArray array = layouts.getJSONArray(i);
//                if(array!=null&&array.length()>0){
//                    JSONObject item = array.getJSONObject(0);
//                    String type = item.getString(JSON_TYPE);
//                    if (type.equals(JSON_RULE)) {
//                        //活动规则
//                        firstArray.put(array);
//                    } else if (type.equals(JSON_ACTIVITYTIME)) {
//                        //活动倒计时
//                        firstArray.put(array);
//                    } else {
//                        secondArray.put(array);
//                    }
//                }
//            }
//            JSONArray result = new JSONArray();
////            result.put(firstArray);
////            result.put(secondArray);
//            for(int m=0;m<firstArray.length();m++){
//                JSONArray item1 = firstArray.getJSONArray(m);
//                result.put(item1);
//            }
//            for(int n=0;n<secondArray.length();n++){
//                JSONArray item2 = secondArray.getJSONArray(n);
//                result.put(item2);
//            }
//            return result;
//        }
//        return null;
//    }
//
//
//    /**
//     * 针对优惠券和商品列表优化 json
//     *
//     * @param layouts
//     * @return
//     */
//    private JSONArray resolveCouponAndBkJson(JSONArray layouts) {
//        JSONArray result = new JSONArray();
//
//        JSONArray tempArray = new JSONArray();//零时array
//        String tempType = null;
//        for (int i = 0; i < layouts.length(); i++) {
//            JSONArray array = layouts.getJSONArray(i);
//            if(array!=null&&array.length()>0){
//                JSONObject item = array.getJSONObject(0);
//                String type = item.getString(JSON_TYPE);
//                if (type.equals(JSON_COUPON)||type.equals(JSON_MORECOUPON)) {
//                    //优惠券
//                    if(tempType!=null && !JSON_COUPON.equals(tempType)){
//                        JSONArray couponArray = generateListJson(tempType,tempArray);
//                        result.put(couponArray);
//                        tempArray = new JSONArray();
//                    }
//                    tempType = JSON_COUPON;
//                    tempArray.put(array);
//                } else if (type.equals(JSON_BK)) {
//                    //爆款
//                    if(tempType!=null && !JSON_BK.equals(tempType)){
//                        JSONArray bkArray = generateListJson(tempType,tempArray);
//                        result.put(bkArray);
//                        tempArray = new JSONArray();
//                    }
//                    tempType = JSON_BK;
//                    tempArray.put(array);
//                }else{
//                    if(tempType!=null){
//                        JSONArray bkcArray = generateListJson(tempType,tempArray);
//                        result.put(bkcArray);
//                        tempType =null;
//                        tempArray = new JSONArray();
//                    }
//                    result.put(array);
//                }
//            }
//
//        }
//        if(tempType!=null){
//            JSONArray bkcArray = generateListJson(tempType,tempArray);
//            result.put(bkcArray);
//            tempType =null;
//            tempArray = null;
//        }
//        return result;
//    }
//
//    /**
//     *  根据type 和array 生成置顶格式的优惠券和爆款json
//     * @param type
//     * @param tempArray
//     * @return
//     */
//    private JSONArray generateListJson(String type,JSONArray tempArray){
//        JSONArray result = new JSONArray();
//        JSONObject item = new JSONObject();
//        JSONArray newArray = new JSONArray(tempArray.toString());
//        item.put("type",type);
//        item.put("layouts",newArray);
//        result.put(item);
//        return result;
//    }
//
//
//    /**
//     * Task #17410
//     *
//     * @param activityId
//     * @return
//     */
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "请求成功"),
//            @ApiResponse(code = 404, message = "返回数据为空"),
//            @ApiResponse(code = 415, message = "请求参数错误"),
//            @ApiResponse(code = 422, message = "校验错误"),
//            @ApiResponse(code = 500, message = "系统内部错误")
//    })
//    @ApiOperation(value = "getCouponsByActivityId", notes = "根据活动id获取优惠券列表接口")
//    @RequestMapping(value = "/getCouponsByActivityId", method = RequestMethod.GET)
//    public Result<Map<String, Object>> getCouponsByActivityId(Integer activityId, String openId) {
//        Result<Map<String, Object>> resultMap = new Result<>();
//        Map<String, Object> map = new HashMap<>();
//        //这里的activityId是促销那边的活动id
//        if (activityId == null || activityId == 0) {
//            resultMap.setCode(ResultCode.C415.getCode());
//            resultMap.setMessage("活动ID不为空");
//            return resultMap;
//        }
//        //获取活动页详情
//        ItemPromotionsByActivityReq param = new ItemPromotionsByActivityReq(activityId, openId);
//        List<CouponPriceListResp> list = promotionService.getCouponsByActivityId(param);
//
//        for(int i=0;i<list.size();i++){
//            CouponPriceListResp item = list.get(i);
//
//            int status = 0;// 0:可以领取，1：已领取，2：抢光了，3：已结束
//            // @ApiModelProperty("状态 1、可以领取 0、未开始 2、已领取 3、已领完")
//            if (item.getStatus()==2) {
//                status = 1;
//            }
//            int remainingCount = item.getRemainingCount();//剩余件数
//            if (remainingCount <= 0) {
//                status = 2;
//            }
//            Date endT = item.getEndT();
//            if (new Date().getTime() > endT.getTime()) {
//                status = 3;
//            }
//
//            item.setStatus(status);
//        }
//
//
//        map.put("list", list);
//        resultMap.setDataMap(map);
//        resultMap.setCode(ResultCode.C200.getCode());
//        resultMap.setMessage("查询成功");
//        return resultMap;
//    }
//
//    /**
//     * Task #17412
//     *
//     * @param activityId
//     * @return
//     */
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "请求成功"),
//            @ApiResponse(code = 404, message = "返回数据为空"),
//            @ApiResponse(code = 415, message = "请求参数错误"),
//            @ApiResponse(code = 422, message = "校验错误"),
//            @ApiResponse(code = 500, message = "系统内部错误")
//    })
//    @ApiOperation(value = "getBkByActivityId", notes = "根据活动id获取爆款列表接口")
//    @RequestMapping(value = "/getBkByActivityId", method = RequestMethod.GET)
//    public Result<Map<String, Object>> getBkByActivityId(Integer activityId, Integer page, Integer pageSize) {
//        Result<Map<String, Object>> resultMap = new Result<>();
//        Map<String, Object> map = new HashMap<>();
//        //这里的activityId是促销那边的活动id
//        if (activityId == null || activityId == 0) {
//            resultMap.setCode(ResultCode.C415.getCode());
//            resultMap.setMessage("活动ID不为空");
//            return resultMap;
//        }
//        if (page == null || page <= 0) {
//            page = 1;
//        }
//        if (pageSize == null || pageSize <= 0) {
//            pageSize = 10;
//        }
//        //获取活动页详情
//        ItemPromotionsByActivityReq param = new ItemPromotionsByActivityReq(activityId, null);
//        List<ItemPromotionsByActivityResp> list = promotionService.getItemPromotionsByActivityId(param);
//
//        int len = list.size();
//        if (len > 0) {
//            int totalPage = len / pageSize;
//            if (len % pageSize > 0) {
//                //除不尽
//                totalPage = totalPage + 1;
//            }
//            if (page > totalPage) {
//                //如果请求页大于总页数，说明到底了
////                page = totalPage;
//                map.put("list", null);
//                resultMap.setDataMap(map);
//                resultMap.setCode(ResultCode.C200.getCode());
//                resultMap.setMessage("查询成功");
//                return resultMap;
//            }
//            int start = (page - 1) * pageSize;
//            int end = page * pageSize - 1;
//            if (end >= len) {
//                end = len - 1;
//            }
//
//            List<Map> result = new ArrayList<Map>();
//
//            List<List<Map>> resultArray = new ArrayList<>();
//
//
//            for (; start <= end; start++) {
//                ItemPromotionsByActivityResp item = list.get(start);
//                Map newItem = new HashMap();
////                JSONObject newItem = new JSONObject();
//
//                newItem.put("bkId", item.getSku());
//                newItem.put("bkCover", item.getSkuImgUrl());
//
//                BigDecimal stock = item.getRemainingStock();
//                newItem.put("stock", stock);
//
//                BigDecimal promotion = item.getSkuPromotionPrice();//优惠的价格
//                BigDecimal promotionPrice = item.getSkuPrice();//促销价
//                BigDecimal srcPrice = BigDecimal.ZERO;
//                if (promotion.compareTo(BigDecimal.ZERO) > 0 && promotionPrice.compareTo(BigDecimal.ZERO) > 0) {
//                    srcPrice = promotion.add(promotionPrice);
//                }
//                Date endTime = item.getEndTime();//结束时间
//                BigDecimal totalInventory = item.getTotalInventory();//活动总库存
//                newItem.put("oldPrice", srcPrice);
//                newItem.put("price", promotionPrice);
//                newItem.put("goodStatus", "2");
//                newItem.put("totalStock", totalInventory);
//                String bkUrl = webHost + "/shopGoods?id=" + item.getSku();
//                newItem.put("url", bkUrl);
//                int status = 0;//0：立即抢购；1：抢光了；2：提醒我；3：已设提醒；4：已结束
//                if (stock.compareTo(BigDecimal.ZERO) <= 0) {
//                    //小于等于0
//                    status = 1;
//                }
//                if (endTime != null && endTime.compareTo(new Date()) < 0) {
//                    status = 4;
//                }
//                newItem.put("status", status);
//
//                ServiceResult<List<GoodsInfoVo>> goodsInfoResult = productShopGoodsService.getOffShopGoodsInfoBySkus(String.valueOf(item.getSku()));
//                if (goodsInfoResult.isSuccess()) {
//                    List<GoodsInfoVo> goodsInfos = goodsInfoResult.getData();
//                    if (goodsInfos != null && goodsInfos.size() > 0) {
//                        //取第一条数据
//                        GoodsInfoVo goodsInfo = goodsInfos.get(0);
//                        newItem.put("title", goodsInfo.getPdtName());
//                        newItem.put("remark", goodsInfo.getRemark());
////                        newItem.put("bkCover", goodsInfo.getFirstImgUrl());
//                    }
//                } else {
//                    LOGGER.error("[getBkByActivityId] productShopGoodsService.getOffShopGoodsInfoBySkus 调用失败，param=" + String.valueOf(item.getSku()) + "  [result]=" + goodsInfoResult.getMsg());
//                }
////                JSONArray itemArray = new JSONArray();
////                itemArray.put(newItem);
//                List<Map> itemArray = new ArrayList<>();
//                itemArray.add(newItem);
//                resultArray.add(itemArray);
////                result.add(newItem);
//            }
//            map.put("layouts", resultArray);
//        }
//
//        map.put("page", page);
//        map.put("pageSize", pageSize);
//        map.put("type",JSON_BK);
//
//        resultMap.setDataMap(map);
//        resultMap.setCode(ResultCode.C200.getCode());
//        resultMap.setMessage("查询成功");
//        return resultMap;
//    }
//
//
//    /**
//     * @return
//     */
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "请求成功"),
//            @ApiResponse(code = 404, message = "返回数据为空"),
//            @ApiResponse(code = 415, message = "请求参数错误"),
//            @ApiResponse(code = 422, message = "校验错误"),
//            @ApiResponse(code = 500, message = "系统内部错误")
//    })
//    @ApiOperation(value = "cuponissue", notes = "领券接口")
//    @RequestMapping(value = "/cuponissue", method = RequestMethod.GET)
//    public Result<Map<String, Object>> cuponissue(Integer couponId, String openId) {
//        Result<Map<String, Object>> resultMap = new Result<>();
//        Map<String, Object> map = new HashMap<>();
//        //这里的activityId是促销那边的活动id
//        if (couponId == null || couponId == 0) {
//            resultMap.setCode(ResultCode.C415.getCode());
//            resultMap.setMessage("优惠券ID不为空");
//            return resultMap;
//        }
//        if (openId == null || openId.equals("0")) {
//                resultMap.setCode(ResultCode.C415.getCode());
//            resultMap.setMessage("请先登录");
//            return resultMap;
//        }
//
//        //领券
//        CuponTakenResult result = null;
//        String code = null;
//        String errMessage = null;
//        try {
//            result = cuponIssueService.userTakeCupon(openId, couponId, 1, "app", 1, null, null);
//
//            //将优惠券的状态返回
//            PocCouponDetailView couponDetail = cuponQueryService.getCouponDetailById(couponId, openId);
//            if (couponDetail != null) {
//                String couponCode = couponDetail.getCouponCode();
//                int status = 0;// 0:可以领取，1：已领取，2：抢光了，3：已结束
//                if (couponCode != null) {
//                    status = 1;
//                }
//                int remainingCount = couponDetail.getRemainingCount();//剩余件数
//                if (remainingCount <= 0) {
//                    status = 2;
//                }
//                Date endT = couponDetail.getEndT();
//                if (new Date().getTime() > endT.getTime()) {
//                    status = 3;
//                }
//                map.put("status", status);
//            }
//        } catch (HighConcurrentException e) {
//            LOGGER.error(e.getMessage(), e);
//            code = ResultCode.C1018.getCode();
//            errMessage = ResultCode.C1018.getDesc();
//        } catch (OutOfStockException e) {
//            LOGGER.error(e.getMessage(), e);
//            code = ResultCode.C1014.getCode();
//            errMessage = ResultCode.C1014.getDesc();
//        } catch (ChannelNotExistException e) {
//            LOGGER.error(e.getMessage(), e);
//            code = ResultCode.C1016.getCode();
//            errMessage = ResultCode.C1016.getDesc();
//        } catch (NotFoundException e) {
//            LOGGER.error(e.getMessage(), e);
//            code = ResultCode.C1012.getCode();
//            errMessage = ResultCode.C1012.getDesc();
//        } catch (CuponAlreadyTakenException e) {
//            LOGGER.error(e.getMessage(), e);
//            code = ResultCode.C1013.getCode();
//            errMessage = ResultCode.C1013.getDesc();
//        } catch (OverCuponIssueTimeDuration | CuponInactiveException e) {
//            LOGGER.error(e.getMessage(), e);
//            code = ResultCode.C1015.getCode();
//            errMessage = ResultCode.C1015.getDesc();
//        } catch (Exception e) {
//            LOGGER.error(e.getMessage(), e);
//            code = ResultCode.C500.getCode();
//            errMessage = ResultCode.C500.getDesc();
//        }
//        if(code!=null && errMessage!=null){
//            resultMap.setMessage(errMessage);
//            resultMap.setCode(code);
//        }else{
//            //没有抛异常
//            if (result != null) {
//                if (result.getResult() == 2) {
//                    //抛错
//                    resultMap.setMessage("调用领券接口失败");
//                    resultMap.setCode(ResultCode.C500.getCode());
//                } else {
//                    map.put("result", result);
//                    resultMap.setDataMap(map);
//                    resultMap.setCode(ResultCode.C200.getCode());
//                    resultMap.setMessage("操作成功");
//                }
//            }
//        }
//        return resultMap;
//    }
//
//
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "请求成功"),
//            @ApiResponse(code = 404, message = "返回数据为空"),
//            @ApiResponse(code = 415, message = "请求参数错误"),
//            @ApiResponse(code = 422, message = "校验错误"),
//            @ApiResponse(code = 500, message = "系统内部错误")
//    })
//    @ApiOperation(value = "spu", notes = "根据spu获取三维模型信息")
//    @RequestMapping(value = "/spu")
//    Object getDataBySpu(String spu) {
//        Result<CmsThreeDimensional> resultMap = new Result<>("200", "请求成功");
//        if (StringUtils.isEmpty(spu)) {
//            resultMap.setMessage("spu参数缺失");
//            resultMap.setCode(ResultCode.C415.getCode());
//            return resultMap;
//        }
//        Map<String, Object> params = Maps.newHashMap();
//        params.put("spu", spu);
//        params.put("status", "adopt");
//        resultMap.setDataMap(activityService.queryDataBySpu(params));
//        return resultMap;
//    }
//
//
//    @RequestMapping(value = "/spulist")
//    Object spulist(String brandId,Integer pageSize) {
//
//        pageSize=pageSize==null?4:pageSize;
//
//        Result<List<CmsThreeDimensional>> resultMap = new Result<>("200", "请求成功");
//        if (StringUtils.isEmpty(brandId)) {
//            resultMap.setMessage("brandId参数缺失");
//            resultMap.setCode(ResultCode.C415.getCode());
//            return resultMap;
//        }
//        Map<String, Object> params = Maps.newHashMap();
//        params.put("brandId", brandId);
//        params.put("status", "adopt");
//        params.put("pageSize",pageSize);
//        resultMap.setDataMap(activityService.queryDataByBrandId(params));
//        return resultMap;
//    }
//
//
//    @RequestMapping(value = "/addShare", method = RequestMethod.POST)
//    Object addShare(@RequestParam String openId) {
//
//        Result resultMap = new Result("200", "请求成功");
//
//        if (StringUtils.isEmpty(openId.trim())) {
//            resultMap.setMessage("参数缺失");
//            resultMap.setCode(ResultCode.C415.getCode());
//            return resultMap;
//        }
//
//        com.chinaredstar.uc.dubbo.core.api.vo.ServiceResult<UsersVo> serviceResult =iUserService.getUserInfoByOpenid(openId);
//        if (serviceResult.getData()==null){
//            resultMap.setCode("-401");
//            resultMap.setMessage("非法用户");
//            return  resultMap;
//        }
//        ActivityPrizeShare activityPrizeShare = new ActivityPrizeShare();
//        activityPrizeShare.setOpenId(openId);
//        activityService.addShare(activityPrizeShare);
//        return resultMap;
//    }
//
//    @RequestMapping(value = "/shareCount", method = RequestMethod.GET)
//    Object getShareCount(@RequestParam String openId) {
//
//        Result<Map<String, Object>> resultMap = new Result("200", "请求成功");
//
//        if (StringUtils.isEmpty(openId.trim())) {
//            resultMap.setMessage("参数缺失");
//            resultMap.setCode(ResultCode.C415.getCode());
//            return resultMap;
//        }
//
//        Map<String, Object> paramMap = Maps.newHashMap();
//        paramMap.put("openId", openId);
//
//        Map<String, Object> dataMap = Maps.newHashMap();
//        dataMap.put("count", activityService.queryShareCount(paramMap));
//
//        resultMap.setDataMap(dataMap);
//        return resultMap;
//    }
//
//
//
//    @RequestMapping(value = "/prizeList", method = RequestMethod.GET)
//    //Object getDataList(@RequestParam String marketCode,@RequestParam Integer gameRound) {
//    Object getDataList(@RequestParam Integer gameRound) {
//
//        Result<Map<String, Object>> resultMap = new Result("200", "请求成功");
//
//
///*        if (StringUtils.isEmpty(marketCode.trim())) {
//            resultMap.setMessage("参数缺失");
//            resultMap.setCode(ResultCode.C415.getCode());
//        }*/
//
//        Map<String, Object> paramMap = Maps.newHashMap();
//        //paramMap.put("marketCode", marketCode);
//
//        if (gameRound==1){
//
//            Map<String, Object> dataMap = Maps.newHashMap();
//            paramMap.put("gameRound", gameRound);
//
//            List<ActivityPrizeMarket> dataList = activityService.queryByDataMap(paramMap);
//
///*            int count = activityService.queryPrizeCount(paramMap);
//            int tempCount = 0;
//            if (marketCode.equals(M_WZ)||marketCode.equals(M_JQ)){
//                tempCount=30-count;
//
//            }else if (marketCode.equals(M_PJ)){
//                tempCount=1500-count;
//            }*/
//
//            //List<ActivityPrizeMarket> tempList = Lists.newArrayList();
//            //tempList.add(activityPrizeMarket);
//            int tempCount=0;
//            for (ActivityPrizeMarket activityPrizeMarket:dataList){
//                if (M_PJ.equals(activityPrizeMarket.getMarketCode())){
//                    tempCount=coupon_Count-activityPrizeMarket.getCount();
//                }else if (M_WZ.equals(activityPrizeMarket.getMarketCode())){
//                    tempCount=prize_Count-activityPrizeMarket.getCount();
//                }else if (M_JQ.equals(activityPrizeMarket.getMarketCode())){
//                    tempCount=JQ_Count-activityPrizeMarket.getCount();
//                }
//                activityPrizeMarket.setCount(tempCount>0?tempCount:0);
//            }
//            dataMap.put("dataList",dataList);
//            resultMap.setDataMap(dataMap);
//        }else if (gameRound==2){
//            Map<String, Object> dataMap = Maps.newHashMap();
//            paramMap.put("gameRound", gameRound);
//            dataMap.put("dataList", activityService.getPrizeList(paramMap));
//            resultMap.setDataMap(dataMap);
//        }
//
//        return resultMap;
//    }
//
//
//    @Autowired
//    MsgService msgService;
//
//    @Value("${temp_couponId}")
//    private  String couponId;
//
//   private static  final  String M_PJ="1169";
//   private static  final  String M_JQ="1119";
//   private static  final  String M_WZ="1113";
//
//   private static final Integer prize_Count=500;
//   private static final Integer JQ_Count=100;
//   private static final Integer coupon_Count=1500;
//
//
//   //private static  final  Integer CouponId =110;
//
//    @Autowired
//    IUserService iUserService;
//
//    @RequestMapping(value = "/addPrize", method = RequestMethod.POST)
//    Object getDataList(ActivityPrizeUser activityPrizeUser,HttpServletRequest httpServletRequest) {
//
//        Result<Map<String, Object>> resultMap = new Result("200", "请求成功");
//
//
//        if (StringUtils.isEmpty(activityPrizeUser.getOpenId()) || StringUtils.isEmpty(activityPrizeUser.getPrizeId()) || StringUtils.isEmpty(activityPrizeUser.getMobile())) {
//            resultMap.setMessage("参数缺失");
//            resultMap.setCode(ResultCode.C415.getCode());
//            return resultMap;
//        }
//
//        com.chinaredstar.uc.dubbo.core.api.vo.ServiceResult<UsersVo> serviceResult =iUserService.getUserInfoByOpenid(activityPrizeUser.getOpenId());
//        if (serviceResult.getData()==null){
//            resultMap.setCode("-401");
//            resultMap.setMessage("非法用户");
//            return  resultMap;
//        }else if (!activityPrizeUser.getMobile().equals(serviceResult.getData().getMobile())){
//            resultMap.setCode("-401");
//            resultMap.setMessage("非法用户");
//            return  resultMap;
//        }
//
//        ActivityPrizeMarket activityPrizeMarket = activityService.getActivityPrizeMarketById(activityPrizeUser.getPrizeId());
//
//        if (activityPrizeMarket==null){
//            resultMap.setMessage("商品不存在");
//            resultMap.setCode(ResultCode.C415.getCode());
//            return resultMap;
//        }
//
//        Map<String,Object> tempParams= Maps.newHashMap();
//        tempParams.put("openId",activityPrizeUser.getOpenId());
//        tempParams.put("gameRound",activityPrizeMarket.getGameRound());
//        if (activityService.queryShareCount(tempParams)>0){
//            resultMap.setMessage("您已领取过奖品啦!");
//            resultMap.setCode(ResultCode.C500.getCode());
//            return resultMap;
//        }
//
//
//
//
//
//
//        if (activityPrizeMarket.getGameRound()==1){
//
//            String marketCode=activityPrizeMarket.getMarketCode();
//            Map<String,Object> params = Maps.newHashMap();
//            params.put("marketCode",marketCode);
//            params.put("gameRound",activityPrizeMarket.getGameRound());
//            int count = activityService.queryPrizeCount(params);
//
//            int tempCount = 0;
//            if (marketCode.equals(M_WZ)){
//                tempCount=prize_Count-count;
//            }else if (marketCode.equals(M_PJ)){
//                tempCount=coupon_Count-count;
//            }else if(marketCode.equals(M_JQ)){
//                tempCount=JQ_Count-count;
//            }
//
//            if (tempCount<=0){
//                resultMap.setMessage("库存不足!");
//                resultMap.setCode(ResultCode.C500.getCode());
//                return resultMap;
//            }
//        }
//
//
//        //领券
//        CuponTakenResult result = null;
//        if (M_PJ.equals(activityPrizeMarket.getMarketCode())&&activityPrizeMarket.getGameRound()==1){
//
//            Map<String, Object> map = new HashMap<>();
//
//            String code = null;
//            String errMessage = null;
//            try {
//                Integer CouponId = Integer.parseInt(couponId);
//
//                result = cuponIssueService.userTakeCupon(activityPrizeUser.getOpenId(), CouponId, 1, "app", 1, null, null);
//
//                //将优惠券的状态返回
///*                PocCouponDetailView couponDetail = cuponQueryService.getCouponDetailById(CouponId,activityPrizeUser.getOpenId());
//                if (couponDetail != null) {
//                    String couponCode = couponDetail.getCouponCode();
//                    int status = 0;// 0:可以领取，1：已领取，2：抢光了，3：已结束
//                    if (couponCode != null) {
//                        status = 1;
//                    }
//                    int remainingCount = couponDetail.getRemainingCount();//剩余件数
//                    if (remainingCount <= 0) {
//                        status = 2;
//                    }
//                    Date endT = couponDetail.getEndT();
//                    if (new Date().getTime() > endT.getTime()) {
//                        status = 3;
//                    }
//                    map.put("status", status);
//                }*/
//            } catch (HighConcurrentException e) {
//                LOGGER.error(e.getMessage(), e);
//                code = ResultCode.C1018.getCode();
//                errMessage = ResultCode.C1018.getDesc();
//            } catch (OutOfStockException e) {
//                LOGGER.error(e.getMessage(), e);
//                code = ResultCode.C1014.getCode();
//                errMessage = ResultCode.C1014.getDesc();
//            } catch (ChannelNotExistException e) {
//                LOGGER.error(e.getMessage(), e);
//                code = ResultCode.C1016.getCode();
//                errMessage = ResultCode.C1016.getDesc();
//            } catch (NotFoundException e) {
//                LOGGER.error(e.getMessage(), e);
//                code = ResultCode.C1012.getCode();
//                errMessage = ResultCode.C1012.getDesc();
//            } catch (CuponAlreadyTakenException e) {
//                LOGGER.error(e.getMessage(), e);
//                code = ResultCode.C1013.getCode();
//                errMessage = ResultCode.C1013.getDesc();
//            } catch (OverCuponIssueTimeDuration | CuponInactiveException e) {
//                LOGGER.error(e.getMessage(), e);
//                code = ResultCode.C1015.getCode();
//                errMessage = ResultCode.C1015.getDesc();
//            } catch (Exception e) {
//                LOGGER.error(e.getMessage(), e);
//                code = ResultCode.C500.getCode();
//                errMessage = ResultCode.C500.getDesc();
//            }
//            if(code!=null && errMessage!=null){
//                resultMap.setMessage(errMessage);
//                resultMap.setCode(code);
//                return  resultMap;
//            }else{
//                //没有抛异常
//                if (result != null) {
//                    if (result.getResult() == 2) {
//                        //抛错
//                        resultMap.setMessage("领券失败");
//                        resultMap.setCode(ResultCode.C500.getCode());
//                        return  resultMap;
//                    }else{
//                        map.put("result", result);
//                        resultMap.setDataMap(map);
//                        resultMap.setCode(ResultCode.C200.getCode());
//                        resultMap.setMessage("操作成功");
//                    }
//                }
//            }
//
//        }
//
//
//        activityPrizeUser.setGameRound(activityPrizeMarket.getGameRound());
//        activityPrizeUser.setMarketCode(activityPrizeMarket.getMarketCode());
//
//        int insertResult = activityService.addUserPrize(activityPrizeUser);
//
//        LOGGER.info("---->奖品领取result---->" + result);
//
///*        if (insertResult == 500) {
//            resultMap.setMessage("您已领取过奖品啦!");
//            resultMap.setCode(ResultCode.C500.getCode());
//            return resultMap;
//        }*/
//
//        if (insertResult == 401) {
//            resultMap.setMessage("库存不足!");
//            resultMap.setCode(ResultCode.C500.getCode());
//            return resultMap;
//        }
//
//        if (insertResult < 0) {
//            resultMap.setMessage("服务器打瞌睡啦,稍后再试吧");
//            resultMap.setCode(ResultCode.C500.getCode());
//            return resultMap;
//        }
//
//
//        //if(activityPrizeMarket!=null){
//
//            String smsCode=getValidateCode();
//            StringBuffer smsBuffer = new StringBuffer();
//
//            if(activityPrizeMarket.getGameRound()==1&&!M_PJ.equals(activityPrizeMarket.getMarketCode())){
//
//                if (M_JQ.equals(activityPrizeMarket.getMarketCode())){
//                    smsBuffer.append("恭喜您参与 [2天来了]拼图游戏，第一关挑战成功，凭兑换码领取").append(activityPrizeMarket.getPrizeName())
//                            .append("，请3月份周末前往红星美凯龙").append(activityPrizeMarket.getMarketName()).append("领取，兑换码为").append(smsCode).append("，数量有限先到先得，咨询热线")
//                            .append(ActivityUtil.getMarketInfo(activityPrizeMarket.getMarketCode()).get("tel"));
//
//                }else if (M_WZ.equals(activityPrizeMarket.getMarketCode())){
//                    smsBuffer.append("恭喜您参与 [2天来了]拼图游戏，第一关挑战成功，凭兑换码领取").append(activityPrizeMarket.getPrizeName())
//                            .append("，请3月31日前前往红星美凯龙").append(activityPrizeMarket.getMarketName()).append("领取，兑换码为").append(smsCode).append("，数量有限先到先得，咨询热线")
//                            .append(ActivityUtil.getMarketInfo(activityPrizeMarket.getMarketCode()).get("tel"));
//                }
//
//            }else if (activityPrizeMarket.getGameRound()==2){
//                String marketCode=activityPrizeMarket.getMarketCode();
//                if (M_PJ.equals(marketCode)||M_JQ.equals(marketCode)){
//                    smsBuffer.append("恭喜您，参与[2天来了]拼图游戏，第二关挑战成功，凭兑换码享受")
//                            .append(activityPrizeMarket.getPrizeName())
//                            .append("惊爆价，请于3.11-3.12前往红星美凯龙").append(activityPrizeMarket.getMarketName()).append(activityPrizeMarket.getShopNumber())
//                            .append("购买，兑换码").append(smsCode).append(",咨询热线").append(ActivityUtil.getMarketInfo(marketCode).get("tel"));
//                }else if (M_WZ.equals(marketCode)){
//                    smsBuffer.append("恭喜您，参与[2天来了]拼图游戏，第二关挑战成功，凭兑换码")
//                             .append(smsCode).append("，于3.11-3.12日前往红星美凯龙吴中商场参与抽奖，详情请咨询热线")
//                             .append(ActivityUtil.getMarketInfo(marketCode).get("tel"));
//                }
//            }
//
//            String smsStr = smsBuffer.toString();
//            if (!StringUtils.isEmpty(smsStr)){
//                String ip=httpServletRequest.getRemoteAddr();
//                if (StringUtils.isEmpty(ip)){
//                    ip="127.0.0.1";
//                }
//                msgService.sendMessage(activityPrizeUser.getMobile(),null,null,null,smsStr,ip);
//            }
//        //}
//
//
//        LOGGER.info("----奖品领取成功----");
//
//        return resultMap;
//    }
//
//
//    public  static  String  getValidateCode(){
//        return String.valueOf(Math.random()).substring(2,6);
//    }
//
//}
