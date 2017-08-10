/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.chinaredstar.cms.api.model;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author jianxiong.lei
 * @date 2017-08-07 14:28
 */
@ApiModel(value = "老师信息",description = "老师信息")
public class Teacher implements Serializable {

    private static final long serialVersionUID = 4807514244026762243L;

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
}