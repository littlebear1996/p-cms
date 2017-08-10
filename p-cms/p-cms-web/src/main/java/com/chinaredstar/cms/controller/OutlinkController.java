package com.chinaredstar.cms.controller;import com.chinaredstar.cms.api.component.ServiceResult;import com.chinaredstar.cms.api.service.OutlinkService;import com.chinaredstar.cms.api.vo.outlink.OutlinkVo;import com.chinaredstar.cms.vo.Result;import com.chinaredstar.cms.vo.ResultCode;import com.codahale.metrics.annotation.Timed;import com.wordnik.swagger.annotations.Api;import org.slf4j.Logger;import org.slf4j.LoggerFactory;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.web.bind.annotation.PathVariable;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RequestMethod;import org.springframework.web.bind.annotation.RestController;import java.util.ArrayList;import java.util.List;/** * Created by sunny on 16-8-26. */@RestController@RequestMapping("/outlink")@Api(value = "outlink", description = "外链相关接口")@Timedpublic class OutlinkController {    private static final Logger LOGGER = LoggerFactory.getLogger(OutlinkController.class);    @Autowired    private OutlinkService outlinkService;    @RequestMapping(value = "/listByIds/{ids}", method = RequestMethod.GET)    public Result<List<OutlinkVo>> getOutlinksByIds(@PathVariable("ids") String ids) {        Result<List<OutlinkVo>> result = new Result<>();        String[] idList = ids.split(",");        if (idList == null || idList.length == 0) {            LOGGER.error("idList must not be null");            result.setCode(ResultCode.C500.getCode());            result.setMessage("idList must not be null");            return result;        }        try {            List<Integer> outlinkIds = new ArrayList<>();            for (String id : idList) {                outlinkIds.add(Integer.valueOf(id));            }            ServiceResult<List<OutlinkVo>> serviceResult = outlinkService.getOutLinkByIds(outlinkIds);            if (serviceResult != null && serviceResult.isSuccess()) {                result.setCode(ResultCode.C200.getCode());                result.setDataMap(serviceResult.getData());            } else {                result.setCode(ResultCode.C500.getCode());                if(serviceResult != null) {                    result.setMessage(serviceResult.getMsg());                }            }        } catch (Exception e) {            LOGGER.error("系统发生异常：{}", e);            result.setCode(ResultCode.C500.getCode());            result.setDataMap(null);            result.setMessage("服务发生异常");        }        return result;    }}