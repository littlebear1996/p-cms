package com.chinaredstar.cms.service;

import com.chinaredstar.cms.api.component.MdmServiceResult;
import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.model.CmsTopicDetail;
import com.chinaredstar.cms.api.model.CmsTopicType;
import com.chinaredstar.cms.api.service.CmsSysMdmMarketInfoService;
import com.chinaredstar.cms.api.service.TopicService;
import com.chinaredstar.cms.api.vo.mdm.MdmVo;
import com.chinaredstar.cms.api.vo.topic.TopicDetailQueryVo;
import com.chinaredstar.cms.api.vo.topic.TopicTypeQueryVo;
import com.chinaredstar.cms.api.vo.topic.TopicVo;
import com.chinaredstar.mdm.api.IMdSyncExternalService;
import com.chinaredstar.mdm.vo.sync.request.DispatchApiVo;
import com.chinaredstar.mdm.vo.sync.request.DispatchValuesVo;
import com.chinaredstar.mdm.vo.sync.response.DispatchCallbackInfoVo;
import com.chinaredstar.mdm.vo.sync.response.DispatchCallbackMappingVo;
import com.chinaredstar.perseus.utils.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.ws.Dispatch;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaobc on 2016/11/14.
 */
public class CmsSysMdmMarketInfoServiceImplTest extends BaseTest{

    @Autowired
    private IMdSyncExternalService cmsSysMdmMarketInfoService;

    @Test
    public void testDistrict(){

        DispatchApiVo vo = new DispatchApiVo();
        vo.setName("MDM_DISTRICT");

        List<DispatchValuesVo> valuesVoList = new ArrayList<DispatchValuesVo>();
        DispatchValuesVo valueVo = new DispatchValuesVo();
        valueVo.setChangeType("isnert");
        Map<String,Object> value = new HashMap<String,Object>();
        value.put("MD_GUID","1230000qwe");
        value.put("MDM_ID","a0003123");
        value.put("DISTRICT_CODE","3211");
        value.put("DISTRICT_NAME","测试");
        value.put("DISTRICT_LEVEL",1);
        value.put("PARENT_DISTRICT_CODE","0");
        value.put("DELETE", 0);
        valueVo.setValue(value);
        valuesVoList.add(valueVo);
        vo.setDispatchValuesVos(valuesVoList);

        DispatchCallbackInfoVo result =  cmsSysMdmMarketInfoService.syncMd(vo,null,null);
        System.out.println("result=" + result);

    }


    @Test
    public void testRegion(){

        DispatchApiVo vo = new DispatchApiVo();
        vo.setName("MDM_REGION");

        List<DispatchValuesVo> valuesVoList = new ArrayList<DispatchValuesVo>();
        DispatchValuesVo valueVo = new DispatchValuesVo();
        valueVo.setChangeType("update");
        Map<String,Object> value = new HashMap<String,Object>();
        value.put("MD_GUID","35F17B2350D64E70A911FBE666E478D1");
        value.put("MDM_ID","O21367761492454079");
        value.put("REGION_CODE","1005");
        value.put("REGION_NAME","大连区域");
        value.put("PARENT_REGION_CODE","1000");
        value.put("DELETE", 0);
        valueVo.setValue(value);
        valuesVoList.add(valueVo);
        vo.setDispatchValuesVos(valuesVoList);

        DispatchCallbackInfoVo result =  cmsSysMdmMarketInfoService.syncMd(vo,null,null);
        System.out.println("result=" + result);

    }


    @Test
    public void testMarket(){

        DispatchApiVo vo = new DispatchApiVo();
        vo.setName("MDM_MALL");

        List<DispatchValuesVo> valuesVoList = new ArrayList<DispatchValuesVo>();
        DispatchValuesVo valueVo = new DispatchValuesVo();
        valueVo.setChangeType("update");
        Map<String,Object> value = new HashMap<String,Object>();
        value.put("MD_GUID","c64cb16a-973f-43f5-952d-c0a4969d4402");
        value.put("MDM_ID","R81621483714799000");
        value.put("REGION_ONE","6100");
        value.put("OMS_TEMP","1090");
        value.put("STATUS","正常");
        value.put("PS_CODE","20002402");
//        value.put("REGION_TWO","4001");
        value.put("CITY_CODE","640100");
        value.put("MALL_CODE","10082");
        value.put("FULL_NAME","银川丽景商场");
        value.put("MODIFYTIME","2017-04-17 21:55:35");
        value.put("ADDRESS","银川市兴庆区丽景南街316号");
        value.put("CREATETIME","2017-03-14 10:02:36");
        value.put("GPS","106.302428;38.454409");
        value.put("PROVINCE_CODE","640000");
        value.put("DELETE", 0);
        valueVo.setValue(value);
        valuesVoList.add(valueVo);
        vo.setDispatchValuesVos(valuesVoList);

        DispatchCallbackInfoVo result =  cmsSysMdmMarketInfoService.syncMd(vo,null,null);
        System.out.println("result=" + result);

    }



}
