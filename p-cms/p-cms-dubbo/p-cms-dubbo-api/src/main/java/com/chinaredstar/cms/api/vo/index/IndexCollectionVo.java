package com.chinaredstar.cms.api.vo.index;

import com.chinaredstar.cms.api.vo.collection.CmsCollectionTargetVo;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ApiModel("合辑信息表")
public class IndexCollectionVo implements Serializable {

    private static final long serialVersionUID = 6377630473764131001L;

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    private Integer id;

    /**
     * 类型：家装 3，商品 1，房产 4，商场 2
     */
    @ApiModelProperty("类型：家装 3，商品 1，房产 4，商场 2")
    private Long typeId;

    /**
     * 家装公司31，设计师32，方案33；家装百科合辑34，学校合辑41，小区合辑42，楼盘合辑43；房产百科合辑44，品牌合辑21，导购员合辑22，商场外链23，商场文章24，
     */
    @ApiModelProperty("家装公司31，设计师32，方案33；家装百科合辑34，学校合辑41，小区合辑42，楼盘合辑43；房产百科合辑44，品牌合辑21，导购员合辑22，商场外链23，商场文章24，")
    private Integer subType;

    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String desc;

    /**
     * 封面图URL
     */
    @ApiModelProperty("封面图URL")
    private String coverUrl;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 排序号
     */
    @ApiModelProperty("排序号")
    private Integer sortNo;

    /**
     * 上线时间
     */
    @ApiModelProperty("上线时间")
    private Date onlineTime;

    /**
     * 浏览量
     */
    @ApiModelProperty("浏览量")
    private Integer viewCount;

    /**
     * 点赞量
     */
    @ApiModelProperty("点赞量")
    private Integer agreeCount;

    /**
     * 商场id
     */
    @ApiModelProperty("商场ID")
    private String marketId;

    /**
     * 商场名字
     */
    @ApiModelProperty("商场名字")
    private String marketName;

    private List<IndexCollectionDataVo> collectionDataList;

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

    public List<IndexCollectionDataVo> getCollectionDataList() {
        return collectionDataList;
    }

    public void setCollectionDataList(List<IndexCollectionDataVo> collectionDataList) {
        this.collectionDataList = collectionDataList;
    }

    public List<CmsCollectionTargetVo> getCmsCollectionTargetVoList() {
        return cmsCollectionTargetVoList;
    }

    public void setCmsCollectionTargetVoList(List<CmsCollectionTargetVo> cmsCollectionTargetVoList) {
        this.cmsCollectionTargetVoList = cmsCollectionTargetVoList;
    }
}
