package com.ahxinin.pay.clientobject;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
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

    public static WeixinPayNotifyHeaderCO init(HttpServletRequest request){
        WeixinPayNotifyHeaderCO weixinPayNotifyHeaderCO = new WeixinPayNotifyHeaderCO();
        weixinPayNotifyHeaderCO.setSignature(request.getHeader("Wechatpay-Signature"));
        weixinPayNotifyHeaderCO.setSerial(request.getHeader("Wechatpay-Serial"));
        weixinPayNotifyHeaderCO.setNonce(request.getHeader("Wechatpay-Nonce"));
        weixinPayNotifyHeaderCO.setTimestamp(request.getHeader("Wechatpay-Timestamp"));
        weixinPayNotifyHeaderCO.setSignatureType(request.getHeader("Wechatpay-Signature-Type"));
        return weixinPayNotifyHeaderCO;
    }

}
