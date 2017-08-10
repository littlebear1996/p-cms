package com.chinaredstar.cms.mq;

import com.chinaredstar.cms.api.service.CmsIndexPromotionService;
import com.chinaredstar.cms.api.vo.index.IndexPromotionGoodsVo;
import com.chinaredstar.mmc.bean.PromotionEndMsg;
import com.chinaredstar.perseus.utils.JsonUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by yixin.sun on 2017/5/23.
 */
@Component("promitionMQListener")
public class PromotionMQListener implements MessageListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(PromotionMQListener.class);
    @Autowired
    private CmsIndexPromotionService indexPromotionService;

    private final Integer PROMOTION_END = 4;
    private final Integer PROMOTION_CANCEL = 5;
    private final Integer PROMOTION_TYPE_BUY = 29;

    @Override
    public void onMessage(Message message) {
        byte[] body = message.getBody();
        if (body == null || body.length == 0) {
            return;
        }
        try {
            String msg = new String(body);
            EventMessage eventMessage = (EventMessage) JsonUtil.fromJson(msg, EventMessage.class);
            if (eventMessage == null) {
                return;
            }
            String attachment = eventMessage.getAttachment();
            if (StringUtils.isBlank(attachment)) {
                return;
            }
            PromotionEndMsg promotionEndMsg = (PromotionEndMsg) JsonUtil.fromJson(attachment, PromotionEndMsg.class);
            if (promotionEndMsg.getPromotionType().intValue() != PROMOTION_TYPE_BUY) {
                return;
            }
            Integer promotionId = promotionEndMsg.getPromotionId();
            Byte status = promotionEndMsg.getStatus();
            if (status.intValue() == PROMOTION_END || status.intValue() == PROMOTION_CANCEL) {
                indexPromotionService.deleteByPromotionId(String.valueOf(promotionId));
            }
        } catch (Exception e) {
            LOGGER.error("消费促销活动结束消息发生异常：", e);
        }
    }
}
