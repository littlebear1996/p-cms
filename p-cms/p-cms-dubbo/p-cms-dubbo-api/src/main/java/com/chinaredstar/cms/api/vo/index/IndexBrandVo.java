package com.chinaredstar.cms.api.vo.index;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pengfei.wang on 2017/3/24.
 */
@ApiModel("大牌")
public class IndexBrandVo implements Serializable {

    private static final long serialVersionUID = 1720520257449331309L;

    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("封面图")
    private String cover;

    @ApiModelProperty("品牌id")
    private String brandId;

    @ApiModelProperty("品牌名称")
    private String brandName;

    @ApiModelProperty("副标题")
    private String subTitle;

    @ApiModelProperty("关联店铺")
    private String shopId;

    @ApiModelProperty("关联店铺名称")
    private String shopName;

    @ApiModelProperty("排序号")
    private Integer sortNo;

    @ApiModelProperty("浏览次数")
    private Integer viewCount;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("是否首页推荐,false:否;true;是")
    private Boolean isRecommend;

    @ApiModelProperty("是否隐藏头部,false:否;true:是")
    private Boolean hiddenHeader;

    @ApiModelProperty("头图")
    private String headImage;

    @ApiModelProperty("视频地址")
    private String videoUrl;

    @ApiModelProperty("大牌商品")
    private List<IndexBrandGoodVo> goods;

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

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getRecommend() {
        return isRecommend;
    }

    public void setRecommend(Boolean recommend) {
        isRecommend = recommend;
    }

    public Boolean getHiddenHeader() {
        return hiddenHeader;
    }

    public void setHiddenHeader(Boolean hiddenHeader) {
        this.hiddenHeader = hiddenHeader;
    }

    public List<IndexBrandGoodVo> getGoods() {
        return goods;
    }

    public void setGoods(List<IndexBrandGoodVo> goods) {
        this.goods = goods;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IndexBrandVo{");
        sb.append("id=").append(id);
        sb.append(", cover='").append(cover).append('\'');
        sb.append(", brandId='").append(brandId).append('\'');
        sb.append(", brandName='").append(brandName).append('\'');
        sb.append(", subTitle='").append(subTitle).append('\'');
        sb.append(", shopId='").append(shopId).append('\'');
        sb.append(", shopName='").append(shopName).append('\'');
        sb.append(", sortNo=").append(sortNo);
        sb.append(", viewCount=").append(viewCount);
        sb.append(", description='").append(description).append('\'');
        sb.append(", isRecommend=").append(isRecommend);
        sb.append(", hiddenHeader=").append(hiddenHeader);
        sb.append(", headImage='").append(headImage).append('\'');
        sb.append(", videoUrl='").append(videoUrl).append('\'');
        sb.append(", goods=").append(goods);
        sb.append('}');
        return sb.toString();
    }
}
