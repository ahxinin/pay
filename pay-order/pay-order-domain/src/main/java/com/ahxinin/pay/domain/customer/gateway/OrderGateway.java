package com.ahxinin.pay.domain.customer.gateway;

import com.ahxinin.pay.domain.order.Order;

/**
 * @description: 订单
 * @date : 2023-09-14
 */
public interface OrderGateway {

    Boolean create(Order order);
}
