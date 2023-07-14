package com.ahxinin.pay.domain.customer.gateway;

import com.ahxinin.pay.domain.customer.Pay;
import com.ahxinin.pay.dto.PayCmd;

public interface PayGateway {

    Pay createTrade(PayCmd payCmd);
}
