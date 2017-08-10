package com.chinaredstar.cms.api.model;

import java.util.Date;

/**
 *
 */
public class CmsClass extends BaseModel {
    private static final long serialVersionUID = 264621995516256102L;

    // 目录类型：1：商品，2：房产，3：商场
    /**
     * 目录类型：1：商品
     */
    public static final int CATEGORY_TYPE_PRODUCT = 1;
    /**
     * 目录类型：3：商场
     */
    public static final int CATEGORY_TYPE_MARKET = 3;


    // 目录子类型，101:精选，102：美图，201：新房，202：二手房，301：商品类目，302：商品品牌
    /**
     * 目录子类型，101:精选
     */
    public static final int CATEGORY_SUB_TYPE_PRODUCT_SELECTION = 101;
    /**
     * 目录子类型，102：美图
     */
    public static final int CATEGORY_SUB_TYPE_PRODUCT_PICTURES = 102;
    /**
     * 目录子类型，301：商品类目
     */
    public static final int CATEGORY_SUB_TYPE_MARKET_PRODUCT_CATEGORY = 301;
    /**
     * 目录子类型，302：商品品牌
     */
    public static final int CATEGORY_SUB_TYPE_MARKET_PRODUCT_BRAND = 302;

    /**
     * PK
     */
    private Integer id;

    /**
     * 露出顺序
     */
    private Integer sequence;

    /**
     * 分类中具体值，可以表示标签id，商品品牌id
     */
    private String datas;

    /**
     * 创建者id
     */
    private Integer creatorId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否置顶，1：置顶，0：非置顶
     */
    private Boolean isTop;

    /**
     * 审核状态，0：待审核，1：审核通过，2：审核驳回
     */
    private Integer checkStatus;

    /**
     * 驳回原因
     */
    private String rejectCause;

    /**
     * 类型：1，热销商品品牌，2，商品类目，3，商品内容标签，4，房产内容标签',
     */
    private String type;

    /**
     * 目录类型：1：商品，2：房产，3：商场
     */
    private Integer categoryType;

    /**
     * 目录子类型，101:精选，102：美图，201：新房，202：二手房，301：商品类目，302：商品品牌
     */
    private Integer categorySubType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getDatas() {
        return datas;
    }

    public void setDatas(String datas) {
        this.datas = datas;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getIsTop() {
        return isTop;
    }

    public void setIsTop(Boolean isTop) {
        this.isTop = isTop;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getRejectCause() {
        return rejectCause;
    }

    public void setRejectCause(String rejectCause) {
        this.rejectCause = rejectCause;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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
}