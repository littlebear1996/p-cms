package com.chinaredstar.cms.api.model;

import java.io.Serializable;

public class ActivityPrizeMarket implements Serializable{
    private static final long serialVersionUID = -6941259038341583305L;
    private Integer id;

    private String prizeName;

    private String prizeUrl;

    private String marketName;

    private String marketCode;

    private Integer count;

    private Integer gameRound;

    //展位号
    private String shopNumber;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName == null ? null : prizeName.trim();
    }

    public String getPrizeUrl() {
        return prizeUrl;
    }

    public void setPrizeUrl(String prizeUrl) {
        this.prizeUrl = prizeUrl == null ? null : prizeUrl.trim();
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName == null ? null : marketName.trim();
    }

    public String getMarketCode() {
        return marketCode;
    }

    public void setMarketCode(String marketCode) {
        this.marketCode = marketCode == null ? null : marketCode.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getShopNumber() {
        return shopNumber;
    }

    public void setShopNumber(String shopNumber) {
        this.shopNumber = shopNumber;
    }

    public Integer getGameRound() {
        return gameRound;
    }

    public void setGameRound(Integer gameRound) {
        this.gameRound = gameRound;
    }
}