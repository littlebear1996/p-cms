package com.chinaredstar.cms.api.vo.index;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by pengfei.wang on 2017/3/26.
 */
@ApiModel("App模块配置对象")
public class IndexModuleConfigVo implements Serializable {

    private static final long serialVersionUID = -3528640636965035559L;

    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("副标题")
    private String subTitle;

    @ApiModelProperty("排序")
    private Integer sortNo;

    @ApiModelProperty("类型：1，促销；2，设计家；3，直播；4，生活家；5，大牌；6，专题，7，领券中心")
    private Integer type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IndexModuleConfigVo{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", subTitle='").append(subTitle).append('\'');
        sb.append(", sortNo=").append(sortNo);
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }
}
