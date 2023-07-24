package com.ahxinin.pay.channel.alipay;

import com.ahxinin.pay.channel.PayExtPt;
import com.ahxinin.pay.domain.exception.PayException;
import com.ahxinin.pay.dto.PayCmd;
import com.alibaba.cola.extension.Extension;
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
@Extension(bizId = "pay", useCase = "alipay", scenario = "web")
public class AlipayWebExt implements PayExtPt {

    @Autowired
    private AlipayClient alipayClient;
    @Autowired
    private AlipayConfig config;

    /**
     * 电脑网页支付
     * <a href="https://opendocs.alipay.com/open/59da99d0_alipay.trade.page.pay?pathHash=8e24911d">开发文档</a>
     */
    @Override
    public String pay(PayCmd payCmd) throws PayException {
        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        JSONObject bizContent = buildBizContent(payCmd);

        request.setNotifyUrl(config.getNotifyUrl());
        request.setReturnUrl(config.getReturnUrl());
        request.setBizContent(bizContent.toString());

        AlipayTradePagePayResponse response;
        try {
            response = alipayClient.pageExecute(request);
            if (response.isSuccess()){
                return response.getBody();
            }
        }catch (AlipayApiException e){
            throw new PayException(e.getMessage());
        }

        throw new PayException(response.getMsg()+"_"+response.getSubMsg());
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
