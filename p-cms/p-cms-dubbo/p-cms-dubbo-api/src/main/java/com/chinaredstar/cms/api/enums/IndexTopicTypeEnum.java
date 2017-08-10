package com.chinaredstar.cms.api.enums;

/**
 * Created by pengfei.wang on 2017/3/24.
 */
public enum IndexTopicTypeEnum {
    ARTICLE_CUSTOM(1, "商品文章"),
    ARTICLE_MARKET(2, "商场文章"),
    ARTICLE_HOME(3, "家装案例"),
    ARTICLE_HOUSE(4, "房产文章");

    private Integer value;

    private String desc;

    IndexTopicTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static IndexTopicTypeEnum valueOf(Integer value) {
        for (IndexTopicTypeEnum topicTypeEnum : IndexTopicTypeEnum.values()) {
            if (topicTypeEnum.getValue().equals(value)) {
                return topicTypeEnum;
            }
        }
        return null;
    }
}
