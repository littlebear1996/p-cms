package com.chinaredstar.cms.api.vo.campaign;

import java.io.Serializable;

/**
 * Created by Ykk on 16/10/17.
 */
public class CampaignMarketQueryVo implements Serializable {


    private static final long serialVersionUID = -468473966160878963L;


    /**
     * 活动id
     */
    private Integer campaignId;

    public Integer getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Integer campaignId) {
        this.campaignId = campaignId;
    }

}
