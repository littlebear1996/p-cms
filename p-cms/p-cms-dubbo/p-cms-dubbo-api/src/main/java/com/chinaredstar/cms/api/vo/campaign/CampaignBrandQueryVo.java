package com.chinaredstar.cms.api.vo.campaign;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ykk on 16/10/16.
 */
public class CampaignBrandQueryVo implements Serializable {


    private static final long serialVersionUID = -3738328076177072448L;

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
