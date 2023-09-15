package com.ahxinin.pay.customer;

import com.ahxinin.pay.api.PayGatewayServieI;
import com.ahxinin.pay.api.PayServiceI;
import com.ahxinin.pay.convert.PayCmdMapper;
import com.ahxinin.pay.domain.customer.gateway.PayChannelGateway;
import com.ahxinin.pay.domain.order.Order;
import com.ahxinin.pay.dto.PayCmd;
import com.ahxinin.pay.dto.data.PayDTO;
import com.ahxinin.pay.order.OrderMapper;
import com.alibaba.cola.dto.MultiResponse;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

/**
 * @description: 支付渠道
 * @date : 2023-09-15
 */
@Service
public class PayChannelGatewayImpl implements PayChannelGateway {

    @DubboReference
    private PayServiceI payServiceI;

    @Override
    public String pay(Order order) {
        PayCmd payCmd = PayCmdMapper.INSTANCE.toPayCmd(order);
        MultiResponse<PayDTO> response = payServiceI.pay(payCmd);
        return null;
    }
}
