package com.ahxinin.pay.alipay;

import com.ahxinin.pay.dto.PayCmd;
import com.alibaba.fastjson2.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AlipayService {

    @Autowired
    private AlipayClient alipayClient;
    @Autowired
    private AlipayConfig config;

    /**
     * 电脑网页支付
     * <a href="https://opendocs.alipay.com/open/59da99d0_alipay.trade.page.pay?pathHash=8e24911d">开发文档</a>
     */
    public void pagePay(PayCmd payCmd) throws AlipayApiException {
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        JSONObject bizContent = buildBizContent(payCmd);

        request.setNotifyUrl(config.getNotifyUrl());
        request.setReturnUrl(config.getReturnUrl());
        request.setBizContent(bizContent.toString());
        AlipayTradePagePayResponse response = alipayClient.pageExecute(request);
        if (response.isSuccess()){
            log.info(JSONObject.toJSONString(response));
        }
    }

    private JSONObject buildBizContent(PayCmd payCmd){
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", payCmd.getTradeNo());
        bizContent.put("total_amount", payCmd.convertRealAmount());
        bizContent.put("subject", payCmd.getSubject());
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");
        return bizContent;
    }
}
