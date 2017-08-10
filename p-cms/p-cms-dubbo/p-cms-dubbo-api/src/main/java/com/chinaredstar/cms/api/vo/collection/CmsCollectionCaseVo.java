package com.chinaredstar.cms.api.vo.collection;

import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 方案合辑
 */
public class CmsCollectionCaseVo extends CmsCollectionTargetVo{

    private static final long serialVersionUID = -2012674346122933728L;

    @ApiModelProperty("designerId")
    private Integer designerId;

    @ApiModelProperty("caseId")
    private Integer caseId;

    @ApiModelProperty("方案名称")
    private String title;

    @ApiModelProperty("封面图片")
    private String imgUrl;

    @ApiModelProperty("总图片数量")
    private Integer imgCount;

    @ApiModelProperty("浏览数量")
    private Integer pageView;

    @ApiModelProperty("风格")
    private String designStyleStr;

    @ApiModelProperty("户型")
    private String houseTypeStr;

    @ApiModelProperty("面积")
    private String area;

    @ApiModelProperty("设计师英文名称")
    private String englishName;

    @ApiModelProperty("设计师名称")
    private String name;

    public Integer getDesignerId() {
        return designerId;
    }

    public void setDesignerId(Integer designerId) {
        this.designerId = designerId;
    }

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
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

    public String getDesignStyleStr() {
        return designStyleStr;
    }

    public void setDesignStyleStr(String designStyleStr) {
        this.designStyleStr = designStyleStr;
    }

    public String getHouseTypeStr() {
        return houseTypeStr;
    }

    public void setHouseTypeStr(String houseTypeStr) {
        this.houseTypeStr = houseTypeStr;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CmsCollectionCaseVo {" +
                "designerId=" + designerId +
                ", caseId=" + caseId +
                ", title='" + title + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", imgCount=" + imgCount +
                ", pageView=" + pageView +
                ", designStyleStr='" + designStyleStr + '\'' +
                ", houseTypeStr='" + houseTypeStr + '\'' +
                ", area='" + area + '\'' +
                ", englishName='" + englishName + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}