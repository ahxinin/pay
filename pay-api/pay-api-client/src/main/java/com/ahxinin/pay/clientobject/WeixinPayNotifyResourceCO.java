package com.ahxinin.pay.clientobject;

import java.io.Serializable;
import lombok.Data;

/**
 * @description: 微信支付回调资源数据
 * @date : 2024-03-22
 */
@Data
public class WeixinPayNotifyResourceCO implements Serializable {

    /**
     * 商户appId
     */
    private String appid;

    /**
     * 商户号
     */
    private String mchid;

    /**
     * 商户系统内部订单号
     */
    private String out_trade_no;

    /**
     * 微信支付系统生成的订单号
     */
    private String transaction_id;

    /**
     * 交易类型
     */
    private String trade_type;

    /**
     * 交易状态
     */
    private String trade_state;

    /**
     * 交易状态描述
     */
    private String trade_state_desc;

    /**
     * 付款银行
     */
    private String bank_type;

    /**
     * 附加数据
     */
    private String attach;

    /**
     * 支付完成时间
     */
    private String success_time;

    /**
     * 支付者
     */
    private WeixinPayNotifyPayerCO payer;

    /**
     * 金额
     */
    private WeixinPayNotifyAmountCO amount;

}
