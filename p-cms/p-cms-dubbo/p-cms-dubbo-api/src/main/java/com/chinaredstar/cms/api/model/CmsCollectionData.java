package com.chinaredstar.cms.api.model;

import java.util.Date;

/**
 * CMS合辑数据实体
 */
public class CmsCollectionData extends BaseModel{

    private static final long serialVersionUID = -4670873709007615903L;

    /** PK */
    private Long id;

    /** 合辑ID */
    private Long collectionId;

    /** 目标ID 如:设计师ID */
    private String targetId;

    /** 创建时间 */
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Long collectionId) {
        this.collectionId = collectionId;
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}