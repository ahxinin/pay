package com.ahxinin.pay.configuration;

import com.ahxinin.pay.config.AlipayConfig;
import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.Config;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 支付宝配置
 * @date : 2024-03-04
 */
@Slf4j
@Configuration
public class AlipayConfiguration {

    @Resource
    private AlipayConfig alipayConfig;

    @Bean
    public boolean init(){
        log.info("alipay configuration init");
        Factory.setOptions(getOptions());
        return Boolean.TRUE;
    }

    private Config getOptions() {
        Config config = new Config();
        config.protocol = "https";
        config.gatewayHost = "openapi.alipay.com";
        config.signType = "RSA2";

        config.appId = alipayConfig.getAppId();
        config.merchantPrivateKey = alipayConfig.getMerchantPrivateKey();
        config.alipayPublicKey = alipayConfig.getAlipayPublicKey();

        return config;
    }
}
