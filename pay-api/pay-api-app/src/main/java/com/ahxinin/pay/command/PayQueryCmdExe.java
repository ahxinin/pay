package com.ahxinin.pay.command;

import com.ahxinin.pay.assembler.PayOrderAssembler;
import com.ahxinin.pay.domain.PayOrder;
import com.ahxinin.pay.dto.clientobject.PayEventCO;
import com.ahxinin.pay.dto.clientobject.PayOrderDataCO;
import com.ahxinin.pay.gatewayimpl.rpc.PayMapper;
import com.ahxinin.pay.gatewayimpl.rpc.PayOrderMapper;
import com.alibaba.cola.dto.SingleResponse;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description: 支付订单查询
 * @date : 2024-03-26
 */
@Slf4j
@Component
public class PayQueryCmdExe {

    @Resource
    private PayMapper payMapper;
    @Resource
    private PayOrderMapper payOrderMapper;

    /**
     * 获取支付订单
     */
    public SingleResponse<PayOrderDataCO> getOrderByTradeId(Long tradeId){
        PayOrder payOrder = payOrderMapper.getByTradeId(tradeId);
        PayOrderDataCO payOrderCO = PayOrderAssembler.INSTANCE.toClientObject(payOrder);
        return SingleResponse.of(payOrderCO);
    }

    /**
     * 获取支付结果
     */
    public SingleResponse<PayEventCO> getPayResult(Long payOrderId){
        try {
            String state = payMapper.getPayResult(payOrderId);
            PayEventCO payEventCO = PayEventCO.init(state);
            return SingleResponse.of(payEventCO);
        }catch (Exception e){
            return SingleResponse.buildFailure("1000", e.getMessage());
        }
    }
}
