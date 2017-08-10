package com.chinaredstar.cms.api.service;

import com.chinaredstar.cms.api.component.Page;
import com.chinaredstar.cms.api.component.ServiceResult;
import com.chinaredstar.cms.api.vo.zoo.StudentVo;
import com.chinaredstar.cms.api.vo.zoo.TeacherVo;

import java.util.List;

/**
 * 学生接口
 * Created by Littlebear1996 on 2017/7/31.
 */
public interface StudentService {

    /**
     * 插入一个学生信息
     * @param vo
     */
    ServiceResult addStudent(StudentVo vo);
    /**
     * 获取指定id(学生学号)的学生信息
     * @param id
     * @return studentVo
     */
    ServiceResult<StudentVo> getStudent(Integer id);
    /**
     * 根据学生id修改学生信息
     * @param vo
     */
    ServiceResult updateStudent(StudentVo vo);
    /**
     * 根据学生id删除学生信息
     * @param id
     */
    ServiceResult deleteStudent(Integer id);
    /**
     * 分页获取部分学生的信息
     * @return studentList
     */
    ServiceResult<List<StudentVo>>getListStudent(Page page);
    /**
     * 根据学生id获取班主任的详细信息
     * @return
     */
    ServiceResult<TeacherVo> getTeacherById(Integer id);
}
