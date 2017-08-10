package com.chinaredstar.cms.api.model;

import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * CMS图集记录详细实体类
 */
public class AtlasDetail extends BaseModel {
    private static final long serialVersionUID = -8824846558431241693L;

    /** 图集记录ID */
    private Long atlasId;

    /** 图集明细记录ID */
    private Long id;

    /** 图集明细内容 */
    @ApiModelProperty("图集明细图片url")
    private String imgUrl;

    public Long getAtlasId() {
        return atlasId;
    }

    public void setAtlasId(Long atlasId) {
        this.atlasId = atlasId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "AtlasDetail{" +
                "atlasId=" + atlasId +
                ", id=" + id +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}