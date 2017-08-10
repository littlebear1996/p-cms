package com.chinaredstar.cms.api.vo.index;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by pengfei.wang on 2017/3/27.
 */
@ApiModel("分类品牌")
public class IndexCategoryBrandVo implements Serializable {

    private static final long serialVersionUID = 6608139201544192054L;

    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("分类id")
    private Integer categoryId;

    @ApiModelProperty("分类名称")
    private String categoryName;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("品牌id")
    private String brandId;

    @ApiModelProperty("品牌名称名称")
    private String brandName;

    @ApiModelProperty("品牌logo")
    private String brandLogo;

    @ApiModelProperty("排序号")
    private Integer sortNo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getBrandLogo() {
        return brandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IndexCategoryBrandVo{");
        sb.append("id=").append(id);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", categoryName='").append(categoryName).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", brandId='").append(brandId).append('\'');
        sb.append(", brandName='").append(brandName).append('\'');
        sb.append(", brandLogo='").append(brandLogo).append('\'');
        sb.append(", sortNo=").append(sortNo);
        sb.append('}');
        return sb.toString();
    }
}
