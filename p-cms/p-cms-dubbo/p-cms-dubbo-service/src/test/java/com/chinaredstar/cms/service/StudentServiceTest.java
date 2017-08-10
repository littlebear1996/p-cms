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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author jianxiong.lei
 * @version $Id StudentServiceTest.java, v 0.1 2017-07-31 下午2:32 Littlebear1996 Exp $$
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/applicationContext-*.xml")
public class StudentServiceTest {
    @Autowired
    private StudentService studentService;
    @Test
    public void testAddStudent(){
        Page page = new Page(2,2);
        ServiceResult<List<StudentVo>> result = studentService.getListStudent(page);
        System.out.println(result.toString());
    }
    @Test
    public void test(){
        TeacherVo teacherVo = studentService.getTeacherById(1).getData();
        System.out.println(teacherVo);
    }
    @Test
    public void test1(){
        StudentVo studentVo = studentService.getStudent(1).getData();
        System.out.println(studentVo);
    }
}