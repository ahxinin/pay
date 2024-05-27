package com.ahxinin.pay.gatewayimpl.rpc;

import com.ahxinin.pay.api.PayServiceI;
import com.ahxinin.pay.clientobject.AlipayNotifyCO;
import com.ahxinin.pay.clientobject.WeixinPayNotifyCO;
import com.ahxinin.pay.convert.WeixinPayJsApiNotifyHeaderConvert;
import com.ahxinin.pay.domain.PayNotify;
import com.ahxinin.pay.dto.GetPayResultCmd;
import com.ahxinin.pay.dto.PayCmd;
import com.ahxinin.pay.dto.WeixinMpAuthCmd;
import com.ahxinin.pay.dto.WeixinMpInitCmd;
import com.ahxinin.pay.dto.WeixinPayJsApiNotifyCmd;
import com.ahxinin.pay.dto.WeixinPayJsApiNotifyHeaderCmd;
import com.ahxinin.pay.dto.clientobject.AlipayWapNotifyCO;
import com.ahxinin.pay.dto.clientobject.GetPayUrlCO;
import com.ahxinin.pay.dto.clientobject.PayCO;
import com.ahxinin.pay.dto.clientobject.PayNotifyCO;
import com.ahxinin.pay.dto.clientobject.PayOrderCO;
import com.ahxinin.pay.dto.clientobject.PayResultCO;
import com.ahxinin.pay.dto.clientobject.WeixinMpAuthCO;
import com.ahxinin.pay.dto.clientobject.WeixinMpInitCO;
import com.ahxinin.pay.faced.PayFaced;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.BizException;
import com.alibaba.fastjson2.JSONObject;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;

/**
 * @description: 支付接口
 * @date : 2024-03-19
 */
@Slf4j
@Component
public class PayMapper {

    @DubboReference
    private PayServiceI payService;
    @Resource
    private PayFaced payFaced;

    /**
     * 获取微信公众号授权Url
     */
    public String getWeixinMpAuthPageUrl(String tradeType, Long tradeId){
        WeixinMpInitCmd weixinMpInitCmd = new WeixinMpInitCmd();
        weixinMpInitCmd.setTradeType(tradeType);
        weixinMpInitCmd.setTradeId(tradeId);
        SingleResponse<WeixinMpInitCO> response = payService.weixinMpInit(weixinMpInitCmd);
        if (!response.isSuccess()){
            throw new BizException(response.getErrMessage());
        }

        WeixinMpInitCO weixinMpInitCO = response.getData();
        return weixinMpInitCO.getUrl();
    }

    /**
     * 获取微信公众号openId
     */
    public String getWeixinMpOpenId(String code) {
        WeixinMpAuthCmd weixinMpAuthCmd = new WeixinMpAuthCmd();
        weixinMpAuthCmd.setCode(code);
        SingleResponse<WeixinMpAuthCO> response = payService.weixinMpAuth(weixinMpAuthCmd);
        if (!response.isSuccess()){
            throw new BizException(response.getErrMessage());
        }

        WeixinMpAuthCO weixinMpAuthCO = response.getData();
        return weixinMpAuthCO.getOpenId();
    }

    /**
     * 购买资源预支付交易
     */
    public PayCO pay(PayCmd payCmd) {
        SingleResponse<PayCO> response = payService.pay(payCmd);
        if (!response.isSuccess()){
            throw new BizException(response.getErrMessage());
        }
        return response.getData();
    }

    /**
     * 微信支付通知
     */
    public PayNotify weixinNotify(String requestBody, WeixinPayNotifyCO weixinPayNotifyCO) {
        WeixinPayJsApiNotifyHeaderCmd header = WeixinPayJsApiNotifyHeaderConvert.INSTANCE.convert(weixinPayNotifyCO.getHeader());

        WeixinPayJsApiNotifyCmd notifyCmd = new WeixinPayJsApiNotifyCmd();
        notifyCmd.setId(weixinPayNotifyCO.getId());
        notifyCmd.setCreate_time(weixinPayNotifyCO.getCreate_time());
        notifyCmd.setEvent_type(weixinPayNotifyCO.getEvent_type());
        notifyCmd.setResource_type(weixinPayNotifyCO.getResource_type());
        notifyCmd.setSummary(weixinPayNotifyCO.getSummary());
        notifyCmd.setRequestBody(requestBody);
        notifyCmd.setHeader(header);

        SingleResponse<PayNotifyCO> response = payService.weixinNotify(notifyCmd);
        if (!response.isSuccess()){
            log.error("weixinNotify error, response:{}", JSONObject.toJSONString(response));
            throw new BizException(response.getErrMessage());
        }
        return buildPayNotify(response.getData());
    }

    /**
     * 支付宝支付通知
     */
    public PayNotify aliNotify(AlipayNotifyCO alipayNotifyCO) {
        AlipayWapNotifyCO alipayWapNotifyCO = new AlipayWapNotifyCO();
        alipayWapNotifyCO.setTrade_no(alipayNotifyCO.getTrade_no());
        alipayWapNotifyCO.setOut_trade_no(alipayNotifyCO.getOut_trade_no());
        alipayWapNotifyCO.setTrade_status(alipayNotifyCO.getTrade_status());
        alipayWapNotifyCO.setNotifyParams(alipayNotifyCO.getNotifyParams());
        SingleResponse<PayNotifyCO> response = payService.alipayNotify(alipayWapNotifyCO);
        return buildPayNotify(response.getData());
    }

    /**
     * 获取支付结果
     */
    public String getPayResult(Long payOrderId){
        SingleResponse<PayOrderCO> payOrderCOResponse = payService.getPayOrder(payOrderId);
        PayOrderCO payOrderCO = payOrderCOResponse.getData();

        GetPayResultCmd getPayResultCmd = new GetPayResultCmd();
        getPayResultCmd.setPayOrderId(payOrderId);
        getPayResultCmd.setPayType(payOrderCO.getPayType());
        SingleResponse<PayResultCO> response = payService.getPayResult(getPayResultCmd);
        if (!response.isSuccess()){
            throw new BizException(response.getErrMessage());
        }
        PayResultCO payResultCO = response.getData();
        return payResultCO.getState();
    }

    /**
     * 获取资源支付链接
     */
    public String getResourcePayUrl(Long tradeId) {
        GetPayUrlCO getPayUrlCO = GetPayUrlCO.ofResource(String.valueOf(tradeId));
        return payFaced.getPayQRCode(getPayUrlCO);
    }

    /**
     * 获取套餐支付链接
     */
    public String getPlanPayUrl(Long tradeId) {
        GetPayUrlCO getPayUrlCO = GetPayUrlCO.ofPlan(String.valueOf(tradeId));
        return payFaced.getPayQRCode(getPayUrlCO);
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
