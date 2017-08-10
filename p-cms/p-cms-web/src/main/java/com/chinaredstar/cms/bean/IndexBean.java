package com.chinaredstar.cms.bean;

import com.chinaredstar.cms.api.model.Advertisement;
import com.chinaredstar.cms.api.model.CmsIndexBrand;
import com.chinaredstar.cms.api.model.CmsIndexModuleConfig;
import com.chinaredstar.cms.api.vo.advertisement.AdvertisementVo;
import com.chinaredstar.cms.api.vo.index.*;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ykk on 2017/3/26.
 */
@ApiModel("APP首页接口返回实体对象")
public class IndexBean implements Serializable{


    private static final long serialVersionUID = -6878157739792069539L;

    @ApiModelProperty("配置对象")
    private List<IndexModuleConfigVo> configList;

    @ApiModelProperty("限时购")
    private IndexPromotionVo promotionVo;

    @ApiModelProperty("广告位")
    private List<AdvertisementVo> advertisementList;

    @ApiModelProperty("大牌")
    private List<IndexBrandVo> brandList;


    @ApiModelProperty("精选主题")
    private List<IndexTopicVo> topicList;

    @ApiModelProperty("生活家")
    private List<IndexLifeVo> lifeVoList;


    public List<IndexModuleConfigVo> getConfigList() {
        return configList;
    }

    public List<IndexLifeVo> getLifeVoList() {
        return lifeVoList;
    }

    public void setLifeVoList(List<IndexLifeVo> lifeVoList) {
        this.lifeVoList = lifeVoList;
    }

    public void setConfigList(List<IndexModuleConfigVo> configList) {
        this.configList = configList;
    }

    public List<AdvertisementVo> getAdvertisementList() {
        return advertisementList;
    }

    public void setAdvertisementList(List<AdvertisementVo> advertisementList) {
        this.advertisementList = advertisementList;
    }

    public List<IndexBrandVo> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<IndexBrandVo> brandList) {
        this.brandList = brandList;
    }

    public List<IndexTopicVo> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<IndexTopicVo> topicList) {
        this.topicList = topicList;
    }

    public IndexPromotionVo getPromotionVo() {
        return promotionVo;
    }

    public void setPromotionVo(IndexPromotionVo promotionVo) {
        this.promotionVo = promotionVo;
    }
}
