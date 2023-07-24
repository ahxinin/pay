package com.ahxinin.pay.channel.alipay;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AlipayClientConfig {

    @Autowired
    private AlipayConfig config;

    @Bean
    public AlipayClient init(){
        return new DefaultAlipayClient(AlipayConstant.SERVER_URL, config.getAppId(), config.getPrivateKey(),
                AlipayConstant.FORMAT, AlipayConstant.CHARSET, AlipayConstant.ALIPAY_PUBLIC_KEY, AlipayConstant.SIGN_TYPE);
    }
}