package com.chinaredstar.cms.api.model;

public class CmsIndexCollectionCity extends BaseModel {

    private static final long serialVersionUID = -9145801224684785422L;

    private Integer id;

    private Integer collectionDataId;

    private String cityId;

    private String cityName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCollectionDataId() {
        return collectionDataId;
    }

    public void setCollectionDataId(Integer collectionDataId) {
        this.collectionDataId = collectionDataId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId == null ? null : cityId.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }
}