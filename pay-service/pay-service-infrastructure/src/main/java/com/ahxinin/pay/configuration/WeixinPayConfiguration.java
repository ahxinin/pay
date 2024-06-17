package com.ahxinin.pay.configuration;

import com.ahxinin.pay.config.WeixinPayConfig;
import com.wechat.pay.java.core.Config;
import com.wechat.pay.java.core.RSAAutoCertificateConfig;
import com.wechat.pay.java.core.notification.NotificationConfig;
import com.wechat.pay.java.core.notification.NotificationParser;
import com.wechat.pay.java.service.payments.jsapi.JsapiServiceExtension;
import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 微信支付配置
 * @date : 2024-03-05
 */
@Configuration
@ConditionalOnProperty(name = "pay.weixin.enable", havingValue = "true")
public class WeixinPayConfiguration {

    @Resource
    private WeixinPayConfig weixinPayConfig;

    /**
     * 初始化发起请求服务
     */
    @Bean
    public JsapiServiceExtension initJsApi(){
        //初始化商户配置
        Config config = new RSAAutoCertificateConfig.Builder()
                .merchantId(weixinPayConfig.getMerchantId())
                .privateKeyFromPath(weixinPayConfig.getPrivateKeyPath())
                .merchantSerialNumber(weixinPayConfig.getMerchantSerialNumber())
                .apiV3Key(weixinPayConfig.getApiV3Key())
                .build();
        //初始化服务
        return new JsapiServiceExtension.Builder()
                .config(config)
                .build();
    }

    /**
     * 初始化接收通知服务
     */
    @Bean
    public NotificationParser initNotify(){
        //初始化配置
        NotificationConfig config = new RSAAutoCertificateConfig.Builder()
                .merchantId(weixinPayConfig.getMerchantId())
                .privateKeyFromPath(weixinPayConfig.getPrivateKeyPath())
                .merchantSerialNumber(weixinPayConfig.getMerchantSerialNumber())
                .apiV3Key(weixinPayConfig.getApiV3Key())
                .build();
        //初始化 NotificationParser
        return new NotificationParser(config);
    }
}
