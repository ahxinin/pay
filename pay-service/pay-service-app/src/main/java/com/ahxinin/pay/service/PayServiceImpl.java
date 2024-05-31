package com.ahxinin.pay.service;

import com.ahxinin.pay.api.PayServiceI;
import com.ahxinin.pay.assembler.PayOrderAssembler;
import com.ahxinin.pay.command.PayExe;
import com.ahxinin.pay.config.PayConfig;
import com.ahxinin.pay.domain.PayOrder;
import com.ahxinin.pay.domain.PayOrderResult;
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
import com.ahxinin.pay.enums.PayOrderStateEnum;
import com.ahxinin.pay.faced.PayFaced;
import com.ahxinin.pay.gateway.CommonGateway;
import com.ahxinin.pay.gateway.PayOrderGateway;
import com.ahxinin.pay.gatewayimpl.http.AlipayMapper;
import com.ahxinin.pay.gatewayimpl.http.WeixinPayMapper;
import com.alibaba.cola.dto.SingleResponse;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @description: 支付服务
 * @date : 2024-03-14
 */@Slf4j
@DubboService
public class PayServiceImpl implements PayServiceI {

    @Resource
    private CommonGateway commonGateway;
    @Resource
    private PayExe payExe;
    @Resource
    private WeixinPayMapper weixinPayMapper;
    @Resource
    private AlipayMapper alipayMapper;
    @Resource
    private PayOrderGateway payOrderGateway;
    @Resource
    private PayFaced payFaced;
    @Resource
    private PayConfig payConfig;

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
    public SingleResponse<PayCO> pay(PayCmd payCmd) {
        //优先从缓存获取
        PayCO existPayCO = payOrderGateway.getPayParamCache(payCmd);
        if (existPayCO != null){
            return SingleResponse.of(existPayCO);
        }

        // 保存支付订单
        PayOrder payOrder = PayOrderAssembler.INSTANCE.toPayOrder(payCmd);
        payOrder.initState();
        payOrder.addId(commonGateway.getSnowflakeId());
        payOrderGateway.createPayOrder(payOrder);

        // 支付流程
        PayCO payCO = payExe.execute(payOrder);
        // 设置缓存
        payOrderGateway.addPayCache(payCmd, payOrder, payCO);
        return SingleResponse.of(payCO);
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

    @Override
    public SingleResponse<PayNotifyCO> alipayNotify(AlipayWapNotifyCO alipayWapNotifyCO) {
        try {
            PayOrderResult payOrderResult = alipayMapper.wapNotify(alipayWapNotifyCO);
            log.info("alipay notify success, payOrderResult:{}", payOrderResult.toString());
            PayNotifyCO payNotifyCO = payFaced.notify(payOrderResult);
            payNotifyCO.updateAliSuccess();
            return SingleResponse.of(payNotifyCO);
        }catch (Exception e){
            log.error("alipay notify error, alipayWapNotifyCO:{}", alipayWapNotifyCO.toString(), e);
            return SingleResponse.of(PayNotifyCO.failReturnMessage());
        }
    }

    @Override
    public SingleResponse<PayOrderCO> getPayOrder(Long payOrderId) {
        PayOrder payOrder = payOrderGateway.getById(payOrderId);
        PayOrderCO payOrderCO = PayOrderAssembler.INSTANCE.toClientObject(payOrder);
        return SingleResponse.of(payOrderCO);
    }

    @Override
    public SingleResponse<PayResultCO> getPayResult(GetPayResultCmd getPayResultCmd) {
        Long payOrderId = getPayResultCmd.getPayOrderId();
        String state = payOrderGateway.getStateCache(payOrderId);
        if (PayOrderStateEnum.isFinished(state)){
            return buildPayResult(state);
        }

        PayOrderResult payOrderResult = payExe.queryPayResult(getPayResultCmd);
        // 处理状态更新
        payFaced.notify(payOrderResult);
        return buildPayResult(payOrderResult.getState());
    }

    @Override
    public SingleResponse<String> getPayUrl(GetPayUrlCO getPayUrlCO) {
        String payUrl = payConfig.getWapUrl()
                + "?tradeType=" + getPayUrlCO.getTradeType()
                + "&tradeId=" + getPayUrlCO.getTradeId();
        return SingleResponse.of(payUrl);
    }

    /**
     * 构建支付结果
     */
    private SingleResponse<PayResultCO> buildPayResult(String state){
        PayResultCO payResultCO = PayResultCO.init(state);
        return SingleResponse.of(payResultCO);
    }
}
