package com.ahxinin.pay.order;

import com.ahxinin.pay.domain.customer.gateway.OrderGateway;
import com.ahxinin.pay.domain.order.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderGatewayImpl implements OrderGateway {

    @Override
    public Boolean create(Order order) {
        return null;
    }
}