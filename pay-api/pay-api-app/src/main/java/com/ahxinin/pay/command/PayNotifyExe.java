package com.ahxinin.pay.command;

import com.ahxinin.pay.dto.clientobject.AlipayNotifyCO;
import com.ahxinin.pay.dto.clientobject.WeixinPayNotifyCO;
import com.ahxinin.pay.domain.PayNotify;
import com.ahxinin.pay.dto.clientobject.PayNotifyCO;
import com.ahxinin.pay.gatewayimpl.rpc.PayMapper;
import com.alibaba.fastjson2.JSONObject;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description: 支付通知
 * @date : 2024-03-22
 */
@Slf4j
@Component
public class PayNotifyExe {

    @Resource
    private PayMapper payMapper;

    /**
     * 支付宝支付通知
     */
    public String alipayWapNotify(AlipayNotifyCO alipayNotifyCO) {
        PayNotify payNotify = payMapper.aliNotify(alipayNotifyCO);
        log.info("alipayWapNotify out_trade_no:{}, payNotify:{}", alipayNotifyCO.getOut_trade_no(), payNotify);
        completeBusiness(payNotify);
        return payNotify.getReturnMessage();
    }

    /**
     * 微信支付通知
     */
    public String weixinNotify(JSONObject params, WeixinPayNotifyCO weixinPayNotifyCO) {
        PayNotify payNotify = payMapper.weixinNotify(params.toString(), weixinPayNotifyCO);
        log.info("weixinNotify id:{}, payNotify:{}", weixinPayNotifyCO.getId(), payNotify);
        completeBusiness(payNotify);
        return payNotify.getReturnMessage();
    }

    /**
     * 完成业务处理
     */
    private void completeBusiness(PayNotify payNotify){
        if (payNotify.unlockTranslate()){
            log.info("unlockTranslate userId:{}", payNotify.getUserId());
        }
    }

    /**
     * 构建支付通知
     */
    private PayNotify buildPayNotify(PayNotifyCO payNotifyCO){
        return PayNotify.builder()
                .userId(payNotifyCO.getUserId())
                .returnMessage(payNotifyCO.getReturnMessage())
                .needUnlockTranslate(payNotifyCO.needUnlockTranslate())
                .build();
    }
}
