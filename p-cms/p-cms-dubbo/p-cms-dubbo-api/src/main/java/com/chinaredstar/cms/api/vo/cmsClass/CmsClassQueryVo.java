package com.chinaredstar.cms.api.vo.cmsClass;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 *  cmsClass查询类
         */
public class CmsClassQueryVo implements Serializable{
    private static final long serialVersionUID = -7284164216030386501L;

    @ApiModelProperty("目录类型：1：商品，2：房产，3：商场")
    private Integer categoryType;

    @ApiModelProperty("目录子类型，101:精选，102：美图，201：新房，202：二手房，301：商品类目，302：商品品牌")
    private Integer categorySubType;

    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }

    public Integer getCategorySubType() {
        return categorySubType;
    }

    public void setCategorySubType(Integer categorySubType) {
        this.categorySubType = categorySubType;
    }

    @Override
    public String toString() {
        return "CmsClassQueryVo {" +
                "categoryType=" + categoryType +
                ", categorySubType=" + categorySubType +
                '}';
    }
}
