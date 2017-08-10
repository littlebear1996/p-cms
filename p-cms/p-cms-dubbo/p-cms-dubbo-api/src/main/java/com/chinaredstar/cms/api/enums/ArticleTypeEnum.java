package com.chinaredstar.cms.api.enums;

/**
 * 文章类型枚举
 */
public enum ArticleTypeEnum {

    PRODUCT_ARTICLE(1, "商品文章"),

    MARKET_ARTICLE(2, "商场文章"),

    HOME_CASE_ARTICLE(3, "家装案例文章"),

    HOUSE_PROPERTY_ARTICLE(4, "房产文章");

    private Integer value;

    private String desc;

    ArticleTypeEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static ArticleTypeEnum valueOf(Integer value) {
        for (ArticleTypeEnum articleTypeEnum : ArticleTypeEnum.values()) {
            if (articleTypeEnum.getValue().equals(value)) {
                return articleTypeEnum;
            }
        }
        return null;
    }
}
