package com.ahxinin.pay.dto;

import java.io.Serializable;
import lombok.Data;

/**
 * @description: 微信支付jsApi支付通知资源
 * @date : 2024-03-05
 */
@Data
public class WeixinPayJsApiNotifyResourceCmd implements Serializable {

    /**
     * 加密算法
     */
    private String algorithm;

    /**
     * Base64编码后的开启/停用结果数据密文
     */
    private String ciphertext;

    /**
     * 附加数据
     */
    private String associated_data;

    /**
     * 原始回调类型
     */
    private String original_type;

    /**
     * 加密使用的随机串
     */
    private String nonce;
}
