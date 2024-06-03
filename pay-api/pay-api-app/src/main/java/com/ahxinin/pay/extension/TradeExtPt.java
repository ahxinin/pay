package com.ahxinin.pay.extension;

import com.ahxinin.pay.domain.PayTrade;
import com.ahxinin.pay.dto.PreTradeCmd;
import com.alibaba.cola.extension.ExtensionPointI;

/**
 * @description: 交易
 * @date : 2024-04-17
 */
public interface TradeExtPt extends ExtensionPointI {

    /**
     * 创建支付交易信息
     */
    PayTrade createPayTrade(PreTradeCmd preTradeCmd);
}
