package com.chinaredstar.cms.api.vo.collection;

import com.chinaredstar.cms.api.model.CmsCollectionData;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 */
public class CmsCollectionVo implements Serializable {

    private static final long serialVersionUID = 6377630473764131001L;

    /** PK */
    @ApiModelProperty("PK")
    private Integer id;

    /** 类型ID */
    @ApiModelProperty("类型ID")
    private Long typeId;

    /** 子类型 */
    @ApiModelProperty("子类型")
    private Integer subType;

    /** 标题 */
    @ApiModelProperty("标题")
    private String title;

    /** 描述 */
    @ApiModelProperty("描述")
    private String desc;

    /** 封面图URL */
    @ApiModelProperty("封面图URL")
    private String coverUrl;

    /** 创建时间 */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /** 排序号 */
    @ApiModelProperty("排序号")
    private Integer sortNo;

    /** 上线时间 */
    @ApiModelProperty("上线时间")
    private Date onlineTime;

    /** 浏览量 */
    @ApiModelProperty("浏览量")
    private Integer viewCount;

    /** 点赞量 */
    @ApiModelProperty("点赞量")
    private Integer agreeCount;

    /** 商场id */
    @ApiModelProperty("商场ID")
    private String marketId;

    /** 商场名字 */
    @ApiModelProperty("商场名字")
    private String marketName;

    private List<CmsCollectionData> collectionDataList;

    private List<CmsCollectionTargetVo> cmsCollectionTargetVoList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Integer getSubType() {
        return subType;
    }

    public void setSubType(Integer subType) {
        this.subType = subType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Date getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Date onlineTime) {
        this.onlineTime = onlineTime;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getAgreeCount() {
        return agreeCount;
    }

    public void setAgreeCount(Integer agreeCount) {
        this.agreeCount = agreeCount;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public List<CmsCollectionData> getCollectionDataList() {
        return collectionDataList;
    }

    public void setCollectionDataList(List<CmsCollectionData> collectionDataList) {
        this.collectionDataList = collectionDataList;
    }

    public List<CmsCollectionTargetVo> getCmsCollectionTargetVoList() {
        return cmsCollectionTargetVoList;
    }

    public void setCmsCollectionTargetVoList(List<CmsCollectionTargetVo> cmsCollectionTargetVoList) {
        this.cmsCollectionTargetVoList = cmsCollectionTargetVoList;
    }
}
