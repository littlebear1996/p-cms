package com.chinaredstar.cms.api.vo.cmsClass;

import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 分类, 商场,精选
 * 分类 ,商场,美图
 */
public class CmsClassTagsVo extends CmsClassDataVo {

    private static final long serialVersionUID = 5556143147835958563L;

    @ApiModelProperty("标签ID")
    private Integer id;

    @ApiModelProperty("标签名称")
    private String name;

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
}
