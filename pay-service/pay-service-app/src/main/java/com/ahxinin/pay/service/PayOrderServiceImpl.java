package com.ahxinin.pay.service;

import com.ahxinin.pay.api.PayOrderServiceI;
import com.ahxinin.pay.assembler.PayOrderAssembler;
import com.ahxinin.pay.domain.PayOrder;
import com.ahxinin.pay.dto.ListPayOrderQuery;
import com.ahxinin.pay.dto.clientobject.PayOrderCO;
import com.ahxinin.pay.gateway.PayOrderGateway;
import com.ahxinin.pay.gatewayimpl.database.PayOrderMapper;
import com.ahxinin.pay.gatewayimpl.database.dataobject.PayOrderDO;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.SingleResponse;
import java.util.List;
import javax.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @description: 支付订单
 * @date : 2024-03-26
 */
@DubboService
public class PayOrderServiceImpl implements PayOrderServiceI {

    @Resource
    private PayOrderMapper payOrderMapper;
    @Resource
    private PayOrderGateway payOrderGateway;

    @Override
    public SingleResponse<PayOrderCO> getCacheByTradeId(Long tradeId) {
        PayOrder payOrder = payOrderGateway.getPayOrderCache(tradeId);
        PayOrderCO payOrderCO = PayOrderAssembler.INSTANCE.toClientObject(payOrder);
        return SingleResponse.of(payOrderCO);
    }

    @Override
    public SingleResponse<PageResponse<PayOrderCO>> listOrder(ListPayOrderQuery listPayOrderQuery) {
        List<PayOrderDO> payOrderDOList = payOrderMapper.listPayOrder(listPayOrderQuery);
        Long total = payOrderMapper.countPayOrder(listPayOrderQuery);

        List<PayOrderCO> payOrderCOList = PayOrderAssembler.INSTANCE.toClientObjectList(payOrderDOList);
        PageResponse<PayOrderCO> pageResponse = PageResponse.of(payOrderCOList, total.intValue(), listPayOrderQuery.getPageSize(), listPayOrderQuery.getPageIndex());

        return SingleResponse.of(pageResponse);
    }

    private SingleResponse<PayOrderCO> getByTradeId(Long tradeId) {
        PayOrderDO payOrderDO = payOrderMapper.getByTradeId(tradeId);
        PayOrderCO payOrderCO = PayOrderAssembler.INSTANCE.toClientObject(payOrderDO);
        return SingleResponse.of(payOrderCO);
    }
}
