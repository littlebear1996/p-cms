package com.chinaredstar.cms.api.enums;

/**
 * Created by lenovo on 2016/8/21.
 */
public enum RecommendationContentTypeEnum {
    CASE(1, "方案"),
    DESIGNER(2, "设计师合辑"),
    ARTICLE(3, "文章"),
    ATLAS(4, "图集"),
    OUTLINK(5, "外链"),
    TOPIC(6, "专题");

    private int value;

    private String desc;

    RecommendationContentTypeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static RecommendationContentTypeEnum valueOf(int value) {
        for (RecommendationContentTypeEnum typeEnum : RecommendationContentTypeEnum.values()) {
            if (typeEnum.getValue() == value) {
                return typeEnum;
            }
        }
        return null;
    }
}
