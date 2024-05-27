package com.ahxinin.pay.enums;

import lombok.Getter;

/**
 * @description: 支付订单状态
 * @date : 2024-03-20
 */
public enum PayOrderStateEnum {

    PENDING("pending", "待支付"),
    PAID("paid", "已支付"),
    CANCEL("cancel", "已取消"),
    UNKNOW("unknow", "未知");

    @Getter
    private String code;
    @Getter
    private String desc;

    PayOrderStateEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * 是否已完成
     */
    public static boolean isFinished(String state){
        return PAID.getCode().equals(state) || CANCEL.getCode().equals(state);
    }
}
