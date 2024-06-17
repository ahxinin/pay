package com.ahxinin.pay.api;

import com.ahxinin.pay.dto.WeixinMpAuthCmd;
import com.ahxinin.pay.dto.WeixinMpInitCmd;
import com.ahxinin.pay.dto.WeixinPayJsApiNotifyCmd;
import com.ahxinin.pay.dto.clientobject.PayNotifyCO;
import com.ahxinin.pay.dto.clientobject.WeixinMpAuthCO;
import com.ahxinin.pay.dto.clientobject.WeixinMpInitCO;
import com.alibaba.cola.dto.SingleResponse;

/**
 * @description: 微信支付
 * @date : 2024-06-17
 */
public interface WeixinServiceI {

    /**
     * 获取微信公众号授权页面URL
     */
    SingleResponse<WeixinMpInitCO> weixinMpInit(WeixinMpInitCmd weixinMpInitCmd);

    /**
     * 微信公众号授权
     */
    SingleResponse<WeixinMpAuthCO> weixinMpAuth(WeixinMpAuthCmd weixinMpAuthCmd);

    /**
     * 微信支付通知
     */
    SingleResponse<PayNotifyCO> weixinNotify(WeixinPayJsApiNotifyCmd notifyCmd);
}
