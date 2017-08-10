package com.chinaredstar.cms.api.vo.index;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yixin.sun on 2017/3/24.
 */
public class IndexLifeVo implements Serializable {
    private static final long serialVersionUID = -8970107437124518014L;

    /**
     * id
     */
    @ApiModelProperty("id")
    private Integer id;

    /**
     * 商品banner图
     */
    @ApiModelProperty("商品banner图")
    private String cover;

    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;

    /**
     * 副标题
     */
    @ApiModelProperty("副标题")
    private String subTitle;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String description;

    /**
     * 浏览量
     */
    @ApiModelProperty("浏览量")
    private Integer viewCount;

    /**
     * 生活家商品列表
     */
    @ApiModelProperty("生活家商品列表")
    private List<IndexLifeGoodVo> goodList;

    /**
     * 是否隐藏头部
     */
    @ApiModelProperty("是否隐藏头部")
    private Boolean hiddenHeader;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public List<IndexLifeGoodVo> getGoodList() {
        return goodList;
    }

    public void setGoodList(List<IndexLifeGoodVo> goodList) {
        this.goodList = goodList;
    }

    public Boolean getHiddenHeader() {
        return hiddenHeader;
    }

    public void setHiddenHeader(Boolean hiddenHeader) {
        this.hiddenHeader = hiddenHeader;
    }
}
