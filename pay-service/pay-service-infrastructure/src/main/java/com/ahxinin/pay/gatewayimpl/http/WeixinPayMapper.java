package com.ahxinin.pay.gatewayimpl.http;

import cn.hutool.http.HttpUtil;
import com.ahxinin.pay.config.PayConfig;
import com.ahxinin.pay.config.WeixinPayConfig;
import com.ahxinin.pay.domain.PayOrderResult;
import com.ahxinin.pay.dto.WeixinMpInitCmd;
import com.ahxinin.pay.dto.WeixinPayJsApiNotifyCmd;
import com.ahxinin.pay.dto.WeixinPayJsApiNotifyHeaderCmd;
import com.ahxinin.pay.dto.clientobject.PayCO;
import com.ahxinin.pay.dto.clientobject.WeixinPayCO;
import com.ahxinin.pay.gatewayimpl.database.PayNotifyMapper;
import com.ahxinin.pay.gatewayimpl.database.dataobject.PayNotifyDO;
import com.ahxinin.pay.gatewayimpl.http.dataobject.WeixinMpAuthDO;
import com.ahxinin.pay.gatewayimpl.http.dataobject.WeixinPayJsApiDO;
import com.alibaba.cola.exception.BizException;
import com.alibaba.fastjson2.JSONObject;
import com.wechat.pay.java.core.exception.ServiceException;
import com.wechat.pay.java.core.exception.ValidationException;
import com.wechat.pay.java.core.notification.NotificationParser;
import com.wechat.pay.java.core.notification.RequestParam;
import com.wechat.pay.java.service.payments.jsapi.JsapiServiceExtension;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayRequest;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayWithRequestPaymentResponse;
import com.wechat.pay.java.service.payments.jsapi.model.QueryOrderByOutTradeNoRequest;
import com.wechat.pay.java.service.payments.model.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @description: 微信支付
 * @date : 2024-03-05
 */
@Slf4j
@Component
@ConditionalOnProperty(name = "pay.enable", havingValue = "true")
public class WeixinPayMapper {

    @Autowired
    private JsapiServiceExtension service;
    @Autowired
    private NotificationParser notificationParser;
    @Autowired
    private WeixinPayConfig weixinPayConfig;
    @Autowired
    private PayConfig payConfig;
    @Autowired
    private PayNotifyMapper payNotifyMapper;

    /**
     * 微信jsApi下单
     * @param weixinPayJsApiDO 请求参数
     * @return 支付凭证
     */
    public PayCO jsApiPay(WeixinPayJsApiDO weixinPayJsApiDO){
        try {
            PrepayRequest prepayRequest = weixinPayJsApiDO.buildPrepay(weixinPayConfig.getMpAppId(), weixinPayConfig.getMerchantId());
            PrepayWithRequestPaymentResponse response = service.prepayWithRequestPayment(prepayRequest);
            log.info("weixin jsApiPay sendRequest, weixinPayJsApiDO:{}, response:{}", JSONObject.toJSONString(weixinPayJsApiDO), response.toString());
            WeixinPayCO weixinPayCO = buildResourcePayCO(response);
            return PayCO.buildWeixinPayParam(weixinPayCO);
        }catch (Exception e){
            log.error("weixin jsApiPay error, weixinPayJsApiDO:{}", JSONObject.toJSONString(weixinPayJsApiDO), e);
            throw new BizException(e.getMessage());
        }
    }

    /**
     * 微信jsApi通知
     * @param notifyCmd 请求参数
     * @return 通知结果
     */
    public PayOrderResult jsApiNotify(WeixinPayJsApiNotifyCmd notifyCmd){
        Transaction transaction;
        try {
            RequestParam requestParam = buildRequestParam(notifyCmd.getHeader(), notifyCmd.getRequestBody());
            transaction = notificationParser.parse(requestParam, Transaction.class);
            log.info("weixinPay jsApiNotify, id:{}, transaction:{}", notifyCmd.getId(), JSONObject.toJSONString(transaction));
        }catch (ValidationException e){
            log.error("weixinPay jsApiNotify validation error, weixinPayJsApiNotifyDO:{}", JSONObject.toJSONString(notifyCmd), e);
            throw new BizException(e.getMessage());
        }

        //保存支付通知
        PayNotifyDO payNotifyDO = PayNotifyDO.fromWeixinPay(transaction);
        payNotifyMapper.insert(payNotifyDO);

        return PayOrderResult.weixinPayState(payNotifyDO.getOutTradeNo(), payNotifyDO.getTradeStatus());
    }

    /**
     * 微信订单查询
     * @param outTradeNo 订单号
     * @return 订单状态
     */
    public PayOrderResult query(String outTradeNo){
        try {
            QueryOrderByOutTradeNoRequest request = new QueryOrderByOutTradeNoRequest();
            request.setMchid(weixinPayConfig.getMerchantId());
            request.setOutTradeNo(outTradeNo);
            Transaction transaction = service.queryOrderByOutTradeNo(request);
            log.info("weixinPay query, outTradeNo:{}, transaction:{}", outTradeNo, JSONObject.toJSONString(transaction));
            PayNotifyDO payNotifyDO = PayNotifyDO.fromWeixinPay(transaction);
            return PayOrderResult.weixinPayState(payNotifyDO.getOutTradeNo(), payNotifyDO.getTradeStatus());
        }catch (ServiceException e){
            if (StringUtils.endsWithIgnoreCase(e.getErrorCode(), "ORDER_NOT_EXIST")){
                return PayOrderResult.weixinPayState(outTradeNo, "NOTPAY");
            }
            log.error("weixinPay query error, outTradeNo:{}", outTradeNo, e);
            throw e;
        }
    }

    /**
     * 用户进入微信公众号授权页面
     */
    public String mpInit(WeixinMpInitCmd weixinMpInitCmd){
        return weixinPayConfig.buildInitUrl(weixinMpInitCmd, payConfig.getWapUrl());
    }

    /**
     * 微信公众号授权，获取openId
     */
    public String mpAuth(String code){
        String authUrl = weixinPayConfig.buildAuthUrl(code);
        String result = HttpUtil.get(authUrl);
        WeixinMpAuthDO weixinMpAuthDO = JSONObject.parseObject(result, WeixinMpAuthDO.class);
        return weixinMpAuthDO.getOpenid();
    }

    /**
     * 通知验证参数
     */
    private RequestParam buildRequestParam(WeixinPayJsApiNotifyHeaderCmd header, String requestBody){
        return new RequestParam.Builder()
                .serialNumber(header.getSerial())
                .nonce(header.getNonce())
                .signature(header.getSignature())
                .timestamp(header.getTimestamp())
                .body(requestBody)
                .build();
    }

    /**
     * 构建jsApi支付参数
     */
    private WeixinPayCO buildResourcePayCO(PrepayWithRequestPaymentResponse response){
        return WeixinPayCO.builder()
                .appId(response.getAppId())
                .timestamp(response.getTimeStamp())
                .nonceStr(response.getNonceStr())
                .packageVal(response.getPackageVal())
                .signType(response.getSignType())
                .paySign(response.getPaySign())
                .build();
    }
}
