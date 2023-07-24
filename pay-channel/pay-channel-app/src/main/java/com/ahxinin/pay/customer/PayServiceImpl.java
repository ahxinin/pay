package com.ahxinin.pay.customer;

import com.ahxinin.pay.api.PayServiceI;
import com.ahxinin.pay.domain.customer.gateway.PayGateway;
import com.ahxinin.pay.dto.PayCmd;
import com.ahxinin.pay.dto.data.PayDTO;
import com.alibaba.cola.dto.MultiResponse;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class PayServiceImpl implements PayServiceI {

    @Autowired
    private PayGateway payGateway;

    @Override
    public MultiResponse<PayDTO> pay(PayCmd payCmd) {
        payGateway.createTrade(payCmd);
        return null;
    }
}
