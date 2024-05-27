package com.ahxinin.pay.enums;

/**
 * @description: 微信支付交易状态
 * @date : 2024-04-12
 */
public enum WeixinPayTradeStateEnum {

    SUCCESS("SUCCESS", "支付成功"),
    REFUND("REFUND", "转入退款"),
    NOTPAY("NOTPAY", "未支付"),
    CLOSED("CLOSED", "已关闭"),
    REVOKED("REVOKED", "已撤销（刷卡支付）"),
    USERPAYING("USERPAYING", "用户支付中"),
    PAYERROR("PAYERROR", "支付失败");

    public String code;
    public String desc;

    WeixinPayTradeStateEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static WeixinPayTradeStateEnum parseCode(String code){
        for (WeixinPayTradeStateEnum state : WeixinPayTradeStateEnum.values()) {
            if (state.code.equals(code)) {
                return state;
            }
        }
        return null;
    }

    public boolean tradePadding(){
        return this == NOTPAY;
    }

    public boolean tradeSuccess(){
        return this == SUCCESS;
    }

    public boolean tradeClosed(){
        return this == CLOSED;
    }
}
