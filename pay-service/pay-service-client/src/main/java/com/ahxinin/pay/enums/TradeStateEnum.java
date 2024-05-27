package com.ahxinin.pay.enums;

import lombok.Getter;

/**
 * @description: 资源交易状态
 * @date : 2024-03-05
 */
public enum TradeStateEnum {

    /**
     * 交易成功
     */
    TRADE_SUCCESS("TRADE_SUCCESS", "交易成功"),

    /**
     * 交易关闭
     */
    TRADE_CLOSED("TRADE_CLOSED", "交易关闭"),

    /**
     * 交易结束
     */
    TRADE_FINISH("TRADE_FINISHED", "交易结束"),

    /**
     * 交易异常
     */
    TRADE_EXCEPTION("TRADE_EXCEPTION", "交易异常"),

    /**
     * 交易通知校验失败
     */
    TRADE_NOTIFY_VERIFY_FAIL("TRADE_NOTIFY_VERIFY_FAIL", "交易通知校验失败"),

    TRADE_UNKNOW("TRADE_UNKNOW", "未知状态")
    ;

    @Getter
    private final String tradeState;
    @Getter
    private final String tradeDesc;

    private TradeStateEnum(String tradeState, String tradeDesc){
        this.tradeState = tradeState;
        this.tradeDesc = tradeDesc;
    }
}
