package com.ahxinin.pay.extension;

import com.ahxinin.pay.contants.PlanConstant;
import com.ahxinin.pay.domain.PayOrder;
import com.ahxinin.pay.dto.clientobject.PayNotifyCO;
import com.ahxinin.pay.gateway.PayOrderGateway;
import com.alibaba.cola.extension.Extension;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: 套餐交易
 * @date : 2024-04-17
 */
@Slf4j
@Extension(bizId = PlanConstant.BIZ_ID, useCase = PlanConstant.TRADE_CASE_PLAN)
public class PlanTradeExtPt implements TradeExtPt{

    @Resource
    private PayOrderGateway payOrderGateway;

    @Override
    public PayNotifyCO notifySuccess(Long id) {
        return null;
    }
}
