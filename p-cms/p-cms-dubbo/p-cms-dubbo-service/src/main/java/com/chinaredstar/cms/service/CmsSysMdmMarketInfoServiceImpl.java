package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.model.MdmDistrict;
import com.chinaredstar.cms.api.model.MdmMarketInfo;
import com.chinaredstar.cms.api.model.MdmRegion;
import com.chinaredstar.cms.api.service.CmsSysMdmMarketInfoService;
import com.chinaredstar.cms.mapper.MdmDistrictMapper;
import com.chinaredstar.cms.mapper.MdmMarketInfoMapper;
import com.chinaredstar.cms.mapper.MdmRegionMapper;
import com.chinaredstar.mdm.api.IMdSyncExternalService;
import com.chinaredstar.mdm.vo.mdQuery.request.MdQueryRequestVo;
import com.chinaredstar.mdm.vo.mdQuery.response.MdQueryResponseVo;
import com.chinaredstar.mdm.vo.sync.request.DispatchApiVo;
import com.chinaredstar.mdm.vo.sync.request.DispatchValuesVo;
import com.chinaredstar.mdm.vo.sync.response.DispatchCallbackInfoVo;
import com.chinaredstar.mdm.vo.sync.response.DispatchCallbackMappingVo;
import com.chinaredstar.mdm.vo.sync.response.DispatchCallbackResultVo;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaobc on 16-8-17.
 */
@Service("cmsSysMdmMarketInfoService")
@Timed
public class CmsSysMdmMarketInfoServiceImpl implements CmsSysMdmMarketInfoService, IMdSyncExternalService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CmsSysMdmMarketInfoServiceImpl.class);

    @Autowired
    private MdmDistrictMapper mdmDistrictMapper;
    @Autowired
    private MdmMarketInfoMapper mdmMarketInfoMapper;
    @Autowired
    private MdmRegionMapper mdmRegionMapper;

    /**
     * 同步主逻辑
     *
     * @param dispatchApiVo
     * @param s
     * @param s1
     * @return
     */
    @Override
    public DispatchCallbackInfoVo syncMd(DispatchApiVo dispatchApiVo, String s, String s1) {
        long start = System.currentTimeMillis();
        LOGGER.info("sysMdmMarketInfo请求参数：" + dispatchApiVo);

        DispatchCallbackInfoVo result = new DispatchCallbackInfoVo();

        if (dispatchApiVo == null) {
            LOGGER.error("dispatchApiVo must not be null");
            result.setInvokeCode("500");
            result.setInvokeMsg("dispatchApiVo must not be null");
            return result;
        }
        try {

            String tableName = dispatchApiVo.getName();
            List<DispatchValuesVo> values = dispatchApiVo.getDispatchValuesVos();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//            List<Map<String, Object>> resultItem = new ArrayList<Map<String, Object>>();
//            Map<String, Object> itemMap = new HashMap<String, Object>();

            List<DispatchCallbackResultVo> resultItem = new ArrayList<DispatchCallbackResultVo>();
            DispatchCallbackResultVo itemMap = new DispatchCallbackResultVo();

            if (tableName != null && "MDM_MALL".equals(tableName)) {
                //同步商场
                if (values != null) {
                    for (DispatchValuesVo valuesVo : values) {

                        //遍历所有商场数据
                        String changeType = String.valueOf(valuesVo.getChangeType());
                        Map<String, Object> fields = valuesVo.getValue();
                        if (changeType != null && fields != null) {
                            String mdGuid = String.valueOf(fields.get("MD_GUID"));
                            String mdmId = String.valueOf(fields.get("MDM_ID"));
                            String mallCode = String.valueOf(fields.get("MALL_CODE"));//商场主数据编号
                            String full_name = String.valueOf(fields.get("FULL_NAME"));//商场名称
                            String short_name = String.valueOf(fields.get("SHORT_NAME"));//商场简称
                            String province_code = String.valueOf(fields.get("PROVINCE_CODE"));//省code
                            String country_code = String.valueOf(fields.get("COUNTRY_CODE"));//区县code
                            String address = String.valueOf(fields.get("ADDRESS"));//详细地址
                            String gps = String.valueOf(fields.get("GPS"));//gps
                            String telephone = String.valueOf(fields.get("TELEPHONE"));//电话
                            String status = String.valueOf(fields.get("STATUS"));//状态  项目、筹备、正常、闭店
                            String createtime = String.valueOf(fields.get("CREATETIME"));//创建时间
                            String modifytime = String.valueOf(fields.get("MODIFYTIME"));//更新时间
                            String oms_temp = String.valueOf(fields.get("OMS_TEMP"));//oms的marketNUmber
                            String delete = String.valueOf(fields.get("DELETE"));//删除标识符
                            String region_one = String.valueOf(fields.get("REGION_ONE"));//大区code
                            String region_two = String.valueOf(fields.get("REGION_TWO"));//小区code
                            String city_code = String.valueOf(fields.get("CITY_CODE"));//城市code

                            String regionOneName = "";//大区名称
                            String regionTwoName = "";//小区名称
                            String cityName = "";//城市名称
                            String provinceName = "";//省名称


                            if (status == null || "".equals(status) || !"正常".equals(status) || mdGuid == null || "".equals(mdGuid)) {
                                //不是正常的状态的商场不同步
                                if(status == null || "".equals(status) || !"正常".equals(status)){
                                    changeType = "delete";
                                }else{
                                    continue;
                                }
                            }
                            if (city_code != null && !"".equals(city_code) && !"null".equals(city_code)) {
                                List<MdmDistrict> list = mdmDistrictMapper.queryByDistrictCode(city_code);
                                if (list != null && list.size() > 0) {
                                    cityName = list.get(0).getDistrictName();
                                }
                            }
                            if (province_code != null && !"".equals(province_code) && !"null".equals(province_code)) {
                                List<MdmDistrict> list = mdmDistrictMapper.queryByDistrictCode(province_code);
                                if (list != null && list.size() > 0) {
                                    provinceName = list.get(0).getDistrictName();
                                }
                            }
                            if (region_one != null && !"".equals(region_one) && !"null".equals(region_one)) {
                                List<MdmRegion> list = mdmRegionMapper.queryByRegionCode(region_one);
                                if (list != null && list.size() > 0) {
                                    regionOneName = list.get(0).getRegionName();
                                }

                            }
                            if(region_two!=null && !"".equals(region_two) && !"null".equals(region_two)){
                                List<MdmRegion> list2 = mdmRegionMapper.queryByRegionCode(region_two);
                                if (list2 != null && list2.size() > 0) {
                                    regionTwoName = list2.get(0).getRegionName();
                                }
                            }

                            List<MdmMarketInfo> list = mdmMarketInfoMapper.queryByMdGuid(mdGuid);

                            int id = 0;
                            if (list != null && list.size() > 0) {
                                MdmMarketInfo item = list.get(0);
                                id = item.getId();
                            }
                            if ("delete".equals(changeType) && id>0) {
                                //删除操作
                                mdmMarketInfoMapper.deleteByMdGuid(mdGuid);
                            } else {
                                MdmMarketInfo market = new MdmMarketInfo();
                                market.setCityId(city_code);
                                market.setCityName(cityName);
                                market.setCreateDate(sdf.parse(createtime));
                                market.setDistrictId(0);
                                if(region_one!=null){
                                    market.setFirstOrgId(Integer.parseInt(region_one));
                                }
                                if (province_code != null && !"".equals(province_code) && !"null".equals(province_code)) {
                                    market.setProvinceId(province_code);
                                }
                                if(!"".equals(provinceName)){
                                    market.setProvinceName(provinceName);
                                }
                                market.setFirstOrgName(regionOneName);
                                market.setGps(gps);
                                market.setIsDel(Integer.parseInt(delete));
                                market.setMarketAddress(address);
                                market.setMarketName(full_name);
                                market.setMarketNumber(oms_temp);
                                market.setMarketShort(short_name);
                                market.setMdGuid(mdGuid);
                                market.setMdmId(mdmId);
                                if(region_two!=null&& !"".equals(region_two) && !"null".equals(region_two)){
                                    market.setSecondOrgId(Integer.parseInt(region_two));
                                }
                                if( mallCode!=null&& !"".equals(mallCode) && !"null".equals(mallCode)){
                                    market.setMallCode(mallCode);
                                }
                                market.setSecondOrgName(regionTwoName);
                                market.setTelephone(telephone);
                                market.setUpdateDate(sdf.parse(modifytime));
                                //没值，插入
                                if (list == null || list.size() <= 0) {
                                    mdmMarketInfoMapper.insert(market);
                                    if(market.getId()>0){
                                        id = market.getId();
                                    }
                                } else {
                                    //有值 更新
                                    mdmMarketInfoMapper.updateByMdGuid(market);
                                }
                            }
                            itemMap.setChangeType(changeType);
                            itemMap.setCode("200");
                            itemMap.setMessage("success");
                            itemMap.setGuId(mdGuid);
                            DispatchCallbackMappingVo mappingVo = new DispatchCallbackMappingVo();
                            mappingVo.setId(String.valueOf(id));
                            mappingVo.setMdmId(mdGuid);
                            itemMap.setDispatchCallbackMappingVo(mappingVo);
                            resultItem.add(itemMap);
                        }
                    }
                    result.setInvokeCode("200");
                    result.setInvokeMsg("success");
                    result.setName("MDM_MALL");
                    result.setResults(resultItem);
                }
            } else if (tableName != null && "MDM_REGION".equals(tableName)) {
                //同步区域
                if (values != null) {
                    for (DispatchValuesVo valuesVo : values) {
                        //遍历所有区域数据
                        String changeType = valuesVo.getChangeType();
                        Map<String, Object> fields = valuesVo.getValue();
                        if (changeType != null && fields != null) {
                            String mdGuid = String.valueOf(fields.get("MD_GUID"));
                            String mdmId = String.valueOf(fields.get("MDM_ID"));
                            String region_code = String.valueOf(fields.get("REGION_CODE"));
                            String region_name = String.valueOf(fields.get("REGION_NAME"));
                            String parent_region_code = String.valueOf(fields.get("PARENT_REGION_CODE"));
                            String region_class = String.valueOf(fields.get("REGION_CLASS"));
                            String delete = String.valueOf(fields.get("DELETE"));
                            String region_ps_code = String.valueOf(fields.get("REGION_PS_CODE"));

                            if (region_code == null || "".equals(region_code)) {
                                continue;
                            }
                            int id = 0;
                            List<MdmRegion> list = mdmRegionMapper.queryByMdGuid(mdGuid);
                            if (list != null && list.size() > 0) {
                                MdmRegion item = list.get(0);
                                id = item.getId();
                            }
                            if ("delete".equals(changeType)) {
                                //删除操作
                                mdmRegionMapper.deleteByMdGuid(mdGuid);
                            } else {
                                MdmRegion region = new MdmRegion();
                                region.setMdmId(mdmId);
                                region.setMdGuid(mdGuid);
                                region.setIsDel(Integer.parseInt(delete));
                                region.setParentRegionCode(parent_region_code);
                                region.setRegionClass(region_class);
                                region.setRegionCode(region_code);
                                region.setRegionName(region_name);
                                region.setRegionPsCode(region_ps_code);

                                if (list == null || list.size() <= 0) {
                                    mdmRegionMapper.insert(region);
                                    if(region.getId()>0){
                                        id = region.getId();
                                    }
                                } else {
                                    //有值 更新
                                    mdmRegionMapper.updateByMdGuid(region);
                                }
                            }
                            itemMap.setChangeType(changeType);
                            itemMap.setCode("200");
                            itemMap.setMessage("success");
                            itemMap.setGuId(mdGuid);
                            DispatchCallbackMappingVo mappingVo = new DispatchCallbackMappingVo();
                            mappingVo.setId(String.valueOf(id));
                            mappingVo.setMdmId(mdGuid);
                            itemMap.setDispatchCallbackMappingVo(mappingVo);
                            resultItem.add(itemMap);
                        }
                    }
                    result.setInvokeCode("200");
                    result.setInvokeMsg("success");
                    result.setName("MDM_REGION");
                    result.setResults(resultItem);
                }

            } else if (tableName != null && "MDM_DISTRICT".equals(tableName)) {
                //同步城市
                if (values != null) {
                    for (DispatchValuesVo valuesVo : values) {
                        //遍历所有区域数据
                        String changeType = valuesVo.getChangeType();
                        Map<String, Object> fields = valuesVo.getValue();
                        if (changeType != null && fields != null) {
                            String mdGuid = String.valueOf(fields.get("MD_GUID"));
                            String mdmId = String.valueOf(fields.get("MDM_ID"));
                            String district_code = String.valueOf(fields.get("DISTRICT_CODE"));
                            String district_name = String.valueOf(fields.get("DISTRICT_NAME"));
                            String district_level = String.valueOf(fields.get("DISTRICT_LEVEL"));
                            String parent_district_code = String.valueOf(fields.get("PARENT_DISTRICT_CODE"));
                            String delete = String.valueOf(fields.get("DELETE"));

                            if (district_code == null || "".equals(district_code)) {
                                continue;
                            }
                            int id = 0;
                            List<MdmDistrict> list = mdmDistrictMapper.queryByMdGuid(mdGuid);
                            if (list != null && list.size() > 0) {
                                MdmDistrict item = list.get(0);
                                id = item.getId();
                            }
                            if ("delete".equals(changeType)) {
                                //删除操作
                                mdmDistrictMapper.deleteByMdGuid(mdGuid);
                            } else {
                                MdmDistrict district = new MdmDistrict();
                                district.setIsDel(Integer.parseInt(delete));
                                district.setDistrictCode(district_code);
                                district.setDistrictLevel(Integer.parseInt(district_level));
                                district.setDistrictName(district_name);
                                district.setMdGuid(mdGuid);
                                district.setMdmId(mdmId);
                                district.setParentDistrictCode(parent_district_code);

                                if (list == null || list.size() <= 0) {
                                    mdmDistrictMapper.insert(district);
                                    if (district.getId() > 0) {
                                        id = district.getId();
                                    }
                                } else {
                                    //有值 更新
                                    mdmDistrictMapper.updateByMdGuid(district);
                                }
                            }
                            itemMap.setChangeType(changeType);
                            itemMap.setCode("200");
                            itemMap.setMessage("success");
                            itemMap.setGuId(mdGuid);
                            DispatchCallbackMappingVo mappingVo = new DispatchCallbackMappingVo();
                            mappingVo.setId(String.valueOf(id));
                            mappingVo.setMdmId(mdGuid);
                            itemMap.setDispatchCallbackMappingVo(mappingVo);
                            resultItem.add(itemMap);
                        }
                    }
                    result.setInvokeCode("200");
                    result.setInvokeMsg("success");
                    result.setName("MDM_DISTRICT");
                    result.setResults(resultItem);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("同步主数据商场信息, {}", e);
            result.setInvokeCode("500");
            result.setInvokeMsg(e.toString());
        }
        long end = System.currentTimeMillis();
        long time = end - start;
        LOGGER.info("sysMdmMarketInfo返回数据：" + result + ",耗时：" + (time / 1000) + "秒" + (time % 1000) + "毫秒");
        return result;
    }

    @Override
    public DispatchCallbackInfoVo syncMdBaseJson(String s, String s1, String s2) {
        return null;
    }

    @Override
    public MdQueryResponseVo query(MdQueryRequestVo mdQueryRequestVo) {
        return null;
    }
}
