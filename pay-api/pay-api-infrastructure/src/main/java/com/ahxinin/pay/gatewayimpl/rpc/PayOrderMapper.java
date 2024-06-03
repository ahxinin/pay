package com.ahxinin.pay.gatewayimpl.rpc;

import com.ahxinin.pay.api.PayOrderServiceI;
import com.ahxinin.pay.convert.PayOrderConvert;
import com.ahxinin.pay.domain.PayOrder;
import com.ahxinin.pay.dto.ListPayOrderQuery;
import com.ahxinin.pay.dto.clientobject.PayOrderCO;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

/**
 * @description: 支付订单
 * @date : 2024-03-26
 */
@Slf4j
@Component
public class PayOrderMapper {

    @DubboReference
    private PayOrderServiceI payOrderService;

    public PayOrder getByTradeId(Long tradeId){
        SingleResponse<PayOrderCO> response = payOrderService.getCacheByTradeId(tradeId);
        if (!response.isSuccess()){
            throw new BizException(response.getErrMessage());
        }

        PayOrderCO payOrderCO = response.getData();
        return PayOrderConvert.INSTANCE.toDomainObject(payOrderCO);
    }
}
