package com.chinaredstar.cms.api.vo.index;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pengfei.wang on 2017/3/27.
 */
@ApiModel("主app分类")
public class IndexCategoryVo implements Serializable {

    private static final long serialVersionUID = -5190454031138100926L;

    @ApiModelProperty("分类id")
    private Integer id;

    @ApiModelProperty("分类名称")
    private String title;

    @ApiModelProperty("分类排序")
    private Integer sortNo;

    @ApiModelProperty("banner图")
    private String banner;

    @ApiModelProperty("banner跳转链接类型：1，h5；4,商品文章；")
    private String linkType;

    @ApiModelProperty("banner跳转链接")
    private String linkUrl;

    @ApiModelProperty("分类")
    private List<IndexCategoryClassVo> classes;

    @ApiModelProperty("分类品牌")
    private List<IndexCategoryBrandVo> brands;

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

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public List<IndexCategoryClassVo> getClasses() {
        return classes;
    }

    public void setClasses(List<IndexCategoryClassVo> classes) {
        this.classes = classes;
    }

    public List<IndexCategoryBrandVo> getBrands() {
        return brands;
    }

    public void setBrands(List<IndexCategoryBrandVo> brands) {
        this.brands = brands;
    }

    public String getLinkType() {
        return linkType;
    }

    public void setLinkType(String linkType) {
        this.linkType = linkType;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IndexCategoryVo{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", sortNo=").append(sortNo);
        sb.append(", banner='").append(banner).append('\'');
        sb.append(", linkType='").append(linkType).append('\'');
        sb.append(", linkUrl='").append(linkUrl).append('\'');
        sb.append(", classes=").append(classes);
        sb.append(", brands=").append(brands);
        sb.append('}');
        return sb.toString();
    }
}
