package com.chinaredstar.cms.api.vo.index;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by pengfei.wang on 2017/4/1.
 */
public class IndexTicketVo implements Serializable {

    private static final long serialVersionUID = 129949609185773893L;

    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("banner图")
    private String banner;

    @ApiModelProperty("banner跳转")
    private String url;

    @ApiModelProperty("城市id")
    private String cityId;

    @ApiModelProperty("城市名字")
    private String cityName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IndexTicketVo{");
        sb.append("id=").append(id);
        sb.append(", banner='").append(banner).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", cityId='").append(cityId).append('\'');
        sb.append(", cityName='").append(cityName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
