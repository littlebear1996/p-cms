package com.chinaredstar.cms.api.vo.cmsClass;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 */
public class CmsClassVo implements Serializable {

    private static final long serialVersionUID = -7229275846595738183L;

    @ApiModelProperty("PK")
    private Integer id;

    @ApiModelProperty("顺序")
    private Integer sequence;

    @ApiModelProperty("分类中具体值，可以表示标签id，商品品牌id")
    private String datas;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("目录类型：1：商品，2：房产，3：商场")
    private Integer categoryType;

    @ApiModelProperty("目录子类型，101:精选，102：美图，201：新房，202：二手房，301：商品类目，302：商品品牌")
    private Integer categorySubType;

    @ApiModelProperty("分类名称")
    private String categoryName;

    @ApiModelProperty("数据")
    private List<CmsClassDataVo> classData;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getDatas() {
        return datas;
    }

    public void setDatas(String datas) {
        this.datas = datas;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }

    public Integer getCategorySubType() {
        return categorySubType;
    }

    public void setCategorySubType(Integer categorySubType) {
        this.categorySubType = categorySubType;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<CmsClassDataVo> getClassData() {
        return classData;
    }

    public void setClassData(List<CmsClassDataVo> classData) {
        this.classData = classData;
    }
}
