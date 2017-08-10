package com.chinaredstar.cms.api.vo.collection;

import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 品牌合辑
 */
public class CmsCollectionBrandVo extends CmsCollectionTargetVo {

    private static final long serialVersionUID = -3863300308170392114L;

    @ApiModelProperty("品牌ID")
    private Integer id;

    @ApiModelProperty("品牌名称")
    private String brandName;

    @ApiModelProperty("品牌logo")
    private String brandLogo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandLogo() {
        return brandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo;
    }
}