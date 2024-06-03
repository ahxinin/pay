package com.ahxinin.pay.dto.clientobject;

import java.io.Serializable;
import lombok.Data;

/**
 * @description: 微信支付回调金额数据
 * @date : 2024-03-22
 */
@Data
public class WeixinPayNotifyAmountCO implements Serializable {

    /**
     * 订单总金额
     */
    private String total;

    /**
     * 用户支付金额
     */
    private String payer_total;

    /**
     * 货币类型
     */
    private String currency;

    /**
     * 用户支付币种
     */
    private String payer_currency;
}
