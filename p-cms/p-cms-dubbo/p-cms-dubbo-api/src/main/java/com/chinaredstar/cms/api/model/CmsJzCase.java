package com.chinaredstar.cms.api.model;

import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 家装方案
 */
public class CmsJzCase extends BaseModel {

    private static final long serialVersionUID = 2383911950610992819L;

    @ApiModelProperty("方案ID")
    private Integer caseId;

    @ApiModelProperty("设计师ID")
    private Integer designerId;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("封面图")
    private String imgUrl;

    @ApiModelProperty("图数量")
    private Integer imgCount;

    @ApiModelProperty("浏览数")
    private Integer pageView;

    @ApiModelProperty("户型:室#厅#厨#卫#阳台")
    private String houseType;

    @ApiModelProperty("户型:室#厅#厨#卫#阳台")
    private String houseTypeStr;

    @ApiModelProperty("设计风格")
    private Integer designStyle;

    @ApiModelProperty("面积")
    private String area;

    @ApiModelProperty("设计师英文")
    private String name;

    @ApiModelProperty("设计师英文名称")
    private String englishName;

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public Integer getDesignerId() {
        return designerId;
    }

    public void setDesignerId(Integer designerId) {
        this.designerId = designerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getImgCount() {
        return imgCount;
    }

    public void setImgCount(Integer imgCount) {
        this.imgCount = imgCount;
    }

    public Integer getPageView() {
        return pageView;
    }

    public void setPageView(Integer pageView) {
        this.pageView = pageView;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getHouseTypeStr() {
        return houseTypeStr;
    }

    public void setHouseTypeStr(String houseTypeStr) {
        this.houseTypeStr = houseTypeStr;
    }

    public Integer getDesignStyle() {
        return designStyle;
    }

    public void setDesignStyle(Integer designStyle) {
        this.designStyle = designStyle;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    @Override
    public String toString() {
        return "CmsJzCase {" +
                "caseId=" + caseId +
                ", designerId=" + designerId +
                ", title='" + title + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", imgCount=" + imgCount +
                ", pageView=" + pageView +
                ", houseType='" + houseType + '\'' +
                ", houseTypeStr='" + houseTypeStr + '\'' +
                ", designStyle=" + designStyle +
                ", area='" + area + '\'' +
                ", name='" + name + '\'' +
                ", englishName='" + englishName + '\'' +
                '}';
    }
}