package com.ahxinin.pay.gatewayimpl.database.dataobject;

import com.ahxinin.pay.dto.clientobject.AlipayWapNotifyCO;
import com.alibaba.fastjson2.JSONObject;
import com.wechat.pay.java.service.payments.model.Transaction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 支付通知
 * @date : 2024-03-05
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayNotifyDO {

    /**
     * 标识符Id
     */
    private Long id;

    /**
     * 第三方交易号
     */
    private String tradeNo;

    /**
     * 商户订单号
     */
    private String outTradeNo;

    /**
     * 交易状态
     */
    private String tradeStatus;

    /**
     * 通知内容
     */
    private String notifyData;

    /**
     * 是否删除
     */
    private Boolean deleted;

    /**
     * 更新时间
     */
    private Long updatedAt;

    /**
     * 创建时间
     */
    private Long createdAt;

    /**
     * 构建支付宝通知
     */
    public static PayNotifyDO fromAlipay(AlipayWapNotifyCO alipayWapNotifyCO){
        return PayNotifyDO.builder()
                .tradeNo(alipayWapNotifyCO.getTrade_no())
                .outTradeNo(alipayWapNotifyCO.getOut_trade_no())
                .tradeStatus(alipayWapNotifyCO.getTrade_status())
                .notifyData(JSONObject.toJSONString(alipayWapNotifyCO))
                .createdAt(System.currentTimeMillis())
                .build();
    }

    /**
     * 构建微信支付通知
     */
    public static PayNotifyDO fromWeixinPay(Transaction  transaction){
        return PayNotifyDO.builder()
                .tradeNo(transaction.getTransactionId())
                .outTradeNo(transaction.getOutTradeNo())
                .tradeStatus(transaction.getTradeState().name())
                .notifyData(JSONObject.toJSONString(transaction))
                .createdAt(System.currentTimeMillis())
                .build();
    }
}
