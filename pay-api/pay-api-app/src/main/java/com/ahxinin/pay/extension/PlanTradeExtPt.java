package com.ahxinin.pay.extension;

import com.ahxinin.pay.constants.TradeConstant;
import com.ahxinin.pay.domain.PayTrade;
import com.ahxinin.pay.domain.PlanAuth;
import com.ahxinin.pay.dto.PreTradeCmd;
import com.alibaba.cola.extension.Extension;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: 套餐交易
 * @date : 2024-04-17
 */
@Slf4j
@Extension(bizId = TradeConstant.BIZ_ID, useCase = TradeConstant.TRADE_TYPE_PLAN)
public class PlanTradeExtPt implements TradeExtPt{

    @Override
    public PayTrade createPayTrade(PreTradeCmd preTradeCmd) {
        PayTrade payTrade = PayTrade.of(preTradeCmd);

        PlanAuth planAuth = PlanAuth.of();
        payTrade.updatePlanAuth(planAuth);
        return payTrade;
    }
}
