package com.chinaredstar.cms.api.vo.cmsClass;

import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 分类 --> 商场 --> 商品品牌
 */
public class CmsClassBrandVo extends CmsClassDataVo {

    private static final long serialVersionUID = -7474242963782426050L;

    @ApiModelProperty("PK")
    private Integer id;

    @ApiModelProperty("ID UUID")
    private Integer idUuid;

    @ApiModelProperty("公司ID")
    private Integer companyId;

    @ApiModelProperty("公司IDUUID")
    private Integer companyIdUuid;

    @ApiModelProperty("品牌编号")
    private String brandNumber;

    @ApiModelProperty("品牌名称")
    private String brandName;

    @ApiModelProperty("品牌拼音")
    private String brandPy;

    @ApiModelProperty("品牌描述")
    private String bandDescription;

    @ApiModelProperty("品牌LOGO")
    private String brandLogo;

    @ApiModelProperty("宣传类型")
    private Byte publicityType;

    @ApiModelProperty("宣传资料")
    private String publicityMaterial;

    @ApiModelProperty("导购手册")
    private String guideImg;

    @ApiModelProperty("分类ID")
    private Integer categoryId;

    @ApiModelProperty("分类名称")
    private String categoryName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUuid() {
        return idUuid;
    }

    public void setIdUuid(Integer idUuid) {
        this.idUuid = idUuid;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getCompanyIdUuid() {
        return companyIdUuid;
    }

    public void setCompanyIdUuid(Integer companyIdUuid) {
        this.companyIdUuid = companyIdUuid;
    }

    public String getBrandNumber() {
        return brandNumber;
    }

    public void setBrandNumber(String brandNumber) {
        this.brandNumber = brandNumber;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandPy() {
        return brandPy;
    }

    public void setBrandPy(String brandPy) {
        this.brandPy = brandPy;
    }

    public String getBandDescription() {
        return bandDescription;
    }

    public void setBandDescription(String bandDescription) {
        this.bandDescription = bandDescription;
    }

    public String getBrandLogo() {
        return brandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo;
    }

    public Byte getPublicityType() {
        return publicityType;
    }

    public void setPublicityType(Byte publicityType) {
        this.publicityType = publicityType;
    }

    public String getPublicityMaterial() {
        return publicityMaterial;
    }

    public void setPublicityMaterial(String publicityMaterial) {
        this.publicityMaterial = publicityMaterial;
    }

    public String getGuideImg() {
        return guideImg;
    }

    public void setGuideImg(String guideImg) {
        this.guideImg = guideImg;
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
}
