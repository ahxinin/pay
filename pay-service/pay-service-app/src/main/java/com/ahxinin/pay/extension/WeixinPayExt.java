package com.ahxinin.pay.extension;

import com.ahxinin.pay.config.WeixinPayConfig;
import com.ahxinin.pay.contants.PlanConstant;
import com.ahxinin.pay.domain.PayOrder;
import com.ahxinin.pay.domain.PayOrderResult;
import com.ahxinin.pay.dto.GetPayResultCmd;
import com.ahxinin.pay.dto.clientobject.PayCO;
import com.ahxinin.pay.gatewayimpl.http.WeixinPayMapper;
import com.ahxinin.pay.gatewayimpl.http.dataobject.WeixinPayJsApiDO;
import com.alibaba.cola.extension.Extension;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * @description: 微信支付
 * @date : 2024-03-14
 */
@Slf4j
@Extension(bizId = PlanConstant.BIZ_ID, useCase = PlanConstant.PAY_CASE_WEIXIN)
public class WeixinPayExt implements PayExtPt{

    @Resource
    private WeixinPayMapper weixinPayMapper;
    @Resource
    private WeixinPayConfig weixinPayConfig;

    @Override
    public PayCO pay(PayOrder payOrder) {
        WeixinPayJsApiDO jsApiDO = WeixinPayJsApiDO.fromPrepayOrder(payOrder);
        //通知地址
        jsApiDO.addNotifyUrl(weixinPayConfig.getNotifyUrl());
        //预下单
        return weixinPayMapper.jsApiPay(jsApiDO);
    }

    @Override
    public PayOrderResult queryPayResult(GetPayResultCmd getPayResultCmd) {
        return weixinPayMapper.query(String.valueOf(getPayResultCmd.getPayOrderId()));
    }
}
