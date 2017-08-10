/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.chinaredstar.cms.api.vo.zoo;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author jianxiong.lei
 * @date 2017-08-07 14:12
 */
public class ClassVo implements Serializable{
    private static final long serialVersionUID = 7075650355711637138L;
    /**
     * 班级号码
     */
    @ApiModelProperty("班级号码")
    Integer classNum;
    /**
     * 老师id
     */
    @ApiModelProperty("负责老师")
    TeacherVo teacherVo;

    public ClassVo() {
    }

    public ClassVo(Integer classNum, TeacherVo teacherVo) {
        this.classNum = classNum;
        this.teacherVo = teacherVo;
    }

    public Integer getClassNum() {
        return classNum;
    }

    public void setClassNum(Integer classNum) {
        this.classNum = classNum;
    }

    public TeacherVo getTeacherVo() {
        return teacherVo;
    }

    public void setTeacherVo(TeacherVo teacherVo) {
        this.teacherVo = teacherVo;
    }

    @Override
    public String toString() {
        return "ClassVo{" +
                "classNum=" + classNum +
                ", teacherVo=" + teacherVo +
                '}';
    }
}