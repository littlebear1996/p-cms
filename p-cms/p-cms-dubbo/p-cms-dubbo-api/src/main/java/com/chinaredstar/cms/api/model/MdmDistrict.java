package com.chinaredstar.cms.api.model;

public class MdmDistrict extends BaseModel {

    private static final long serialVersionUID = 4823443317636865287L;

    private Integer id;

    private String mdGuid;

    private String mdmId;

    private String districtCode;

    private String districtName;

    private Integer districtLevel;

    private String parentDistrictCode;

    private Integer isDel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMdGuid() {
        return mdGuid;
    }

    public void setMdGuid(String mdGuid) {
        this.mdGuid = mdGuid;
    }

    public String getMdmId() {
        return mdmId;
    }

    public void setMdmId(String mdmId) {
        this.mdmId = mdmId;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Integer getDistrictLevel() {
        return districtLevel;
    }

    public void setDistrictLevel(Integer districtLevel) {
        this.districtLevel = districtLevel;
    }

    public String getParentDistrictCode() {
        return parentDistrictCode;
    }

    public void setParentDistrictCode(String parentDistrictCode) {
        this.parentDistrictCode = parentDistrictCode;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}