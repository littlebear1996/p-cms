package com.chinaredstar.cms.api.vo.index;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by pengfei.wang on 2017/3/24.
 */
@ApiModel("精选专题")
public class IndexTopicVo implements Serializable {

    private static final long serialVersionUID = 2112768361003476938L;

    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("文章类型：1：商品文章，2：商场文章，3：家装案例文章，4：房产文章; ")
    private Integer articleType;

    @ApiModelProperty("文章id")
    private Integer articleId;

    @ApiModelProperty("副标题")
    private String subTitle;

    @ApiModelProperty("排序号")
    private Integer sortNo;

    @ApiModelProperty("是否首页推荐，false：否；true：是；")
    private Boolean isRecommend;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("封面")
    private String cover;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleType() {
        return articleType;
    }

    public void setArticleType(Integer articleType) {
        this.articleType = articleType;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
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

    public Boolean getRecommend() {
        return isRecommend;
    }

    public void setRecommend(Boolean recommend) {
        isRecommend = recommend;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IndexTopicVo{");
        sb.append("id=").append(id);
        sb.append(", articleType=").append(articleType);
        sb.append(", articleId=").append(articleId);
        sb.append(", subTitle='").append(subTitle).append('\'');
        sb.append(", sortNo=").append(sortNo);
        sb.append(", isRecommend=").append(isRecommend);
        sb.append(", title='").append(title).append('\'');
        sb.append(", cover='").append(cover).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
