/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.chinaredstar.cms.service;


import com.chinaredstar.cms.api.component.Page;
import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.service.StudentService;
import com.chinaredstar.cms.api.vo.zoo.StudentVo;
import com.chinaredstar.cms.api.vo.zoo.TeacherVo;
import com.chinaredstar.cms.mapper.StudentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jianxiong.lei
 * @version $Id StudentServiceImpl.java, v 0.1 2017-07-31 下午2:18 Littlebear1996 Exp $$
 */
@Service("StudentService")
public class StudentServiceImpl implements StudentService{

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public ServiceResult addStudent(StudentVo vo) {
        ServiceResult serviceResult = new ServiceResult();
        if (vo == null) {
            serviceResult.setSuccess(false);
            serviceResult.setMsg("参数不能为空");
            return serviceResult;
        }
        try {
            studentMapper.addStudent(vo);
            serviceResult.setSuccess(true);
            serviceResult.setData(vo);
        }catch (Exception e) {
            LOGGER.error("学生信息保存失败");
            serviceResult.setSuccess(false);
            serviceResult.setMsg("系统内部错误");
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<StudentVo> getStudent(Integer id) {
        ServiceResult serviceResult = new ServiceResult();
        if(id == null){
            serviceResult.setSuccess(false);
            serviceResult.setMsg("id不能为空");
            return serviceResult;
        }
        try{
            serviceResult.setData(studentMapper.getStudent(id));
            serviceResult.setSuccess(true);
        }catch (Exception e){
            LOGGER.error("获取学生信息失败");
            serviceResult.setSuccess(false);
            serviceResult.setMsg("服务器内部错误");
        }
        return serviceResult;
    }

    @Override
    public ServiceResult updateStudent(StudentVo vo) {
        ServiceResult serviceResult = new ServiceResult();
        if(vo == null){
            serviceResult.setSuccess(false);
            serviceResult.setMsg("参数不能为空");
            return serviceResult;
        }
        try {
            Integer result = studentMapper.updateStudent(vo);
            if(result == 1){
                serviceResult.setSuccess(true);
            }else {
                serviceResult.setSuccess(false);
                serviceResult.setMsg("更新学生信息失败");
            }
        }catch (Exception e){
            LOGGER.error("更新学生信息失败");
            serviceResult.setSuccess(false);
            serviceResult.setMsg("系统内部错误");
        }
        return serviceResult;
    }

    @Override
    public ServiceResult deleteStudent(Integer id) {
        ServiceResult serviceResult = new ServiceResult();
        if(id == null){
            serviceResult.setSuccess(false);
            serviceResult.setMsg("id不能为空");
            return serviceResult;
        }
        try {
            Integer result = studentMapper.deleteStudent(id);
            if(result == 1){
                serviceResult.setSuccess(true);
            }else {
                serviceResult.setSuccess(false);
                serviceResult.setMsg("删除学生信息失败");
            }
        }catch (Exception e){
            LOGGER.error("删除学生信息失败");
            serviceResult.setSuccess(false);
            serviceResult.setMsg("系统内部错误");
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<StudentVo>> getListStudent(Page page) {
        ServiceResult<List<StudentVo>> serviceResult = new ServiceResult<>();
        try{
            List<StudentVo> studentVoList = studentMapper.getListStudent(page);
            serviceResult.setData(studentVoList);
            serviceResult.setSuccess(true);
            LOGGER.info("获取学生列表成功");
            return serviceResult;
        }catch (Exception e){
            LOGGER.info(e.toString());
            LOGGER.error("获取学生列表失败");
            serviceResult.setSuccess(false);
            serviceResult.setMsg("系统内部错误,获取学生列表失败");
            return serviceResult;
        }
    }

    @Override
    public ServiceResult<TeacherVo> getTeacherById(Integer id) {
        ServiceResult<TeacherVo> serviceResult = new ServiceResult<>();
        if(id == null){
            serviceResult.setSuccess(false);
            serviceResult.setMsg("id不能为空");
            return serviceResult;
        }
        try{
            serviceResult.setData(studentMapper.getTeacherById(id));
            serviceResult.setSuccess(true);
        }catch (Exception e){
            LOGGER.error("获取班主任信息失败");
            serviceResult.setSuccess(false);
            serviceResult.setMsg("服务器内部错误");
        }
        return serviceResult;
    }

}