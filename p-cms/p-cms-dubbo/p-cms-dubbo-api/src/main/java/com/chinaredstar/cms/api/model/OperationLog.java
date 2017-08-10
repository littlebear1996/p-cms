package com.chinaredstar.cms.api.model;

import java.util.Date;

/**
 * 操作日志实体
 */
public class OperationLog extends BaseModel {
    private static final long serialVersionUID = 3064994232692816299L;

    /** PK */
    private Long id;

    /** 操作者ID */
    private String operator;

    /** 操作者名称 */
    private String operatorName;

    /** 操作类型: 编辑,驳回,通过审核 */
    private String operateType;

    /** 操作描述 */
    private String operateDesc;

    /** 创建时间 */
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getOperateDesc() {
        return operateDesc;
    }

    public void setOperateDesc(String operateDesc) {
        this.operateDesc = operateDesc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}