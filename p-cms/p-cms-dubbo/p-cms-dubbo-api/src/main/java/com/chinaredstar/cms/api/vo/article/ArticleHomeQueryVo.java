package com.chinaredstar.cms.api.vo.article;

import java.io.Serializable;

/**
 * 家装文章查询对象
 */
public class ArticleHomeQueryVo implements Serializable {

    private static final long serialVersionUID = -2768239171565304566L;
    private Boolean isTop;

    public Boolean getTop() {
        return isTop;
    }

    public void setTop(Boolean top) {
        isTop = top;
    }
}
