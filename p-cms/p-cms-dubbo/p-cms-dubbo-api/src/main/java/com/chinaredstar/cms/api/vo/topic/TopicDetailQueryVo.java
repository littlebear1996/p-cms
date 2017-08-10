package com.chinaredstar.cms.api.vo.topic;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by Ykk on 2016/11/10.
 */
public class TopicDetailQueryVo implements Serializable {


    private static final long serialVersionUID = 3976101581333251782L;
    /**
     * 专题分类id,外键
     */
    @ApiModelProperty("专题分类id")
    private Integer topicTypeId;

    public Integer getTopicTypeId() {
        return topicTypeId;
    }

    public void setTopicTypeId(Integer topicTypeId) {
        this.topicTypeId = topicTypeId;
    }

    private Integer pageStart;


    public Integer getPageStart() {
        return (pageNo > 0 ? (pageNo - 1) : 0) * pageSize;
    }

//    public void setPageStart(Integer pageStart) {
//        this.pageStart = pageStart;
//    }

    @ApiModelProperty("第几页")
    private Integer pageNo;



    @ApiModelProperty("每页记录数")
    private Integer pageSize;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}
