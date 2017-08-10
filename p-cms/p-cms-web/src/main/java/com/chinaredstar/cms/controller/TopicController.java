package com.chinaredstar.cms.controller;

import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.model.CmsTopicDetail;
import com.chinaredstar.cms.api.model.CmsTopicType;
import com.chinaredstar.cms.api.service.TopicService;
import com.chinaredstar.cms.api.vo.topic.TopicDetailQueryVo;
import com.chinaredstar.cms.api.vo.topic.TopicTypeQueryVo;
import com.chinaredstar.cms.vo.Result;
import com.chinaredstar.cms.vo.ResultCode;
import com.codahale.metrics.annotation.Timed;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**专题合辑
 * Created by Ykk on 2016/11/11.
 */
@RestController
@RequestMapping("/topic")
@Api(value = "topic", description = "专题合辑接口")
@Timed
public class TopicController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TopicController.class);

    @Autowired
    private TopicService topicService;

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 404, message = "返回数据为空"),
            @ApiResponse(code = 415, message = "请求参数错误"),
            @ApiResponse(code = 422, message = "校验错误"),
            @ApiResponse(code = 500, message = "系统内部错误")
    })

    @ApiOperation(value = "获取专题详情", notes = "获取专题详情",response =CmsTopicDetail.class)
    @RequestMapping(value = "detail", method = RequestMethod.POST)
    public Result<List<CmsTopicDetail>> getTopicDetail(@ModelAttribute TopicDetailQueryVo vo) {
        Result<List<CmsTopicDetail>> result = new Result<>();
        if(vo == null || vo.getTopicTypeId() == null){
            result.setCode(ResultCode.C415.getCode());
            result.setMessage("请求参数不正确，topicTypeId不能为空");
            return result;
        }
        try {
            ServiceResult<List<CmsTopicDetail>>  detailServiceResultList = topicService.getTopicDetail(vo);
            if (detailServiceResultList.isSuccess()){
                //数据处理、排序

                result.setCode(ResultCode.C200.getCode());
                result.setDataMap(detailServiceResultList.getData());
            }
        } catch (Exception e) {
            LOGGER.error("获取专题详情数据异常：{}", e);
            e.printStackTrace();
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("获取专题详情异常");
        }
        return result;
    }


    @ApiOperation(value = "获取专题分类", notes = "获取专题分类", response = CmsTopicType.class)
    @RequestMapping(value = "type", method = RequestMethod.POST)
    public Result<List<CmsTopicType>> getTopicType(@ModelAttribute TopicTypeQueryVo vo) {
        Result<List<CmsTopicType>> result = new Result<>();
        if(vo == null || vo.getTopicId() == null){
            result.setCode(ResultCode.C415.getCode());
            result.setMessage("请求参数不正确，topicId不能为空");
            return result;
        }
        try {
            ServiceResult<List<CmsTopicType>> topicTypeServiceResult = topicService.getTopicType(vo);
            if (topicTypeServiceResult.isSuccess()) {
                //数据处理、排序

                result.setCode(ResultCode.C200.getCode());
                result.setDataMap(topicTypeServiceResult.getData());
            }
        } catch (Exception e) {
            LOGGER.error("获取专题分类数据异常：{}", e);
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("获取专题分类异常");
        }
        return result;
    }

}
