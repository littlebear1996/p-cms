package com.chinaredstar.cms.api.vo.mdm;

import com.chinaredstar.cms.api.vo.atlas.AtlasDetailVo;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaobc on 16-8-19.
 */
public class MdmVo implements Serializable {
    private static final long serialVersionUID = 1068432412831588856L;
    /**
     * 主数据对应的表名
     */
    @ApiModelProperty("主数据对应的表名")
    private String name;
    /**
     * 表格描述
     */
    @ApiModelProperty("表格描述")
    private String description;

    /**
     * 存放的数据
     */
    @ApiModelProperty("存放的数据")
    private List<Map<String,Object>> values;


    public MdmVo() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Map<String, Object>> getValues() {
        return values;
    }

    public void setValues(List<Map<String, Object>> values) {
        this.values = values;
    }
}
