package com.chinaredstar.cms.mapper;

import com.chinaredstar.cms.api.component.Page;
import com.chinaredstar.cms.api.vo.zoo.StudentVo;
import com.chinaredstar.cms.api.vo.zoo.TeacherVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Littlebear1996 on 2017/7/31.
 */
public interface StudentMapper {
    /**
     * 插入一个学生信息
     * @param vo
     */
    Integer addStudent(StudentVo vo);
    /**
     * 获取指定id(学生学号)的学生信息
     * @param id
     * @return studentVo
     */
    StudentVo getStudent(Integer id);
    /**
     * 根据学生id修改学生信息
     * @param vo
     */
    Integer updateStudent(StudentVo vo);
    /**
     * 根据学生id删除学生信息
     * @param id
     */
    Integer deleteStudent(Integer id);
    /**
     * 获取所有学生的信息
     * @return studentList
     */
    List<StudentVo> getListStudent(@Param("page") Page page);
    /**
     * 根据学生id获取班主任的详细信息
     * @param id
     * @return
     */
    TeacherVo getTeacherById(Integer id);
}
