package com.ahxinin.pay.clientobject;

import java.io.Serializable;
import lombok.Data;

/**
 * @description: 微信支付通知加密数据
 * @date : 2024-04-12
 */
@Data
public class WeixinPayNotifyEncryptCO implements Serializable {

/**
     * 加密算法类型
     */
    private String algorithm;

    /**
     * 数据密文
     */
    private String ciphertext;

    /**
     * 附加数据
     */
    private String associated_data;

    /**
     * 原始类型
     */
    private String original_type;

    /**
     * 随机串
     */
    private String nonce;
}
