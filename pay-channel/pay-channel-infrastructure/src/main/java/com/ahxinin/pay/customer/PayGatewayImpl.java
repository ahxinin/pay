package com.ahxinin.pay.customer;

import com.ahxinin.pay.domain.customer.Pay;
import com.ahxinin.pay.domain.customer.gateway.PayGateway;
import com.ahxinin.pay.dto.PayCmd;
import org.springframework.stereotype.Service;

@Service
public class PayGatewayImpl implements PayGateway {

    @Override
    public Pay createTrade(PayCmd payCmd) {
        return null;
    }
}
