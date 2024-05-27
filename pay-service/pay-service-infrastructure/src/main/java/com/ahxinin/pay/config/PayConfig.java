package com.ahxinin.pay.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description: 支付配置
 * @date : 2024-03-26
 */
@Data
@Component
@ConfigurationProperties(prefix ="pay")
public class PayConfig {

    private String wapUrl;
}
