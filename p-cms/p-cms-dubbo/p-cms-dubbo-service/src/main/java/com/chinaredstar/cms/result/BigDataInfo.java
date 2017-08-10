package com.chinaredstar.cms.result;

/**
 * Created by yixin.sun on 2016/12/27.
 */
public class BigDataInfo {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDataHistory getHistory() {
        return history;
    }

    public void setHistory(BigDataHistory history) {
        this.history = history;
    }

    private String id;

    private BigDataHistory history;
}
