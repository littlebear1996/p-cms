package com.chinaredstar.cms.api.model;

import java.io.Serializable;

public class ActivityPrizeShare implements Serializable {
    private static final long serialVersionUID = 6120859437556952840L;
    private Integer id;

    private String openId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }
}