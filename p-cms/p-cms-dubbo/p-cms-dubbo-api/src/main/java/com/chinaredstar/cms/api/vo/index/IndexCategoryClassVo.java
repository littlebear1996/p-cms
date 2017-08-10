package com.chinaredstar.cms.api.vo.index;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yixin.sun on 2017/3/27.
 */
@ApiModel("类目分类")
public class IndexCategoryClassVo implements Serializable {
    private static final long serialVersionUID = 5021777021069554124L;

    /**
     * id
     */
    @ApiModelProperty("id")
    private Integer id;

    /**
     * 主分类id
     */
    @ApiModelProperty("主分类id")
    private Integer categoryId;

    /**
     * 分类id
     */
    @ApiModelProperty("分类id")
    private String classId;

    /**
     * 分类名称
     */
    @ApiModelProperty("分类名称")
    private String className;

    /**
     * 分类图片
     */
    @ApiModelProperty("分类图片")
    private String classImgUrl;

    /**
     * 父级分类id
     */
    private Integer pid;

    /**
     * 分类排序号
     */
    private Integer sortNo;

    private List<IndexCategoryClassVo> subClassList;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassImgUrl() {
        return classImgUrl;
    }

    public void setClassImgUrl(String classImgUrl) {
        this.classImgUrl = classImgUrl;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public List<IndexCategoryClassVo> getSubClassList() {
        return subClassList;
    }

    public void setSubClassList(List<IndexCategoryClassVo> subClassList) {
        this.subClassList = subClassList;
    }
}
