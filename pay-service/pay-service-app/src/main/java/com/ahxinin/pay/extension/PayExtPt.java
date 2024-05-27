package com.ahxinin.pay.extension;

import com.ahxinin.pay.domain.PayOrder;
import com.ahxinin.pay.domain.PayOrderResult;
import com.ahxinin.pay.dto.GetPayResultCmd;
import com.ahxinin.pay.dto.clientobject.PayCO;
import com.alibaba.cola.extension.ExtensionPointI;

/**
 * @description: 支付
 * @date : 2024-03-14
 */
public interface PayExtPt extends ExtensionPointI {

    /**
     * 支付费用
     */
    PayCO pay(PayOrder payOrder);

    /**
     * 查询支付费用
     */
    PayOrderResult queryPayResult(GetPayResultCmd getPayResultCmd);
}
