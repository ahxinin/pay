package com.ahxinin.pay.domain;

import com.ahxinin.pay.dto.clientobject.AlipayWapNotifyCO;
import com.ahxinin.pay.enums.PayOrderStateEnum;
import com.ahxinin.pay.enums.WeixinPayTradeStateEnum;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 支付订单结果
 * @date : 2024-04-17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayOrderResult implements Serializable {


    /**
     * 主键
     */
    private Long id;

    /**
     * 支付订单状态
     * see {@link PayOrderStateEnum}
     */
    private String state;

    /**
     * 支付宝交易状态
     * @param notifyCO 支付宝回调参数
     */
    public static PayOrderResult alipayState(AlipayWapNotifyCO notifyCO){
        Long id = Long.valueOf(notifyCO.getOut_trade_no());
        if (notifyCO.tradeSuccess()){
            return initState(id, PayOrderStateEnum.PAID);
        }
        if (notifyCO.tradeClosed()){
            return initState(id, PayOrderStateEnum.CANCEL);
        }
        return initState(id, PayOrderStateEnum.UNKNOW);
    }

    /**
     * 微信交易状态
     */
    public static PayOrderResult weixinPayState(String outTradeNo, String tradeState){
        Long payOrderId = Long.valueOf(outTradeNo);
        WeixinPayTradeStateEnum stateEnum = WeixinPayTradeStateEnum.parseCode(tradeState);
        if (stateEnum == null){
            return initState(payOrderId, PayOrderStateEnum.UNKNOW);
        }
        if (stateEnum.tradePadding()){
            return initState(payOrderId, PayOrderStateEnum.PENDING);
        }
        if (stateEnum.tradeSuccess()){
            return initState(payOrderId, PayOrderStateEnum.PAID);
        }
        if (stateEnum.tradeClosed()){
            return initState(payOrderId, PayOrderStateEnum.CANCEL);
        }
        return initState(payOrderId, PayOrderStateEnum.UNKNOW);
    }

    /**
     * 未知交易状态
     */
    public static PayOrderResult unknowState(Long id){
        return initState(id, PayOrderStateEnum.UNKNOW);
    }

    /**
     * 交易成功
     */
    public Boolean success(){
        return PayOrderStateEnum.PAID.getCode().equals(state);
    }

    /**
     * 交易取消
     */
    public Boolean cancel(){
        return PayOrderStateEnum.CANCEL.getCode().equals(state);
    }


    /**
     * 初始化交易状态
     */
    private static PayOrderResult initState(Long id, PayOrderStateEnum stateEnum){
        return PayOrderResult.builder()
                .id(id)
                .state(stateEnum.getCode())
                .build();
    }
}
