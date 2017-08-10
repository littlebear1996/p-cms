package com.chinaredstar.cms.api.vo.cmsClass;

import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 分类 --> 商场 --> 商品类目
 */
public class CmsClassCategoryVo extends CmsClassDataVo {

    private static final long serialVersionUID = -1685586016863312060L;

    @ApiModelProperty("PK")
    private Integer categoryId;

    @ApiModelProperty("分类名称")
    private String categoryName;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
