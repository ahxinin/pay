package com.ahxinin.pay.config;

import com.ahxinin.pay.dto.WeixinMpInitCmd;
import com.wechat.pay.java.core.http.UrlEncoder;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description: 微信支付配置
 * @date : 2024-03-15
 */
@Data
@Component
@ConfigurationProperties(prefix ="weixinpay")
public class WeixinPayConfig {

    private String merchantId;

    private String privateKeyPath;

    private String merchantSerialNumber;

    private String apiV3Key;

    private String mpAppId;

    private String mpAppSecret;

    private String redirectUrl;

    private String notifyUrl;

    /**
     * 进入授权页面
     */
    public String buildInitUrl(WeixinMpInitCmd weixinMpInitCmd){
        redirectUrl = redirectUrl + weixinMpInitCmd.buildUrlParam();
        String url = UrlEncoder.urlEncode(redirectUrl);
        return "https://open.weixin.qq.com/connect/oauth2/authorize?"
                + "appid=" + this.mpAppId
                + "&redirect_uri=" + url
                + "&response_type=code"
                + "&scope=snsapi_base"
                + "#wechat_redirect";
    }

    /**
     * 获取openId
     */
    public String buildAuthUrl(String code){
        return "https://api.weixin.qq.com/sns/oauth2/access_token?"
                + "appid=" + this.mpAppId
                + "&secret=" + this.mpAppSecret
                + "&code=" + code
                + "&grant_type=authorization_code";
    }
}
