package com.ahxinin.pay.api;

import com.ahxinin.pay.dto.ListPayOrderQuery;
import com.ahxinin.pay.dto.clientobject.PayOrderCO;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.SingleResponse;

/**
 * @description: 支付订单
 * @date : 2024-03-26
 */
public interface PayOrderServiceI {

    /**
     * 根据tradeId查询
     */
    SingleResponse<PayOrderCO> getCacheByTradeId(Long tradeId);

    /**
     * 查询订单
     */
    SingleResponse<PageResponse<PayOrderCO>> listOrder(ListPayOrderQuery listPayOrderQuery);
}
