package com.ahxinin.pay.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description: 支付宝支付配置
 * @date : 2024-03-15
 */
@Data
@Component
@ConfigurationProperties(prefix ="alipay")
public class AlipayConfig {

    private String appId;

    private String merchantPrivateKey;

    private String alipayPublicKey;

    private String notifyUrl;
}
