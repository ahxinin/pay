package com.ahxinin.pay.gateway;


import com.ahxinin.pay.domain.PayTrade;
import com.ahxinin.pay.domain.PrePayResult;

/**
 * @description: 支付网关
 * @date : 2024-03-19
 */
public interface PayGateway {

    PrePayResult prePay(PayTrade payTrade);

    String getType();
}
