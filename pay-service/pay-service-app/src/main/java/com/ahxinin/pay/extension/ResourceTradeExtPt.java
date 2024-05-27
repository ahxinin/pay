package com.ahxinin.pay.extension;

import com.ahxinin.pay.contants.PlanConstant;
import com.ahxinin.pay.dto.clientobject.PayNotifyCO;
import com.alibaba.cola.extension.Extension;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: 资源交易
 * @date : 2024-04-17
 */
@Slf4j
@Extension(bizId = PlanConstant.BIZ_ID, useCase = PlanConstant.TRADE_CASE_RESOURCE)
public class ResourceTradeExtPt implements TradeExtPt{

    @Override
    public PayNotifyCO notifySuccess(Long id) {
        return null;
    }
}
