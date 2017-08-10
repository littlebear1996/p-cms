/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.chinaredstar.cms.api.model;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
/**
 * 学生信息
 * @author jianxiong.lei
 * @version $Id Student.java, v 0.1 2017-08-01 下午2:50 Littlebear1996 Exp $$
 */
@ApiModel(value = "学生信息",description = "学生信息")
public class Student extends BaseModel {
    private static final long serialVersionUID = 121818380269785260L;
    /**
     * 学生id(学号),主键,自增长
     */
    @ApiModelProperty("学生id(学号)")
    private Integer id;
    /**
     * 学生姓名
     */
    @ApiModelProperty("学生姓名")
    private String name;
    /**
     * 学生性别
     */
    @ApiModelProperty("学生性别")
    private String sex;
    /**
     * 学生年龄
     */
    @ApiModelProperty("学生年龄")
    private Integer age;
    /**
     * 学生班级
     */
    @ApiModelProperty("学生班级")
    private Integer classNum;
    /**
     *学生电话号
     */
    @ApiModelProperty("学生手机号")
    private String phoneNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getClassNum() {
        return classNum;
    }

    public void setClassNum(Integer classNum) {
        this.classNum = classNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}