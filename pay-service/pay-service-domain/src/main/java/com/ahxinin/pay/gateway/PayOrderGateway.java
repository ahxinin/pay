package com.ahxinin.pay.gateway;

import com.ahxinin.pay.domain.PayOrder;
import com.ahxinin.pay.dto.PayCmd;
import com.ahxinin.pay.dto.clientobject.PayCO;

/**
 * @description: 支付订单网关
 * @date : 2024-03-21
 */
public interface PayOrderGateway {

    /**
     * 创建支付订单
     */
    void createPayOrder(PayOrder payOrder);

    /**
     * 更新支付订单状态
     */
    Boolean updatePaid(Long id);

    /**
     * 更新支付订单状态
     */
    Boolean updateCancel(Long id);

    /**
     * 获取支付订单
     */
    PayOrder getById(Long id);

    /**
     * 更新状态缓存
     */
    void updateStateCache(Long id, String state);

    /**
     * 获取状态缓存
     */
    String getStateCache(Long id);

    /**
     * 设置支付缓存
     */
    void addPayCache(PayCmd payCmd, PayOrder payOrder, PayCO payCO);

    /**
     * 获取支付参数缓存
     */
    PayCO getPayParamCache(PayCmd payCmd);

    /**
     * 获取支付订单缓存
     */
    PayOrder getPayOrderCache(Long tradeId);
}
