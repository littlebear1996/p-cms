/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.chinaredstar.cms.controller;

import com.chinaredstar.cms.api.component.Page;
import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.StudentService;
import com.chinaredstar.cms.api.vo.zoo.StudentVo;
import com.chinaredstar.cms.api.vo.zoo.TeacherVo;
import com.chinaredstar.cms.vo.Result;
import com.chinaredstar.cms.vo.ResultCode;
import com.codahale.metrics.annotation.Timed;
import com.wordnik.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 学生信息页面
 * @author jianxiong.lei
 * @version $Id StudentController.java, v 0.1 2017-08-01 下午2:26 Littlebear1996 Exp $$
 */
@RestController
@RequestMapping("/students")
@Api(value = "student",description = "学生信息接口")
public class StudentController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @ApiResponses( value = {
            @ApiResponse(code = 200,message = "请求成功"),
            @ApiResponse(code = 404,message = "返回数据为空"),
            @ApiResponse(code = 415,message = "请求参数错误"),
            @ApiResponse(code = 422,message = "校验错误"),
            @ApiResponse(code = 500,message = "系统内部错误")
    })

    @ApiOperation(value = "添加一个学生信息",notes = "添加一个学生信息")
    @RequestMapping(value = "",method = RequestMethod.POST)
    public Result addStudent(@ModelAttribute(value = "studentVo") StudentVo studentVo){
        Result result = new Result();
        if(studentVo == null){
            result.setCode(ResultCode.C415.getCode());
            result.setMessage("请求参数不正确,学生信息不能为空");
            return result;
        }
        try {
                ServiceResult serviceResult = studentService.addStudent(studentVo);
                if(serviceResult.isSuccess()){
                result.setCode(ResultCode.C200.getCode());
            }
        }catch (Exception e){
            LOGGER.error("添加学生信息异常");
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("添加学生信息异常");
        }
        return result;
    }
    @ApiResponses( value = {
            @ApiResponse(code = 200,message = "请求成功"),
            @ApiResponse(code = 404,message = "返回数据为空"),
            @ApiResponse(code = 415,message = "请求参数错误"),
            @ApiResponse(code = 422,message = "校验错误"),
            @ApiResponse(code = 500,message = "系统内部错误")
    })
    @ApiOperation(value = "通过id获取一个学生信息",notes = "通过id获取一个学生信息")
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public Result<StudentVo> getStudent(@ApiParam("学生id") @PathVariable("id") Integer id){
        LOGGER.info("-----------通过学生id查询学生信息----------");
        Result<StudentVo> result = new Result<>(ResultCode.C200.getCode());
        if(id == null){
            LOGGER.error("id不能为空");
            result.setCode(ResultCode.C415.getCode());
            result.setMessage("学生id不能为空");
            return result;
        }
        try{
            ServiceResult<StudentVo> serviceResult = studentService.getStudent(id);
            if(serviceResult.isSuccess()){
                result.setDataMap(serviceResult.getData());
            }else{
                result.setCode(ResultCode.C500.getCode());
                result.setMessage("服务端返回错误");
            }
        }catch (Exception e){
            LOGGER.error("调用Dubbo接口时服务器发生异常"+e.getMessage());
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("系统内部错误");
        }
        return result;
    }
    @ApiResponses( value = {
            @ApiResponse(code = 200,message = "请求成功"),
            @ApiResponse(code = 404,message = "返回数据为空"),
            @ApiResponse(code = 415,message = "请求参数错误"),
            @ApiResponse(code = 422,message = "校验错误"),
            @ApiResponse(code = 500,message = "系统内部错误")
    })
    @ApiOperation(value = "更新某个学生的信息",notes = "更新某个学生的信息")
    @RequestMapping(value = "",method = RequestMethod.PUT)
    public Result updateStudent(@ModelAttribute("vo") StudentVo vo){
        LOGGER.info("开始更新......");
        Result result = new Result();
        try{
            ServiceResult serviceResult = studentService.updateStudent(vo);
            if(serviceResult.isSuccess()){
                result.setCode(ResultCode.C200.getCode());
            }else{
                result.setCode(ResultCode.C500.getCode());
                result.setMessage("更新学生信息失败");
            }
        }catch (Exception e){
            LOGGER.error("更新学生信息失败");
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("服务端发生未知错误,更新学生信息失败");
        }
        return result;
    }
    @ApiResponses( value = {
            @ApiResponse(code = 200,message = "请求成功"),
            @ApiResponse(code = 404,message = "返回数据为空"),
            @ApiResponse(code = 415,message = "请求参数错误"),
            @ApiResponse(code = 422,message = "校验错误"),
            @ApiResponse(code = 500,message = "系统内部错误")
})
    @ApiOperation(value = "通过id删除学生信息",notes = "通过id删除学生信息")
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public Result deleteStudent(@PathVariable("id") Integer id){
        LOGGER.info("-----------通过学生id删除学生信息----------");
        Result result = new Result();
        if(id == null){
            LOGGER.error("id不能为空");
            result.setCode(ResultCode.C415.getCode());
            result.setMessage("学生id不能为空!");
            return result;
        }
        try{
            ServiceResult serviceResult = studentService.deleteStudent(id);
            if(serviceResult.isSuccess()){
                result.setCode(ResultCode.C200.getCode());
                result.setMessage("删除成功");
            }else{
                result.setCode(ResultCode.C500.getCode());
                result.setMessage(serviceResult.getMsg());
            }
        }catch (Exception e){
            LOGGER.error("调用Dubbo接口时服务器发生异常"+e.getMessage());
            result.setCode(ResultCode.C500.getCode());
            result.setMessage("系统内部错误");
        }
        return result;
    }
    @ApiResponses( value = {
            @ApiResponse(code = 200,message = "请求成功"),
            @ApiResponse(code = 404,message = "返回数据为空"),
            @ApiResponse(code = 415,message = "请求参数错误"),
            @ApiResponse(code = 422,message = "校验错误"),
            @ApiResponse(code = 500,message = "系统内部错误")
    })
    @ApiOperation(value = "分页获取学生信息",notes = "分页获取学生信息")
    @RequestMapping(value = "list",method = RequestMethod.POST)
    public Result<List<StudentVo>> getListStudent(@ModelAttribute("page") Page page){
        Result<List<StudentVo>> result = new Result<>();
        try {
            ServiceResult<List<StudentVo>> serviceResult = studentService.getListStudent(page);
            if(serviceResult!=null && serviceResult.isSuccess()) {
                result.setCode(ResultCode.C200.getCode());
                result.setDataMap(serviceResult.getData());
                return result;
            }
            if(serviceResult!=null){
                LOGGER.error("获取学生列表失败"+serviceResult.getMsg());
            }
        } catch (Exception e){
            LOGGER.error("获取学生列表失败"+e.getMessage());
        }
        result.setCode(ResultCode.C500.getCode());
        result.setMessage("获取学生列表失败");
        return result;
    }
    @ApiResponses( value = {
            @ApiResponse(code = 200,message = "请求成功"),
            @ApiResponse(code = 404,message = "返回数据为空"),
            @ApiResponse(code = 415,message = "请求参数错误"),
            @ApiResponse(code = 422,message = "校验错误"),
            @ApiResponse(code = 500,message = "系统内部错误")
    })
    @ApiOperation(value = "根据学生id获取某学生的班主任详细信息",notes = "根据学生id获取某学生的班主任详细信息")
    @RequestMapping(value = "/teacher/{id}",method = RequestMethod.GET)
    public Result<TeacherVo> getTeacherById(@PathVariable("id") Integer id){
        Result<TeacherVo> result = new Result<>();
        if(id == null){
            result.setCode(ResultCode.C415.getCode());
            result.setMessage("输入id不能为空");
        }
        try{
            ServiceResult<TeacherVo> serviceResult = studentService.getTeacherById(id);
            if(serviceResult.isSuccess()){
                result.setCode(ResultCode.C200.getCode());
                result.setDataMap(serviceResult.getData());
                return result;
            }else {
                result.setCode(ResultCode.C500.getCode());
                result.setMessage(serviceResult.getMsg());
                return result;
            }
        }catch (Exception e){
            result.setCode(ResultCode.C500.getCode());
            result.setMessage(e.getMessage());
            return result;
        }
    }
}