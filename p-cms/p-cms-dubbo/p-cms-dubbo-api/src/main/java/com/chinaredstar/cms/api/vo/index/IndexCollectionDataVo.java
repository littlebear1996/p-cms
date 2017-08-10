package com.chinaredstar.cms.api.vo.index;

import com.chinaredstar.cms.api.vo.collection.CmsCollectionTargetVo;

import java.io.Serializable;

/**
 * 合辑数据Vo
 */
public class IndexCollectionDataVo implements Serializable {

    /** PK */
    private Long id;

    /** 合辑ID */
    private Long collectionId;

    /** 目标ID 如:设计师ID */
    private String targetId;

    private Integer sortNo;

    private CmsCollectionTargetVo cmsCollectionTargetData;

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

    public CmsCollectionTargetVo getCmsCollectionTargetData() {
        return cmsCollectionTargetData;
    }

    public void setCmsCollectionTargetData(CmsCollectionTargetVo cmsCollectionTargetData) {
        this.cmsCollectionTargetData = cmsCollectionTargetData;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }
}
