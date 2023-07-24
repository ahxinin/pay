package com.ahxinin.pay.customer;

import com.ahxinin.pay.channel.PayExtPt;
import com.ahxinin.pay.channel.alipay.AlipayWebExt;
import com.ahxinin.pay.domain.customer.Pay;
import com.ahxinin.pay.domain.customer.gateway.PayGateway;
import com.ahxinin.pay.domain.exception.PayException;
import com.ahxinin.pay.dto.PayCmd;
import com.alibaba.cola.extension.BizScenario;
import com.alibaba.cola.extension.ExtensionExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayGatewayImpl implements PayGateway {

    @Autowired
    private ExtensionExecutor extensionExecutor;

    @Override
    public Pay createTrade(PayCmd payCmd) {
        try {
            BizScenario bizScenario = BizScenario.newDefault();
            String data = extensionExecutor.execute(PayExtPt.class, bizScenario, pt -> pt.pay(payCmd));
            return Pay.buildData(data);
        } catch (PayException e) {
            throw new RuntimeException(e);
        }
    }
}