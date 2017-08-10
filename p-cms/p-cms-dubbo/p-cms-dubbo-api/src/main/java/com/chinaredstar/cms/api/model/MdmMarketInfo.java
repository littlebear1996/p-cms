package com.chinaredstar.cms.api.model;

import java.util.Date;

public class MdmMarketInfo extends BaseModel {

    private static final long serialVersionUID = 3229996039660154906L;

    private Integer id;

    private Integer idUuid;

    private String mdGuid;

    private String mdmId;

    private String marketNumber;

    private String marketName;

    private Integer firstOrgId;

    private Integer secondOrgId;

    private String firstOrgName;

    private String secondOrgName;

    private String provinceId;

    private String provinceName;

    private String cityId;

    private String cityName;

    private Integer districtId;

    private String marketAddress;

    private String marketShort;

    private String gps;

    private String telephone;

    private Integer isDel;

    private Date createDate;

    private Date updateDate;

    private String mallCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUuid() {
        return idUuid;
    }

    public void setIdUuid(Integer idUuid) {
        this.idUuid = idUuid;
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

    public String getMarketNumber() {
        return marketNumber;
    }

    public void setMarketNumber(String marketNumber) {
        this.marketNumber = marketNumber;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public Integer getFirstOrgId() {
        return firstOrgId;
    }

    public void setFirstOrgId(Integer firstOrgId) {
        this.firstOrgId = firstOrgId;
    }

    public Integer getSecondOrgId() {
        return secondOrgId;
    }

    public void setSecondOrgId(Integer secondOrgId) {
        this.secondOrgId = secondOrgId;
    }

    public String getFirstOrgName() {
        return firstOrgName;
    }

    public void setFirstOrgName(String firstOrgName) {
        this.firstOrgName = firstOrgName;
    }

    public String getSecondOrgName() {
        return secondOrgName;
    }

    public void setSecondOrgName(String secondOrgName) {
        this.secondOrgName = secondOrgName;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public String getMarketAddress() {
        return marketAddress;
    }

    public void setMarketAddress(String marketAddress) {
        this.marketAddress = marketAddress;
    }

    public String getMarketShort() {
        return marketShort;
    }

    public void setMarketShort(String marketShort) {
        this.marketShort = marketShort;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getMallCode() {
        return mallCode;
    }

    public void setMallCode(String mallCode) {
        this.mallCode = mallCode;
    }
}