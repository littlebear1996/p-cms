package com.chinaredstar.cms.utils;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by niu on 2017/2/28.
 */
public class ActivityUtil {


    public  static Map<String,String> getMarketInfo(String marketCode){

        Map<String,String> dataMap = Maps.newHashMap();
        if ("1169".equals(marketCode)){
            dataMap.put("tel","021-54331999");
        }else if ("1119".equals(marketCode)){
            dataMap.put("tel","021-31215888");
        }else if("1113".equals(marketCode)){
            dataMap.put("tel","021-66551888");
        }
        return  dataMap;
    }

}
