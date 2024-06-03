package com.ahxinin.pay.dto.clientobject;

import java.io.Serializable;
import lombok.Data;

/**
 * @description: 微信支付通知头部数据
 * @date : 2024-03-22
 */
@Data
public class WeixinPayNotifyHeaderCO implements Serializable {

    private String signature;

    private String serial;

    private String nonce;

    private String timestamp;

    private String signatureType;

}
