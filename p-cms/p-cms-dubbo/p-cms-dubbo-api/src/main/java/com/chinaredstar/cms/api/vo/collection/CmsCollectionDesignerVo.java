package com.chinaredstar.cms.api.vo.collection;

import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 设计师合辑中设计师信息
 */
public class CmsCollectionDesignerVo extends CmsCollectionTargetVo{
    private static final long serialVersionUID = 4862427787392608962L;

    @ApiModelProperty("设计师ID")
    private Integer id;

    @ApiModelProperty("公司ID")
    private Integer companyId;

    @ApiModelProperty("公司名称")
    private String companyName;

    @ApiModelProperty("设计师姓名")
    private String name;

    @ApiModelProperty("设计师英文名")
    private String englishName;

    @ApiModelProperty("等级")
    private Integer level;

    @ApiModelProperty("头像")
    private String imageUrl;

    @ApiModelProperty("评分")
    private Double score = 0.00;

    @ApiModelProperty("设计预算（已经通过字典转换了）")
    private String designerBudget;

    @ApiModelProperty("浏览数")
    private Long pvCnt;

    @ApiModelProperty("已接单数量")
    private Long acceptCount = 0l;

    @ApiModelProperty("是否软装:0否1是")
    private Integer isSoftDecoration;

    @ApiModelProperty("是否材料选品:0否1是")
    private Integer isMaterial;

    @ApiModelProperty("是否园林设计:0否1是")
    private Integer isYlDesign;

    @ApiModelProperty("是否可预约:0否1是")
    private Integer isBooking;

    @ApiModelProperty("当月剩余可预约数量")
    private Integer enabledBookingCnt;

    @ApiModelProperty("设计师openid")
    private String openId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getDesignerBudget() {
        return designerBudget;
    }

    public void setDesignerBudget(String designerBudget) {
        this.designerBudget = designerBudget;
    }

    public Long getPvCnt() {
        return pvCnt;
    }

    public void setPvCnt(Long pvCnt) {
        this.pvCnt = pvCnt;
    }

    public Long getAcceptCount() {
        return acceptCount;
    }

    public void setAcceptCount(Long acceptCount) {
        this.acceptCount = acceptCount;
    }

    public Integer getIsSoftDecoration() {
        return isSoftDecoration;
    }

    public void setIsSoftDecoration(Integer isSoftDecoration) {
        this.isSoftDecoration = isSoftDecoration;
    }

    public Integer getIsMaterial() {
        return isMaterial;
    }

    public void setIsMaterial(Integer isMaterial) {
        this.isMaterial = isMaterial;
    }

    public Integer getIsYlDesign() {
        return isYlDesign;
    }

    public void setIsYlDesign(Integer isYlDesign) {
        this.isYlDesign = isYlDesign;
    }

    public Integer getIsBooking() {
        return isBooking;
    }

    public void setIsBooking(Integer isBooking) {
        this.isBooking = isBooking;
    }

    public Integer getEnabledBookingCnt() {
        return enabledBookingCnt;
    }

    public void setEnabledBookingCnt(Integer enabledBookingCnt) {
        this.enabledBookingCnt = enabledBookingCnt;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}