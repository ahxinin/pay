package com.ahxinin.pay.customer;

import com.ahxinin.pay.api.PayGatewayServieI;
import com.ahxinin.pay.convert.OrderMapper;
import com.ahxinin.pay.domain.customer.gateway.OrderGateway;
import com.ahxinin.pay.domain.order.Order;
import com.ahxinin.pay.dto.UnionPayCmd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 支付网关
 * @date : 2023-09-14
 */
@Service
public class PayGatewayService implements PayGatewayServieI {

    @Autowired
    private OrderGateway orderGateway;

    @Override
    public String unionPay(UnionPayCmd unionPayCmd) {
        //TODO
        Order order = OrderMapper.INSTANCE.toOrder(unionPayCmd);
        orderGateway.create(order);

        return null;
    }
}
