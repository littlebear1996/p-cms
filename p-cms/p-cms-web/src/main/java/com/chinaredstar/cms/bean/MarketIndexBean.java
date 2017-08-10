package com.chinaredstar.cms.bean;

import com.chinaredstar.cms.api.vo.article.ArticleMarketVo;
import com.chinaredstar.cms.api.vo.index.IndexAdvertisementVo;
import com.chinaredstar.cms.api.vo.index.IndexCollectionVo;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ykk on 2017/3/29.
 */
@ApiModel("商场首页")
public class MarketIndexBean implements Serializable{

    private static final long serialVersionUID = -3939007958786334791L;

    @ApiModelProperty("商场首页广告")
    private List<IndexAdvertisementVo> advertisementList;

    @ApiModelProperty("品牌合辑")
    private List<IndexCollectionVo> brandCollectionList;

    @ApiModelProperty("商场文章")
    private List<ArticleMarketVo> articleMarketList;

    public List<ArticleMarketVo> getArticleMarketList() {
        return articleMarketList;
    }

    public void setArticleMarketList(List<ArticleMarketVo> articleMarketList) {
        this.articleMarketList = articleMarketList;
    }

    public List<IndexCollectionVo> getBrandCollectionList() {
        return brandCollectionList;
    }

    public void setBrandCollectionList(List<IndexCollectionVo> brandCollectionList) {
        this.brandCollectionList = brandCollectionList;
    }

    public List<IndexAdvertisementVo> getAdvertisementList() {
        return advertisementList;
    }

    public void setAdvertisementList(List<IndexAdvertisementVo> advertisementList) {
        this.advertisementList = advertisementList;
    }
}
