package com.ahxinin.pay.service;

import com.ahxinin.pay.api.WeixinServiceI;
import com.ahxinin.pay.domain.PayOrderResult;
import com.ahxinin.pay.dto.WeixinMpAuthCmd;
import com.ahxinin.pay.dto.WeixinMpInitCmd;
import com.ahxinin.pay.dto.WeixinPayJsApiNotifyCmd;
import com.ahxinin.pay.dto.clientobject.PayNotifyCO;
import com.ahxinin.pay.dto.clientobject.WeixinMpAuthCO;
import com.ahxinin.pay.dto.clientobject.WeixinMpInitCO;
import com.ahxinin.pay.faced.PayFaced;
import com.ahxinin.pay.gatewayimpl.http.WeixinPayMapper;
import com.alibaba.cola.dto.SingleResponse;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * @description: 微信支付
 * @date : 2024-06-17
 */
@Slf4j
@DubboService
@ConditionalOnProperty(name = "pay.weixin.enable", havingValue = "true")
public class WeixinServiceImpl implements WeixinServiceI {

    @Resource
    private PayFaced payFaced;
    @Resource
    private WeixinPayMapper weixinPayMapper;

    @Override
    public SingleResponse<WeixinMpInitCO> weixinMpInit(WeixinMpInitCmd weixinMpInitCmd) {
        String url = weixinPayMapper.mpInit(weixinMpInitCmd);
        WeixinMpInitCO weixinMpInitCO = WeixinMpInitCO.init(url);
        return SingleResponse.of(weixinMpInitCO);
    }

    @Override
    public SingleResponse<WeixinMpAuthCO> weixinMpAuth(WeixinMpAuthCmd weixinMpAuthCmd) {
        String openId = weixinPayMapper.mpAuth(weixinMpAuthCmd.getCode());
        WeixinMpAuthCO weixinMpAuthCO = WeixinMpAuthCO.init(openId);
        return SingleResponse.of(weixinMpAuthCO);
    }

    @Override
    public SingleResponse<PayNotifyCO> weixinNotify(WeixinPayJsApiNotifyCmd notifyCmd) {
        try {
            log.info("weixin notify, notifyCmd:{}", notifyCmd.toString());
            PayOrderResult payOrderResult = weixinPayMapper.jsApiNotify(notifyCmd);
            PayNotifyCO payNotifyCO = payFaced.notify(payOrderResult);
            payNotifyCO.updateWeixinSuccess();
            return SingleResponse.of(payNotifyCO);
        }catch (Exception e){
            log.error("weixin notify error, notifyCmd:{}", notifyCmd.toString(), e);
            return SingleResponse.of(PayNotifyCO.failReturnMessage());
        }
    }
}
