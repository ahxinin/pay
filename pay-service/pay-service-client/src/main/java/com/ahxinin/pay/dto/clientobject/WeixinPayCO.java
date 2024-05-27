package com.ahxinin.pay.dto.clientobject;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 微信支付请求
 * @date : 2024-03-20
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeixinPayCO implements Serializable {

    private String appId;

    private String timestamp;

    private String nonceStr;

    private String packageVal;

    private String signType;

    private String paySign;
}
