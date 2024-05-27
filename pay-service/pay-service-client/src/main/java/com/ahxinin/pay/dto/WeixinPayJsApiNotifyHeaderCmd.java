package com.ahxinin.pay.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * @description: 微信支付jsApi通知请求头
 * @date : 2024-03-05
 */
@Data
public class WeixinPayJsApiNotifyHeaderCmd implements Serializable {

    /**
     * 微信支付签名
     */
    private String signature;

    /**
     * 微信支付平台证书的序列号
     */
    private String serial;

    /**
     * 签名中的随机数
     */
    private String nonce;

    /**
     * 签名中的时间戳
     */
    private String timestamp;
}
