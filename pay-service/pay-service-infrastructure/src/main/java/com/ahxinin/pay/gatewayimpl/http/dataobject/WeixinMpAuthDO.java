package com.ahxinin.pay.gatewayimpl.http.dataobject;

import lombok.Data;

/**
 * @description: 微信公众号授权返回值
 * @date : 2024-03-15
 */
@Data
public class WeixinMpAuthDO {

    private String access_token;

    private Long expires_in;

    private String refresh_token;

    private String openid;

    private String scope;

    private Boolean is_snapshotuser;

    private String unionid;
}
