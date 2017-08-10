package com.chinaredstar.cms.api.vo.topic;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by pengfei.wang on 2016/11/18.
 */
public class TopicTypeQueryVo implements Serializable {

    private static final long serialVersionUID = 3976101581333251782L;
    /**
     * 专题id,外键
     */
    @ApiModelProperty("专题id")
    private Integer topicId;

    @ApiModelProperty("第几页")
    private Integer pageNo;

    @ApiModelProperty("每页记录数")
    private Integer pageSize;

    private Integer pageStart;

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getPageStart() {
        return (pageNo > 0 ? (pageNo - 1) : 0) * pageSize;
    }

//    public void setPageStart(Integer pageStart) {
//        this.pageStart = pageStart;
//    }

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
