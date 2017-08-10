package com.chinaredstar.cms.api.vo.atlas;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by sunny on 16-9-2.
 */
public class AtlasDetailVo implements Serializable {
    private static final long serialVersionUID = -8227504770395799259L;
    /**
     * 图集记录ID
     */
    private Long atlasId;

    /**
     * 图集明细记录ID
     */
    private Long id;

    /**
     * 图集明细内容
     */
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
        return "AtlasDetailVo{" +
                "atlasId=" + atlasId +
                ", id=" + id +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
