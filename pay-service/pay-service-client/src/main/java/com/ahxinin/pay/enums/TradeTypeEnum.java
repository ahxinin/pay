package com.ahxinin.pay.enums;

import lombok.Getter;

/**
 * @description: 交易类型
 * @date : 2024-03-15
 */
public enum TradeTypeEnum {

    USER_PLAN("user-plan", "用户套餐"),;

    @Getter
    private String code;

    @Getter
    private String desc;

    TradeTypeEnum(String code, String desc){
        this.code = code;
        this.desc = desc;
    }
}
