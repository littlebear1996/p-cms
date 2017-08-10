package com.chinaredstar.cms.api.model;

/**
 * 广告位置实体
 */
public class AdvertisementPosition extends BaseModel {

    private static final long serialVersionUID = 481722804424326885L;

    /** 自增主键id */
    private Integer id;

    /** 位置名称 */
    private String positionName;

    /** 位置code，唯一(商场为商场表的UUID) */
    private String positionCode;

    /** 位置类型，1：主app，2：家装，3：商场，4：房产 */
    private Integer positionType;

    /** 位置情况描述 */
    private String positionDesc;

    /** 标题 */
    private String title;

    /** 父位置id，二级目录 */
    private String parentName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public Integer getPositionType() {
        return positionType;
    }

    public void setPositionType(Integer positionType) {
        this.positionType = positionType;
    }

    public String getPositionDesc() {
        return positionDesc;
    }

    public void setPositionDesc(String positionDesc) {
        this.positionDesc = positionDesc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}