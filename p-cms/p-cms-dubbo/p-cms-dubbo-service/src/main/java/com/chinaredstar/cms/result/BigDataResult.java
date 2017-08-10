package com.chinaredstar.cms.result;

import java.util.List;

/**
 * Created by yixin.sun on 2016/12/27.
 */
public class BigDataResult {
    private Integer code;

    private String error;

    private String owner;

    private String operation;

    private List<BigDataInfo> info;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public List<BigDataInfo> getInfo() {
        return info;
    }

    public void setInfo(List<BigDataInfo> info) {
        this.info = info;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
