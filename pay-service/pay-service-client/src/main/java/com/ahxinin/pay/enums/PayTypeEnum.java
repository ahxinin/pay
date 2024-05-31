package com.ahxinin.pay.enums;

import lombok.Getter;

/**
 * @description: 支付类型
 * @date : 2024-03-18
 */
public enum PayTypeEnum {

    WEIXIN("weixin", "微信支付"),

    ALIPAY("ali", "支付宝");

    @Getter
    private String code;
    @Getter
    private String desc;

    PayTypeEnum(String code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public static PayTypeEnum parse(String code) {
        for (PayTypeEnum payTypeEnum: PayTypeEnum.values()) {
            if (payTypeEnum.getCode().equals(code)) {
                return payTypeEnum;
            }
        }
        return null;
    }
}
