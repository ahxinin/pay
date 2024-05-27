package com.ahxinin.pay.dto.clientobject;

import com.ahxinin.pay.enums.TradeStateEnum;
import com.ahxinin.pay.enums.TradeTypeEnum;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 支付通知返回值
 * @date : 2024-03-15
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayNotifyCO implements Serializable {

    /**
     * 用户Id
     */
    private Long userId;

    /**
     * 交易Id
     */
    private Long tradeId;

    /**
     * 交易类型
     * see {@link TradeTypeEnum}
     */
    private String tradeType;

    /**
     * 交易状态
     * see {@link TradeStateEnum}
     */
    private String tradeState;

    /**
     * 返回消息
     */
    private String returnMessage;

    /**
     * 交易失败
     */
    public static PayNotifyCO failed(){
        return PayNotifyCO.builder()
            .tradeState(TradeStateEnum.TRADE_UNKNOW.getTradeState())
            .build();
    }

    /**
     * 交易取消
     */
    public static PayNotifyCO cancel(){
        return PayNotifyCO.builder()
            .tradeState(TradeStateEnum.TRADE_CLOSED.getTradeState())
            .build();
    }

    /**
     * 微信交易成功返回信息
     */
    public void updateWeixinSuccess(){
        this.returnMessage= "SUCCESS";
    }

    /**
     * 支付宝交易成功返回信息
     */
    public void updateAliSuccess(){
        this.returnMessage= "success";
    }

    /**
     * 失败返回信息
     */
    public static PayNotifyCO failReturnMessage(){
        return PayNotifyCO.builder()
            .returnMessage("FAIL")
            .build();
    }

    /**
     * 是否需要解锁翻译内容
     */
    public boolean needUnlockTranslate(){
        return TradeTypeEnum.USER_PLAN.getCode().equals(tradeType)
                && TradeStateEnum.TRADE_SUCCESS.getTradeState().equals(tradeState);
    }
}
