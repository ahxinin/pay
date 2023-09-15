package com.ahxinin.pay.domain.customer.gateway;

import com.ahxinin.pay.domain.order.Order;

/**
 * @description: 支付渠道
 * @date : 2023-09-15
 */
public interface PayChannelGateway {

    /**
     * 支付能力
     */
    String pay(Order order);
}
