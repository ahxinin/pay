package com.ahxinin.pay.customer;

import com.ahxinin.pay.alipay.AlipayService;
import com.ahxinin.pay.domain.customer.Pay;
import com.ahxinin.pay.domain.customer.gateway.PayGateway;
import com.ahxinin.pay.dto.PayCmd;
import com.alipay.api.AlipayApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayGatewayImpl implements PayGateway {

    @Autowired
    private AlipayService alipayService;

    @Override
    public Pay createTrade(PayCmd payCmd) {
        try {
            alipayService.pagePay(payCmd);
        } catch (AlipayApiException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
