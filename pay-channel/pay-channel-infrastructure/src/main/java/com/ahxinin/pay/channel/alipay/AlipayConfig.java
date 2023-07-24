package com.ahxinin.pay.channel.alipay;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "alipay")
public class AlipayConfig {

    private String appId;

    private String privateKey;

    private String notifyUrl;

    private String returnUrl;
}
