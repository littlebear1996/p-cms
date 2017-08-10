package com.chinaredstar.cms.api.model;

public class MdmRegion extends BaseModel {

    private static final long serialVersionUID = 8764230318127056028L;

    private Integer id;

    private String mdGuid;

    private String mdmId;

    private String regionCode;

    private String regionName;

    private String parentRegionCode;

    private String regionClass;

    private Integer isDel;

    private String regionPsCode;

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

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getParentRegionCode() {
        return parentRegionCode;
    }

    public void setParentRegionCode(String parentRegionCode) {
        this.parentRegionCode = parentRegionCode;
    }

    public String getRegionClass() {
        return regionClass;
    }

    public void setRegionClass(String regionClass) {
        this.regionClass = regionClass;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getRegionPsCode() {
        return regionPsCode;
    }

    public void setRegionPsCode(String regionPsCode) {
        this.regionPsCode = regionPsCode;
    }
}