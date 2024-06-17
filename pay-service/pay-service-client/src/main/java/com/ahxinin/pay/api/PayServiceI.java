package com.ahxinin.pay.api;

import com.ahxinin.pay.dto.GetPayResultCmd;
import com.ahxinin.pay.dto.PayCmd;
import com.ahxinin.pay.dto.WeixinMpAuthCmd;
import com.ahxinin.pay.dto.WeixinMpInitCmd;
import com.ahxinin.pay.dto.WeixinPayJsApiNotifyCmd;
import com.ahxinin.pay.dto.clientobject.AlipayWapNotifyCO;
import com.ahxinin.pay.dto.clientobject.GetPayUrlCO;
import com.ahxinin.pay.dto.clientobject.PayCO;
import com.ahxinin.pay.dto.clientobject.PayNotifyCO;
import com.ahxinin.pay.dto.clientobject.PayOrderCO;
import com.ahxinin.pay.dto.clientobject.PayResultCO;
import com.ahxinin.pay.dto.clientobject.WeixinMpAuthCO;
import com.ahxinin.pay.dto.clientobject.WeixinMpInitCO;
import com.alibaba.cola.dto.SingleResponse;

/**
 * @description: 支付服务
 * @date : 2024-03-14
 */
public interface PayServiceI {

    /**
     * 预支付请求
     */
    SingleResponse<PayCO> pay(PayCmd payCmd);

    /**
     * 支付宝支付通知
     */
    SingleResponse<PayNotifyCO> alipayNotify(AlipayWapNotifyCO alipayWapNotifyCO);

    /**
     * 获取支付订单
     */
    SingleResponse<PayOrderCO> getPayOrder(Long payOrderId);

    /**
     * 获取支付结果
     */
    SingleResponse<PayResultCO> getPayResult(GetPayResultCmd getPayResultCmd);

    /**
     * 获取支付链接
     */
    SingleResponse<String> getPayUrl(GetPayUrlCO getPayUrlCO);
}
