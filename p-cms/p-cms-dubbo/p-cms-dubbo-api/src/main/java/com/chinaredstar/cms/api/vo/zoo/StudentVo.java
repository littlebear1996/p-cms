/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */
package com.chinaredstar.cms.api.vo.zoo;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author Littlebear1996
 * @version $Id StudentVo.java, v 0.1 2017-07-31 上午9:28 Littlebear1996 Exp $$
 */
public class StudentVo implements Serializable {

    private static final long serialVersionUID = -414063584332157892L;
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
    private ClassVo classVo;
    /**
     *学生电话号
     */
    @ApiModelProperty("学生手机号")
    private String phoneNum;

    public StudentVo() {
    }

    public StudentVo(Integer id, String name, String sex, Integer age, ClassVo classVo, String phoneNum) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.classVo = classVo;
        this.phoneNum = phoneNum;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public Integer getAge() {
        return age;
    }

    public ClassVo getClassVo() {
        return classVo;
    }

    public void setClassVo(ClassVo classVo) {
        this.classVo = classVo;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return "StudentVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", classVo=" + classVo +
                ", phoneNum='" + phoneNum + '\'' +
                '}';
    }
}