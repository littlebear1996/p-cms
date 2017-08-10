package com.chinaredstar.cms.api.model;

import java.util.Date;

/**
 *  CMS商品分类信息实体
 */
public class ProductCategory extends BaseModel{

    private static final long serialVersionUID = -3197318564952689780L;

    /** PK */
    private Integer categoryId;

    /** 父分类ID（最顶层是0） */
    private Integer categoryParentId;

    /** 所有上层分类ID(用|连接) */
    private String categoryAncestor;

    /** 分类级别（从1开始） */
    private Integer categoryLevel;

    /** 运营范围（用,连接）0:商户端 1:B2C平台 2:服务平台 */
    private String categoryRange;

    /** 分类名称 */
    private String categoryName;

    /** 状态(0:未启用；1:启用) */
    private Integer useState;

    /** 分类图片地址 */
    private String imgUrl;

    /** 排序(小的在前) */
    private Integer displayOrder;

    /** 创建时间 */
    private Date createDate;

    /** 创建者 */
    private String createOperator;

    /** 更新者 */
    private String updateOperator;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCategoryParentId() {
        return categoryParentId;
    }

    public void setCategoryParentId(Integer categoryParentId) {
        this.categoryParentId = categoryParentId;
    }

    public String getCategoryAncestor() {
        return categoryAncestor;
    }

    public void setCategoryAncestor(String categoryAncestor) {
        this.categoryAncestor = categoryAncestor == null ? null : categoryAncestor.trim();
    }

    public Integer getCategoryLevel() {
        return categoryLevel;
    }

    public void setCategoryLevel(Integer categoryLevel) {
        this.categoryLevel = categoryLevel;
    }

    public String getCategoryRange() {
        return categoryRange;
    }

    public void setCategoryRange(String categoryRange) {
        this.categoryRange = categoryRange;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getUseState() {
        return useState;
    }

    public void setUseState(Integer useState) {
        this.useState = useState;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateOperator() {
        return createOperator;
    }

    public void setCreateOperator(String createOperator) {
        this.createOperator = createOperator;
    }

    public String getUpdateOperator() {
        return updateOperator;
    }

    public void setUpdateOperator(String updateOperator) {
        this.updateOperator = updateOperator;
    }
}