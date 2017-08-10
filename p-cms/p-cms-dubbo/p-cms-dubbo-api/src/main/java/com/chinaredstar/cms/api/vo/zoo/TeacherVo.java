/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.chinaredstar.cms.api.vo.zoo;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author jianxiong.lei
 * @date 2017-08-07 14:19
 */
public class TeacherVo implements Serializable{
    private static final long serialVersionUID = 5483539722214496772L;
    /**
     * 老师Id
     */
    @ApiModelProperty("老师id")
    private Integer teacherId;
    /**
     * 老师姓名
     */
    @ApiModelProperty("老师姓名")
    private String teacherName;
    /**
     * 老师所授科目
     */
    @ApiModelProperty("老师所授科目")
    private String type;
    /**
     * 老师联系方式
     */
    @ApiModelProperty("老师联系方式")
    private String teacherTel;

    public TeacherVo() {
    }

    public TeacherVo(Integer teacherId, String teacherName, String type, String teacherTel) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.type = type;
        this.teacherTel = teacherTel;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTeacherTel() {
        return teacherTel;
    }

    public void setTeacherTel(String teacherTel) {
        this.teacherTel = teacherTel;
    }

    @Override
    public String toString() {
        return "TeacherVo{" +
                "teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", type='" + type + '\'' +
                ", teacherTel=" + teacherTel +
                '}';
    }
}