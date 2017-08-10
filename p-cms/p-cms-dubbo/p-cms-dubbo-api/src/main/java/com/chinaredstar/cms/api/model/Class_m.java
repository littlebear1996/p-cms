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
 * @date 2017-08-07 14:26
 */
@ApiModel(value = "班级信息",description = "班级信息")
public class Class_m implements Serializable{

    private static final long serialVersionUID = 6744095351943534979L;

    /**
     * 班级号码
     */
    @ApiModelProperty("班级号码")
    Integer classNum;
    /**
     * 老师id
     */
    @ApiModelProperty("老师id")
    Integer teacherId;

    public Integer getClassNum() {
        return classNum;
    }

    public void setClassNum(Integer classNum) {
        this.classNum = classNum;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
}