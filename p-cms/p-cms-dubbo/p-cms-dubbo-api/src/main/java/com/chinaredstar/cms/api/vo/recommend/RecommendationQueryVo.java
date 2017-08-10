package com.chinaredstar.cms.api.vo.recommend;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by lenovo on 2016/8/21.
 */
public class RecommendationQueryVo implements Serializable{
    private static final long serialVersionUID = 8187311202432988893L;

    @ApiModelProperty("推荐分类:1家装,2商品,3房产,4商场,5主APP")
    private Integer type;

    @ApiModelProperty("推荐子类型一:1APP推荐,2PC推荐")
    private Integer subType1;

    @ApiModelProperty("推荐子类型二:1家装首页,2好玩")
    private Integer subType2;

    @ApiModelProperty("是否是浏览")
    private Boolean isView;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSubType1() {
        return subType1;
    }

    public void setSubType1(Integer subType1) {
        this.subType1 = subType1;
    }

    public Integer getSubType2() {
        return subType2;
    }

    public void setSubType2(Integer subType2) {
        this.subType2 = subType2;
    }

    public Boolean getView() {
        return isView;
    }

    public void setView(Boolean view) {
        isView = view;
    }
}
