package com.ahxinin.pay.domain;

import lombok.Data;

/**
 * @description: 预支付交易
 * @date : 2024-03-19
 */
@Data
public class PrePayResult {

    private String payType;

    private String payParam;

    public PrePayResult(String payType, String payParam) {
        this.payType = payType;
        this.payParam = payParam;
    }

    public static PrePayResult buildPreTrade(String payType, String payParam){
        return new PrePayResult(payType, payParam);
    }
}
